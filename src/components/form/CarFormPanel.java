package components.form;

import data.CarSegment;
import data.TrainSegment;

import javax.swing.*;


/**
 * Created by Angel on 7/16/2016.
 */
public class CarFormPanel extends FormPanel<CarSegment> {
    private static CarFormPanel carFormPanel = null;

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
