package com.example.trishudey.hubsystemhelper.Activities.services.hub;

/*
Lists various options available to users within a hub
 */
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.main.LoginPage;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_Admin;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_User;
import com.example.trishudey.hubsystemhelper.Activities.services.processingAreas.HubElements;



import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HubPage extends Activity {

    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private TextView textView;
    String message;
    String hubId;
    String facility;
    public static Spinner dropdown;
    public final static String EXTRA_MESSAGE = "com.example.trishudey.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_page);
        Intent intent = getIntent();
        message = intent.getStringExtra(EXTRA_MESSAGE);
        hubId = intent.getStringExtra("hubId");
        facility = intent.getStringExtra("facility");
        String m = intent.getStringExtra(EXTRA_MESSAGE);
        m = m + " " +intent.getStringExtra("type");
        m = m + " " +intent.getStringExtra("coc");
        System.out.println(m);
        dropdown = (Spinner) findViewById(R.id.selectTask);
        String items[] = new String[3];
        items [0] = "Select Task";
        items[1] = "Sortation";
        items[2] = "Bagging";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String select = (String) parent.getItemAtPosition(position);
                if (!select.equals("Select Task")) {

                    Intent intent2 = new Intent(HubPage.this, HubElements.class);
                    intent2.putExtra(EXTRA_MESSAGE, message);
                    intent2.putExtra("hubId", hubId);
                    intent2.putExtra("task",select);
                    intent2.putExtra("facility",facility);
                    startActivity(intent2);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        textView = (TextView)findViewById(R.id.textView43);
        textView.setText("Welcome to " + m);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.navListhub);
        String[] serviceArray = { "SCAN","GET CONFIG","BACK" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, serviceArray);
        mDrawerList.setAdapter(mAdapter);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        mActivityTitle = getTitle().toString();
        mDrawerList.bringToFront();
        mDrawerLayout.requestLayout();

        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                        intent.setPackage("com.google.zxing.client.android");
                        intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");//for Qr code, its "QR_CODE_MODE" , for barcode "PRODUCT_MODE"
                        intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
                        startActivityForResult(intent, 0);
                        break;

                    case 1:
                        Intent intent2 = new Intent(HubPage.this, Config.class);
                        intent2.putExtra(EXTRA_MESSAGE, message);
                        intent2.putExtra("hubId",hubId);
                        startActivity(intent2);

                        break;

                    case 2:
                        Intent in = getIntent();
                        String send = in.getStringExtra("callBack");
                        if (LoginPage.user.equals("admin")) {

                            Intent intent3 = new Intent(HubPage.this, Options_Page_Admin.class);
                            intent3.putExtra("com.example.trishudey.MESSAGE", send);
                            startActivity(intent3);
                            finish();
                        } else {

                            Intent intent4 = new Intent(HubPage.this, Options_Page_User.class);
                            intent4.putExtra("com.example.trishudey.MESSAGE", send);
                            startActivity(intent4);
                            finish();
                        }
                        break;

                }
            }
        });

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        setUpDrawer();

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //Changes 'back' button action
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            if (LoginPage.user.equals("admin")) {
                Intent in = new Intent(HubPage.this,Options_Page_Admin.class);
                in.putExtra(EXTRA_MESSAGE,message);
                startActivity(in);
                finish();
            }
            else {
                Intent in = new Intent(HubPage.this,Options_Page_User.class);
                in.putExtra(EXTRA_MESSAGE,message);
                startActivity(in);
                finish();
            }

        }
        return true;
    }
    private void setUpDrawer()
    {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle("Navigation");
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }
    // show the scanned output on Log
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Log.d("SEARCH_EAN", "OK, EAN: " + contents + ", FORMAT: " + format);//this is the result
            } else
            if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Log.d("SEARCH_EAN","CANCEL");
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hub_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    private static List<String> list(String... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }

}
