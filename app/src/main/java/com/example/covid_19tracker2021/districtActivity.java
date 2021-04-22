package com.example.covid_19tracker2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class districtActivity extends AppCompatActivity {
    EditText edtsearchd;
    ListView listviewd;
    SimpleArcLoader simpleArcLoaderdd;
    TextView tvcasesd,tvactived,deathcased,recoveredd;
    TextView districtview;
    ScrollView s1_main,s2;
    PieChart p1d;
   int statetotalcases,statetotalActivecases,statetotaldeaths,statetotalrecovered;
public static List<Modelclass> ml1=new ArrayList<>();
listadapter ldp1;
public int positionstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);

        edtsearchd=(EditText)findViewById(R.id.edtSearchdistrict);
        listviewd=(ListView)findViewById(R.id.list_item_district);

        tvcasesd=(TextView)findViewById(R.id.d2tvaffectedcasesdistrict);
        deathcased=(TextView)findViewById(R.id.d2tvdeathdistrict);

        tvactived=(TextView)findViewById(R.id.d2tvactivedistrict);
        s1_main=(ScrollView)findViewById(R.id.scrollsdistrictmain);
        s2=(ScrollView)findViewById(R.id.scrollsdistrict);
        p1d=(PieChart)findViewById(R.id.piechartdistrict);
        simpleArcLoaderdd=(SimpleArcLoader)findViewById(R.id.loaderdistrict);
        recoveredd=(TextView)findViewById(R.id.d2tvrecoverddistrict);
        districtview=(TextView)findViewById(R.id.d2_main_district);

        Intent i1=getIntent();
        positionstate=i1.getIntExtra("position",0);
        districtview.setText(GUJARATCASES.ls1.get(positionstate).getStates());
        
        fetchdistrictdata();
        getSupportActionBar().setTitle("search for ditrict wise data");

        listviewd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(districtActivity.this,lastdataentry.class).putExtra("position2",position));
            }
        });
        edtsearchd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ldp1.getFilter().filter(s);
                    ldp1.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void fetchdistrictdata() {
        String state=GUJARATCASES.ls1.get(positionstate).getStates();
            String strurl="https://api.covid19india.org/state_district_wise.json";
            simpleArcLoaderdd.start();
            StringRequest strrequest=new StringRequest(Request.Method.GET, strurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject js1=new JSONObject(response.toString());
                        JSONObject jsmain=js1.getJSONObject(state).getJSONObject("districtData");


                        Iterator<String> keys = jsmain.keys();
                        while(keys.hasNext()){
                            String actuallDIstrict=(String)keys.next();
                         // Log.i("state",actuallDIstrict);
                            int dtotalcases=jsmain.getJSONObject(actuallDIstrict).getInt("confirmed");
                            int drecovered=jsmain.getJSONObject(actuallDIstrict).getInt("recovered");
                            int ddeaths=jsmain.getJSONObject(actuallDIstrict).getInt("deceased");
                            int dactivecases=jsmain.getJSONObject(actuallDIstrict).getInt("active");
                            ml1.add(new Modelclass(actuallDIstrict,dtotalcases,drecovered,dactivecases,ddeaths));
                           statetotalActivecases=statetotalActivecases+dactivecases;
                           statetotalcases=statetotalcases+dtotalcases;
                           statetotaldeaths=statetotaldeaths+ddeaths;
                           statetotalrecovered=statetotalrecovered+drecovered;


                            //

                        }


                        ldp1=new listadapter(districtActivity.this,ml1);
                        listviewd.setAdapter(ldp1);

                        p1d.addPieSlice(new PieModel("Cases",Integer.parseInt(String.valueOf(statetotalcases)), Color.parseColor("#FFA726")));
                        p1d.addPieSlice(new PieModel("Recoverd",Integer.parseInt(String.valueOf(statetotalrecovered)), Color.parseColor("#66BB6A")));
                        p1d.addPieSlice(new PieModel("Deaths",Integer.parseInt(String.valueOf(statetotaldeaths)), Color.parseColor("#EF5350")));
                        p1d.addPieSlice(new PieModel("Active",Integer.parseInt(String.valueOf(statetotalActivecases)), Color.parseColor("#29B6F6")));
                        p1d.startAnimation();

                        tvactived.setText(String.valueOf(statetotalActivecases));
                        tvcasesd.setText(String.valueOf(statetotalcases));
                        recoveredd.setText(String.valueOf(statetotalrecovered));
                        deathcased.setText(String.valueOf(statetotaldeaths));

                        simpleArcLoaderdd.stop();
                        simpleArcLoaderdd.setVisibility(View.GONE);


                      
                        //want to add key s first
                        //then keys data
                        //then add state wise data api
                        //then add click listener of listview
                        //then create new view for the same
                        //and then show in piechart manner
                        //program completed




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(strrequest);
        }

    @Override
    public void onBackPressed() {
        ml1.clear();
        super.onBackPressed();
    }
}