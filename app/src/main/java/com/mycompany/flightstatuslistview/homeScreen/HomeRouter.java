package com.mycompany.flightstatuslistview.homeScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import com.mycompany.flightstatuslistview.FlightModel;
import com.mycompany.flightstatuslistview.boardingScreen.FlightBoardingActivity;

import java.lang.ref.WeakReference;

/**
 * Created by mkaratadipalayam on 10/10/16.
 */



interface HomeRouterInput{
    public Intent determineNextScreen(int position);
    public void passDataToNextScene(int position, Intent intent);
}

public class HomeRouter implements HomeRouterInput, AdapterView.OnItemClickListener {

    public static String TAG = HomeRouter.class.getSimpleName();
    public WeakReference<HomeActivity> activity;


    @NonNull
    @Override
    public Intent determineNextScreen(int position) {
        //Based on the position or someother data decide what is the next scene
        Intent intent = new Intent(activity.get(),FlightBoardingActivity.class);
        return intent;
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


}
