package com.mycompany.flightstatuslistview.homeScreen;

import com.mycompany.flightstatuslistview.FlightModel;

import java.util.ArrayList;

/**
 * Created by mkaratadipalayam on 20/09/16.
 */

interface FlightWorkerInput{
    ArrayList<FlightModel> getFutureFlights();
    ArrayList<FlightModel> getPastFlights();
}
public class FlightWorker implements FlightWorkerInput {

    public ArrayList<FlightModel> getFutureFlights() {

        ArrayList<FlightModel> flightsList = new ArrayList<>();

        FlightModel flight1 = new FlightModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2017/10/31";
        flight1.departureCity = "BLR";
        flight1.arrivalCity = "CJB";
        flight1.departureTime = "06:00";
        flight1.arrivalTime = "06:50";

        flightsList.add(flight1);

        FlightModel flight2 = new FlightModel();
        flight2.flightName = "9Z 15";
        flight2.startingTime = "2017/02/31";
        flight2.departureCity = "BLR";
        flight2.arrivalCity = "CJB";
        flight2.departureTime = "09:00";
        flight2.arrivalTime = "09:50";
        flightsList.add(flight2);

        FlightModel flight3 = new FlightModel();
        flight3.flightName = "9Z 142";
        flight3.startingTime = "2017/12/31";
       flight3.departureCity = "BLR";
        flight3.arrivalCity = "CJB";
        flight3.departureTime = "18:10";
        flight3.arrivalTime = "19:00";
        flightsList.add(flight3);

        return flightsList;
    }

    public ArrayList<FlightModel> getPastFlights() {

        ArrayList<FlightModel> flightsList = new ArrayList<>();

        FlightModel flight1 = new FlightModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2015/10/31";
       flight1.departureCity = "BLR";
        flight1.arrivalCity = "CJB";
        flight1.departureTime = "06:00";
        flight1.arrivalTime = "06:50";

        flightsList.add(flight1);

        FlightModel flight2 = new FlightModel();
        flight2.flightName = "9Z 15";
        flight2.startingTime = "2015/11/31";
        flight2.departureCity = "BLR";
        flight2.arrivalCity = "CJB";
        flight2.departureTime = "09:00";
        flight2.arrivalTime = "09:50";
        flightsList.add(flight2);

        FlightModel flight3 = new FlightModel();
        flight3.flightName = "9Z 142";
        flight3.startingTime = "2015/12/31";
       flight3.departureCity = "BLR";
        flight3.arrivalCity = "CJB";
        flight3.departureTime = "18:10";
        flight3.arrivalTime = "19:00";
        flightsList.add(flight3);

        return flightsList;
    }

}
