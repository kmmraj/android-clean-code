package com.mycompany.flightstatuslistview.homeScreen;

import android.util.Log;

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
    public static String TAG = HomePresenterUnitTest.class.getSimpleName();

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

        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);

        //When
        homePresenter.presentHomeMetaData(homeResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to HomePresenter Then displayHomeMetaData should be called", homeActivityInputSpy.isdisplayHomeMetaDataCalled);
    }

    @Test
    public void presentHomeMetaData_with_inVaildInput_shouldNotCall_displayHomeMetaData(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.listOfFlights = null;

        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);

        //When
        homePresenter.presentHomeMetaData(homeResponse);

        //Then
        Assert.assertFalse("When the valid input is passed to HomePresenter Then displayHomeMetaData should NOT be called", homeActivityInputSpy.isdisplayHomeMetaDataCalled);
    }

    @Test
    public void verify_HomePresenter_getDaysDiff_is_CalcualtedCorrectly_ForFutureTrips(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeResponse homeResponse = new HomeResponse();

        ArrayList<FlightModel> flightsList = new ArrayList<>();

        FlightModel flight1 = new FlightModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2017/12/31";
        flight1.departureCity = "BLR";
        flight1.arrivalCity = "CJB";
        flight1.departureTime = "18:10";
        flight1.arrivalTime = "19:00";
        flightsList.add(flight1);
        homeResponse.listOfFlights = flightsList;

        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);

        //When
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(2017,5,30,0,0,0);
        homePresenter.setCurrentTime(currentTime);
        homePresenter.presentHomeMetaData(homeResponse);

        //Then
        // "It has been " + daysDiff + " days since you flew";
        String ExpectedText = "You have " + "184" + " days to fly";
        String ActualText = homeActivityInputSpy.homeViewModelCopy.listOfFlights.get(0).noOfDaysToFly;
        Assert.assertEquals("When current date is 2016/10/12 & Flying Date is 2016/10/31 Then no of days should be 19",ExpectedText,ActualText);

    }

    @Test
    public void verify_HomePresenter_getDaysDiff_is_CalcualtedCorrectly_ForPastTrips(){
        //Given
        HomePresenter homePresenter = new HomePresenter();
        HomeResponse homeResponse = new HomeResponse();

        ArrayList<FlightModel> flightsList = new ArrayList<>();

        FlightModel flight1 = new FlightModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2016/10/01";
        flight1.departureCity = "BLR";
        flight1.arrivalCity = "CJB";
        flight1.departureTime = "18:10";
        flight1.arrivalTime = "19:00";

        flightsList.add(flight1);


        homeResponse.listOfFlights = flightsList;

        HomeActivityInputSpy homeActivityInputSpy = new HomeActivityInputSpy();
        homePresenter.output = new WeakReference<HomeActivityInput>(homeActivityInputSpy);


        //When
        Calendar currentTime = Calendar.getInstance();
        //currentTime.set(2017,5,30,0,0,0);
        currentTime.set(2017,5,30);
        Log.e(TAG, "verify_HomePresenter_getDaysDiff_is_CalcualtedCorrectly_ForPastTrips: "+currentTime.toString() );
        homePresenter.setCurrentTime(currentTime);
        homePresenter.presentHomeMetaData(homeResponse);


        //Then
        // "It has been " + daysDiff + " days since you flew";
        String ExpectedText = "It has been " + 272 + " days since you flew";
        String ActualText = homeActivityInputSpy.homeViewModelCopy.listOfFlights.get(0).noOfDaysToFly;
        Assert.assertEquals("When current date is 2017/05/30 & Flying Date is 2016/10/01 Then no of days should be 271",ExpectedText,ActualText);

    }

    private class HomeActivityInputSpy implements HomeActivityInput {
        public boolean isdisplayHomeMetaDataCalled = false;
        public HomeViewModel homeViewModelCopy;
        @Override
        public void displayHomeMetaData(HomeViewModel homeViewModel) {
            isdisplayHomeMetaDataCalled = true;
            homeViewModelCopy = homeViewModel;
        }
    }
}
