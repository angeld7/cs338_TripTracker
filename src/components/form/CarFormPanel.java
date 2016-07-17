package components.form;

import data.CarSegment;
import data.TrainSegment;

import javax.swing.*;


/**
 * Created by Angel on 7/16/2016.
 */
public class CarFormPanel extends FormPanel<CarSegment> {
    //date, pickup-time, company, city, location, price(optional), confirmation #(optional)
    //e.g.: 7/12/05, 10:20, Hertz, Philadelphia, airport, $23.95
    private static CarFormPanel carFormPanel = null;

    private JTextField date;
    private JTextField pickupTime;
    private JTextField company;
    private JTextField city;
    private JTextField location;
    private JTextField price;
    private JTextField confirmation;

    private CarFormPanel() {
        add(new JLabel("I am a car!!"));
    }

    public static CarFormPanel get() {
        if (carFormPanel == null) {
            carFormPanel = new CarFormPanel();
        }
        return carFormPanel;
    }

    @Override
    public void clearFields() {

    }

    @Override
    protected void populateTripSegmentData() {

    }

    @Override
    public CarSegment flushChanges() {
        if(tripSegment == null) {
            tripSegment = new CarSegment();
        }
        return tripSegment;
    }
}
