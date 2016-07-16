package data;

/**
 * Created by Angel on 7/16/2016.
 */
public class CarSegment extends TripSegment {
    @Override
    public TripSegmentType getTripSegmentType() {
        return TripSegmentType.CAR;
    }
}
