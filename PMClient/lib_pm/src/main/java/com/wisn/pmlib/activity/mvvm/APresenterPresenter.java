package com.wisn.pmlib.activity.mvvm;


import com.wisn.pmlib.activity.mvp.AbsPresenter;

/**
 * Presenter stub.
 */
public class APresenterPresenter extends AbsPresenter implements APresenterContract.Presenter {

    public APresenterPresenter(APresenterContract.View view) {
        super(view);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected APresenterContract.View getView() {
        return super.getView();
    }

    // TODO

}