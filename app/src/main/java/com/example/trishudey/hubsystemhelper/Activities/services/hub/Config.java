package com.example.trishudey.hubsystemhelper.Activities.services.hub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.repositories.GetData;
import com.example.trishudey.hubsystemhelper.repositories.JsonGetResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Config extends Activity {

    String hub;


    String time[];
    Spinner slots;
    String selectedTime;
    String hubId;
    String selectedProcessingArea;
    Spinner ProcessingAreas;
    String PA[];
    Button proceed;
    String selectedSlot;
    public static String[] PAID;
    String Idhub;
    GetData gd = new GetData();
    int PAposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        Intent in = getIntent();
        hub = in.getStringExtra("com.example.trishudey.MESSAGE");
        Idhub = in.getStringExtra("hubId");

        JSONObject jsonObject[];
        jsonObject = gd.getallHubs();
        final JsonGetResponse jsonGetResponse = new JsonGetResponse();


        //find the hub ID of the selected hub
        String hubFacilityId = null;
        for(int i=1;i<=jsonObject.length;i++)
        {
            try {
                if(hub.equals(jsonObject[i - 1].getString("name")))
                {
                    hubFacilityId = jsonObject[i-1].getString("facilityServiceId");
                    hubId = jsonObject[i-1].getString("hubId");
                    break;
                }

            } catch (JSONException e) {
                Log.d("Expection","JSON");
            }
        }

        TextView text1 = (TextView)findViewById(R.id.text_try);
        text1.setText("Selected Hub Facility ID : " + hubFacilityId);

        time = gd.getSlots(hubFacilityId);




                        slots = (Spinner)findViewById(R.id.slot);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, time);
                        slots.setAdapter(adapter);
                        slots.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                if(!((String) parent.getItemAtPosition(position)).equals("Select time slot"))
                                {
                                    selectedTime = (String) parent.getItemAtPosition(position);
                                    try {
                                        selectedSlot = jsonGetResponse.jsonObject1[position-1].getString("id");
                                    } catch (JSONException e) {
                                        Log.d("EXception","JSON");
                                    }

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });





        PA = gd.getPA(hubId);





                    PA[0] = "Select Processing area";
                    ProcessingAreas = (Spinner)findViewById(R.id.processingArea);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PA);
                    ProcessingAreas.setAdapter(adapter1);

                    ProcessingAreas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(!((String) parent.getItemAtPosition(position)).equals("Select Processing area"))
                            {
                                selectedProcessingArea = PAID[position];
                                PAposition = position-1;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });




        proceed = (Button)findViewById(R.id.button4);
        final String finalHubFacilityId = hubFacilityId;
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Config.this,D3.class);
                intent.putExtra("com.example.trishudey.MESSAGE",hub);
                intent.putExtra("selected time",selectedSlot);
                intent.putExtra("selected pa",selectedProcessingArea);
                intent.putExtra("facility id", finalHubFacilityId);
                intent.putExtra("hubId",Idhub);
                intent.putExtra("position",PAposition);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
