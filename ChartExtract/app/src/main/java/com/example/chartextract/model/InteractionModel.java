package com.example.chartextract.model;

import com.example.chartextract.view.AxisView;

import java.util.ArrayList;

public class InteractionModel {

    private ArrayList<ChartExtractModelListener> subscribers;
    private float viewWidth, viewHeight;
    private SelectionBoxModel Box;
    private ChartPointModel selectedChartPointModel;
    private AxisView AxisView;

    public InteractionModel() {
        subscribers = new ArrayList<>();
    }

    public void setViewSize(float width, float height) {
        viewWidth = width;
        viewHeight = height;
    }

    public void setAxisView(AxisView AV){
        this.AxisView = AV;
    }

    public void addSubscriber(ChartExtractModelListener subscriber) {
        subscribers.add(subscriber);
    }

    private void notifySubscribers() {
        for (ChartExtractModelListener listener : subscribers) {
            listener.modelChanged();
        }
    }

    public float getViewWidth(){
        return this.viewWidth;
    }

    public float getViewHeight(){
        return this.viewHeight;
    }

    public void setSelectionBox(SelectionBoxModel B){
        this.Box = B;
        notifySubscribers();
    }

    public SelectionBoxModel getSelectionBox(){
        return this.Box;
    }

    public void setSelectedChartPointModel(ChartPointModel P){
        this.selectedChartPointModel = P;

        if(P != null && AxisView != null) {
            AxisView.setLbl_CurrentLoc(P.getxCoord() + "," + P.getyCoord());
        } else if (P == null && AxisView != null){
            AxisView.setLbl_CurrentLoc("0,0");
        }

        notifySubscribers();
    }

    public ChartPointModel getSelectedChartPointModel(){
        return selectedChartPointModel;
    }

}
