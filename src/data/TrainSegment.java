package data;

/**
 * Created by Angel on 7/16/2016.
 */
public class TrainSegment extends TripSegment {
    @Override
    public TripSegmentType getTripSegmentType() {
        return TripSegmentType.TRAIN;
    }
}
