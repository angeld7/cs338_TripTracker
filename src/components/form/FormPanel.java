package components.form;

import data.TripSegment;

import javax.swing.*;

/**
 * Created by Angel on 7/16/2016.
 */
public abstract class FormPanel<T extends TripSegment> extends JPanel {

    T tripSegment;
    boolean unsavedChanges = false;

    public abstract void clearFields();

    public void clear() {
        tripSegment = null;
        clearFields();
    }

    public void setTripSegment(T segment){
        tripSegment = segment;
        populateTripSegmentData();
        unsavedChanges = false;
    }

    protected abstract void populateTripSegmentData();

    public abstract T flushChanges();

    public T getTripSegment() {
        return tripSegment;
    }

    public boolean hasUnsavedChanges() {
        return unsavedChanges;
    }
}
