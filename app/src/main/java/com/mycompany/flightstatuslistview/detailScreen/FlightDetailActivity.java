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

    FlightModel flightModel;
    public static final String TAG = FlightDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);
        getSupportActionBar().hide();
        flightModel = getIntent().getParcelableExtra("flight");

        if(flightModel != null){
            TextView flightNameTextView = (TextView) findViewById(R.id.flightName);
            flightNameTextView.setText(flightModel.flightName);
            TextView startingTimeTextView = (TextView) findViewById(R.id.startingTime);
            startingTimeTextView.setText(flightModel.startingTime);



            TextView checkInStatus = (TextView) findViewById(R.id.checkInStatus);


            String flightTime = flightModel.startingTime;

            int year = Integer.parseInt(flightTime.substring(0,4));
            int month = Integer.parseInt(flightTime.substring(5,7));
            int day = Integer.parseInt(flightTime.substring(8,10));

            //Log.e(TAG,"year/month/day is "+  year+ "/"+month+"/"+day);

            Calendar startingTime = Calendar.getInstance();
            startingTime.set(year,month-1,day,0,0,0);
            Calendar currentTime = Calendar.getInstance();
            //Log.e(TAG,"Current Time is : "+ currentTime.getTime());

            long msDiff = startingTime.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
            long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
            Log.e(TAG,"diff is  "+ daysDiff);


            if (daysDiff <= 30){
                checkInStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                checkInStatus.setText("Check In Open");
            } else  if (daysDiff <= 60){
                checkInStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                checkInStatus.setText("Check Not Open");
            } else {
                checkInStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                checkInStatus.setText("Check Not Open - Come back");
            }




            TextView gate = (TextView) findViewById(R.id.gate);
            gate.setText("Gate: "+flightModel.gate);
            TextView terminal = (TextView) findViewById(R.id.terminal);
            terminal.setText("Terminal: "+flightModel.terminal);


        }
    }
}
