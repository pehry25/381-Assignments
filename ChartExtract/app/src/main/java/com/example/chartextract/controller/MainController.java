package com.example.chartextract.controller;

import android.view.MotionEvent;
import android.view.View;

import com.example.chartextract.model.ChartModel;
import com.example.chartextract.model.ChartPointModel;
import com.example.chartextract.model.InteractionModel;

public class MainController implements View.OnTouchListener {

    private enum State {READY, CREATING_SELBOX, DRAGGING_SELBOX, CREATING_POINT, MOVING_POINT, MOVING_SELBOX}

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

    public void setChartModel(ChartModel nModel) {
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

                        if(ChartModel.getSelectionBoxModel() == null){

                            // User has tapped on ChartModel
                            ChartModel.setSelectionBox(normX, normY,0,0);
                            IModel.setSelectionBox(ChartModel.getSelectionBoxModel());

                            currentState = State.CREATING_SELBOX;
                        } else {
                            // Check to see if user has touched down on a handler or a Chart Point
                            if(ChartModel.checkResizeHandles(normX, normY, IModel.getViewWidth(), IModel.getViewHeight())){
                                currentState = State.DRAGGING_SELBOX;
                            } else if (ChartModel.checkMoveHandles(normX, normY, IModel.getViewWidth(), IModel.getViewHeight())) {
                                currentState = State.MOVING_SELBOX;
                            } else if (ChartModel.findChartPoint(normX, normY) != null){
                                ChartPointModel CP = ChartModel.findChartPoint(normX, normY);
                                IModel.setSelectedChartPointModel(CP);
                                currentState = State.MOVING_POINT;
                            } else {
                                // Create chart point
                                ChartModel.addChartPoint(normX, normY,  IModel.getViewWidth(), IModel.getViewHeight(), IModel.getSelectionBox());
                                IModel.setSelectedChartPointModel(ChartModel.getLatestChartPoint());

                                currentState = State.CREATING_POINT;
                            }
                        }

                        break;
                }

                break;
            case CREATING_SELBOX:

                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        IModel.setSelectedChartPointModel(null);
                        currentState = State.READY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ChartModel.resizeSelectionBox(normDX, normDY);
                        currentState = State.CREATING_SELBOX;
                        break;
                }

                break;

            case MOVING_SELBOX:
                switch(event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        ChartModel.moveSelectionBox(normDX, normDY);
                        currentState = State.MOVING_SELBOX;
                        break;
                    case MotionEvent.ACTION_UP:
                        IModel.setSelectedChartPointModel(null);
                        currentState = State.READY;
                        break;
                }

                break;

            case DRAGGING_SELBOX:
                switch(event.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        ChartModel.resizeSelectionBox(normDX, normDY);
                        currentState = State.DRAGGING_SELBOX;
                        break;
                    case MotionEvent.ACTION_UP:
                        currentState = State.READY;
                        break;
                }

                break;

            case CREATING_POINT:

                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        IModel.setSelectedChartPointModel(null);
                        currentState = State.READY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ChartModel.moveChartPoint(IModel.getSelectedChartPointModel(), normDX, normDY, IModel.getSelectionBox());
                        IModel.setSelectedChartPointModel(IModel.getSelectedChartPointModel());
                        currentState = State.CREATING_POINT;
                        break;
                }

                break;

            case MOVING_POINT:
                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        IModel.setSelectedChartPointModel(null);
                        currentState = State.READY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ChartModel.moveChartPoint(IModel.getSelectedChartPointModel(), normDX, normDY, IModel.getSelectionBox());
                        IModel.setSelectedChartPointModel(IModel.getSelectedChartPointModel());
                        currentState = State.MOVING_POINT;
                        break;
                }
        }
        return true;
    }
}
