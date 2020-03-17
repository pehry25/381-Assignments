package com.example.chartextract.model;

import java.util.ArrayList;

public class InteractionModel {

    private ArrayList<ChartExtractModelListener> subscribers;
    private float viewWidth, viewHeight;
    private SelectionBox Box;
    private ChartPoint SelectedChartPoint;

    public InteractionModel() {
        subscribers = new ArrayList<>();
    }

    public void setViewSize(float width, float height) {
        viewWidth = width;
        viewHeight = height;
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

    public void setSelectionBox(SelectionBox B){
        this.Box = B;
        notifySubscribers();
    }

    public void setSelectedChartPoint(ChartPoint P){
        this.SelectedChartPoint = P;
        notifySubscribers();
    }

    public ChartPoint getSelectedChartPoint(){
        return SelectedChartPoint;
    }

}
