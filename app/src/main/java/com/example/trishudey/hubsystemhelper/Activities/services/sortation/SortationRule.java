package com.example.trishudey.hubsystemhelper.Activities.services.sortation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.example.trishudey.hubsystemhelper.Activities.main.LoginPage;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_Admin;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_User;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SortationRule extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.trishudey.MESSAGE";

    Spinner value1;
    EditText value2;
    Spinner value3;
    Spinner value4;
    Spinner value5;
    Spinner value6;
    Spinner value7;
    Spinner value8;
    EditText value9;

    String content1=null;
    String content2=null;
    String content3=null;
    String content4=null;
    String content5=null;
    String content6=null;
    String content7=null;
    String content8=null;
    String content9=null;
    String json = "";
    JSONObject jObj;
    String url;
    String pUrl = "";
    String shipmentId = null;

    TextView text ;

    int click=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortation_rule);

        value1 = (Spinner) findViewById(R.id.value1);
        value2 = (EditText) findViewById(R.id.value2);
        value3 = (Spinner) findViewById(R.id.value3);
        value4 = (Spinner) findViewById(R.id.value4);
        value5 = (Spinner) findViewById(R.id.value5);
        value6 = (Spinner) findViewById(R.id.value6);
        value7 = (Spinner) findViewById(R.id.value7);
        value8 = (Spinner) findViewById(R.id.value8);
        value9 = (EditText) findViewById(R.id.editText);

        String items1[] = {"Merchant","W","M","O"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items1);
        value1.setAdapter(adapter);

        String items2[] = {"Goods Type","S","D"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);
        value3.setAdapter(adapter1);

        String items3[] = {"Vendor","E (EKART) ","B (BLUEDART) ","P (SPEED POST)","G (GOJAVAS)","S (AFL)","R (REDEXPRESS)",
        "F (FIRST FLIGHT)","D (DEHLIVERY)","C (ECOM)","O (OTHERS)"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items3);
        value4.setAdapter(adapter3);

        String items5[] = {"Product Category","002 (BOOKS)","001 (ELECTRONICS)","003 (OTHERS)"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items5);
        value5.setAdapter(adapter5);

        String items6[] = {"QOS","R","N"};
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items6);
        value6.setAdapter(adapter6);

        String items7[] = {"Size","S","M","L"};
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items7);
        value7.setAdapter(adapter7);

        String items8[] = {"Flow","F","R"};
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items8);
        value8.setAdapter(adapter8);

        final Button getSortationFactor = (Button) findViewById(R.id.GetSortationFactor);


        value1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                content1 = (String) parent.getItemAtPosition(position);
                if(!content1.equals("Merchant"))
                {
                    pUrl = pUrl + "&merchant=" + content1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        value3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                content3 = (String) parent.getItemAtPosition(position);
                if(!content3.equals("Goods Type"))
                {
                    pUrl = pUrl + "&goodsType=" + content3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        value4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                content4 = (String) parent.getItemAtPosition(position);
                if(!content4.equals("Vendor"))
                {
                    pUrl = pUrl + "&vendor=" + content4.charAt(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        value5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                content5 = (String) parent.getItemAtPosition(position);
                if(!content5.equals("Product Category"))
                {
                    pUrl = pUrl + "&productCategory=" + content5.substring(0,3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        value6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                content6 = (String) parent.getItemAtPosition(position);
                if(!content6.equals("QOS"))
                {
                    pUrl = pUrl + "&qos=" + content6;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        value7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                content7 = (String) parent.getItemAtPosition(position);
                if(!content7.equals("Size"))
                {
                    pUrl = pUrl + "&size=" + content7;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        value8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                content8 = (String) parent.getItemAtPosition(position);
                if (!content8.equals("Flow")) {
                    pUrl = pUrl + "&flow=" + content8;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        url = "http://hubsystem-app.nm.flipkart.com/v1/hub/sortationcode?";
        getSortationFactor.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {

                content2 = value2.getText().toString();
                content9 = value9.getText().toString();

                url = url+"shipmentId=" + content9;




                try {
                    HttpClient client = new DefaultHttpClient();




                    if (!content2.isEmpty())
                        url = url + "&destination=" + content2+pUrl;






                    HttpURLConnection c = null;
                    try {
                        URL u = new URL(url);
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
                                jObj = new JSONObject(json);
                            String sortationcode = jObj.getString("sortationcode");
                            Intent intent = new Intent(SortationRule.this,SortationFactor.class);
                            intent.putExtra(EXTRA_MESSAGE, sortationcode);
                            startActivity(intent);
                                break;
                            default: Context context = getApplicationContext();
                                CharSequence text = "Didn't recieve enough parameters";
                                int duration = Toast.LENGTH_SHORT;

                                //Show a toast to inform the user
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                toast.show();
                                if(LoginPage.user.equals("admin"))
                                {
                                    Intent intent1 = new Intent(SortationRule.this,Options_Page_Admin.class);
                                    startActivity(intent1);
                                    finish();
                                }
                                else
                                {
                                    Intent intent1 = new Intent(SortationRule.this,Options_Page_User.class);
                                    startActivity(intent1);
                                    finish();
                                }

                        }

                    }catch (JSONException e) {
                        Log.e("JSON Parser", "Error parsing data " + e.toString());
                    }

//
                } catch (Exception e) {

                }
            }
            });


            }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sortation_rule, menu);
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
