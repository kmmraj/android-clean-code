package com.mycompany.flightstatuslistview.homeScreen;


import com.mycompany.flightstatuslistview.BuildConfig;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Created by mkaratadipalayam on 28/05/17.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "app/src/main/AndroidManifest.xml", sdk = 21)
public class HomeRouterUnitTest {

    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }

    @Test
    public void routerCalling_with_Correct() {
        //Given
        HomeRouter homeRouter = new HomeRouter();


        //When
      // homeRouter.passDataToNextScene();

        //Then
      //  Assert.assertTrue("When the valid input is passed to HomePresenter Then displayHomeMetaData should be called",homePresenterOutputSpy.isdisplayHomeMetaDataCalled);
    }


}
