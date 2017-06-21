package com.mycompany.flightstatuslistview.boardingScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mycompany.flightstatuslistview.FlightModel;
import com.mycompany.flightstatuslistview.R;


interface BoardingActivityInput {
    void displayBoardingData(BoardingViewModel viewModel);
}


public class BoardingActivity extends AppCompatActivity
        implements BoardingActivityInput {

    private static String TAG = BoardingActivity.class.getSimpleName();
    BoardingInteractorInput output;
    BoardingRouter router;
    private FlightModel flightModel;
    private TextView mPassengerName;
    private TextView mFlightCode;
    private TextView mDepartureCity;
    private TextView mArrivalCity;
    private TextView mBoardingTime;
    private TextView mDepartureTime;
    private TextView mDepartureDate;
    private TextView mArrivalTime;
    private TextView mGate;
    private TextView mTerminal;
    private TextView mSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_boarding);
        flightModel = getIntent().getParcelableExtra("flight");
        bindViews();
        //do the setup

        BoardingConfigurator.INSTANCE.configure(this);
        BoardingRequest aBoardingRequest = new BoardingRequest();
        //populate the request


        output.fetchBoardingData(aBoardingRequest);
        // Do other work
    }

    private void bindViews(){
        mPassengerName = (TextView) findViewById(R.id.tv_passengerName);
        mFlightCode = (TextView) findViewById(R.id.tv_flightNumberValue);
        mDepartureCity = (TextView) findViewById(R.id.tv_departureAirport);
        mArrivalCity = (TextView) findViewById(R.id.tv_arrivalAirport);
        mBoardingTime = (TextView) findViewById(R.id.tv_boardingTime);
        mDepartureTime = (TextView) findViewById(R.id.tv_departureTime);
        mArrivalTime = (TextView) findViewById(R.id.tv_arrivalTime);
        mDepartureDate = (TextView) findViewById(R.id.tv_departureDate);
        mGate = (TextView) findViewById(R.id.tv_gateValue);
        mTerminal = (TextView) findViewById(R.id.tv_terminalValue);
        mSeat = (TextView) findViewById(R.id.tv_seatValue);
    }


    @Override
    public void displayBoardingData(BoardingViewModel viewModel) {
        Log.e(TAG, "displayBoardingData() called with: viewModel = [" + viewModel + "]");
        // Deal with the data
        CheckINModel checkINModel = viewModel.checkINModel;
        mPassengerName.setText("Mohan Karats");
       // mFlightCode.setText(flightModel.flightName);
        mArrivalCity.setText(flightModel.arrivalCity);
        mArrivalTime.setText(flightModel.arrivalTime);
        mDepartureCity.setText(flightModel.departureCity);
        mDepartureTime.setText(flightModel.departureTime);
       // mBoardingTime.setText(flightModel.);
        mDepartureDate.setText(flightModel.startingTime);



        mGate.setText(checkINModel.gate);
        mTerminal.setText(checkINModel.terminal);
        mSeat.setText(checkINModel.seat);
    }
}
