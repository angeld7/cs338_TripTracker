package components.interfaces;

import data.TripSegment;
import data.TripSegmentType;

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This is the interface for the presenter.  The only current implementation is the DataEntryScreen.
 */
public interface FormPresenter {
    void editNewTripSegment(TripSegmentType tripSegmentType);
    void dataUpdated(TripSegment tripSegment);
    void inputValidationFailed(String message);
    void clearValidationErrors();
}
