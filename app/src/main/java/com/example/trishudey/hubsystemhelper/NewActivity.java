package com.example.trishudey.hubsystemhelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;



//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abishekkrishnan.hubsystemhelper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by trishu.dey on 26/08/15.
 */
public class NewActivity extends Activity {

    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    public static TextView name;
    public Button Btngetdata;

    //URL to get JSON Array
    public String url = "http://10.0.2.2:27015/";

    public static String TAG_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        name = (TextView) findViewById(R.id.name);
        Intent intent = getIntent();
        //     setContentView(R.layout.activity_main);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:27015/";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        String x = response;
                        name.setText("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                name.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
//        Btngetdata = (Button)findViewById(R.id.getdata);
//        Btngetdata.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                new JSONParse().execute();
//            }
//
//        });
//    }
    }


    class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //setContentView(R.layout.new_activity);


            //   pDialog = new ProgressDialog(NewActivity.this);


        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JsonParser jParser = new JsonParser();

            // Getting JSON from URL
            JSONObject json = null;


            json = jParser.getJSONFromUrl("http://10.0.2.2:27015/");

            return json;
        }


        @Override
        protected void onPostExecute(final JSONObject json) {
            //    pDialog.dismiss();


            //Your code goes here
            //TextView name1 =(TextView)findViewById(R.id.name);
            String name = null;
            try {
                name = json.getString(NewActivity.TAG_NAME);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(name);
            NewActivity.name.setText("Welcome  " + name);


        }
    }
}

