package com.example.chartextract.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AxisView extends LinearLayout {

    private TextView lbl_Yaxis;
    private TextView lbl_Xaxis;
    private TextView lbl_ToY;
    private TextView lbl_ToX;
    private TextView lbl_CurrentLoc;

    private EditText txt_YLow;
    private EditText txt_YHigh;
    private EditText txt_XLow;
    private EditText txt_XHigh;

    private final int TXTBOXWIDTH = 125;
    private final int FONTSIZE = 16;

    public AxisView(Context aContext){
        super(aContext);

        lbl_Yaxis = new TextView(aContext);
        lbl_Yaxis.setText("   Y Axis: ");
        lbl_Yaxis.setTextSize(FONTSIZE);
        lbl_Yaxis.setTypeface(null, Typeface.BOLD);

        lbl_Xaxis = new TextView(aContext);
        lbl_Xaxis.setText("   X Axis: ");
        lbl_Xaxis.setTextSize(FONTSIZE);
        lbl_Xaxis.setTypeface(null, Typeface.BOLD);

        lbl_CurrentLoc = new TextView(aContext);
        lbl_CurrentLoc.setText("0,0");
        lbl_CurrentLoc.setTextSize(FONTSIZE+10);
        lbl_CurrentLoc.setTypeface(null, Typeface.BOLD);
        lbl_CurrentLoc.setGravity(Gravity.CENTER);
        lbl_CurrentLoc.setBackgroundColor(Color.GRAY);

        lbl_ToY = new TextView(aContext);
        lbl_ToY.setText(" to ");
        lbl_ToY.setTextSize(FONTSIZE);
        lbl_ToY.setTypeface(null, Typeface.BOLD);

        lbl_ToX = new TextView(aContext);
        lbl_ToX.setText(" to ");
        lbl_ToX.setTextSize(FONTSIZE);
        lbl_ToX.setTypeface(null, Typeface.BOLD);

        txt_YLow = new EditText(aContext);
        txt_YLow.setTextSize(FONTSIZE);
        txt_YLow.setWidth(TXTBOXWIDTH);
        txt_YLow.setGravity(Gravity.CENTER);
        txt_YLow.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        txt_YLow.setFocusable(false);
        txt_YLow.setClickable(false);
        txt_YLow.setFocusableInTouchMode(false);

        txt_YHigh = new EditText(aContext);
        txt_YHigh.setTextSize(FONTSIZE);
        txt_YHigh.setWidth(TXTBOXWIDTH);
        txt_YHigh.setGravity(Gravity.CENTER);
        txt_YHigh.setFocusable(false);
        txt_YHigh.setClickable(false);
        txt_YHigh.setFocusableInTouchMode(false);

        txt_XLow = new EditText(aContext);
        txt_XLow.setTextSize(FONTSIZE);
        txt_XLow.setWidth(TXTBOXWIDTH);
        txt_XLow.setGravity(Gravity.CENTER);
        txt_XLow.setFocusable(false);
        txt_XLow.setClickable(false);
        txt_XLow.setFocusableInTouchMode(false);

        txt_XHigh = new EditText(aContext);
        txt_XHigh.setTextSize(FONTSIZE);
        txt_XHigh.setWidth(TXTBOXWIDTH);
        txt_XHigh.setGravity(Gravity.CENTER);
        txt_XHigh.setFocusable(false);
        txt_XHigh.setClickable(false);
        txt_XHigh.setFocusableInTouchMode(false);

        LinearLayout XLayout = new LinearLayout(aContext);
        XLayout.setOrientation(LinearLayout.HORIZONTAL);
        XLayout.addView(lbl_Xaxis);
        XLayout.addView(txt_XLow);
        XLayout.addView(lbl_ToX);
        XLayout.addView(txt_XHigh);

        LinearLayout YLayout = new LinearLayout(aContext);
        YLayout.setOrientation(LinearLayout.HORIZONTAL);
        YLayout.addView(lbl_Yaxis);
        YLayout.addView(txt_YLow);
        YLayout.addView(lbl_ToY);
        YLayout.addView(txt_YHigh);

        LinearLayout root = new LinearLayout(aContext);
        root.setOrientation(LinearLayout.VERTICAL);
        root.addView(YLayout);
        root.addView(XLayout);
        root.addView(lbl_CurrentLoc);

        this.setOrientation(LinearLayout.VERTICAL);
        this.addView(root);
        setBackgroundColor(Color.LTGRAY);
    }
}
