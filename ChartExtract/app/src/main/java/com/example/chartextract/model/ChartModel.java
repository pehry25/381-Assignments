package com.example.chartextract.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ChartModel {

    private Bitmap UploadedChart;
    private ArrayList<ChartExtractModelListener> subscribers;
    private ArrayList<ChartPoint> chartPoints;
    private SelectionBox SelectionBox;

    public ChartModel(){
        subscribers = new ArrayList<>();
        chartPoints = new ArrayList<>();
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
        notifySubs();
    }

    public void resizeSelectionBox(float dx, float dy){
        SelectionBox.resize(dx, dy);
        notifySubs();
    }

    public void moveSelectionBox(float dx, float dy){
        SelectionBox.move(dx,dy);
        notifySubs();
    }

    public boolean checkResizeHandles(float dx, float dy, float nheight, float nwidth){
        return SelectionBox.selectedResizeHandles(dx, dy, nheight, nwidth);
    }

    public boolean checkMoveHandles(float dx, float dy, float nheight, float nwidth){
        return SelectionBox.selectedMoveHandle(dx, dy, nheight, nwidth);
    }

    public void addChartPoint(float dx, float dy, float nheight, float nwidth){
        this.chartPoints.add(new ChartPoint(dx, dy));
        notifySubs();
    }

    public int getChartPointsCount(){
        return this.chartPoints.size();
    }

    public ArrayList<ChartPoint> getChartPointsList(){
        return this.chartPoints;
    }

    public ChartPoint getLatestChartPoint(){
        return this.chartPoints.get(chartPoints.size()-1);
    }

    public void moveSelectedChartPoint(ChartPoint c, float dx, float dy){
        c.move(dx, dy);
        notifySubs();
    }
}
