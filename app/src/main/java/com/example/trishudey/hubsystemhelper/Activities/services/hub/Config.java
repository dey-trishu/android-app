package com.example.trishudey.hubsystemhelper.Activities.services.hub;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.requests.JsonParser;

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
    JSONArray jsonArray;
    JSONArray jsonArray1;
    JSONObject jsonObject2[];
    String startTime[];
    String endTime[];
    String time[];
    Spinner slots;
    String selectedTime;
    String hubId;
    String selectedProcessingArea;
    Spinner ProcessingAreas;
    String PA[];
    Button proceed;
    String selectedSlot;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        Intent in = getIntent();
        hub = in.getStringExtra("com.example.trishudey.MESSAGE");
        JsonParser parser = new JsonParser();
        JSONObject jsonObject[] ;
        final JSONObject jsonObject1[] ;
        jsonObject = parser.getJSONFromUrl("http://hubsystem-app.nm.flipkart.com/v1/hub/all");

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
                e.printStackTrace();
            }
        }

        TextView text1 = (TextView)findViewById(R.id.text_try);
        text1.setText("Selected Hub Facility ID : " + hubFacilityId);

        String url_slots = "http://hubsystem-app.nm.flipkart.com/v1/slots?facilityId="+hubFacilityId;
        HttpURLConnection c = null;
        try {
            URL u = new URL(url_slots);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    String json = sb.toString();
                    JSONObject jObj = new JSONObject(json);
                    //select only first child of hub to display
                    String st = jObj.getString("status");
                    if(st.equals("200"))
                    {
                        jsonArray = jObj.getJSONArray("data");
                        jsonObject1 = new JSONObject[jsonArray.length()];
                        startTime = new String[jsonArray.length()];
                        endTime =  new String[jsonArray.length()];
                        time = new String[jsonArray.length()+1];
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            jsonObject1[i] = jsonArray.getJSONObject(i);
                            startTime[i] = jsonObject1[i].getString("startTime");
                            endTime[i] = jsonObject1[i].getString("endTime");
                            time[i+1] = startTime[i] +"---"+ endTime [i];


                        }
                        time[0] = "Select time slot";
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
                                        selectedSlot = jsonObject1[position-1].getString("id");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                    else {
                        Context context = getApplicationContext();
                        CharSequence text = "No slots available";
                        int duration = Toast.LENGTH_SHORT;
                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
            }
            c.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://hubsystem-app.nm.flipkart.com/v1/hub/" + hubId+"/config?task=sortation";
        HttpURLConnection c1 = null;
        try {
            URL u1 = new URL(url);
            c1 = (HttpURLConnection) u1.openConnection();
            c1.setRequestMethod("GET");
            c1.setRequestProperty("Content-length", "0");
            c1.setUseCaches(false);
            c1.setAllowUserInteraction(false);
            c1.connect();
            int status = c1.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    InputStreamReader input = new InputStreamReader(c1.getInputStream());
                    BufferedReader br1 = new BufferedReader(input);
                    StringBuilder sb1 = new StringBuilder();
                    String line;
                    while ((line = br1.readLine()) != null) {
                        sb1.append(line + "\n");
                    }
                    br1.close();
                    String json = sb1.toString();
                    JSONObject jObj = new JSONObject(json);
                    //select only first child of hub to display
                    jsonArray1 = jObj.getJSONArray("processingAreas");
                    jsonObject2 = new JSONObject[jsonArray1.length()];
                    PA = new String[jsonArray1.length()+1];
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        jsonObject2[i] = jsonArray1.getJSONObject(i);
                        PA[i+1] = jsonObject2[i].getString("id");
                    }
                    PA[0] = "Select Processing area";
                    ProcessingAreas = (Spinner)findViewById(R.id.processingArea);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PA);
                    ProcessingAreas.setAdapter(adapter);

                    ProcessingAreas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(!((String) parent.getItemAtPosition(position)).equals("Select Processing area"))
                            {
                                selectedProcessingArea = (String) parent.getItemAtPosition(position);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        proceed = (Button)findViewById(R.id.button4);
        final String finalHubFacilityId = hubFacilityId;
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Config.this,Show_Config.class);
                intent.putExtra("com.example.trishudey.MESSAGE",hub);
                intent.putExtra("selected time",selectedSlot);
                intent.putExtra("selected pa",selectedProcessingArea);
                intent.putExtra("facility id", finalHubFacilityId);
                startActivity(intent);
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
