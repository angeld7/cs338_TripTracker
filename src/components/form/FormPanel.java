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

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This is the base form that all forms extend from
 */
public abstract class FormPanel<T extends TripSegment> extends JPanel {
    /**
     * Specifies the fields that ar currently failing validation
     */
    private Set<JComponent> erroredComponents = new HashSet<>();
    /**
     * All fields on the form
     */
    protected List<JComponent> fields = new LinkedList<>();
    protected T tripSegment;
    protected FormPresenter presenter;
    /**
     * Used to detect when a form is edited
     */
    protected KeyListener keyListener;
    /**
     * Handler for validation checks
     */
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

    /**
     * Clears the tripSegment and resets all of the components
     */
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

    /**
     * Populates all of the fields with the data in the trip segment
     */
    protected abstract void populateTripSegmentData();

    /**
     * Saves all of the data in the fields to the trip segment object
     * @return
     */
    public abstract T flushChanges();

    public T getTripSegment() {
        return tripSegment;
    }

    /**
     * Validates all fields
     * @return
     */
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
