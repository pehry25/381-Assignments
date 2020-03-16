package com.example.chartextract.model;

import java.util.ArrayList;

public class InteractionModel {

    ArrayList<ChartExtractModelListener> subscribers;
    private float viewWidth, viewHeight;

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

}
