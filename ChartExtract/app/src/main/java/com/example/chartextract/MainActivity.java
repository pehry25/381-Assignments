package com.example.chartextract;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chartextract.controller.MainController;
import com.example.chartextract.model.ChartModel;
import com.example.chartextract.model.InteractionModel;
import com.example.chartextract.view.AxisView;
import com.example.chartextract.view.ChartView;
import com.example.chartextract.view.DetailView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AxisView AxisView;
    private ChartView ChartView;
    private DetailView DetailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AxisView = new AxisView(this);
        ChartView = new ChartView(this);
        DetailView = new DetailView(this);

        ChartModel ChartModel = new ChartModel();
        InteractionModel IModel = new InteractionModel();

        MainController Controller = new MainController();

        DetailView.SetController(Controller);
        DetailView.SetIModel(IModel);


        LinearLayout root = findViewById(R.id.root);

        root.addView(DetailView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO Code here to handle options selection on the Main Menu
        //Handlers for this go HERE. Not controlelr!

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.choose_chart:
                chooseChartSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void chooseChartSelected(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select a chart"), 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK) {
            // do something with the selected image
            if(this.ChartView != null){
                try {
                    Uri selectedImage = data.getData(); //The uri with the location of the image
                    Bitmap nBitmap = MediaStore.Images.Media.getBitmap(this.getBaseContext().getContentResolver(), selectedImage);
                    ChartView.SetChartBitmap(nBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
