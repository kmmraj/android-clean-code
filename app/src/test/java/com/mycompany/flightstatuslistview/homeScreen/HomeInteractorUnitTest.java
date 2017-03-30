package com.mycompany.flightstatuslistview.homeScreen;

import com.mycompany.flightstatuslistview.BuildConfig;
import com.mycompany.flightstatuslistview.FlightModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

/**
 * Created by mkaratadipalayam on 12/10/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "app/src/main/AndroidManifest.xml", sdk = 21)
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
        HomeInteractorOutputSpy homeInteractorOutputSpy = new HomeInteractorOutputSpy();
        homeInteractor.output = homeInteractorOutputSpy;
        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
        Assert.assertTrue("When the valid input is passed to HomeInteractor Then presentHomeMetaData should be called",
                 homeInteractorOutputSpy.presentHomeMetaDataIsCalled);
    }

    @Test
    public void fetchHomeMetaData_with_vaildInput_FutureTrip_shouldCall_Worker_getFutureTrips(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.isFutureTrips = true;

        //Setup TestDoubles
        HomeInteractorOutputSpy homeInteractorOutputSpy = new HomeInteractorOutputSpy();
        homeInteractor.output = homeInteractorOutputSpy;
        FlightWorkerInputSpy flightWorkerInputSpy = new FlightWorkerInputSpy();
        homeInteractor.setFlightWorkerInput(flightWorkerInputSpy);

        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
        Assert.assertTrue("When the input is passed to HomeInteractor is FutureTrip" +
                "Then getFutureFlights should be called in Worker",
                flightWorkerInputSpy.isgetFlightsMethodCalled);
    }

    @Test
    public void fetchHomeMetaData_with_vaildInput_PastTrip_shouldCall_Worker_getPastTrips(){
        //Given
        HomeInteractor homeInteractor = new HomeInteractor();
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.isFutureTrips = false;

        //Setup TestDoubles
        HomeInteractorOutputSpy homeInteractorOutputSpy = new HomeInteractorOutputSpy();
        homeInteractor.output = homeInteractorOutputSpy;
        FlightWorkerInputSpy flightWorkerInputSpy = new FlightWorkerInputSpy();
        homeInteractor.setFlightWorkerInput(flightWorkerInputSpy);

        //When
        homeInteractor.fetchHomeMetaData(homeRequest);

        //Then
        Assert.assertTrue("When the input is passed to HomeInteractor is FutureTrip" +
                        "Then getFutureFlights should be called in Worker",
                flightWorkerInputSpy.isgetPastFlightsMethodCalled);
    }

    private class HomeInteractorOutputSpy implements HomePresenterInput {

        boolean presentHomeMetaDataIsCalled = false;
        HomeResponse homeResponseCopy;
        @Override
        public void presentHomeMetaData(HomeResponse response) {
            presentHomeMetaDataIsCalled = true;
            homeResponseCopy = response;
        }
    }

    private class FlightWorkerInputSpy implements FlightWorkerInput {

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
    };
}
