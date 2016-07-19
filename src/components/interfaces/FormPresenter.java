package components.interfaces;

import data.TripSegment;
import data.TripSegmentType;

/**
 * Created by Angel on 7/18/2016.
 */
public interface FormPresenter {
    void editNewTripSegment(TripSegmentType tripSegmentType);
    void dataUpdated(TripSegment tripSegment);
    void inputValidationFailed(String message);
    void clearValidationErrors();
}
