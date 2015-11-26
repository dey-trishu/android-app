package com.example.trishudey.hubsystemhelper.Activities.main;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.services.create.BusinessPartner;
import com.example.trishudey.hubsystemhelper.Activities.services.create.CreateHubActivity;
import com.example.trishudey.hubsystemhelper.Activities.services.hub.HubPage;
import com.example.trishudey.hubsystemhelper.Activities.services.accounts.ManageAccounts;
import com.example.trishudey.hubsystemhelper.Activities.services.service.Service;
import com.example.trishudey.hubsystemhelper.Activities.services.sortation.SortationRule;
import com.example.trishudey.hubsystemhelper.repositories.GetData;


import org.json.JSONException;
import org.json.JSONObject;
/*
Lists the options (activities) available to the user
 */
public class Options_Page_Admin extends ActionBarActivity {

    public static String items[];
    public static Spinner dropdown;
    public static String prev = "";
    String message;
    public static ProgressBar progressBar;
    public final static String EXTRA_MESSAGE = "com.example.trishudey.MESSAGE";

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final Intent intent = getIntent();
        message = intent.getStringExtra(SortationRule.EXTRA_MESSAGE);
        setContentView(R.layout.activity_options__page);
        dropdown = (Spinner) findViewById(R.id.allHubs);
        LoginPage.progressBar.setVisibility(View.GONE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Getting JSON from URL
        GetData gd = new GetData();
        JSONObject jsonObject[];
        jsonObject = gd.getallHubs();

        String hubs[] = new String[jsonObject.length + 1];
        final String type[] = new String[jsonObject.length +1];
        final String coc[] = new String[jsonObject.length+1];
        hubs[0] = "Select Hub";
        for (int i = 1; i <= jsonObject.length; i++) {
            try {
                hubs[i] = jsonObject[i - 1].getString("name");
                type[i] = jsonObject[i-1].getString("type");
                coc[i] = jsonObject[i-1].getString("coc");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        items = hubs;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
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
                    Intent intent1 = new Intent(Options_Page_Admin.this, HubPage.class);
                    intent1.putExtra(EXTRA_MESSAGE, select);
                    intent1.putExtra("callBack", message);
                    intent1.putExtra("type", type[position]);
                    intent1.putExtra("coc", coc[position]);
                    startActivity(intent1);
                    finish();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button createHub = (Button) findViewById(R.id.createHub);
        createHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Page_Admin.this, CreateHubActivity.class);
                startActivity(intent);

            }
        });
        Button sortationRule = (Button)findViewById(R.id.sortationRule);
        sortationRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Page_Admin.this, SortationRule.class);
                startActivity(intent);
                finish();
            }
        });
        Button businessPartner = (Button) findViewById(R.id.businessPartner);
        businessPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Page_Admin.this, BusinessPartner.class);
                startActivity(intent);
            }
        });

        Button service = (Button) findViewById(R.id.button17);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options_Page_Admin.this, Service.class);
                startActivity(intent);
            }
        });

        Button logout = (Button) findViewById(R.id.button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //Changes 'back' button action
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            open();
        }
        return true;
    }

    public void open(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to leave the app ?");
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle("Logout");
        alertDialogBuilder.setIcon(R.drawable.ic_action_alert);


        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(Options_Page_Admin.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_options__page, menu);
//        return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options__page, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void openSearch() {
        startActivity(new Intent(SearchManager.INTENT_ACTION_GLOBAL_SEARCH));
    }
    private void openSettings() {
        Intent intent = new Intent(Options_Page_Admin.this, ManageAccounts.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);    }
}