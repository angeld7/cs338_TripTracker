package data;


/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This is the data structure for the train segment
 */
public class TrainSegment extends TripSegment {
    private String company;
    private String trainNumber;
    private String departureCity;
    private String arrivalCity;
    private String arrivalTime;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
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
     * e.g.: 6/1/05, 7:45, Amtrak, 128, Philadelphia, Washington, 9:20
     * @return string form of this class
     */
    @Override
    public String toString() {
        return String.format("%1$2s %2$2s, %3$2s, %4$2s, %5$2s, %6$2s, $%7$(,.2f %8$2s",
                getDateString(),
                company == null ? "" : company,
                trainNumber == null ? "" : trainNumber,
                departureCity == null ? "" : departureCity,
                arrivalCity == null ? "" : arrivalCity,
                arrivalTime == null ? "" : arrivalTime,
                price,
                confirmationNumber == null || confirmationNumber.trim().equals("") ? "" : ", " + confirmationNumber
        );
    }

    @Override
    public TripSegmentType getTripSegmentType() {
        return TripSegmentType.TRAIN;
    }
}
