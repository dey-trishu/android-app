package com.example.trishudey.hubsystemhelper.Activities.services.resources;
/*
    This class shows the resources that are already assigned to a processing area.
    It is shown on click of the image that represents the processing area.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.main.LoginPage;
import com.example.trishudey.hubsystemhelper.Activities.services.processingAreas.HubElements;
import com.example.trishudey.hubsystemhelper.requests.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowResource extends Activity {

    String hub="";
    String PAid="";
    TextView r[] = new TextView[20];
    Button button,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resource);
        Intent intent = getIntent();
        String data = intent.getStringExtra("hub name");
        int i = 0;
        //get the name of the hub from the intent
        while(data.charAt(i)!='+')
        {

                hub = hub + data.charAt(i);
            i++;
        }
        i++;
        // get processing area id from intent
        while(i<data.length())
        {
            PAid = PAid  + data.charAt(i);
            i++;
        }

        r[0] = (TextView)findViewById(R.id.resource1);
        r[1] = (TextView)findViewById(R.id.resource2);
        r[2] = (TextView)findViewById(R.id.resource3);
        r[3] = (TextView)findViewById(R.id.resource4);
        r[4] = (TextView)findViewById(R.id.resource5);
        r[5] = (TextView)findViewById(R.id.resource6);
        r[6] = (TextView)findViewById(R.id.resource7);
        r[7] = (TextView)findViewById(R.id.resource8);
        r[8] = (TextView)findViewById(R.id.resource9);
        r[9] = (TextView)findViewById(R.id.resource10);
        r[10] = (TextView)findViewById(R.id.resource11);
        r[11] = (TextView)findViewById(R.id.resource12);
        r[12] = (TextView)findViewById(R.id.resource13);
        r[13] = (TextView)findViewById(R.id.resource14);
        r[14] = (TextView)findViewById(R.id.resource15);
        r[15] = (TextView)findViewById(R.id.resource16);
        r[16] = (TextView)findViewById(R.id.resource17);
        r[17] = (TextView)findViewById(R.id.resource18);
        r[18] = (TextView)findViewById(R.id.resource19);
        r[19] = (TextView)findViewById(R.id.resource20);
        button = (Button)findViewById(R.id.button11);
        add = (Button)findViewById(R.id.button12);


        JsonParser jParser = new JsonParser();

        // Getting JSON from URL
        final JSONObject jsonObject[] ;
        jsonObject = jParser.getJSONFromUrl("http://hubsystem-app.nm.flipkart.com/v1/admin/getResources/"+PAid);

        //if there are no resources assigned to a processing area
        if(jsonObject.length == 0)
        {
            Context context = getApplicationContext();
            CharSequence text = "No resources assigned here!";
            int duration = Toast.LENGTH_SHORT;
            //Show a toast to inform the user
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
            toast.show();
        }
        for(int j=0;j<jsonObject.length;j++)
        {
            try {
                //show the name of the resource
                r[j].setText(jsonObject[j].getString("type") + " " + jsonObject[j].getString("id"));
                r[j].setPaintFlags(r[j].getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                final int finalJ = j;
                r[j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(LoginPage.user.equals("admin"))
                        {
                            Intent in = new Intent(ShowResource.this,MoveResource.class);
                            try {
                                in.putExtra("com.example.trishudey.MESSAGE",hub+"+"+PAid+"+"+jsonObject[finalJ].getString("id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            startActivity(in);
                        }
                        else {
                            Context context = getApplicationContext();
                            CharSequence text = "You are not an admin";
                            int duration = Toast.LENGTH_SHORT;
                            //Show a toast to inform the user
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        }

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //on button click go back to previous page
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ShowResource.this,HubElements.class);
                in.putExtra("com.example.trishudey.MESSAGE",hub);
                startActivity(in);
                finish();
            }
        });
        //add a new resource
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginPage.user.equals("admin"))
                {
                    Intent in = new Intent(ShowResource.this,AddResourceContent.class);
                    in.putExtra("com.example.trishudey.MESSAGE",hub+"+"+PAid);
                    startActivity(in);
                    finish();
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_resource, menu);
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
