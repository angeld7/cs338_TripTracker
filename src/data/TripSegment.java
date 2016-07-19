package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This is the base class for all of the segments it contains data relevant to all segments
 */
public abstract class TripSegment {
    Date date;
    String confirmationNumber;
    float price;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDateString() {
        return date == null ? "" : new SimpleDateFormat("M/dd/yy, hh:mm a, ").format(date);
    }

    public abstract TripSegmentType getTripSegmentType();
}
