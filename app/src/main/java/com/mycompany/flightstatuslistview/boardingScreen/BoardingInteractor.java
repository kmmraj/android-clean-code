package com.mycompany.flightstatuslistview.boardingScreen;

interface BoardingInteractorInput {
    void fetchBoardingData(BoardingRequest request);
}


public class BoardingInteractor implements BoardingInteractorInput {

    public static String TAG = BoardingInteractor.class.getSimpleName();
    public BoardingPresenterInput output;
    private BoardingWorkerInput aBoardingWorkerInput;

    private BoardingWorkerInput getBoardingWorkerInput() {
        if (aBoardingWorkerInput == null) return new BoardingWorker();
        return aBoardingWorkerInput;
    }

    public void setBoardingWorkerInput(BoardingWorkerInput aBoardingWorkerInput) {
        this.aBoardingWorkerInput = aBoardingWorkerInput;
    }

    @Override
    public void fetchBoardingData(BoardingRequest request) {
        aBoardingWorkerInput = getBoardingWorkerInput();
        BoardingResponse aBoardingResponse = new BoardingResponse();
        // Call the workers
        aBoardingResponse.checkINModel = aBoardingWorkerInput.getCheckINDetails(request);
        output.presentBoardingData(aBoardingResponse);
    }
}
