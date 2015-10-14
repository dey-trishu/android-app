package com.example.trishudey.hubsystemhelper.Activities.services.service;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.requests.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetService extends Activity {

    Spinner drop;
    EditText name;
    Button ok;
    JSONObject jsonObject[];
    JSONArray jArray;
    JSONObject jObj[];
    TextView text;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_service);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        name = (EditText)findViewById(R.id.name_service);
        drop = (Spinner)findViewById(R.id.serviceOptions);
        ok = (Button) findViewById(R.id.button19);
        text =(TextView)findViewById(R.id.textView33);

        String menu_items[] = {"Select option","Get by name","Get all"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, menu_items);
        drop.setAdapter(adapter);

        drop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String) parent.getItemAtPosition(position);
                if (!select.equals("Select option")) {
                    if (select.equals("Get by name")) {
                        name.setVisibility(view.VISIBLE);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String url = "http://10.0.2.2:27015/v1/admin/service/" + name.getText().toString();
                                JsonParser parser = new JsonParser();
                                jsonObject = parser.getJSONFromUrl(url);
                                if(jsonObject.length != 0) {
                                    System.out.println(jsonObject);
                                    try {
                                        jArray = jsonObject[0].getJSONArray("rules");
                                        jObj = new JSONObject[jArray.length()];
                                        text.setText("");
                                        //Get each object from JSON Array
                                        for (int i = 0; i < jArray.length(); i++) {
                                            jObj[i] = jArray.getJSONObject(i);
                                            if (jObj[i].has("merchant"))
                                                text.setText(text.getText().toString() + "\nMERCHANT : " + jObj[i].getString("merchant") + " ");
                                            if (jObj[i].has("goodType"))
                                                text.setText(text.getText().toString() + "\nGOODS TYPE : " + jObj[i].getString("goodType") + " ");
                                            if (jObj[i].has("productCategory"))
                                                text.setText(text.getText().toString() + "\nPRODUCT CATEGORY : " + jObj[i].getString("productCategory") + " ");
                                            if (jObj[i].has("vendor"))
                                                text.setText(text.getText().toString() + "\nVENDOR : " + jObj[i].getString("vendor") + " ");
                                            if (jObj[i].has("flow"))
                                                text.setText(text.getText().toString() + "\nFLOW : " + jObj[i].getString("flow") + " ");
                                            if (jObj[i].has("qos"))
                                                text.setText(text.getText().toString() + "\nQOS : " + jObj[i].getString("qos") + " ");
                                            if (jObj[i].has("size"))
                                                text.setText(text.getText().toString() + "\nSIZE : " + jObj[i].getString("size") + " ");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    Context context = getApplicationContext();
                                    CharSequence text = "service does not exist";
                                    int duration = Toast.LENGTH_SHORT;

                                    //Show a toast to inform the user
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                    toast.show();
                                    Intent intent = new Intent(GetService.this,Service.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

                    }

                    if(select.equals("Get all"))
                    {
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String url = "http://10.0.2.2:27015/v1/admin/service";
                                text.setText("");
                                name.setVisibility(v.INVISIBLE);
                                JsonParser parser = new JsonParser();
                                jsonObject = parser.getJSONFromUrl(url);
                                System.out.println(jsonObject);
                                try {
                                    for(int j = 0;j<jsonObject.length;j++){
                                    jArray = jsonObject[j].getJSONArray("rules");
                                    jObj = new JSONObject[jArray.length()];
                                    text.setText(text.getText().toString()+"\n\nService name : "+jsonObject[j].getString("serviceName"));
                                    //Get each object from JSON Array
                                    for(int i=0;i<jArray.length();i++) {
                                        jObj[i] = jArray.getJSONObject(i);
                                        if(jObj[i].has("merchant"))
                                            text.setText(text.getText().toString()+"\nMERCHANT : " + jObj[i].getString("merchant")+" ");
                                        if(jObj[i].has("goodType"))
                                            text.setText(text.getText().toString()+"\nGOODS TYPE : " + jObj[i].getString("goodType")+" ");
                                        if(jObj[i].has("productCategory"))
                                            text.setText(text.getText().toString()+"\nPRODUCT CATEGORY : " + jObj[i].getString("productCategory")+" ");
                                        if(jObj[i].has("vendor"))
                                            text.setText(text.getText().toString()+"\nVENDOR : " + jObj[i].getString("vendor")+" ");
                                        if(jObj[i].has("flow"))
                                            text.setText(text.getText().toString()+"\nFLOW : " + jObj[i].getString("flow")+" ");
                                        if(jObj[i].has("qos"))
                                            text.setText(text.getText().toString()+"\nQOS : " + jObj[i].getString("qos")+" ");
                                        if(jObj[i].has("size"))
                                            text.setText(text.getText().toString()+"\nSIZE : " + jObj[i].getString("size")+" ");
                                    }}
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get, menu);
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
