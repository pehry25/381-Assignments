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
import com.example.chartextract.model.ChartPoint;
import com.example.chartextract.model.InteractionModel;
import com.example.chartextract.model.SelectionBox;

public class ChartView extends View implements ChartExtractModelListener {

    private Paint myPaint;
    private Canvas myCanvas;

    private ChartModel Model;
    private MainController Controller;
    private InteractionModel iModel;

    private float left, top, width, height;

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
        this.setOnTouchListener(Controller);
    }

    public void SetIModel(InteractionModel nIModel){
        this.iModel = nIModel;
    }

    public void SetChartBitmap(Bitmap nChartBitmap){ this.Model.setUploadedChart(nChartBitmap); }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        iModel.setViewSize(this.getWidth(), this.getHeight());
    }

    public void onDraw(Canvas c){
        myPaint.setStyle(Style.FILL);
        Bitmap bm_Chart = Model.getUploadedChart();

        float dLeft, dTop, dRight, dBottom;

        if(bm_Chart != null){
            Bitmap bm_Full = Bitmap.createScaledBitmap(bm_Chart, this.getWidth(), this.getHeight(), false);
            c.drawBitmap(bm_Full, 0,0,null);

            SelectionBox b = Model.getSelectionBox();
            if(b != null){

                myPaint.setStyle(Style.STROKE);
                myPaint.setColor(Color.RED);
                myPaint.setStrokeWidth(5);

                dLeft = b.getLeft() * getWidth();
                dTop = b.getTop() * getHeight();
                dRight = (b.getLeft() + b.getWidth()) * getWidth() + 100;
                dBottom = (b.getTop() + b.getHeight()) * getHeight() + 100;

                c.drawRect(dLeft, dTop, dRight, dBottom, myPaint);

                myPaint.setStyle(Style.FILL);
                // Draw 4 Circles on the ENDS of the selection box
                c.drawCircle(dLeft, dTop, 10, myPaint); // TOP LEFT
                c.drawCircle(dRight, dTop, 10, myPaint); // TOP RIGHT
                c.drawCircle(dLeft, dBottom, 10, myPaint); // BOTTOM LEFT
                c.drawCircle(dRight, dBottom, 10, myPaint); // BOTTOM RIGHT

                if(Model.getChartPointsCount() > 0){
                    myPaint.setStyle(Style.FILL_AND_STROKE);
                    for(ChartPoint CP : Model.getChartPointsList()){

                        if(CP == iModel.getSelectedChartPoint()){
                            myPaint.setColor(Color.GREEN);
                        } else {
                            myPaint.setStrokeWidth(20);
                            myPaint.setColor(Color.BLACK);
                            c.drawCircle(CP.getX() * getWidth(), CP.getY() * getHeight(), 15, myPaint);
                            myPaint.setColor(Color.YELLOW);
                        }
                        myPaint.setStrokeWidth(5);
                        c.drawCircle(CP.getX() * getWidth(), CP.getY() * getHeight(), 15, myPaint);
                    }
                }

            }
        }

    }

    public void modelChanged(){
        this.invalidate();
    }

}
