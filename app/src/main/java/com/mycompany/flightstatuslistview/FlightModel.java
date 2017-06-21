package com.mycompany.flightstatuslistview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mkaratadipalayam on 20/09/16.
 */
public class FlightModel implements Parcelable {

    public String flightName;
    public String startingTime;
    public String departureCity;
    public String arrivalCity;
    public String departureTime;
    public String arrivalTime;

    public FlightModel() {}

    protected FlightModel(Parcel in) {
        flightName = in.readString();
        startingTime = in.readString();
        departureCity = in.readString();
        arrivalCity = in.readString();
        departureTime = in.readString();
        arrivalTime = in.readString();
    }

    public static final Creator<FlightModel> CREATOR = new Creator<FlightModel>() {
        @Override
        public FlightModel createFromParcel(Parcel in) {
            return new FlightModel(in);
        }

        @Override
        public FlightModel[] newArray(int size) {
            return new FlightModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(flightName);
        dest.writeString(startingTime);
        dest.writeString(departureCity);
        dest.writeString(arrivalCity);
        dest.writeString(departureTime);
        dest.writeString(arrivalTime);
    }
}
