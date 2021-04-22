package com.example.covid_19tracker2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvcases,tvtodaydeath,tvcritical,tvactive,todaycases,recovered;
    ScrollView sl;
    PieChart p1;
    SimpleArcLoader sl1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvcases=(TextView)findViewById(R.id.tvaffectedcases);
        tvtodaydeath=(TextView)findViewById(R.id.tvtotaldeath);
        tvcritical=(TextView)findViewById(R.id.tvcritical);
        tvactive=(TextView)findViewById(R.id.tvactive);
        todaycases=(TextView)findViewById(R.id.tvtotalcases);
        sl=(ScrollView)findViewById(R.id.scrolls);
        p1=(PieChart)findViewById(R.id.piechart);
        sl1=(SimpleArcLoader)findViewById(R.id.loader);
        recovered=(TextView)findViewById(R.id.tvrecoverd);

        fetch();
    }

    private void fetch() {
        String strurl="https://corona.lmao.ninja/v2/all";
        StringRequest strrequest=new StringRequest(Request.Method.GET, strurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo1=new JSONObject(response.toString());
                    tvcases.setText(jo1.get("cases").toString());
                    tvtodaydeath.setText(jo1.get("todayDeaths").toString());
                    tvcritical.setText(jo1.get("critical").toString());
                    tvactive.setText(jo1.get("active").toString());
                    todaycases.setText(jo1.get("todayCases").toString());
                    recovered.setText(jo1.get("recovered").toString());

                    p1.addPieSlice(new PieModel("Cases",Integer.parseInt(tvcases.getText().toString()), Color.parseColor("#FFA726")));
                    p1.addPieSlice(new PieModel("Recoverd",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#66BB6A")));
                    p1.addPieSlice(new PieModel("Deaths",Integer.parseInt(jo1.get("deaths").toString()), Color.parseColor("#EF5350")));
                    p1.addPieSlice(new PieModel("Active",Integer.parseInt(tvactive.getText().toString()), Color.parseColor("#29B6F6")));
                    p1.startAnimation();
sl1.stop();
                    sl1.setVisibility(View.GONE);

                    sl.setVisibility(View.VISIBLE);



                } catch (JSONException e) {
                    e.printStackTrace();
                    sl1.stop();
                    sl1.setVisibility(View.GONE);

                    sl.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sl1.stop();
                sl1.setVisibility(View.GONE);

                sl.setVisibility(View.VISIBLE);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(strrequest);
    }

    public void gotrackfunction(View view) {
        startActivity(new Intent(MainActivity.this,GUJARATCASES.class));
    }

    public void gotocasewise(View view) {
        startActivity(new Intent(MainActivity.this,covidcasesindia.class));
    }
}