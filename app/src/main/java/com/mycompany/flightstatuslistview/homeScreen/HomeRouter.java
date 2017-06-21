package com.mycompany.flightstatuslistview.homeScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import com.mycompany.flightstatuslistview.FlightModel;
import com.mycompany.flightstatuslistview.boardingScreen.BoardingActivity;
import com.mycompany.flightstatuslistview.pastTripScreen.PastTripActivity;


import java.lang.ref.WeakReference;
import java.util.Calendar;

import util.CalendarUtil;

/**
 * Created by mkaratadipalayam on 10/10/16.
 */



interface HomeRouterInput{
    Intent determineNextScreen(int position);
    void passDataToNextScene(int position, Intent intent);
}

public class HomeRouter implements HomeRouterInput, AdapterView.OnItemClickListener {

    public static String TAG = HomeRouter.class.getSimpleName();
    public WeakReference<HomeActivity> activity;
    private Calendar currentTime;


    public Calendar getCurrentTime() {
        if(currentTime == null) return Calendar.getInstance();
        return currentTime;
    }
    public void setCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
    }



    @NonNull
    @Override
    public Intent determineNextScreen(int position) {
        //Based on the position or someother data decide what is the next scene

        FlightModel flight = activity.get().listOfVMFlights.get(position);
        Calendar startingTime = CalendarUtil.getCalendar(flight.startingTime);

        if(isFutureFlight(startingTime)) {
            return new Intent(activity.get(), BoardingActivity.class);
        } else {
            return new Intent(activity.get(), PastTripActivity.class);
        }
    }

    @Override
    public void passDataToNextScene(int position, Intent intent) {
        //Based on the position or someother data decide the data for the next scene
        FlightModel flight = activity.get().listOfVMFlights.get(position);
        intent.putExtra("flight",flight);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Log.e(TAG, "onItemClick() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
        Intent intent = determineNextScreen(position);
        passDataToNextScene(position, intent);
        activity.get().startActivity(intent);
    }

    private boolean isFutureFlight(Calendar startingTime){
        long startTimeInMills = startingTime.getTimeInMillis();
        long currentTimeInMills = getCurrentTime().getTimeInMillis();
        return startTimeInMills >= currentTimeInMills;
    }

}
