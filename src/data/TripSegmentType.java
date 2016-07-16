package data;

/**
 * Created by Angel on 7/16/2016.
 */
public enum TripSegmentType {
    FLIGHT("Flight"), TRAIN("Train"), CAR("Car");

    String name;

    TripSegmentType(String name) {
        this.name = name;
    }
}
