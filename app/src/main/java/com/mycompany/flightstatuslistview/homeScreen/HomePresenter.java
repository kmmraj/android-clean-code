package com.mycompany.flightstatuslistview.homeScreen;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mycompany.flightstatuslistview.FlightModel;
import com.mycompany.flightstatuslistview.FlightViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

interface HomePresenterInput {
    public void presentHomeMetaData(HomeResponse response);
}

public class HomePresenter implements HomePresenterInput {

    public static String TAG = HomePresenter.class.getSimpleName();

    public WeakReference<HomeActivityInput> output;
    private Calendar currentTime;


    public Calendar getCurrentTime() {
        if(currentTime == null) return Calendar.getInstance();
        return currentTime;
    }



    public void setCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void presentHomeMetaData(HomeResponse response) {
        // Log.e(TAG, "presentHomeMetaData() called with: response = [" + response + "]");
        //Do your decoration or filtering here
        HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.listOfFlights = new ArrayList<>();


        if (response.listOfFlights != null) {

            for (FlightModel fm : response.listOfFlights) {
                FlightViewModel fvm = new FlightViewModel();

                fvm.checkInStatus = fm.checkInStatus;
                fvm.terminal = fm.terminal;
                fvm.gate = fm.gate;
                fvm.flightName = fm.flightName;
                fvm.startingTime = fm.startingTime;
                //Decoration
                Calendar startingTime = getStartingTimeCalendar(fvm);
                long daysDiff = getDaysDiff(getCurrentTime().getTimeInMillis(),startingTime.getTimeInMillis());
                setDaysFlyDecorationText(fvm, daysDiff);

                homeViewModel.listOfFlights.add(fvm);
            }


            output.get().displayHomeMetaData(homeViewModel);
        }
    }

    private void setDaysFlyDecorationText(FlightViewModel fvm, long daysDiff) {
        if(daysDiff >=0){
            fvm.noOfDaysToFly = "You have " + daysDiff + " days to fly";
        } else {
            daysDiff =-daysDiff;
            fvm.noOfDaysToFly = "It has been " + daysDiff + " days since you flew";
        }
    }

    @NonNull
    private Calendar getStartingTimeCalendar(FlightViewModel fvm) {
        int year = Integer.parseInt(fvm.startingTime.substring(0,4));
        int month = Integer.parseInt(fvm.startingTime.substring(5,7));
        int day = Integer.parseInt(fvm.startingTime.substring(8,10));



        Calendar startingTime = Calendar.getInstance();
        startingTime.set(year,month-1,day,0,0,0);
        return startingTime;
    }

    private long getDaysDiff(long startTime,long endTime) {

        long msDiff = endTime - startTime;
        long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
        Log.e(TAG,"diff is  "+ daysDiff);
        return daysDiff;
    }
}
