package data;

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This is the data structure for the car segment
 */
public class CarSegment extends TripSegment {
    //date, pickup-time, company, city, location, price(optional), confirmation #(optional)
    String company;
    String city;
    String location;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * toString() is customized to print out the string specified in the requirements.
     * e.g.: 7/12/05, 10:20, Hertz, Philadelphia, airport, $23.95
     *
     * @return string form of this class
     */
    @Override
    public String toString() {
        return String.format("%1$2s %2$2s, %3$2s, %4$2s, $%5$(,.2f %6$2s",
                getDateString(),
                company == null ? "" : company,
                city == null ? "" : city,
                location == null ? "" : location,
                price,
                confirmationNumber == null || confirmationNumber.trim().equals("") ? "" : ", " + confirmationNumber
        );
    }

    @Override
    public TripSegmentType getTripSegmentType() {
        return TripSegmentType.CAR;
    }
}
