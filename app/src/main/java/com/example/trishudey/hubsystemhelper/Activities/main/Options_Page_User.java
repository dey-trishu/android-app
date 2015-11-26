package com.example.trishudey.hubsystemhelper.Activities.main;
/**
 * The users apart from admin are redirected to this page !
 * They do not have any creation rights they can only get data (pre-formed)
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.services.hub.HubPage;
import com.example.trishudey.hubsystemhelper.Activities.services.sortation.SortationRule;
import com.example.trishudey.hubsystemhelper.repositories.GetData;

import org.json.JSONException;
import org.json.JSONObject;


public class Options_Page_User extends Activity {

    public static String items[];
    public static Spinner dropdown;
    public static String prev = "";
    String message;
    public static ProgressBar progressBar;
    public final static String EXTRA_MESSAGE = "com.example.trishudey.MESSAGE";
    public static Options_Page_User fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__page__user);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        fa = this;
        final Intent intent = getIntent();
        message = intent.getStringExtra(SortationRule.EXTRA_MESSAGE);
        dropdown = (Spinner) findViewById(R.id.allHubs);
        LoginPage.progressBar.setVisibility(View.GONE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        GetData gd = new GetData();
        JSONObject jsonObject[];
        jsonObject = gd.getallHubs();

        final String hubs[] = new String[jsonObject.length + 1];

        final String type[] = new String[jsonObject.length +1];
        final String coc[] = new String[jsonObject.length+1];
        final String hubId[] = new String[jsonObject.length +1];
        coc[0] = "Select Hub";
        for (int i = 1; i <= jsonObject.length; i++) {
            try {
                hubs[i] = jsonObject[i - 1].getString("name");
                hubId[i] = jsonObject[i-1].getString("hubId");
                type[i] = jsonObject[i-1].getString("type");
                coc[i] = jsonObject[i-1].getString("coc");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        items = coc;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(prev);
                String select = (String) parent.getItemAtPosition(position);
                if (!select.equals("Select Hub") ) {
                    if(!select.equals(prev))
                    progressBar.setVisibility(View.VISIBLE);
                    prev = "";
                    prev = prev + select;
                    Intent intent1 = new Intent(Options_Page_User.this, HubPage.class);
                    intent1.putExtra(EXTRA_MESSAGE, hubs[position]);
                    intent1.putExtra("callBack", message);
                    intent1.putExtra("type", type[position]);
                    intent1.putExtra("coc", coc[position]);
                    intent1.putExtra("hubId",hubId[position]);
                    startActivity(intent1);
                    finish();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        Button sortationRule = (Button) findViewById(R.id.sortationRule);
//        sortationRule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Options_Page_User.this, SortationRule.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
//        Button service = (Button) findViewById(R.id.service);
//        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
//        service.startAnimation(hyperspaceJumpAnimation);

//        Button resource = (Button) findViewById(R.id.allresources);
//        Animation hyperspaceJumpAnimation1 = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
//        resource.startAnimation(hyperspaceJumpAnimation1);


//        service.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Options_Page_User.this, GetService.class);
//                startActivity(intent);
//            }
//        });
//
//
//        resource.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Options_Page_User.this,AllResources.class);
//                startActivity(intent);
//            }
//        });
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


        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(Options_Page_User.this, LoginPage.class);
                startActivity(intent);
                Options_Page_User.this.finish();
            }
        });

        alertDialogBuilder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
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
