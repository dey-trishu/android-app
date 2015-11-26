package com.example.trishudey.hubsystemhelper.Activities.services.hub;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.repositories.JsonParser;

import org.json.JSONObject;

public class Facilities extends Activity {
    EditText text ;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);

        text = (EditText)findViewById(R.id.editText7);
        ok = (Button)findViewById(R.id.button16);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = text.getText().toString();
                String url = "http://ekl-facilities.nm.flipkart.com/facilities?id=" + id;
                JsonParser parser = new JsonParser();
                JSONObject jsonObject[];
                jsonObject = parser.getJSONFromUrl(url);
                Intent intent = new Intent(Facilities.this,Show_Options_Facilities.class);
                intent.putExtra("facility",jsonObject);
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facilities, menu);
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
