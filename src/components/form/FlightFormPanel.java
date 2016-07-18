package components.form;

import components.utility.FormUtility;
import data.FlightSegment;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Angel on 7/16/2016.
 */
public class FlightFormPanel extends FormPanel<FlightSegment> {
    //date, time, airline, number, departure city, arrival city, arrival time, price (optional), confirmation # (optional)
    //e.g.: 4/19/05, 2:30, US Airways, 425, Philadelphia, Boston, 4:15, $243.00, GHZ841
    private static FlightFormPanel flightFormPanel = null;

    private JTextField date;
    private JTextField time;
    private JComboBox airline;
    private JTextField number;
    private JTextField departureCity;
    private JTextField arrivalCity;
    private JTextField arrivalTime;
    private JTextField price;
    private JTextField confirmationNumber;

    private FlightFormPanel() {
        super();
        setLayout(new GridBagLayout());
        initAndAddFields();
    }

    public void initAndAddFields() {
        date = new JTextField();
        time = new JTextField();
        airline = new JComboBox();
        number = new JTextField();
        departureCity = new JTextField();
        arrivalCity = new JTextField();
        arrivalTime = new JTextField();
        price = new JTextField();
        confirmationNumber = new JTextField();

        FormUtility.addLabel("Airline:", this);
        FormUtility.addWidthScaledField(airline, this, 5);
        FormUtility.addLabel("Flight Number:", this);
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
        if (tripSegment == null) {
            tripSegment = new FlightSegment();
        }
        return tripSegment;
    }
}
