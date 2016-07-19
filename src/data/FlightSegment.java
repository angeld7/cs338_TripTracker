package data;

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This is the data structure for the flight segment
 */
public class FlightSegment extends TripSegment {
    //date, time, airline, flightNumber, departure city, arrival city, arrival time, price (optional), confirmation # (optional)
    private String airline;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private String arrivalTime;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * toString() is customized to print out the string specified in the requirements.
     * e.g.: 4/19/05, 2:30, US Airways, 425, Philadelphia, Boston, 4:15, $243.00, GHZ841
     * @return string form of this class
     */
    @Override
    public String toString() {
        return String.format("%1$2s %2$2s, %3$2s, %4$2s, %5$2s, %6$2s, $%7$(,.2f %8$2s",
                getDateString(),
                airline == null ? "" : airline,
                flightNumber == null ? "" : flightNumber,
                departureCity == null ? "" : departureCity,
                arrivalCity == null ? "" : arrivalCity,
                arrivalTime == null ? "" : arrivalTime,
                price,
                confirmationNumber == null ? "" : ", " + confirmationNumber
        );
    }

    @Override
    public TripSegmentType getTripSegmentType() {
        return TripSegmentType.FLIGHT;
    }
}
