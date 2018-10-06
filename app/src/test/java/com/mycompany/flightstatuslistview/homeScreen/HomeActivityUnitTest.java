package com.mycompany.flightstatuslistview.homeScreen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by mkaratadipalayam on 13/10/16.
 */

@RunWith(RobolectricTestRunner.class)
public class HomeActivityUnitTest {
    @Before
    public void setUp(){}
    @After
    public void tearDown(){}


    @Test
    public void HomeActivity_ShouldNOT_be_Null(){
        //Given
        HomeActivity activity = Robolectric.setupActivity(HomeActivity.class);
        //When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchHomeMetaData(){
        //Given
        HomeActivityOutputSpy homeActivityOutputSpy = new HomeActivityOutputSpy();
        HomeActivity homeActivity = Robolectric.setupActivity(HomeActivity.class);
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchMetaData to test our condition
        homeActivity.output = homeActivityOutputSpy;

        //When
        homeActivity.fetchMetaData();

        //Then
        Assert.assertTrue(homeActivityOutputSpy.fetchHomeMetaDataIsCalled);
  }

    @Test
    public void onCreate_Calls_fetchHomeMetaData_withCorrectData(){
        //Given
        HomeActivityOutputSpy homeActivityOutputSpy = new HomeActivityOutputSpy();
        HomeActivity homeActivity = Robolectric.setupActivity(HomeActivity.class);
        homeActivity.output = homeActivityOutputSpy;

        //When
        homeActivity.fetchMetaData();

        //Then
        Assert.assertNotNull(homeActivity);
        Assert.assertTrue(homeActivityOutputSpy.homeRequestCopy.isFutureTrips);
    }



    private class HomeActivityOutputSpy implements HomeInteractorInput {

        boolean fetchHomeMetaDataIsCalled = false;
        HomeRequest homeRequestCopy;
        @Override
        public void fetchHomeMetaData(HomeRequest request) {
            fetchHomeMetaDataIsCalled = true;
            homeRequestCopy = request;
        }
    }


}
