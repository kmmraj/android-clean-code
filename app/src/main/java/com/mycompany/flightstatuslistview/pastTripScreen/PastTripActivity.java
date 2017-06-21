package com.mycompany.flightstatuslistview.pastTripScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.mycompany.flightstatuslistview.FlightModel;
import com.mycompany.flightstatuslistview.R;


/**
 * Created by mkaratadipalayam on 20/06/17.
 */

public class PastTripActivity extends AppCompatActivity {

    private static final String TAG = PastTripActivity.class.getSimpleName();


    private FlightModel flightModel;

    TextView mPnrValue;
    private TextView mPassengerName;
    TextView mFlightCode;
    private TextView mDepartureCity;
    private TextView mArrivalCity;
    private TextView mBoardingTime;
    private TextView mDepartureTime;
    TextView mDepartureDate;
    private TextView mArrivalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_trip);
        getSupportActionBar().hide();
        flightModel = getIntent().getParcelableExtra("flight");
        bindViews();
        displayTripData(flightModel);
    }

    private void bindViews(){
        mPassengerName = (TextView) findViewById(R.id.tv_passengerName);
        //mFlightCode = (TextView) findViewById(R.id.tv_flightNumberValue);
        mDepartureCity = (TextView) findViewById(R.id.tv_departureAirport);
        mArrivalCity = (TextView) findViewById(R.id.tv_arrivalAirport);
        mBoardingTime = (TextView) findViewById(R.id.tv_boardingTime);
        mDepartureTime = (TextView) findViewById(R.id.tv_departureTime);
        mArrivalTime = (TextView) findViewById(R.id.tv_arrivalTime);
       // mDepartureDate = (TextView) findViewById(R.id.tv_departureDate);

    }

    private void displayTripData(FlightModel fightModel) {
        Log.e(TAG, "displayBoardingData() called with: viewModel = [" + fightModel + "]");
        // Deal with the data
        mPassengerName.setText("Mr. Mohan Karats");
        // mFlightCode.setText(flightModel.flightName);
        mArrivalCity.setText(flightModel.arrivalCity);
        mArrivalTime.setText(flightModel.arrivalTime);
        mDepartureCity.setText(flightModel.departureCity);
        mDepartureTime.setText(flightModel.departureTime);
        // mBoardingTime.setText(flightModel.);
//        mDepartureDate.setText(flightModel.startingTime);



    }
}
