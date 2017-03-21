package com.mycompany.flightstatuslistview.homeScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.flightstatuslistview.FlightViewModel;
import com.mycompany.flightstatuslistview.R;

import java.util.ArrayList;



 interface HomeActivityInput {
    public void displayHomeMetaData(HomeViewModel viewModel);
}

 interface HomeActivityOutput {
    public void fetchHomeMetaData(HomeRequest request);
}

public class HomeActivity extends AppCompatActivity
        implements //AdapterView.OnItemClickListener,
        HomeActivityInput,
        HomePresenterOutput {

    ArrayList<FlightViewModel> listOfVMFlights;

    HomeActivityOutput output;
    HomeRouter router;

    public static String TAG = HomeActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        HomeConfigurator.INSTANCE.configure(this);
        fetchMetaData();
    }

    public void fetchMetaData() {
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.isFutureTrips = true; //TODO remove this hardcoding
        output.fetchHomeMetaData(homeRequest);
        createFlightListView();
    }

    private void createFlightListView() {
        ListView listView = (ListView) findViewById(R.id.listOfFlights);
        listView.setAdapter(new FlightListAdapter());
        listView.setOnItemClickListener(router);
    }

    @Override
    public void displayHomeMetaData(HomeViewModel viewModel) {
        Log.e(TAG, "displayHomeMetaData() called with: viewModel = [" + viewModel + "]");
        listOfVMFlights = viewModel.listOfFlights;
    }





    private class FlightListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public FlightListAdapter(){
            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return listOfVMFlights.size();
        }

        @Override
        public Object getItem(int position) {
            return listOfVMFlights.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.cell_flight_status,null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.flightNameTextView = (TextView) convertView.findViewById(R.id.flightName);
                viewHolder.startTimeTextView = (TextView) convertView.findViewById(R.id.startingTime);
                //viewHolder.numberOfSeatsTextView = (TextView) convertView.findViewById(R.id.numberOfSeats);

                convertView.setTag(viewHolder);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.flightNameTextView.setText( listOfVMFlights.get(position).flightName);
            //viewHolder.numberOfSeatsTextView.setText( listOfVMFlights.get(position).numberofSeats);
            viewHolder.startTimeTextView.setText( listOfVMFlights.get(position).noOfDaysToFly);
            return convertView;
        }
    }

    class ViewHolder {
        TextView flightNameTextView;
        TextView startTimeTextView;
        TextView numberOfSeatsTextView;
    }
}
