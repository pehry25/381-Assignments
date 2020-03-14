package com.example.chartextract;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
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
    private MainController Controller;

    private final int PIXELWIDTH = 1080;
    private final int TOPROWHEIGHT = 325;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AxisView = new AxisView(this);
        InteractionModel IModel = new InteractionModel();

        // CHART VIEW \\
        ChartView = new ChartView(this);
        ChartModel ChartModel = new ChartModel();
        this.Controller = new MainController();

        ChartView.SetController(this.Controller);
        ChartView.SetModel(ChartModel);
        ChartModel.addSubscriber(ChartView);
        IModel.addSubscriber(ChartView);

        // DETAIL VIEW \\
        DetailView = new DetailView(this);
        DetailView.SetController(this.Controller);
        DetailView.SetModel(ChartModel);
        ChartModel.addSubscriber(DetailView);
        IModel.addSubscriber(DetailView);



        // ADD VIEWS TO LAYOUT \\
        LinearLayout TopRow = new LinearLayout(this);
        TopRow.setOrientation(LinearLayout.HORIZONTAL);
        TopRow.addView(DetailView, new LinearLayout.LayoutParams(
                PIXELWIDTH/2,
                TOPROWHEIGHT));
        TopRow.addView(AxisView, new LinearLayout.LayoutParams(
                PIXELWIDTH/2,
                TOPROWHEIGHT));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.addView(TopRow);
        root.addView(ChartView);

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
        //TODO Code here to handle options selection on the Main Menu
        //Handlers for this go HERE. Not controlelr!

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.choose_chart:
                chooseChartSelected();
                return true;
            case R.id.take_screenshot:
                return super.onOptionsItemSelected(item);
            case R.id.export_data:
                return super.onOptionsItemSelected(item);
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
