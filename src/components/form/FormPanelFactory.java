package components.form;

import components.interfaces.FormPresenter;
import data.TripSegment;
import data.TripSegmentType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Angel on 7/16/2016.
 */
public class FormPanelFactory {

    FormPresenter formPresenter;
    Map<TripSegmentType, FormPanel> forms = new HashMap<>();

    public FormPanelFactory(FormPresenter formPresenter) {
        this.formPresenter = formPresenter;
    }

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
