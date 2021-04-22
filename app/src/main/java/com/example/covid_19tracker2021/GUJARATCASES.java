package com.example.covid_19tracker2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GUJARATCASES extends AppCompatActivity {
EditText edtsearch;
ListView listview;
SimpleArcLoader simpleArcLoader;
public static List<statemodelclass> ls1=new ArrayList<>();
listadapterstate stadp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_u_j_a_r_a_t_c_a_s_e_s);

        edtsearch=(EditText)findViewById(R.id.edtSearch);
        listview=(ListView)findViewById(R.id.list_item);
        simpleArcLoader=(SimpleArcLoader)findViewById(R.id.loader2);
        getSupportActionBar().setTitle("INDIAREGION");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),districtActivity.class).putExtra("position",position));
            }
        });

        fetchthedata();
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

               stadp.getFilter().filter(s);
               stadp.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void fetchthedata() {
        String strurl="https://gist.githubusercontent.com/shubhamjain/35ed77154f577295707a/raw/7bc2a915cff003fb1f8ff49c6890576eee4f2f10/IndianStates.json";
        simpleArcLoader.start();
        StringRequest strrequest=new StringRequest(Request.Method.GET, strurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo2=new JSONObject(response.toString());
                    ls1.add(new statemodelclass(jo2.get("AN").toString(),"AN"));
                    ls1.add(new statemodelclass(jo2.get("AP").toString(),"AP"));
                    ls1.add(new statemodelclass(jo2.get("AR").toString(),"AR"));
                    ls1.add(new statemodelclass(jo2.get("AS").toString(),"AS"));
                    ls1.add(new statemodelclass(jo2.get("BR").toString(),"BR"));
                    ls1.add(new statemodelclass(jo2.get("CG").toString(),"CG"));
                    ls1.add(new statemodelclass(jo2.get("CH").toString(),"CH"));
                    ls1.add(new statemodelclass(jo2.get("DN").toString()+" and Daman and Diu","DN"));

                    ls1.add(new statemodelclass(jo2.get("DL").toString(),"DL"));
                    ls1.add(new statemodelclass(jo2.get("GA").toString(),"GA"));
                    ls1.add(new statemodelclass(jo2.get("GJ").toString(),"GJ"));
                    ls1.add(new statemodelclass(jo2.get("HR").toString(),"HR"));
                    ls1.add(new statemodelclass(jo2.get("HP").toString(),"HP"));
                    ls1.add(new statemodelclass(jo2.get("JK").toString(),"JK"));
                    ls1.add(new statemodelclass(jo2.get("JH").toString(),"JH"));
                    ls1.add(new statemodelclass(jo2.get("KA").toString(),"KA"));
                    ls1.add(new statemodelclass(jo2.get("KL").toString(),"KL"));
                    ls1.add(new statemodelclass(jo2.get("LA").toString(),"LA"));
                    ls1.add(new statemodelclass(jo2.get("LD").toString(),"LD"));
                    ls1.add(new statemodelclass(jo2.get("MP").toString(),"MP"));
                    ls1.add(new statemodelclass(jo2.get("MH").toString(),"MH"));
                    ls1.add(new statemodelclass(jo2.get("MN").toString(),"MN"));
                    ls1.add(new statemodelclass(jo2.get("ML").toString(),"ML"));
                    ls1.add(new statemodelclass(jo2.get("MZ").toString(),"MZ"));
                    ls1.add(new statemodelclass(jo2.get("NL").toString(),"NL"));
                    ls1.add(new statemodelclass(jo2.get("OR").toString(),"OR"));
                    ls1.add(new statemodelclass(jo2.get("PY").toString(),"PY"));
                    ls1.add(new statemodelclass(jo2.get("PB").toString(),"PB"));
                    ls1.add(new statemodelclass(jo2.get("RJ").toString(),"RJ"));
                    ls1.add(new statemodelclass(jo2.get("SK").toString(),"SK"));
                    ls1.add(new statemodelclass(jo2.get("TN").toString(),"TN"));
                    ls1.add(new statemodelclass(jo2.get("TS").toString(),"TS"));
                    ls1.add(new statemodelclass(jo2.get("TR").toString(),"TR"));
                    ls1.add(new statemodelclass(jo2.get("UP").toString(),"UP"));
                    ls1.add(new statemodelclass(jo2.get("UK").toString(),"UK"));
                    ls1.add(new statemodelclass(jo2.get("WB").toString(),"WB"));

                    stadp=new listadapterstate(GUJARATCASES.this,ls1);
                    listview.setAdapter(stadp);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);



                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(strrequest);

    }

    @Override
    public void onBackPressed() {
        ls1.clear();
        super.onBackPressed();
    }
}