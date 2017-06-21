package com.mycompany.flightstatuslistview.boardingScreen;

import java.lang.ref.WeakReference;

interface BoardingPresenterInput {
     void presentBoardingData(BoardingResponse response);
}


public class BoardingPresenter implements BoardingPresenterInput {

    public static String TAG = BoardingPresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<BoardingActivityInput> output;


    @Override
    public void presentBoardingData(BoardingResponse response) {
        // Log.e(TAG, "presentBoardingData() called with: response = [" + response + "]");
        //Do your decoration or filtering here
        // Model and Viewmodel is same here
        BoardingViewModel boardingViewModel = new BoardingViewModel();
        boardingViewModel.checkINModel = response.checkINModel;

        output.get().displayBoardingData(boardingViewModel);
    }


}
