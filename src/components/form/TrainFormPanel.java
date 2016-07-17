package components.form;

import data.TrainSegment;

import javax.swing.*;

/**
 * Created by Angel on 7/16/2016.
 */
public class TrainFormPanel extends FormPanel<TrainSegment> {
    //date, time, company, number, departure city, arrival city, arrival time, price (optional), confirmation # (optional)
    //e.g.: 6/1/05, 7:45, Amtrak, 128, Philadelphia, Washington, 9:20
    private static TrainFormPanel trainFormPanel = null;

    private JTextField date;
    private JTextField time;
    private JTextField company;
    private JTextField number;
    private JTextField departureCity;
    private JTextField arrivalCity;
    private JTextField arrivalTime;
    private JTextField price;
    private JTextField confirmation;

    private TrainFormPanel() {
        initAndAddFields();
    }

    public void initAndAddFields() {
        date = new JTextField();
        time = new JTextField();
        company = new JTextField();
        number = new JTextField();
        departureCity = new JTextField();
        arrivalCity = new JTextField();
        arrivalTime = new JTextField();
        price = new JTextField();
        confirmation = new JTextField();
        add(date);
        add(time);
        add(company);
        add(number);
        add(departureCity);
        add(arrivalCity);
        add(arrivalTime);
        add(price);
        add(confirmation);
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
