package components.form;

import components.utility.FormUtility;
import data.CarSegment;
import data.TrainSegment;

import javax.swing.*;
import java.awt.*;


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
    private JTextField confirmationNumber;

    private CarFormPanel() {
        super();
        setLayout(new GridBagLayout());
        initAndAddFields();
    }

    public void initAndAddFields() {
        date = new JTextField();
        pickupTime = new JTextField();
        company = new JTextField();
        city = new JTextField();
        location = new JTextField();
        price = new JTextField();
        confirmationNumber = new JTextField();

        FormUtility.addLabel("Company:", this);
        FormUtility.addWidthScaledField(company, this, 6);
        FormUtility.addBlankLastField(this);

        FormUtility.addBlankLastField(this);

        FormUtility.addLabel("Confirmation #:", this);
        FormUtility.addWidthScaledField(confirmationNumber, this, 5);
        FormUtility.addLabel("Price:", this);
        FormUtility.addSetWidthField(price, this, 50);
        FormUtility.addBlankLastField(this);

        FormUtility.addBlankLastField(this);

        FormUtility.addLabel("City:", this);
        FormUtility.addWidthScaledField(city, this, 5);
        FormUtility.addLabel("Location:", this);
        FormUtility.addSetWidthField(location, this, 50);
        FormUtility.addBlankLastField(this);
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
