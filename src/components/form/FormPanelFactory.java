package components.form;

import components.interfaces.FormPresenter;
import data.TripSegmentType;

import java.util.HashMap;
import java.util.Map;
/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This factory is used to retrieve the correct form for a given trip segment type
 */
public class FormPanelFactory {

    FormPresenter formPresenter;
    /**
     * Map to store the forms.  There will only be one instance of each form type.
     */
    Map<TripSegmentType, FormPanel> forms = new HashMap<>();

    public FormPanelFactory(FormPresenter formPresenter) {
        this.formPresenter = formPresenter;
    }

    /**
     * Returns the form for the given trip segment type.
     * @param type
     * @return
     */
    public FormPanel getForm(TripSegmentType type) {
        FormPanel form;
        if(forms.containsKey(type)) {
            form = forms.get(type);
        } else {
            form = createForm(type);
            forms.put(type, form);
        }
        return form;
    }

    /**
     * Creates a new instance of a form for a given trip segment type.
     * @param type
     * @return
     */
    private FormPanel createForm(TripSegmentType type) {
        FormPanel form;
        switch (type) {
            case FLIGHT:
                form = new FlightFormPanel(formPresenter);
                break;
            case TRAIN:
                form = new TrainFormPanel(formPresenter);
                break;
            case CAR:
                form = new CarFormPanel(formPresenter);
                break;
            default:
                form = null;
        }
        return form;
    }
}
