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
    void displayHomeMetaData(HomeViewModel viewModel);
}

//interface HomeRouterOutput {
//    ArrayList<FlightViewModel> listOfVMFlights = null;
//     HomeRouter router = null;
//}



public class HomeActivity extends AppCompatActivity implements HomeActivityInput {

    public ArrayList<FlightViewModel> listOfVMFlights;

    HomeInteractorInput output;
    HomeRouter router;

    public static String TAG = HomeActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        HomeConfigurator.INSTANCE.configure(this);
        fetchMetaData();
        createFlightListView();
    }

    public void fetchMetaData() {
        // create Request and set the needed input
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.isFutureTrips = true;
        // Call the output to fetch the data
        output.fetchHomeMetaData(homeRequest);
    }

    private void createFlightListView() {
        ListView listView = (ListView) findViewById(R.id.listOfFlights);
        listView.setAdapter(new FlightListAdapter());
        listView.setClickable(true);
        listView.setOnItemClickListener(router);
    }

    @Override
    public void displayHomeMetaData(HomeViewModel viewModel) {
        Log.e(TAG, "displayHomeMetaData() called with: viewModel = [" + viewModel + "]");
        listOfVMFlights = viewModel.listOfFlights;
    }





    private class FlightListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        FlightListAdapter(){
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
                convertView = layoutInflater.inflate(R.layout.cell_trip_list,null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.flightNameTextView = (TextView) convertView.findViewById(R.id.tv_flightNumberValue);
                viewHolder.startTimeTextView = (TextView) convertView.findViewById(R.id.tv_flightTimeDescription);
                convertView.setTag(viewHolder);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.flightNameTextView.setText( listOfVMFlights.get(position).flightName);
            viewHolder.startTimeTextView.setText( listOfVMFlights.get(position).noOfDaysToFly);
            return convertView;
        }
    }

    class ViewHolder {
        TextView flightNameTextView;
        TextView startTimeTextView;
    }
}
