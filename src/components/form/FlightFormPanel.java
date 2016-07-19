package components.form;

import components.interfaces.FormPresenter;
import components.utility.FormUtility;
import data.FlightSegment;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Angel on 7/16/2016.
 */
public class FlightFormPanel extends FormPanel<FlightSegment> {
    public static final String FLIGHT_NUMBER = "Flight Number";
    public static final String ARRIVAL_TIME = "Arrival Time";
    public static final String DEPARTURE_TIME = "Departure Time";
    public static final String DEPARTURE_DATE = "Departure Date";
    public static final String AIRLINE = "Airline";
    public static final String CONFIRMATION_NUMBER = "Confirmation #";
    public static final String PRICE = "Price";
    public static final String DEPARTURE_CITY = "Departure City";
    public static final String ARRIVAL_CITY = "Arrival City";

    private JFormattedTextField date;
    private JFormattedTextField time;
    private JComboBox airline;
    private JTextField flightNumber;
    private JTextField departureCity;
    private JTextField arrivalCity;
    private JFormattedTextField arrivalTime;
    private JFormattedTextField price;
    private JTextField confirmationNumber;

    public FlightFormPanel(FormPresenter formPresenter) {
        super(formPresenter);
        setLayout(new GridBagLayout());
        initFields();
        addFields();
    }

    public void initFields() {
        date = FormUtility.getRequiredDateField(defaultInputValidationFailHandler, DEPARTURE_DATE);
        time = FormUtility.getRequiredTimeField(defaultInputValidationFailHandler, DEPARTURE_TIME);
        String[] airlines = {"US Airways", "American Airlines", "Frontier Airlines", "Virgin Airlines", "Jet Blue"};
        airline = new JComboBox(airlines);
        flightNumber = new JTextField();
        departureCity = new JTextField();
        arrivalCity = new JTextField();
        arrivalTime = FormUtility.getRequiredTimeField(defaultInputValidationFailHandler, ARRIVAL_TIME);
        price = FormUtility.getCurrencyField(defaultInputValidationFailHandler);
        confirmationNumber = new JTextField();

        date.addKeyListener(keyListener);
        time.addKeyListener(keyListener);
        airline.addKeyListener(keyListener);
        flightNumber.addKeyListener(keyListener);
        departureCity.addKeyListener(keyListener);
        arrivalCity.addKeyListener(keyListener);
        arrivalTime.addKeyListener(keyListener);
        price.addKeyListener(keyListener);
        confirmationNumber.addKeyListener(keyListener);

        //FormUtility.addRequiredValidator(airline, defaultInputValidationFailHandler, "Airline");
        FormUtility.addRequiredValidator(flightNumber, defaultInputValidationFailHandler, FLIGHT_NUMBER);
        FormUtility.addRequiredValidator(departureCity, defaultInputValidationFailHandler, DEPARTURE_CITY);
        FormUtility.addRequiredValidator(arrivalCity, defaultInputValidationFailHandler, ARRIVAL_CITY);

        fields.add(airline);
        fields.add(flightNumber);
        fields.add(confirmationNumber);
        fields.add(price);
        fields.add(departureCity);
        fields.add(date);
        fields.add(time);
        fields.add(arrivalCity);
        fields.add(arrivalTime);
    }

    private void addFields() {
        FormUtility.addRequiredLabel(AIRLINE, this);
        FormUtility.addWidthScaledField(airline, this, 5);
        FormUtility.addRequiredLabel(FLIGHT_NUMBER, this);
        FormUtility.addWidthScaledField(flightNumber, this, 1);
        FormUtility.addBlankLastField(this);

        FormUtility.addLabel(CONFIRMATION_NUMBER, this);
        FormUtility.addWidthScaledField(confirmationNumber, this, 5);
        FormUtility.addLabel(PRICE, this);
        FormUtility.addSetWidthField(price, this, 50);
        FormUtility.addBlankLastField(this);


        FormUtility.addBlankLastField(this);

        FormUtility.addRequiredLabel(DEPARTURE_CITY, this);
        FormUtility.addWidthScaledField(departureCity, this, 5);
        FormUtility.addRequiredLabel(DEPARTURE_DATE, this);
        FormUtility.addSetWidthField(date, this, 50);
        FormUtility.addBlankLastField(this);

        FormUtility.addWidthScaledField(new JPanel(), this, 6);
        FormUtility.addRequiredLabel(DEPARTURE_TIME, this);
        FormUtility.addSetWidthField(time, this, 50);
        FormUtility.addBlankLastField(this);

        FormUtility.addBlankLastField(this);

        FormUtility.addRequiredLabel(ARRIVAL_CITY, this);
        FormUtility.addWidthScaledField(arrivalCity, this, 5);
        FormUtility.addRequiredLabel(ARRIVAL_TIME, this);
        FormUtility.addSetWidthField(arrivalTime, this, 50);
        FormUtility.addBlankLastField(this);
    }

    @Override
    public void clearFields() {
        date.setText("");
        time.setText("");
        airline.setSelectedIndex(0);
        flightNumber.setText("");
        departureCity.setText("");
        arrivalCity.setText("");
        arrivalTime.setText("");
        price.setText("");
        confirmationNumber.setText("");
    }

    @Override
    protected void populateTripSegmentData() {
        date.setValue(tripSegment.getDate());
        time.setValue(tripSegment.getDate());
        airline.setSelectedItem(tripSegment.getAirline());
        flightNumber.setText(tripSegment.getFlightNumber());
        departureCity.setText(tripSegment.getDepartureCity());
        arrivalCity.setText(tripSegment.getArrivalCity());
        arrivalTime.setText(tripSegment.getArrivalTime());
        price.setValue(tripSegment.getPrice());
        confirmationNumber.setText(tripSegment.getConfirmationNumber());
    }

    @Override
    public FlightSegment flushChanges() {
        if (tripSegment == null) {
            tripSegment = new FlightSegment();
        }
        tripSegment.setDate(FormUtility.parseDateFromDateAndTime(date, time));
        tripSegment.setAirline(airline.getSelectedItem().toString());
        tripSegment.setFlightNumber(flightNumber.getText());
        tripSegment.setDepartureCity(departureCity.getText());
        tripSegment.setArrivalCity(arrivalCity.getText());
        tripSegment.setArrivalTime(arrivalTime.getText());
        try {
            tripSegment.setPrice(Float.parseFloat(price.getText()));
        }catch (NumberFormatException e) {
            tripSegment.setPrice(0);
        }
        tripSegment.setConfirmationNumber(confirmationNumber.getText());
        return tripSegment;
    }
}
