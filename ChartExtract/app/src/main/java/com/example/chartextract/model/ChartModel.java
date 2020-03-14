package com.example.chartextract.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ChartModel {

    private Bitmap UploadedChart;
    ArrayList<ChartExtractModelListener> subscribers;

    public ChartModel(){
        subscribers = new ArrayList<>();
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
}
