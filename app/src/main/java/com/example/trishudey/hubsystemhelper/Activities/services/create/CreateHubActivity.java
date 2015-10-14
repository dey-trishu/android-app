package com.example.trishudey.hubsystemhelper.Activities.services.create;
/**
 * creates a new hub
 */
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_Admin;
import com.example.trishudey.hubsystemhelper.requests.PostToUrl;

public class CreateHubActivity extends Activity {

    EditText name;
    EditText coc;
    Spinner type; // drop down menu
    EditText zone;
    EditText facilityServiceHubId;
    Button saveChanges;
    String Hname;
    String Hcoc;
    String Htype;
    String Hzone;
    String Hfacility;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_create_hub);
        name = (EditText) findViewById(R.id.hubname);
        coc = (EditText) findViewById(R.id.hubCOC);
        type = (Spinner) findViewById(R.id.hubType);
        zone =(EditText) findViewById(R.id.hubZone);
        facilityServiceHubId = (EditText) findViewById(R.id.hubfacility);

        String Hubtypes[] = {"Types","FCMOTHERHUB", "MPMOTHERHUB", "PICKUPHUB", "DELIVERYHUB"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Hubtypes);
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Htype = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        saveChanges = (Button) findViewById(R.id.save);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hname = name.getText().toString();
                Hcoc = coc.getText().toString();
                Hzone = zone.getText().toString();
                Hfacility = facilityServiceHubId.getText().toString();


                String url ="http://10.0.2.2:27015/v1/admin/addHub";
                String params = "&name="+Hname+"&coc="+Hcoc+"&type="+Htype+"&zone="+Hzone+"&facilityServiceHubId="+Hfacility;

                PostToUrl postToUrl = new PostToUrl();
                int code = postToUrl.post(url,params);
                    if(code == 200)
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Changes Saved";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(CreateHubActivity.this,Options_Page_Admin.class);
                        startActivity(intent);
                    }
                    else{
                        Context context = getApplicationContext();
                        CharSequence text = "Error : Check Params or try again";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(CreateHubActivity.this,Options_Page_Admin.class);
                        startActivity(intent);
                    }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_hub, menu);
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
