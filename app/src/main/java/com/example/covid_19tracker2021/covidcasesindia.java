package com.example.covid_19tracker2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class covidcasesindia extends AppCompatActivity {
RecyclerView rs1;
SimpleArcLoader sml1;
NewsAdapter news_adapter;
List<newsmodelclass> nwpc=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidcasesindia);
        rs1=(RecyclerView)findViewById(R.id.recycled);
        sml1=(SimpleArcLoader)findViewById(R.id.loader_news);

        rs1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));


        fetchdatatostore();

        //start the loader
        //store the data in model class
        //then pass this model dta to recyclerview
        //which store all the data to its right position
        //then end the loader
    }

    private void fetchdatatostore() {
        sml1.start();
        String strurl="https://newsapi.org/v2/top-headlines?country=in&q=covid&apiKey=87142d23bf63479f8a2562c38506cc17";
    //request data
        StringRequest stringRequest=new StringRequest(Request.Method.GET, strurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //convert string to Json Object
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    //get Source array from the object
                    JSONArray jsonArray=jsonObject.getJSONArray("articles");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject js1_sourcemain=jsonArray.getJSONObject(i);
                        String title=js1_sourcemain.getString("title");
                        String descreption=js1_sourcemain.getString("description");
                        String urlphoto=js1_sourcemain.getString("urlToImage");
                        String date=js1_sourcemain.getString("publishedAt");
                        String Content=js1_sourcemain.getString("content");
                        JSONObject source_json=js1_sourcemain.getJSONObject("source");
                        String source=source_json.getString("name");
                        String date_actualldata=date.substring(1,11);
                        nwpc.add(new newsmodelclass(title,source,date_actualldata,descreption,urlphoto,Content));
                    }
                    //setup adapter
                    news_adapter=new NewsAdapter(covidcasesindia.this,nwpc);
                    //set adapter to recycler view
                    rs1.setAdapter(news_adapter);
                    sml1.stop();
                    sml1.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        })
        {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("key", "Value");
                return headers;
            }
        };
        //add request to queue
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}