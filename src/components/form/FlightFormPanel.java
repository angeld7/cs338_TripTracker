package components.form;

import data.FlightSegment;
import data.TrainSegment;

import javax.swing.*;


/**
 * Created by Angel on 7/16/2016.
 */
public class FlightFormPanel extends FormPanel<FlightSegment> {
    private static FlightFormPanel flightFormPanel = null;

    private FlightFormPanel() {
        add(new JLabel("I am a flight!!"));
    }

    public static FlightFormPanel get() {
        if (flightFormPanel == null) {
            flightFormPanel = new FlightFormPanel();
        }
        return flightFormPanel;
    }

    @Override
    public void clearFields() {

    }

    @Override
    public void setTripSegment(FlightSegment segment) {

    }

    @Override
    protected void populateTripSegmentData() {

    }

    @Override
    public FlightSegment flushChanges() {
        if(tripSegment == null) {
            tripSegment = new FlightSegment();
        }
        return tripSegment;
    }
}
