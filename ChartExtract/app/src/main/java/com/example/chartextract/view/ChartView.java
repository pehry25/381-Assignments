package com.example.chartextract.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import com.example.chartextract.controller.MainController;
import com.example.chartextract.model.ChartModel;
import com.example.chartextract.model.InteractionModel;

public class ChartView extends View {

    private Uri SelectedChart;
    private Paint myPaint;
    private Canvas myCanvas;
    private Bitmap ChartBitmap;

    private ChartModel Model;
    private MainController Controller;
    private InteractionModel iModel;

    public ChartView(Context aContext){
        super(aContext);
        myPaint = new Paint();
        myCanvas = new Canvas();

    }

    public void SetModel(ChartModel nModel){
        this.Model = nModel;
    }

    public void SetController(MainController nController){
        this.Controller = nController;
    }

    public void SetIModel(InteractionModel nIModel){
        this.iModel = nIModel;
    }

    public Bitmap GetChartBitmap(){
        return this.ChartBitmap;
    }

    public void SetChartBitmap(Bitmap nChartBitmap){
        this.ChartBitmap = nChartBitmap;
    }

    public void onDraw(){

    }

    public void modelChanged(){
        this.invalidate();
    }

}
