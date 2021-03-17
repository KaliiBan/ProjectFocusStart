package com.example.mystudyproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;


     private static final String JSON_URL = "https://www.cbr-xml-daily.ru/daily_json.js";
     ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        loadJSONFromURL(JSON_URL);
    mSwipeRefreshLayout = listView.findViewById(R.id.refresher);
        //mSwipeRefreshLayout.setOnRefreshListener(this); Выдает ошибку 

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loadJSONFromURL(JSON_URL);
                handler.postDelayed(this, 60000);
            }
        };
        handler.postDelayed(runnable, 60000) ;


}



    private void loadJSONFromURL(String url) {

final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
progressBar.setVisibility(ListView.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(ListView.INVISIBLE);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject jsonObject = object.getJSONObject("Valute");
                    ArrayList<JSONObject> listItems = new ArrayList<JSONObject>();
                    listItems.add(jsonObject.getJSONObject("AUD"));
                    listItems.add(jsonObject.getJSONObject("AZN"));
                    listItems.add(jsonObject.getJSONObject("GBP"));
                    listItems.add(jsonObject.getJSONObject("AMD"));
                    listItems.add(jsonObject.getJSONObject("BYN"));
                    listItems.add(jsonObject.getJSONObject("BGN"));
                    listItems.add(jsonObject.getJSONObject("BRL"));
                    listItems.add(jsonObject.getJSONObject("HUF"));
                    listItems.add(jsonObject.getJSONObject("HKD"));
                    listItems.add(jsonObject.getJSONObject("DKK"));
                    listItems.add(jsonObject.getJSONObject("USD"));
                    listItems.add(jsonObject.getJSONObject("EUR"));
                    listItems.add(jsonObject.getJSONObject("INR"));
                    listItems.add(jsonObject.getJSONObject("KZT"));
                    listItems.add(jsonObject.getJSONObject("CAD"));
                    listItems.add(jsonObject.getJSONObject("KGS"));
                    listItems.add(jsonObject.getJSONObject("CNY"));
                    listItems.add(jsonObject.getJSONObject("MDL"));
                    listItems.add(jsonObject.getJSONObject("NOK"));
                    listItems.add(jsonObject.getJSONObject("PLN"));
                    listItems.add(jsonObject.getJSONObject("RON"));
                    listItems.add(jsonObject.getJSONObject("XDR"));
                    listItems.add(jsonObject.getJSONObject("SGD"));
                    listItems.add(jsonObject.getJSONObject("TJS"));
                    listItems.add(jsonObject.getJSONObject("TRY"));
                    listItems.add(jsonObject.getJSONObject("TMT"));
                    listItems.add(jsonObject.getJSONObject("UZS"));
                    listItems.add(jsonObject.getJSONObject("UAH"));
                    listItems.add(jsonObject.getJSONObject("CZK"));
                    listItems.add(jsonObject.getJSONObject("CHF"));
                    listItems.add(jsonObject.getJSONObject("ZAR"));
                    listItems.add(jsonObject.getJSONObject("KRW"));
                    listItems.add(jsonObject.getJSONObject("JPY"));

                    ListAdapter adapter = new ListViewAdapter(getApplicationContext(),R.layout.row,R.id.textViewName,listItems);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(false);
                loadJSONFromURL(JSON_URL);
            }
        }, 100);
    }
}
