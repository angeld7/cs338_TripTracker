package components.form;

import data.TripSegmentType;

/**
 * Created by Angel on 7/16/2016.
 */
public class FormPanelFactory {
    public static FormPanel getForm(TripSegmentType type) {
        FormPanel form;
        switch (type) {
            case FLIGHT:
                form = FlightFormPanel.get();
                break;
            case TRAIN:
                form = TrainFormPanel.get();
                break;
            case CAR:
                form = CarFormPanel.get();
                break;
            default:
                form = null;
        }
        return form;
    }
}
