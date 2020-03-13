package com.example.chartextract.view;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chartextract.R;
import com.example.chartextract.controller.MainController;
import com.example.chartextract.model.ChartModel;
import com.example.chartextract.model.InteractionModel;


public class DetailView extends View {

    private ChartModel Model;
    private MainController Controller;
    private InteractionModel iModel;

    private TextView lbl_Yaxis;
    private TextView lbl_XAxis;
    private TextView lbl_To;
    private EditText txt_YAxisLow;
    private EditText txt_YAxisHigh;
    private EditText txt_XAxisLow;
    private EditText txt_XAxisHigh;

    public DetailView(Context aContext) {
        super(aContext);

        lbl_Yaxis = new TextView(aContext);
        lbl_Yaxis.setText("Y Axis: ");

        lbl_XAxis = new TextView(aContext);
        lbl_XAxis.setText("X Axis: ");

        lbl_To = new TextView(aContext);
        lbl_To.setText(" to ");

        txt_YAxisLow = new EditText(aContext);
        txt_YAxisHigh = new EditText(aContext);

        txt_XAxisLow = new EditText(aContext);
        txt_XAxisHigh = new EditText(aContext);

        LinearLayout root = findViewById(R.id.detailview);
        LinearLayout XRow = findViewById(R.id.detailview);
        LinearLayout YRow = findViewById(R.id.detailview);

        XRow.addView(lbl_XAxis);
        XRow.addView(txt_XAxisLow);
        XRow.addView(lbl_To);
        XRow.addView(txt_XAxisHigh);

        YRow.addView(lbl_Yaxis);
        YRow.addView(txt_YAxisLow);
        YRow.addView(lbl_To);
        YRow.addView(txt_YAxisHigh);


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

    public void onDraw(){

    }

    public void modelChanged(){
        this.invalidate();
    }
}
