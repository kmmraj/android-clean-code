package com.mycompany.flightstatuslistview.homeScreen;

import android.support.annotation.NonNull;

import com.mycompany.flightstatuslistview.ArrayEmptyException;
import com.mycompany.flightstatuslistview.BuildConfig;
import com.mycompany.flightstatuslistview.FlightModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

/**
 * Created by mkaratadipalayam on 12/10/16.
 */
@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, manifest = "app/src/main/AndroidManifest.xml", sdk = 21)
public class HomeInteractorUnitTest {
    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void fetchHomeMetaData_with_vaildInput_shouldCall_presentHomeMetaData(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.isFutureTrips = true;
        HomePresenterInputSpy homePresenterInputSpy = new HomePresenterInputSpy();
        homeInteractor.output = homePresenterInputSpy;
        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
        Assert.assertTrue("When the valid input is passed to HomeInteractor " +
                        "Then presentHomeMetaData should be called",
                 homePresenterInputSpy.presentHomeMetaDataIsCalled);
    }

    @Test
    public void fetchHomeMetaData_with_vaildInput_FutureTrip_shouldCall_Worker_getFutureTrips(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.isFutureTrips = true;

        //Setup TestDoubles
        homeInteractor.output = new HomePresenterInputSpy();
        FlightWorkerInputSpy flightWorkerInputSpy = new FlightWorkerInputSpy();
        homeInteractor.setFlightWorkerInput(flightWorkerInputSpy);

        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
        Assert.assertTrue("When the input is passed to HomeInteractor is FutureTrip" +
                "Then getFutureFlights should be called in Worker",
                flightWorkerInputSpy.isgetFutureFlightsMethodCalled);
    }

    @Test
    public void fetchHomeMetaData_with_vaildInput_PastTrip_shouldCall_Worker_getPastTrips(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.isFutureTrips = false;

        //Setup TestDoubles
        homeInteractor.output = new HomePresenterInputSpy();
        FlightWorkerInputSpy flightWorkerInputSpy = new FlightWorkerInputSpy();
        homeInteractor.setFlightWorkerInput(flightWorkerInputSpy);

        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
        Assert.assertTrue("When the input is passed to HomeInteractor is FutureTrip" +
                        "Then getFutureFlights should be called in Worker",
                flightWorkerInputSpy.isgetPastFlightsMethodCalled);
    }


    @Test(expected = ArrayEmptyException.class)
    public void fetchHomeMetaData_fetchingNull_shouldThrowArrayEmptyException(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.isFutureTrips = false;

        //Setup TestDoubles
        homeInteractor.output = new HomePresenterInputSpy();
        FlightWorkerInputReturnNullSpy flightWorkerInputReturnNullSpy = new FlightWorkerInputReturnNullSpy();
        homeInteractor.setFlightWorkerInput(flightWorkerInputReturnNullSpy);

        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
//      // Check for ArrayEmptyException -- See this method Annotation
    }

    private class HomePresenterInputSpy implements HomePresenterInput {

        boolean presentHomeMetaDataIsCalled = false;
        HomeResponse homeResponseCopy;
        @Override
        public void presentHomeMetaData(HomeResponse response) {
            presentHomeMetaDataIsCalled = true;
            homeResponseCopy = response;
        }
    }

    private class FlightWorkerInputSpy implements FlightWorkerInput {

        boolean isgetFutureFlightsMethodCalled = false;
        boolean isgetPastFlightsMethodCalled = false;

        @Override
        public ArrayList<FlightModel> getFutureFlights() {
            isgetFutureFlightsMethodCalled = true;
            return getFlightModels();
        }

        @Override
        public ArrayList<FlightModel> getPastFlights() {
            isgetPastFlightsMethodCalled = true;
            return getFlightModels();
        }

        @NonNull
        private ArrayList<FlightModel> getFlightModels() {
            ArrayList<FlightModel> flightsList = new ArrayList<>();
            FlightModel flight1 = new FlightModel();
            flight1.flightName = "9Z 231";
            flight1.startingTime = "2016/10/31";
            flight1.departureCity = "BLR";
            flight1.arrivalCity = "CJB";
            flight1.departureTime = "18:10";
            flight1.arrivalTime = "19:00";
            flightsList.add(flight1);
            return flightsList;
        }
    }

    private class FlightWorkerInputReturnNullSpy implements FlightWorkerInput {

        boolean isgetFlightsMethodCalled = false;
        boolean isgetPastFlightsMethodCalled = false;

        @Override
        public ArrayList<FlightModel> getFutureFlights() {
            isgetFlightsMethodCalled = true;
            return null;
        }

        @Override
        public ArrayList<FlightModel> getPastFlights() {
            isgetPastFlightsMethodCalled = true;
            return null;
        }
    }
}
