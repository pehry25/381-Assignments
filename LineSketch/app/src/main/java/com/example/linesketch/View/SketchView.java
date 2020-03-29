package com.example.linesketch.View;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

public class SketchView extends LinearLayout {

    public SketchView(Context aContext){
        super(aContext);


        LinearLayout root = new LinearLayout(aContext);
        root.setOrientation(LinearLayout.VERTICAL);

        this.setOrientation(LinearLayout.VERTICAL);
        this.addView(root);
        setBackgroundColor(Color.LTGRAY);

    }

}

