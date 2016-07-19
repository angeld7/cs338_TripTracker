package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Angel on 7/16/2016.
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
