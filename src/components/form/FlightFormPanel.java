package components.form;

import data.FlightSegment;
import data.TrainSegment;

import javax.swing.*;


/**
 * Created by Angel on 7/16/2016.
 */
public class FlightFormPanel extends FormPanel<FlightSegment> {
    //date, time, airline, number, departure city, arrival city, arrival time, price (optional), confirmation # (optional)
    //e.g.: 4/19/05, 2:30, US Airways, 425, Philadelphia, Boston, 4:15, $243.00, GHZ841
    private static FlightFormPanel flightFormPanel = null;

    private JTextField date;
    private JTextField time;
    private JTextField airline;
    private JTextField number;
    private JTextField departureCity;
    private JTextField arrivalCity;
    private JTextField arrivalTime;
    private JTextField price;
    private JTextField confirmationNumber;

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
