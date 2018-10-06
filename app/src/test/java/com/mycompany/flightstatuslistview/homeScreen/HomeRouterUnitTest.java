package com.mycompany.flightstatuslistview.homeScreen;


import android.content.Intent;

import com.mycompany.flightstatuslistview.BuildConfig;
import com.mycompany.flightstatuslistview.FlightViewModel;
import com.mycompany.flightstatuslistview.boardingScreen.BoardingActivity;
import com.mycompany.flightstatuslistview.pastTripScreen.PastTripActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Created by mkaratadipalayam on 28/05/17.
 */

@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, manifest = "app/src/main/AndroidManifest.xml", sdk = 21)
public class HomeRouterUnitTest {
    public static String TAG = HomeRouterUnitTest.class.getSimpleName();

    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void homeRouter_determineNextScreen_when_futureTripIs_Input() {
        //Given
        HomeRouter homeRouter = new HomeRouter();
        ArrayList<FlightViewModel> flightList = new ArrayList<>();
        FlightViewModel flight1 = new FlightViewModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2017/12/31";
        flight1.departureCity = "BLR";
        flight1.arrivalCity = "CJB";
        flight1.departureTime = "18:10";
        flight1.arrivalTime = "19:00";
        flightList.add(flight1);

        FlightViewModel flight2 = new FlightViewModel();
        flight2.flightName = "9Z 222";
        flight2.startingTime = "2016/12/31";
        flight2.departureCity = "BLR";
        flight2.arrivalCity = "CJB";
        flight2.departureTime = "18:10";
        flight2.arrivalTime = "19:00";
        flightList.add(flight2);

        HomeActivity homeActivity = Robolectric.setupActivity(HomeActivity.class);
        homeActivity.listOfVMFlights = flightList;
        homeActivity.router = homeRouter;
        homeRouter.activity = new WeakReference<HomeActivity>(homeActivity);

        Calendar currentTime = Calendar.getInstance();
        currentTime.set(2017,5,30,0,0,0);
        homeRouter.setCurrentTime(currentTime);


        //When - Futrure Trip is Input

        Intent intent = homeRouter.determineNextScreen(0);

        //Then
        String targetActivityName = intent.getComponent().getClassName();
        Assert.assertEquals("When the future travel date is passed to HomeRouter"
                +" Then next Intent should be BoardingActivity",targetActivityName, BoardingActivity.class.getName());
    }


    @Test
    public void homeRouter_determineNextScreen_when_pastTripIs_Input() {
        //Given
        HomeRouter homeRouter = new HomeRouter();
        ArrayList<FlightViewModel> flightList = new ArrayList<>();
        FlightViewModel flight1 = new FlightViewModel();
        flight1.flightName = "9Z 231";
        flight1.startingTime = "2017/12/31";
        flight1.departureCity = "BLR";
        flight1.arrivalCity = "CJB";
        flight1.departureTime = "18:10";
        flight1.arrivalTime = "19:00";
        flightList.add(flight1);

        FlightViewModel flight2 = new FlightViewModel();
        flight2.flightName = "9Z 222";
        flight2.startingTime = "2016/12/31";
        flight2.departureCity = "BLR";
        flight2.arrivalCity = "CJB";
        flight2.departureTime = "18:10";
        flight2.arrivalTime = "19:00";
        flightList.add(flight2);

        HomeActivity homeActivity = Robolectric.setupActivity(HomeActivity.class);
        homeActivity.listOfVMFlights = flightList;
        homeActivity.router = homeRouter;
        homeRouter.activity = new WeakReference<HomeActivity>(homeActivity);

        Calendar currentTime = Calendar.getInstance();
        currentTime.set(2017,5,30,0,0,0);
        homeRouter.setCurrentTime(currentTime);



        //When - Past Trip is Input
        Intent intent = homeRouter.determineNextScreen(1);

        //Then
        String targetActivityName = intent.getComponent().getClassName();
        Assert.assertEquals("When the past travel date is passed to HomeRouter"
                +" Then next Intent should be PastTripActivity",targetActivityName, PastTripActivity.class.getName());
    }



}
