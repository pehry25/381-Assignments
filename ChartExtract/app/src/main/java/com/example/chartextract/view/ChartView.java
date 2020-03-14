package com.example.chartextract.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import com.example.chartextract.controller.MainController;
import com.example.chartextract.model.ChartExtractModelListener;
import com.example.chartextract.model.ChartModel;
import com.example.chartextract.model.InteractionModel;

public class ChartView extends View implements ChartExtractModelListener {

    private Paint myPaint;
    private Canvas myCanvas;

    private ChartModel Model;
    private MainController Controller;
    private InteractionModel iModel;

    public ChartView(Context aContext){
        super(aContext);
        myPaint = new Paint();
        myCanvas = new Canvas();
        setBackgroundColor(Color.YELLOW);
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

    public void SetChartBitmap(Bitmap nChartBitmap){ this.Model.setUploadedChart(nChartBitmap); }

    public void onDraw(Canvas c){
        myPaint.setStyle(Style.FILL);
        Bitmap bm_Chart = Model.getUploadedChart();

        if(bm_Chart != null){
            Bitmap bm_Full = Bitmap.createScaledBitmap(bm_Chart, this.getWidth(), this.getHeight(), false);
            c.drawBitmap(bm_Full, 0,0,null);
        }

    }

    public void modelChanged(){
        this.invalidate();
    }

}
