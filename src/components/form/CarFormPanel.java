package components.form;

import components.interfaces.FormPresenter;
import components.utility.FormUtility;
import data.CarSegment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;


/**
 * Created by Angel on 7/16/2016.
 */
public class CarFormPanel extends FormPanel<CarSegment> {
    public static final String DATE = "Date";
    public static final String PICKUP_TIME = "Pickup Time";
    public static final String LOCATION = "Location";
    public static final String CITY = "City";
    public static final String PRICE = "Price";
    public static final String CONFIRMATION_NUMBER = "Confirmation #";
    public static final String COMPANY = "Company";

    private JFormattedTextField date;
    private JFormattedTextField pickupTime;
    private JComboBox company;
    private JTextField city;
    private JTextField location;
    private JFormattedTextField price;
    private JTextField confirmationNumber;

    public CarFormPanel(FormPresenter formPresenter) {
        super(formPresenter);
        setLayout(new GridBagLayout());
        initFields();
        addFields();
    }

    public void initFields() {
        date = FormUtility.getRequiredDateField(defaultInputValidationFailHandler, DATE);
        pickupTime = FormUtility.getRequiredTimeField(defaultInputValidationFailHandler, PICKUP_TIME);
        String[] companies = {"Hertz", "Holman", "Enterprise", "Kayak"};
        company = new JComboBox(companies);
        city = new JTextField();
        location = new JTextField();
        price = FormUtility.getCurrencyField(defaultInputValidationFailHandler);
        confirmationNumber = new JTextField();

        date.addKeyListener(keyListener);
        pickupTime.addKeyListener(keyListener);
        company.addKeyListener(keyListener);
        city.addKeyListener(keyListener);
        location.addKeyListener(keyListener);
        price.addKeyListener(keyListener);
        confirmationNumber.addKeyListener(keyListener);

        FormUtility.addRequiredValidator(city, defaultInputValidationFailHandler, CITY);
        FormUtility.addRequiredValidator(location, defaultInputValidationFailHandler, LOCATION);
        FormUtility.addRequiredValidator(confirmationNumber, defaultInputValidationFailHandler, CONFIRMATION_NUMBER);

        fields.add(company);
        fields.add(confirmationNumber);
        fields.add(price);
        fields.add(city);
        fields.add(location);
        fields.add(date);
        fields.add(pickupTime);
    }

    private void addFields() {
        FormUtility.addRequiredLabel(COMPANY, this);
        FormUtility.addWidthScaledField(company, this, 3);

        FormUtility.addBlankLastField(this);

        FormUtility.addLabel(CONFIRMATION_NUMBER, this);
        FormUtility.addWidthScaledField(confirmationNumber, this, 1);
        FormUtility.addLabel(PRICE, this);
        FormUtility.addSetWidthField(price, this, 50);
        FormUtility.addBlankLastField(this);

        FormUtility.addBlankLastField(this);

        FormUtility.addRequiredLabel(CITY, this);
        FormUtility.addWidthScaledField(city, this, 1);
        FormUtility.addRequiredLabel(LOCATION, this);
        FormUtility.addSetWidthField(location, this, 50);
        FormUtility.addBlankLastField(this);

        FormUtility.addRequiredLabel(DATE, this);
        FormUtility.addWidthScaledField(date, this, 1);
        FormUtility.addRequiredLabel(PICKUP_TIME, this);
        FormUtility.addWidthScaledField(pickupTime, this, 1);
        FormUtility.addBlankLastField(this);
    }

    @Override
    public void clearFields() {
        date.setText("");
        pickupTime.setText("");
        company.setSelectedIndex(0);
        city.setText("");
        location.setText("");
        price.setText("");
        confirmationNumber.setText("");
    }

    @Override
    protected void populateTripSegmentData() {
        date.setValue(tripSegment.getDate());
        pickupTime.setValue(tripSegment.getDate());
        company.setSelectedItem(tripSegment.getCompany());
        city.setText(tripSegment.getCity());
        location.setText(tripSegment.getLocation());
        price.setValue(tripSegment.getPrice());
        confirmationNumber.setText(tripSegment.getConfirmationNumber());
    }

    @Override
    public CarSegment flushChanges() {
        if (tripSegment == null) {
            tripSegment = new CarSegment();
        }
        tripSegment.setDate(FormUtility.parseDateFromDateAndTime(date, pickupTime));
        tripSegment.setCompany(company.getSelectedItem().toString());
        tripSegment.setLocation(location.getText());
        try {
            tripSegment.setPrice(Float.parseFloat(price.getText()));
        } catch (NumberFormatException e) {
            tripSegment.setPrice(0);
        }
        tripSegment.setConfirmationNumber(confirmationNumber.getText());
        tripSegment.setCity(city.getText());
        return tripSegment;
    }
}
