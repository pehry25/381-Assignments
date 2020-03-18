package com.example.chartextract.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ChartModel {

    private Bitmap UploadedChart;
    private ArrayList<ChartExtractModelListener> subscribers;
    private ArrayList<ChartPointModel> chartPointModels;
    private SelectionBoxModel SelectionBoxModel;
    private InteractionModel iModel;

    public ChartModel(){
        subscribers = new ArrayList<>();
        chartPointModels = new ArrayList<>();
        SelectionBoxModel = null;
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

    public SelectionBoxModel getSelectionBoxModel() {
        return SelectionBoxModel;
    }

    public void setSelectionBox(float nLeft, float nTop, float nWidth, float nHeight) {
        SelectionBoxModel = new SelectionBoxModel(nLeft, nTop, nWidth, nHeight);
        notifySubs();
    }

    public void resizeSelectionBox(float dx, float dy){
        SelectionBoxModel.resize(dx, dy);
        notifySubs();
    }

    public void moveSelectionBox(float dx, float dy){
        SelectionBoxModel.move(dx,dy);
        notifySubs();
    }

    public boolean checkResizeHandles(float dx, float dy, float nheight, float nwidth){
        return SelectionBoxModel.selectedResizeHandles(dx, dy, nheight, nwidth);
    }

    public boolean checkMoveHandles(float dx, float dy, float nheight, float nwidth){
        return SelectionBoxModel.selectedMoveHandle(dx, dy, nheight, nwidth);
    }

    public void addChartPoint(float dx, float dy, float nwidth, float nheight, SelectionBoxModel Box){
        this.chartPointModels.add(new ChartPointModel(dx, dy, nwidth, nheight, Box));
        notifySubs();
    }

    public int getChartPointsCount(){
        return this.chartPointModels.size();
    }

    public ArrayList<ChartPointModel> getChartPointsList(){
        return this.chartPointModels;
    }

    public ChartPointModel getLatestChartPoint(){
        return this.chartPointModels.get(chartPointModels.size()-1);
    }

    public void moveChartPoint(ChartPointModel c, float dx, float dy, SelectionBoxModel Box){
        c.move(dx, dy, Box);
        notifySubs();
    }

    public ChartPointModel findChartPoint(float x, float y) {
        ChartPointModel Point = null;

        for(ChartPointModel CP : chartPointModels){
            if(CP.contains(x,y)){
                Point = CP;
                break;
            }
        }

        return Point;
    }
}


