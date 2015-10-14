package com.example.trishudey.hubsystemhelper.Activities.services.create;
/*
This page adds a provider GOR/MANUAL
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class BusinessPartner extends Activity {

    Spinner partners; // drop down for provider type
    EditText installationId;
    EditText data;
    Button create;
    String selectedPartner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_partner);
        partners = (Spinner) findViewById(R.id.ProviderType);
        installationId = (EditText) findViewById(R.id.installationId);
        data =  (EditText) findViewById(R.id.data);
        create =(Button)findViewById(R.id.createProvider);

        //populate drop down menu
        String providerType [] ={"Provider Type","GOR","MANUAL"};
        //set adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, providerType);
        partners.setAdapter(adapter);

        //set on item selected listener
        partners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPartner = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = installationId.getText().toString();
                String data_entered = data.getText().toString();
                String url = "http://10.0.2.2:27015/v1/admin/addProvider";
                String params = "&type="+selectedPartner+"&installationId="+id+"&data="+data_entered;

                PostToUrl postToUrl = new PostToUrl();
                int code = postToUrl.post(url,params);
                    //200 OK proceed
                    if(code == 200)
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Changes Saved";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(BusinessPartner.this,Options_Page_Admin.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Error ! Check Params or Try again";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(BusinessPartner.this,Options_Page_Admin.class);
                        startActivity(intent);
                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_business_partner, menu);
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
