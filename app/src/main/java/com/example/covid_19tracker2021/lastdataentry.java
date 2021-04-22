package com.example.covid_19tracker2021;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class lastdataentry extends AppCompatActivity {
    SimpleArcLoader lsimpleArcLoaderdd;
    TextView ltvcasesd,ltvactived,ldeathcased,lrecoveredd;
   TextView ldistricname;
    PieChart lp1d;
   int simplepositiion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lastdataentry);
        lp1d=(PieChart)findViewById(R.id.lastpiechartdistrict);
        lsimpleArcLoaderdd=(SimpleArcLoader)findViewById(R.id.lastloaderdistrict);
        ltvactived=(TextView)findViewById(R.id.lasttvactivedistrict);
        ltvcasesd=(TextView)findViewById(R.id.lasttvaffectedcasesdistrict);
        ldeathcased=(TextView)findViewById(R.id.lasttvdeathdistrict);
        lrecoveredd=(TextView)findViewById(R.id.lasttvrecoverddistrict);
        ldistricname=(TextView)findViewById(R.id.last_main_district);

        Intent lastdata=getIntent();
        simplepositiion=lastdata.getIntExtra("position2",0);
        lsimpleArcLoaderdd.start();
        getSupportActionBar().setTitle(" DistrictWise data");

        ltvactived.setText(String.valueOf(districtActivity.ml1.get(simplepositiion).getActivecases()));
        ltvcasesd.setText(String.valueOf(districtActivity.ml1.get(simplepositiion).getTotalcases()));
        ldeathcased.setText(String.valueOf(districtActivity.ml1.get(simplepositiion).getDeathcases()));
        lrecoveredd.setText(String.valueOf(districtActivity.ml1.get(simplepositiion).getRecoveredcases()));
        ldistricname.setText(String.valueOf(districtActivity.ml1.get(simplepositiion).getDistrictname()));

        lp1d.addPieSlice(new PieModel("Cases",Integer.parseInt(ltvcasesd.getText().toString()), Color.parseColor("#FFA726")));
        lp1d.addPieSlice(new PieModel("Recoverd",Integer.parseInt(lrecoveredd.getText().toString()), Color.parseColor("#66BB6A")));
        lp1d.addPieSlice(new PieModel("Deaths",Integer.parseInt(ldeathcased.getText().toString()), Color.parseColor("#EF5350")));
        lp1d.addPieSlice(new PieModel("Active",Integer.parseInt(ltvactived.getText().toString()), Color.parseColor("#29B6F6")));
        lp1d.startAnimation();

        lsimpleArcLoaderdd.stop();
        lsimpleArcLoaderdd.setVisibility(View.GONE);







    }
}