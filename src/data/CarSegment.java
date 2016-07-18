package data;

import java.util.Date;

/**
 * Created by Angel on 7/16/2016.
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

    @Override
    public String toString() {
        //e.g.: 7/12/05, 10:20, Hertz, Philadelphia, airport, $23.95
        return "CarSegment{" +
                "date=" + date +
                ", company='" + company + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public TripSegmentType getTripSegmentType() {
        return TripSegmentType.CAR;
    }
}
