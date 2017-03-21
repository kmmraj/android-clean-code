package com.mycompany.flightstatuslistview;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.StrictMode;

/**
 * Created by mkaratadipalayam on 20/09/16.
 */
public class FlightModel implements Parcelable {

    public String flightName;
    public String startingTime;
    public String numberofSeats;
    public String gate;
    public String terminal;
    public String checkInStatus;

    public FlightModel() {}

    protected FlightModel(Parcel in) {
        flightName = in.readString();
        startingTime = in.readString();
        numberofSeats = in.readString();
        gate = in.readString();
        terminal = in.readString();
        checkInStatus = in.readString();
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
        dest.writeString(numberofSeats);
        dest.writeString(gate);
        dest.writeString(terminal);
        dest.writeString(checkInStatus);
    }
}
