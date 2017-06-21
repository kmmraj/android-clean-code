package com.mycompany.flightstatuslistview.detailScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mycompany.flightstatuslistview.FlightModel;
import com.mycompany.flightstatuslistview.R;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class FlightDetailActivity extends AppCompatActivity {

    private FlightModel flightModel;
    private static final String TAG = FlightDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);
        getSupportActionBar().hide();
        flightModel = getIntent().getParcelableExtra("flight");

        if(flightModel != null){
            TextView flightNameTextView = (TextView) findViewById(R.id.flightName);
            flightNameTextView.setText("Flight: "+flightModel.flightName);
            TextView startingTimeTextView = (TextView) findViewById(R.id.startingTime);
            startingTimeTextView.setText("Date: "+flightModel.startingTime);



            TextView checkInStatus = (TextView) findViewById(R.id.checkInStatus);


            String flightTime = flightModel.startingTime;

            int year = Integer.parseInt(flightTime.substring(0,4));
            int month = Integer.parseInt(flightTime.substring(5,7));
            int day = Integer.parseInt(flightTime.substring(8,10));

            //Log.e(TAG,"year/month/day is "+  year+ "/"+month+"/"+day);

            Calendar startingTime = Calendar.getInstance();
            startingTime.set(year,month-1,day,0,0,0);
            //Calendar currentTime = Calendar.getInstance();
            //Log.e(TAG,"Current Time is : "+ currentTime.getTime());

            long msDiff = startingTime.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
            long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
            Log.e(TAG,"diff is  "+ daysDiff);


            if (daysDiff <= 30){
                checkInStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                checkInStatus.setText("Checkin open");
            } else  if (daysDiff <= 60){
                checkInStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                checkInStatus.setText("Checkin not Open");
            } else {
                checkInStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                checkInStatus.setText("Checkin not open - Come back");
            }




//            TextView arrivalCity = (TextView) findViewById(R.id.arrivalCity);
//            arrivalCity.setText("Gate: "+flightModel.arrivalCity);
//            TextView departureTime = (TextView) findViewById(R.id.departureTime);
//            departureTime.setText("Terminal: "+flightModel.departureTime);


        }
    }
}
