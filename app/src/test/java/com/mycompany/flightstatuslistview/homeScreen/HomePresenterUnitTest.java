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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by mkaratadipalayam on 11/10/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "app/src/main/AndroidManifest.xml", sdk = 21)
public class HomePresenterUnitTest {
    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void presentHomeMetaData_with_vaildInput_shouldCall_displayHomeMetaData(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.listOfFlights = new FlightWorker().getFutureFlights();

        HomePresenterOutputSpy homePresenterOutputSpy = new HomePresenterOutputSpy();
        homePresenter.output = new WeakReference<HomePresenterOutput>(homePresenterOutputSpy);

        //When
        homePresenter.presentHomeMetaData(homeResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to HomePresenter Then displayHomeMetaData should be called",homePresenterOutputSpy.isdisplayHomeMetaDataCalled);
    }

    @Test
    public void presentHomeMetaData_with_inVaildInput_shouldNotCall_displayHomeMetaData(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.listOfFlights = null;

        HomePresenterOutputSpy homePresenterOutputSpy = new HomePresenterOutputSpy();
        homePresenter.output = new WeakReference<HomePresenterOutput>(homePresenterOutputSpy);

        //When
        homePresenter.presentHomeMetaData(homeResponse);

        //Then
        Assert.assertFalse("When the valid input is passed to HomePresenter Then displayHomeMetaData should NOT be called",homePresenterOutputSpy.isdisplayHomeMetaDataCalled);
    }

    @Test
    public void verify_HomePresenter_getDaysDiff_is_CalcualtedCorrectly_ForFutureTrips(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeResponse homeResponse = new HomeResponse();

        ArrayList<FlightModel> flightsList = new ArrayList<>();

        FlightModel flight1 = new FlightModel();
        flight1.flightName = "A5 231";
        flight1.startingTime = "2016/10/31";
        flight1.numberofSeats = "6";
        flight1.gate = "33";
        flight1.terminal = "T1";

        flightsList.add(flight1);


        homeResponse.listOfFlights = flightsList;

        HomePresenterOutputSpy homePresenterOutputSpy = new HomePresenterOutputSpy();
        homePresenter.output = new WeakReference<HomePresenterOutput>(homePresenterOutputSpy);


        //When
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(2016,9,12,0,0,0);
        homePresenter.setCurrentTime(currentTime);
        homePresenter.presentHomeMetaData(homeResponse);


        //Then
        // "It has been " + daysDiff + " days since you flew";
        String ExpectedText = "You have " + "19" + " days to fly";
        String ActualText = homePresenterOutputSpy.homeViewModelCopy.listOfFlights.get(0).noOfDaysToFly;
        Assert.assertEquals("When current date is 2016/10/12 & Flying Date is 2016/10/31 Then no of days should be 19",ExpectedText,ActualText);

    }

    @Test
    public void verify_HomePresenter_getDaysDiff_is_CalcualtedCorrectly_ForPastTrips(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeResponse homeResponse = new HomeResponse();

        ArrayList<FlightModel> flightsList = new ArrayList<>();

        FlightModel flight1 = new FlightModel();
        flight1.flightName = "A5 231";
        flight1.startingTime = "2016/10/01";
        flight1.numberofSeats = "6";
        flight1.gate = "33";
        flight1.terminal = "T1";

        flightsList.add(flight1);


        homeResponse.listOfFlights = flightsList;

        HomePresenterOutputSpy homePresenterOutputSpy = new HomePresenterOutputSpy();
        homePresenter.output = new WeakReference<HomePresenterOutput>(homePresenterOutputSpy);


        //When
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(2016,9,12,0,0,0);
        homePresenter.setCurrentTime(currentTime);
        homePresenter.presentHomeMetaData(homeResponse);


        //Then
        // "It has been " + daysDiff + " days since you flew";
        String ExpectedText = "It has been " + 10 + " days since you flew";
        String ActualText = homePresenterOutputSpy.homeViewModelCopy.listOfFlights.get(0).noOfDaysToFly;
        Assert.assertEquals("When current date is 2016/10/12 & Flying Date is 2016/10/01 Then no of days should be 11",ExpectedText,ActualText);

    }

    private class HomePresenterOutputSpy implements HomePresenterOutput {
        public boolean isdisplayHomeMetaDataCalled = false;
        public HomeViewModel homeViewModelCopy;
        @Override
        public void displayHomeMetaData(HomeViewModel homeViewModel) {
            isdisplayHomeMetaDataCalled = true;
            homeViewModelCopy = homeViewModel;
        }
    }
}
