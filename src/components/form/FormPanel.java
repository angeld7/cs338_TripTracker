package components.form;

import components.interfaces.FormPresenter;
import components.interfaces.InputValidationPassFailHandler;
import components.utility.FormUtility;
import data.TripSegment;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.concurrent.locks.Lock;

/**
 * Created by Angel on 7/16/2016.
 */
public abstract class FormPanel<T extends TripSegment> extends JPanel {
    private Set<JComponent> erroredComponents = new HashSet<>();
    protected List<JComponent> fields = new LinkedList<>();
    protected T tripSegment;
    protected FormPresenter presenter;
    protected KeyListener keyListener;
    protected InputValidationPassFailHandler defaultInputValidationFailHandler;

    public abstract void clearFields();

    public FormPanel(final FormPresenter presenter) {
        this.presenter = presenter;
        keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                presenter.dataUpdated(flushChanges());
            }
        };
        defaultInputValidationFailHandler = new InputValidationPassFailHandler() {
            @Override
            public void fail(String message, JComponent component) {
                synchronized (erroredComponents) {
                    erroredComponents.add(component);
                }
                presenter.inputValidationFailed(message);
            }

            @Override
            public void pass(JComponent component) {
                synchronized (erroredComponents) {
                    erroredComponents.remove(component);
                }
                presenter.clearValidationErrors();
            }
        };
    }

    public void clear() {
        tripSegment = null;
        erroredComponents.clear();
        for (JComponent field : fields) {
            FormUtility.validationSuccess(field);
        }
        clearFields();
    }

    public void setTripSegment(T segment) {
        tripSegment = segment;
        populateTripSegmentData();
    }

    protected abstract void populateTripSegmentData();

    public abstract T flushChanges();

    public T getTripSegment() {
        return tripSegment;
    }

    public boolean hasErrors() {
        boolean error = false;
        synchronized (erroredComponents) {
            error = erroredComponents.size() > 0;
        }
        Iterator<JComponent> iterator = fields.iterator();
        while (!error && iterator.hasNext()) {
            JComponent component = iterator.next();
            InputVerifier verifier = component.getInputVerifier();
            if (verifier != null) {
                component.getInputVerifier().verify(component);
            }
        }
        synchronized (erroredComponents) {
            if (erroredComponents.size() > 0) {
                JComponent component = erroredComponents.iterator().next();
                InputVerifier verifier = component.getInputVerifier();
                verifier.verify(component);
                component.requestFocus();
                error = true;
            }
        }

        return error;
    }
}
