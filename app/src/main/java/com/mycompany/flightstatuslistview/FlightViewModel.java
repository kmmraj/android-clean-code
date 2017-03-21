package com.mycompany.flightstatuslistview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mkaratadipalayam on 11/10/16.
 */

public class FlightViewModel extends FlightModel implements Parcelable {


    public String noOfDaysToFly;


    public FlightViewModel() {}

    protected FlightViewModel(Parcel in) {
        super(in);
        noOfDaysToFly = in.readString();
    }

    public static final Creator<FlightViewModel> CREATOR = new Creator<FlightViewModel>() {
        @Override
        public FlightViewModel createFromParcel(Parcel in) {
            return new FlightViewModel(in);
        }

        @Override
        public FlightViewModel[] newArray(int size) {
            return new FlightViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(noOfDaysToFly);
    }

}
