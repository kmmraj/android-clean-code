package com.mycompany.flightstatuslistview.homeScreen;

import com.mycompany.flightstatuslistview.BuildConfig;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

/**
 * Created by mkaratadipalayam on 13/10/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "app/src/main/AndroidManifest.xml", sdk = 21)
public class HomeActivityUnitTest {
    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }


    @Test
    public void HomeActivity_ShouldNOT_be_Null(){
        //Given
        HomeActivity homeActivity = Robolectric.setupActivity(HomeActivity.class);
        //When

        // Then
        Assert.assertNotNull(homeActivity);
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
        Assert.assertNotNull(homeActivity);
        Assert.assertTrue(homeActivityOutputSpy.fetchHomeMetaDataIsCalled);
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
