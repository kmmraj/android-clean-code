package com.mycompany.flightstatuslistview.boardingScreen;

import java.lang.ref.WeakReference;


public enum BoardingConfigurator {
    INSTANCE;

    public void configure(BoardingActivity activity) {

        BoardingRouter router = new BoardingRouter();
        router.activity = new WeakReference<>(activity);

        BoardingPresenter presenter = new BoardingPresenter();
        presenter.output = new WeakReference<BoardingActivityInput>(activity);

        BoardingInteractor interactor = new BoardingInteractor();
        interactor.output = presenter;

        if (activity.output == null) {
            activity.output = interactor;
        }
        if (activity.router == null) {
            activity.router = router;
        }
    }
}
