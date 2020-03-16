package com.example.chartextract.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ChartModel {

    private Bitmap UploadedChart;
    private ArrayList<ChartExtractModelListener> subscribers;
    private SelectionBox SelectionBox;

    public ChartModel(){
        subscribers = new ArrayList<>();
        SelectionBox = null;
    }

    public void setUploadedChart(Bitmap nChart){
        this.UploadedChart = nChart;

        if(nChart != null) {
            notifySubs();
        }
    }

    public Bitmap getUploadedChart() {
        return this.UploadedChart;
    }

    public void addSubscriber(ChartExtractModelListener subscriber) {
        subscribers.add(subscriber);
    }

    private void notifySubs(){
        for(ChartExtractModelListener listener : subscribers){
            listener.modelChanged();
        }
    }

    public SelectionBox getSelectionBox() {
        return SelectionBox;
    }

    public void setSelectionBox(float nLeft, float nTop, float nWidth, float nHeight) {
        SelectionBox = new SelectionBox(nLeft, nTop, nWidth, nHeight);
    }
}
