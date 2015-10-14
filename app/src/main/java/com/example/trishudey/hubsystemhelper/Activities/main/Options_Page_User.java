package com.example.trishudey.hubsystemhelper.Activities.main;
/**
 * The users apart from admin are redirected to this page !
 * They do not have any creation rights they can only get data (pre-formed)
 */
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.services.hub.HubPage;
import com.example.trishudey.hubsystemhelper.Activities.services.sortation.SortationRule;
import com.example.trishudey.hubsystemhelper.requests.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Options_Page_User extends Activity {

    public static String items[];
    public static Spinner dropdown;
    public static String prev = "";
    String message;
    public static ProgressBar progressBar;
    public final static String EXTRA_MESSAGE = "com.example.trishudey.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__page__user);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final Intent intent = getIntent();
        message = intent.getStringExtra(SortationRule.EXTRA_MESSAGE);
        dropdown = (Spinner) findViewById(R.id.allHubs);
        LoginPage.progressBar.setVisibility(View.GONE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        JsonParser jParser = new JsonParser();
        JSONObject jsonObject[];
        jsonObject = jParser.getJSONFromUrl("\n" +
                "http://hubsystem-app.nm.flipkart.com/v1/hub/all");

        String hubs[] = new String[jsonObject.length + 1];
        hubs[0] = "Select Hub";
        for (int i = 1; i <= jsonObject.length; i++) {
            try {
                hubs[i] = jsonObject[i - 1].getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        items = hubs;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(prev);
                String select = (String) parent.getItemAtPosition(position);
                if (!select.equals("Select Hub") && !select.equals(prev)) {
                    progressBar.setVisibility(View.VISIBLE);
                    prev = "";
                    prev = prev + select;
                    Intent intent1 = new Intent(Options_Page_User.this, HubPage.class);
                    intent1.putExtra(EXTRA_MESSAGE, select);
                    intent1.putExtra("callBack", message);
                    startActivity(intent1);
                    finish();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button sortationRule = (Button) findViewById(R.id.sortationRule);
        sortationRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Page_User.this, SortationRule.class);
                startActivity(intent);
            }
        });

        Button logout = (Button) findViewById(R.id.button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Page_User.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options__page__user, menu);
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
