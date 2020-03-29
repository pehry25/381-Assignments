package com.example.linesketch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.linesketch.View.SketchView;

public class SketchMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SketchView SketchView = new SketchView(this);

        SeekBar Slider1 = new SeekBar(this);
        SeekBar Slider2 = new SeekBar(this);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);

        root.addView(SketchView);
        root.addView(Slider1);
        root.addView(Slider2);

        this.setContentView(root);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
//            case R.id.choose_chart:
//                chooseChartSelected();
//                return true;
//            case R.id.take_screenshot:
//                takeScreenshot();
//            case R.id.export_data:
//                exportData();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
