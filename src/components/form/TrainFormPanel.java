package components.form;

import components.utility.FormUtility;
import data.TrainSegment;

import javax.swing.*;
import java.awt.*;

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
    private JTextField confirmationNumber;

    private TrainFormPanel() {
        super();
        setLayout(new GridBagLayout());
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
        confirmationNumber = new JTextField();


        FormUtility.addLabel("Company:", this);
        FormUtility.addWidthScaledField(company, this, 5);
        FormUtility.addLabel("Train Number:", this);
        FormUtility.addWidthScaledField(number, this, 1);
        FormUtility.addBlankLastField(this);

        FormUtility.addLabel("Confirmation #:", this);
        FormUtility.addWidthScaledField(confirmationNumber, this, 5);
        FormUtility.addLabel("Price:", this);
        FormUtility.addSetWidthField(price, this, 50);
        FormUtility.addBlankLastField(this);

        FormUtility.addBlankLastField(this);

        FormUtility.addLabel("Departure City:", this);
        FormUtility.addWidthScaledField(departureCity, this, 5);
        FormUtility.addLabel("Departure Date:", this);
        FormUtility.addSetWidthField(date, this, 50);
        FormUtility.addBlankLastField(this);

        FormUtility.addWidthScaledField(new JPanel(), this, 6);
        FormUtility.addLabel("Departure Time:", this);
        FormUtility.addSetWidthField(time, this, 50);
        FormUtility.addBlankLastField(this);

        FormUtility.addBlankLastField(this);

        FormUtility.addLabel("Arrival City:", this);
        FormUtility.addWidthScaledField(arrivalCity, this, 5);
        FormUtility.addLabel("Arrival Time:", this);
        FormUtility.addSetWidthField(arrivalTime, this, 50);
        FormUtility.addBlankLastField(this);
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
        if (tripSegment == null) {
            tripSegment = new TrainSegment();
        }
        return tripSegment;
    }
}
