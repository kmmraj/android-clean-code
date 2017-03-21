package com.mycompany.flightstatuslistview.homeScreen;

import com.mycompany.flightstatuslistview.FlightModel;
import com.mycompany.flightstatuslistview.FlightViewModel;

import java.util.ArrayList;

/**
 * Created by mkaratadipalayam on 10/10/16.
 */

public class HomeModel {
}
class HomeViewModel{
    //TODO - filter to have only the needed data
    ArrayList<FlightViewModel> listOfFlights;
}
class HomeRequest{
    boolean isFutureTrips;
}

class HomeResponse {
    ArrayList<FlightModel> listOfFlights;
}