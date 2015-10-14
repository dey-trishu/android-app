package com.example.trishudey.hubsystemhelper.Activities.services.hub;

/*
Lists various options available to users within a hub
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.main.LoginPage;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_Admin;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_User;
import com.example.trishudey.hubsystemhelper.Activities.services.processingAreas.HubElements;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HubPage extends Activity {

    Button scan;
    Button elements;
    Button back;
    Button config;
    public final static String EXTRA_MESSAGE = "com.example.trishudey.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_page);
        Intent intent = getIntent();
        final String message = intent.getStringExtra(EXTRA_MESSAGE);
        scan = (Button) findViewById(R.id.button6);
        elements = (Button)findViewById(R.id.button7);
        back = (Button)findViewById(R.id.button15);
        config = (Button)findViewById(R.id.button3);

        //send intent to a barcode scanner
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent(HubPage.this,HubActivity.class);
//                startActivity(in);
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "PRODUCT_MODE");//for Qr code, its "QR_CODE_MODE" , for barcode "PRODUCT_MODE"
                intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
                startActivityForResult(intent, 0);
            }
        });
        //show the elements of the hub
        elements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HubPage.this,HubElements.class);
                in.putExtra(EXTRA_MESSAGE,message);
                startActivity(in);
            }
        });

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HubPage.this,Config.class);
                in.putExtra(EXTRA_MESSAGE,message);
                startActivity(in);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                String send = in.getStringExtra("callBack");
                if(LoginPage.user.equals("admin"))
                {

                    Intent intent = new Intent(HubPage.this,Options_Page_Admin.class);
                    intent.putExtra("com.example.trishudey.MESSAGE",send);
                    startActivity(intent);
                    finish();
                }
                else
                {

                    Intent intent = new Intent(HubPage.this,Options_Page_User.class);
                    intent.putExtra("com.example.trishudey.MESSAGE",send);
                    startActivity(intent);
                    finish();
                }

            }
        });

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private static List<String> list(String... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }

}
