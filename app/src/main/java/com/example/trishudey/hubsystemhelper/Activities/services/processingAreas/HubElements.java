package com.example.trishudey.hubsystemhelper.Activities.services.processingAreas;
/**
 * Displays elements eg: processing areas within a hub
 */
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.main.LoginPage;
import com.example.trishudey.hubsystemhelper.Activities.services.resources.ShowResource;
import com.example.trishudey.hubsystemhelper.repositories.GetData;
import com.example.trishudey.hubsystemhelper.repositories.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HubElements extends Activity {

    ImageButton image[] = new ImageButton[9]; // each processing area is shown as an image
    CheckBox check[] = new CheckBox[9];
    TextView text[] = new TextView[9];
    public static String hub;
    public static String facility;
    public static String task;
    JSONArray jArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_elements);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        image[0] = (ImageButton) findViewById(R.id.imageButton4);
        image[1] = (ImageButton) findViewById(R.id.imageButton5);
        image[2] = (ImageButton) findViewById(R.id.imageButton6);
        image[3] = (ImageButton) findViewById(R.id.imageButton7);
        image[4] = (ImageButton) findViewById(R.id.imageButton8);
        image[5] = (ImageButton) findViewById(R.id.imageButton9);
        image[6] = (ImageButton) findViewById(R.id.imageButton10);
        image[7] = (ImageButton) findViewById(R.id.imageButton11);
        image[8] = (ImageButton) findViewById(R.id.imageButton12);

        check[0] = (CheckBox) findViewById(R.id.checkBox);
        check[1] = (CheckBox) findViewById(R.id.checkBox2);
        check[2] = (CheckBox) findViewById(R.id.checkBox3);
        check[3] = (CheckBox) findViewById(R.id.checkBox4);
        check[4] = (CheckBox) findViewById(R.id.checkBox5);
        check[5] = (CheckBox) findViewById(R.id.checkBox6);
        check[6] = (CheckBox) findViewById(R.id.checkBox7);
        check[7] = (CheckBox) findViewById(R.id.checkBox8);
        check[8] = (CheckBox) findViewById(R.id.checkBox9);

        text[0] = (TextView) findViewById(R.id.textView6);
        text[1] = (TextView) findViewById(R.id.textView7);
        text[2] = (TextView) findViewById(R.id.textView8);
        text[3] = (TextView) findViewById(R.id.textView9);
        text[4] = (TextView) findViewById(R.id.textView10);
        text[5] = (TextView) findViewById(R.id.textView11);
        text[6] = (TextView) findViewById(R.id.textView12);
        text[7] = (TextView) findViewById(R.id.textView13);
        text[8] = (TextView) findViewById(R.id.textView14);


        Intent in = getIntent();
        hub = in.getStringExtra("com.example.trishudey.MESSAGE");
        task = in.getStringExtra("task");
        facility = in.getStringExtra("facility");
        JsonParser parser = new JsonParser();
        JSONObject jsonObject[];
        final JSONObject jsonObject1[];
        jsonObject = parser.getJSONFromUrl("http://hubsystem-app.nm.flipkart.com/v1/hub/all");

        //find the hub ID of the selected hub
        String hubId = null;
        for (int i = 1; i <= jsonObject.length; i++) {
            try {
                if (hub.equals(jsonObject[i - 1].getString("name")))
                    hubId = jsonObject[i - 1].getString("hubId");
            } catch (JSONException e) {
                Log.d("Exception","JSON");
            }
        }


        try {
            //get the processing areas under the hub


            GetData gd = new GetData();
            jArray = gd.gethubElement(hubId);


            jsonObject1 = new JSONObject[jArray.length()];
            for (int i = 0; i < jArray.length(); i++) {
                jsonObject1[i] = jArray.getJSONObject(i);
            }

            Button getPA = (Button) findViewById(R.id.button8);
            final String finalHubId = hubId;
            getPA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < jArray.length(); i++) {
                        final int finalI = i;
                        image[i].setVisibility(v.VISIBLE);
                        //set different images according to type of processing area
                        try {
                            if (jsonObject1[i].getString("type").equals("SORTER")) {

                                image[i].setBackgroundResource(R.drawable.sort);
                                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(HubElements.this, R.anim.hyperspace_jump);
                                image[i].startAnimation(hyperspaceJumpAnimation);
                                text[i].setText(jsonObject1[i].getString("name"));
                            }
                            if (jsonObject1[i].getString("type").equals("BIN")) {
                                image[i].setBackgroundResource(R.drawable.bin);

                                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(HubElements.this, R.anim.hyperspace_jump);
                                image[i].startAnimation(hyperspaceJumpAnimation);
                                text[i].setText(jsonObject1[i].getString("name"));
                            }
                            if (jsonObject1[i].getString("type").equals("STAGE")) {
                                image[i].setBackgroundResource(R.drawable.stage);

                                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(HubElements.this, R.anim.hyperspace_jump);
                                image[i].startAnimation(hyperspaceJumpAnimation);
                                text[i].setText(jsonObject1[i].getString("name"));
                            }
                            if (jsonObject1[i].getString("type").equals("STATION")) {
                                image[i].setBackgroundResource(R.drawable.station);

                                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(HubElements.this, R.anim.hyperspace_jump);
                                image[i].startAnimation(hyperspaceJumpAnimation);
                                text[i].setText(jsonObject1[i].getString("name"));
                            }


                            check[i].setVisibility(v.VISIBLE);
                            //set check boxes below each image
                            //on click on a check box show children processing areas

                            final int finalI1 = i;
                            check[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                    if (check[finalI1].isChecked()) {
                                        try {
                                            if (jsonObject1[finalI].getString("mappedProcessingAreas").toString().length() - 2 == 0) {
                                                Context context = getApplicationContext();
                                                CharSequence text = "No children";
                                                int duration = Toast.LENGTH_SHORT;
                                                //Show a toast to inform the user
                                                Toast toast = Toast.makeText(context, text, duration);
                                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                                                toast.show();
                                                if (LoginPage.user.equals("admin")) {
                                                    Intent intent = new Intent(HubElements.this, AddProcessingArea.class);
                                                    intent.putExtra("hub name", hub + "+" + jsonObject1[finalI].getString("id") + "+" + finalHubId);
                                                    startActivity(intent);

                                                }


                                            } else {
                                                Intent intent = new Intent(HubElements.this, HubSubProcessingAreas.class);
                                                intent.putExtra("subarea", jsonObject1[finalI].getString("mappedProcessingAreas").toString() + "+" + jsonObject1[finalI].getString("id").toString() + "+" + finalHubId);
                                                intent.putExtra("task", task);
                                                intent.putExtra("facility",facility);
                                                startActivity(intent);

                                            }
                                        } catch (JSONException e) {
                                            Log.d("Exception","JSON");
                                        }

                                    }
                                }
                            });
                        } catch (JSONException e) {
                            Log.d("Exception", "JSON");
                        }

                    }
                    for (int i = 0; i < jArray.length(); i++) {
                        final int finalI = i;
                        image[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                try {
                                    Intent intent = new Intent(HubElements.this, ShowResource.class);
                                    intent.putExtra("hub name", hub + "+" + jsonObject1[finalI].getString("id").toString());
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                    Log.d("Exception", "JSON");
                                }


                            }
                        });
                    }

                    //if currently assigned processing areas is empty
                    //Add new processing areas
                    if (LoginPage.user.equals("admin")) {
                        for (int i = jArray.length(); i < 9; i++) {
                            final int finalI = i;
                            image[i].setVisibility(v.VISIBLE);
                            check[i].setVisibility(v.VISIBLE);
                            image[i].setBackgroundResource(R.drawable.add);

                            image[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(HubElements.this, AddProcessingArea.class);
                                    intent.putExtra("hub name", hub + "+" + "0" + "+" + finalHubId);
                                    startActivity(intent);
                                    finish();

                                }
                            });
                        }
                    } else {
                        for (int i = jArray.length(); i < 9; i++) {
                            final int finalI = i;
                            image[i].setVisibility(v.VISIBLE);
                            // check[i].setVisibility(v.VISIBLE);
                            image[i].setBackgroundResource(R.drawable.blank);

                            image[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Context context = getApplicationContext();
                                    CharSequence text = "You are not an admin";
                                    int duration = Toast.LENGTH_SHORT;
                                    //Show a toast to inform the user
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                                    toast.show();
                                }
                            });
                        }
                        for (int i = jArray.length(); i < 9; i++) {
                            final int finalI = i;
                            image[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Context context = getApplicationContext();
                                    CharSequence text = "You are not an admin";
                                    int duration = Toast.LENGTH_SHORT;
                                    //Show a toast to inform the user
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                                    toast.show();

                                }
                            });
                        }
                    }

                }
            });


        } catch (Exception e) {
            Log.d("Exception", "JSON");

        }


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hub_elements, menu);
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
