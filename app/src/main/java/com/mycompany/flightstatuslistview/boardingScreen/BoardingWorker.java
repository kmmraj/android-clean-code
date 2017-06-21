package com.mycompany.flightstatuslistview.boardingScreen;

interface BoardingWorkerInput {
    //Define needed interfaces
     CheckINModel getCheckINDetails(BoardingRequest boardingRequest);
}

public class BoardingWorker implements BoardingWorkerInput {

    @Override
    public CheckINModel getCheckINDetails(BoardingRequest boardingRequest) {
       CheckINModel checkINModel = new CheckINModel();
        checkINModel.flightName = "";
        checkINModel.startingTime = "";
        checkINModel.gate="24";
        checkINModel.terminal="2";
        checkINModel.seat="6A";
        return  checkINModel;
    }
}
