package components.form;

import components.interfaces.FormPresenter;
import components.utility.FormUtility;
import data.TrainSegment;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This is the form used to edit Train trip segments
 */
public class TrainFormPanel extends FormPanel<TrainSegment> {
    public static final String COMPANY = "Company";
    public static final String TRAIN_NUMBER = "Train Number";
    public static final String CONFIRMATION = "Confirmation #";
    public static final String PRICE = "Price";
    public static final String DEPARTURE_CITY = "Departure City";
    public static final String DEPARTURE_DATE = "Departure Date";
    public static final String DEPARTURE_TIME = "Departure Time";
    public static final String ARRIVAL_CITY = "Arrival City";
    public static final String ARRIVAL_TIME = "Arrival Time";

    private JFormattedTextField date;
    private JFormattedTextField time;
    private JComboBox company;
    private JTextField trainNumber;
    private JTextField departureCity;
    private JTextField arrivalCity;
    private JTextField arrivalTime;
    private JFormattedTextField price;
    private JTextField confirmationNumber;

    public TrainFormPanel(FormPresenter presenter) {
        super(presenter);
        setLayout(new GridBagLayout());
        initFields();
        addFields();
    }

    private void initFields() {
        date = FormUtility.getRequiredDateField(defaultInputValidationFailHandler, DEPARTURE_DATE);
        time = FormUtility.getRequiredTimeField(defaultInputValidationFailHandler, DEPARTURE_TIME);
        String[] companies = {"Amtrak", "Riverline", "NJ Transit", "Bob's fun trains"};
        company = new JComboBox(companies);
        trainNumber = new JTextField();
        departureCity = new JTextField();
        arrivalCity = new JTextField();
        arrivalTime = FormUtility.getRequiredTimeField(defaultInputValidationFailHandler, ARRIVAL_TIME);
        price = FormUtility.getCurrencyField(defaultInputValidationFailHandler);
        confirmationNumber = new JTextField();

        date.addKeyListener(keyListener);
        time.addKeyListener(keyListener);
        company.addKeyListener(keyListener);
        trainNumber.addKeyListener(keyListener);
        departureCity.addKeyListener(keyListener);
        arrivalCity.addKeyListener(keyListener);
        arrivalTime.addKeyListener(keyListener);
        price.addKeyListener(keyListener);
        confirmationNumber.addKeyListener(keyListener);

        FormUtility.addRequiredValidator(trainNumber, defaultInputValidationFailHandler, TRAIN_NUMBER);
        FormUtility.addRequiredValidator(arrivalCity, defaultInputValidationFailHandler, ARRIVAL_CITY);
        FormUtility.addRequiredValidator(arrivalTime, defaultInputValidationFailHandler, ARRIVAL_TIME);
        FormUtility.addRequiredValidator(departureCity, defaultInputValidationFailHandler, DEPARTURE_CITY);

        fields.add(company);
        fields.add(trainNumber);
        fields.add(confirmationNumber);
        fields.add(price);
        fields.add(departureCity);
        fields.add(date);
        fields.add(time);
        fields.add(arrivalCity);
        fields.add(arrivalTime);
    }

    public void addFields() {
        FormUtility.addRequiredLabel(COMPANY, this);
        FormUtility.addWidthScaledField(company, this, 5);
        FormUtility.addRequiredLabel(TRAIN_NUMBER, this);
        FormUtility.addWidthScaledField(trainNumber, this, 1);
        FormUtility.addBlankLastField(this);

        FormUtility.addLabel(CONFIRMATION, this);
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
        company.setSelectedIndex(0);
        trainNumber.setText("");
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
        company.setSelectedItem(tripSegment.getCompany());
        trainNumber.setText(tripSegment.getTrainNumber());
        departureCity.setText(tripSegment.getDepartureCity());
        arrivalCity.setText(tripSegment.getArrivalCity());
        arrivalTime.setText(tripSegment.getArrivalTime());
        price.setValue(tripSegment.getPrice());
        confirmationNumber.setText(tripSegment.getConfirmationNumber());
    }

    @Override
    public TrainSegment flushChanges() {
        if (tripSegment == null) {
            tripSegment = new TrainSegment();
        }
        tripSegment.setDate(FormUtility.parseDateFromDateAndTime(date, time));
        tripSegment.setCompany(company.getSelectedItem().toString());
        tripSegment.setTrainNumber(trainNumber.getText());
        tripSegment.setDepartureCity(departureCity.getText());
        tripSegment.setArrivalCity(arrivalCity.getText());
        tripSegment.setArrivalTime(arrivalTime.getText());
        try {
            tripSegment.setPrice(Float.parseFloat(price.getText()));
        } catch (NumberFormatException e) {
            tripSegment.setPrice(0);
        }
        tripSegment.setConfirmationNumber(confirmationNumber.getText());
        return tripSegment;
    }
}
