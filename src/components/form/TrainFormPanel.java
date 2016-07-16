package components.form;

import data.TrainSegment;

import javax.swing.*;

/**
 * Created by Angel on 7/16/2016.
 */
public class TrainFormPanel extends FormPanel<TrainSegment> {
    private static TrainFormPanel trainFormPanel = null;

    private TrainFormPanel() {
        add(new JLabel("I am a train!!"));
    }

    public static TrainFormPanel get() {
        if (trainFormPanel == null) {
            trainFormPanel = new TrainFormPanel();
        }
        return trainFormPanel;
    }

    @Override
    public void clearFields() {

    }

    @Override
    public void setTripSegment(TrainSegment segment) {

    }

    @Override
    protected void populateTripSegmentData() {

    }

    @Override
    public TrainSegment flushChanges() {
        if(tripSegment == null) {
            tripSegment = new TrainSegment();
        }
        return tripSegment;
    }
}
