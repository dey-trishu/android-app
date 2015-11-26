package com.example.trishudey.hubsystemhelper.Activities.services.resources;
/**
 * add resources to a processing area
 * Considers the selected processing area as parent
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.services.processingAreas.HubElements;
import com.example.trishudey.hubsystemhelper.repositories.PostToUrl;


public class AddResourceContent extends Activity {

    EditText prid;
    EditText pid;
    EditText rtype;
    String prid1;
    String pid1;
    String rtype1;
    Button save;
    String hub="";
    String data;
    String PAid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resource_content);
        final Intent in = getIntent();
        int i = 0;
        data = in.getStringExtra("com.example.trishudey.MESSAGE");
        //get hub name from intent
        while(data.charAt(i)!='+')
        {

            hub = hub + data.charAt(i);
            i++;
        }
        i++;
        //get processing area id from intent
        while(i<data.length())
        {
            PAid = PAid  + data.charAt(i);
            i++;
        }

        prid = (EditText)findViewById(R.id.PRID);    //provider resource id
        pid = (EditText)findViewById(R.id.PID);     // provider id
        rtype =(EditText)findViewById(R.id.Rtype); // resource type
        save = (Button)findViewById(R.id.button13);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prid1 = prid.getText().toString();
                pid1 = pid.getText().toString();
                rtype1 = rtype.getText().toString();

                String url = "http://hubsystem-app.nm.flipkart.com/v1/admin/addResource";

                String params = "&providerResourceId="+prid1+"&providerId="+pid1+"&resourceType="+rtype1+"&processingAreaId="+PAid;
                PostToUrl postParameters = new PostToUrl();
                int code = postParameters.post(url,params);




                    if(code == 200)
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Changes Saved";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(AddResourceContent.this,HubElements.class);
                        intent.putExtra("com.example.trishudey.MESSAGE",hub);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Error ! Check params or Try again !";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(AddResourceContent.this,HubElements.class);
                        intent.putExtra("com.example.trishudey.MESSAGE",hub);
                        startActivity(intent);
                        finish();
                    }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_resource_content, menu);
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
