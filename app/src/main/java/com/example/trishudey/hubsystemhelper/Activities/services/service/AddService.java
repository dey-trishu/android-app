package com.example.trishudey.hubsystemhelper.Activities.services.service;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AddService extends Activity {

    EditText merchant;
    EditText goods;
    EditText vendor;
    EditText product;
    EditText qos;
    EditText size;
    EditText flow;
    EditText service;
    Button rule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        service = (EditText) findViewById(R.id.service);
        rule = (Button) findViewById(R.id.button18);


        merchant = (EditText) findViewById(R.id.rule1);

        goods = (EditText) findViewById(R.id.rule2);

        vendor = (EditText) findViewById(R.id.rule3);

        product = (EditText) findViewById(R.id.rule4);

        qos = (EditText) findViewById(R.id.editText8);

        size = (EditText) findViewById(R.id.editText9);

        flow = (EditText) findViewById(R.id.editText10);




        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String serv = service.getText().toString();

                String m = merchant.getText().toString();
                JSONArray jsonArray = new JSONArray();
                for (int j = 0; j < m.length(); j++) {
                    try {
                        jsonArray.put(j,""+ m.charAt(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                String g = goods.getText().toString();
                JSONArray jsonArray1 = new JSONArray();
                for (int j = 0; j < g.length(); j++) {
                    try {
                        jsonArray1.put(j, ""+g.charAt(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                String v1 = vendor.getText().toString();
                JSONArray jsonArray2 = new JSONArray();
                for (int j = 0; j < v1.length(); j++) {
                    try {
                        jsonArray2.put(j, ""+v1.charAt(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                String p = product.getText().toString();
                JSONArray jsonArray3 = new JSONArray();
                for (int j = 0; j < p.length(); j++) {
                    try {
                        jsonArray3.put(j, ""+p.charAt(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                String q = qos.getText().toString();
                JSONArray jsonArray4 = new JSONArray();
                for (int j = 0; j < q.length(); j++) {
                    try {
                        jsonArray4.put(j, ""+q.charAt(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                String s = size.getText().toString();
                JSONArray jsonArray5 = new JSONArray();
                for (int j = 0; j < s.length(); j++) {
                    try {
                        jsonArray5.put(j, ""+s.charAt(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                String f = flow.getText().toString();
                JSONArray jsonArray6 = new JSONArray();
                for (int j = 0; j < f.length(); j++) {
                    try {
                        jsonArray6.put(j,""+ f.charAt(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                JSONObject jsonObject = new JSONObject();

                try {
                    if (jsonArray.length() != 0)
                        jsonObject.put("merchantCode", jsonArray);
                    if (jsonArray1.length() != 0)
                        jsonObject.put("goodsType", jsonArray1);
                    if (jsonArray2.length() != 0)
                        jsonObject.put("vendor", jsonArray2);
                    if (jsonArray3.length()!= 0)
                        jsonObject.put("productCategory", jsonArray3);
                    int c =jsonArray3.length();
                    System.out.println(c);
                    if (jsonArray4.length() != 0)
                        jsonObject.put("qos", jsonArray4);
                    if (jsonArray5.length()!= 0)
                        jsonObject.put("size", jsonArray5);
                    if (jsonArray6.length() != 0)
                        jsonObject.put("flow", jsonArray6);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray jArray = new JSONArray();
                try {
                    jArray.put(0, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject object = new JSONObject();
                try {
                    object.put("serviceName", serv);
                    object.put("rules", jArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {


                    URL url = new URL("http://hubsystem-app.nm.flipkart.com/v1/admin/service/" + serv + "/add");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type",
                            "application/json");
                    conn.setRequestProperty("charset", "utf-8");
                    conn.setRequestProperty("Content-Length", "" +
                            Integer.toString(object.toString().getBytes().length));
                    conn.setRequestProperty("Content-Language", "en-US");
                    conn.setUseCaches(false);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.connect();

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(object.toString());
                    writer.flush();
                    writer.close();
                    os.close();

                    int code = conn.getResponseCode();
                    System.out.println(code);

                    if(code == 200)
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Changes Saved";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(AddService.this,Service.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Oops ! Not saved";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(AddService.this,Service.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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
