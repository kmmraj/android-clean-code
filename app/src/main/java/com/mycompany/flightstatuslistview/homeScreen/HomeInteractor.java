package com.mycompany.flightstatuslistview.homeScreen;

import android.util.Log;

import com.mycompany.flightstatuslistview.ArrayEmptyException;

/**
 * Created by mkaratadipalayam on 10/10/16.
 */

interface HomeInteractorInput {
    void fetchHomeMetaData(HomeRequest request);
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
        Log.e(TAG,"In method fetchHomeMetaData");
        flightWorkerInput = getFlightWorkerInput();
        HomeResponse homeResponse = new HomeResponse();
        if(request.isFutureTrips) {
            homeResponse.listOfFlights = flightWorkerInput.getFutureFlights();
        } else {
            homeResponse.listOfFlights = flightWorkerInput.getPastFlights();
        }
        //TODO : Add failure case here
        if(null == homeResponse.listOfFlights || homeResponse.listOfFlights.isEmpty()) {
            throw new ArrayEmptyException("Empty Flight List");
        }

        output.presentHomeMetaData(homeResponse);
    }
}
