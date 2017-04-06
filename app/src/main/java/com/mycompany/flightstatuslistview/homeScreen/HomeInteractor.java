package com.mycompany.flightstatuslistview.homeScreen;

import android.util.Log;

/**
 * Created by mkaratadipalayam on 10/10/16.
 */

interface HomeInteractorInput {
    public void fetchHomeMetaData(HomeRequest request);
}

public class HomeInteractor implements HomeInteractorInput{

    public HomePresenterInput output;

    public FlightWorkerInput flightWorkerInput;


    public FlightWorkerInput getFlightWorkerInput() {
        if (flightWorkerInput == null) return new FlightWorker();
        return flightWorkerInput;
    }

    public void setFlightWorkerInput(FlightWorkerInput flightWorkerInput) {
        this.flightWorkerInput = flightWorkerInput;
    }

    public static String TAG = HomeInteractor.class.getSimpleName();

    @Override
    public void fetchHomeMetaData(HomeRequest request) {
        flightWorkerInput = getFlightWorkerInput();
        HomeResponse homeResponse = new HomeResponse();
        if(request.isFutureTrips == true) {
            homeResponse.listOfFlights = flightWorkerInput.getFutureFlights();
        } else {
            homeResponse.listOfFlights = flightWorkerInput.getPastFlights();
        }
        //TODO : Add failure case here
        Log.e(TAG,"In method fetchHomeMetaData");
        output.presentHomeMetaData(homeResponse);
    }
}
