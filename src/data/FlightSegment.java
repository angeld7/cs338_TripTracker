package data;

/**
 * Created by Angel on 7/16/2016.
 */
public class FlightSegment extends TripSegment{
    @Override
    public TripSegmentType getTripSegmentType() {
        return TripSegmentType.FLIGHT;
    }
}
