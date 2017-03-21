package com.mycompany.flightstatuslistview.homeScreen;

import com.mycompany.flightstatuslistview.FlightModel;

import java.util.ArrayList;

/**
 * Created by mkaratadipalayam on 20/09/16.
 */

interface FlightWorkerInput{
    public ArrayList<FlightModel> getFutureFlights();
    public ArrayList<FlightModel> getPastFlights();
}
public class FlightWorker implements FlightWorkerInput {

    public ArrayList<FlightModel> getFutureFlights() {

        ArrayList<FlightModel> flightsList = new ArrayList<>();

        FlightModel flight1 = new FlightModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2016/10/31";
        flight1.numberofSeats = "6";
        flight1.gate = "33";
        flight1.terminal = "T1";

        flightsList.add(flight1);

        FlightModel flight2 = new FlightModel();
        flight2.flightName = "9Z 15";
        flight2.startingTime = "2016/11/31";
        flight2.numberofSeats = "15";
        flight2.gate = "15";
        flight2.terminal = "T0";
        flightsList.add(flight2);

        FlightModel flight3 = new FlightModel();
        flight3.flightName = "9Z 142";
        flight3.startingTime = "2016/12/31";
        flight3.numberofSeats = "33";
        flight3.gate = "6";
        flight3.terminal = "T2";
        flightsList.add(flight3);

        return flightsList;
    }

    public ArrayList<FlightModel> getPastFlights() {

        ArrayList<FlightModel> flightsList = new ArrayList<>();

        FlightModel flight1 = new FlightModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2015/10/31";
        flight1.numberofSeats = "6";
        flight1.gate = "33";
        flight1.terminal = "T1";

        flightsList.add(flight1);

        FlightModel flight2 = new FlightModel();
        flight2.flightName = "9Z 15";
        flight2.startingTime = "2015/11/31";
        flight2.numberofSeats = "15";
        flight2.gate = "15";
        flight2.terminal = "T0";
        flightsList.add(flight2);

        FlightModel flight3 = new FlightModel();
        flight3.flightName = "9Z 142";
        flight3.startingTime = "2015/12/31";
        flight3.numberofSeats = "33";
        flight3.gate = "6";
        flight3.terminal = "T2";
        flightsList.add(flight3);

        return flightsList;
    }

}
