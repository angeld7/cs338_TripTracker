package data;

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This Enum is used to identify all of the types of trip segments we can have.
 */
public enum TripSegmentType {
    FLIGHT("Flight"), TRAIN("Train"), CAR("Car");

    String name;

    TripSegmentType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
