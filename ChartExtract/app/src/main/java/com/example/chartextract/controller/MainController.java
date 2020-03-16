package com.example.chartextract.controller;

import android.view.MotionEvent;
import android.view.View;

import com.example.chartextract.model.ChartModel;
import com.example.chartextract.model.InteractionModel;

public class MainController implements View.OnTouchListener {

    private enum State {READY, DRAGGING}

    private ChartModel ChartModel;
    private State currentState = State.READY;
    private InteractionModel IModel;

    private float normX, normY;
    private float normDX, normDY;
    private float prevNormX, prevNormY;

    public MainController(){
        prevNormX = 0;
        prevNormY = 0;
    }

    public void setModel(ChartModel nModel) {
        ChartModel = nModel;
    }

    public void setIModel(InteractionModel nIModel) {
        IModel = nIModel;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {

        normX = event.getX() / IModel.getViewWidth();
        normY = event.getY() / IModel.getViewHeight();
        normDX = normX - prevNormX;
        normDY = normY - prevNormY;
        prevNormX = normX;
        prevNormY = normY;

        switch(currentState){
            case READY:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(ChartModel.getSelectionBox() != null){
                            // Selection Box exists
                        } else {
                            // SelectionBox does not exist;
                            ChartModel.setSelectionBox(normX, normY, 0, 0);
                        }
                        break;
                }

                break;
            case DRAGGING:
                break;
        }
        return false;
    }
}
