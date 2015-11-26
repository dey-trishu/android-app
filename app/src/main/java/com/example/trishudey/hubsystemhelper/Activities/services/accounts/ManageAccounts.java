package com.example.trishudey.hubsystemhelper.Activities.services.accounts;
/*
Two options provided to user : 1. delete 2.change password
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.services.sortation.SortationRule;
import com.example.trishudey.hubsystemhelper.repositories.GetData;
import com.example.trishudey.hubsystemhelper.database.DBHelper;

import org.json.JSONObject;


public class ManageAccounts extends Activity {
    String delUser;
    String message;
    String user="";
    String pass="";
    String Mycase="";
    int here = 0;
    DBHelper userInformation;
    JSONObject jObj;
    GetData gd = new GetData();

    DBHelper dbHelper;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final Intent intent = getIntent();
        message = intent.getStringExtra(SortationRule.EXTRA_MESSAGE);
        int i = 0;
        userInformation = new DBHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = userInformation.getWritableDatabase();

        //get user and password from intent
        while (message.charAt(i) != '+') {
            user = user + message.charAt(i);
            i++;
        }
        i++;
        while (i < message.length()) {
            if (message.charAt(i) == '+') {
                here = 1;
                break;
            }
            pass = pass + message.charAt(i);
            i++;
        }
        i++;
        if (here == 1) {
            while (i < message.length()) {
                Mycase = Mycase + message.charAt(i);
                i++;
            }

        }
        if (here == 0)
            setContentView(R.layout.activity_manage_accounts);

        if (here == 1 && Mycase.equals("0"))
            setContentView(R.layout.activity_delete_account);
        if (here == 1 && Mycase.equals("1"))
            setContentView(R.layout.activity_update_account);
        if (here == 1 && Mycase.equals("2"))
            setContentView(R.layout.activity_add_account);

        dbHelper = new DBHelper(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout1);
        mDrawerList = (ListView) findViewById(R.id.navList1);
        String[] serviceArray = {"Delete", "Update", "Add"};
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
                        Intent intent = new Intent(ManageAccounts.this, ManageAccounts.class);
                        intent.putExtra(SortationRule.EXTRA_MESSAGE, user + "+" + pass + "+" + "0");
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        Intent intent1 = new Intent(ManageAccounts.this, ManageAccounts.class);
                        intent1.putExtra(SortationRule.EXTRA_MESSAGE, user + "+" + pass + "+" + "1");
                        startActivity(intent1);
                        finish();
                        break;
                    case 2:
                        Intent intent2 = new Intent(ManageAccounts.this, ManageAccounts.class);
                        intent2.putExtra(SortationRule.EXTRA_MESSAGE, user + "+" + pass + "+" + "2");
                        startActivity(intent2);
                        finish();
                        break;
                }
            }
        });

        if (here == 1 && Mycase.equals("0")) {
            Button delete = (Button) findViewById(R.id.button21);
            final EditText name = (EditText) findViewById(R.id.editText2);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (user.equals("admin")) {

                        delUser = name.getText().toString();
                        boolean status = gd.manageaccounts("Delete",delUser,"","");



                            if (status) {
                                Context context = getApplicationContext();
                                CharSequence text = "Successfully Deleted";
                                int duration = Toast.LENGTH_SHORT;

                                //Show a toast to inform the user
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                toast.show();
                            } else {
                                Context context = getApplicationContext();
                                CharSequence text = "Record Not found";
                                int duration = Toast.LENGTH_SHORT;

                                //Show a toast to inform the user
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                toast.show();
                            }




                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "You are not an admin";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                    }
                }
            });
        }

        if (here == 1 && Mycase.equals("1")) {
            Button update = (Button) findViewById(R.id.button22);
            final EditText c_name = (EditText) findViewById(R.id.editText5);
            final EditText c_pass = (EditText) findViewById(R.id.editText3);
            final EditText n_pass = (EditText) findViewById(R.id.editText4);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (user.equals("admin")) {

                        String currUser = c_name.getText().toString();

                        String currPass = c_pass.getText().toString();
                        String newPass = n_pass.getText().toString();
                        int check = 0;
                        if (currUser.isEmpty()) {
                            Context context = getApplicationContext();
                            CharSequence text = "Username missing";
                            int duration = Toast.LENGTH_SHORT;

                            //Show a toast to inform the user
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            check = 1;
                            toast.show();
                        }
                        if (n_pass.getText().toString().length() < 8) {
                            Context context = getApplicationContext();
                            CharSequence text = "Password must be at least 8 characters long";
                            int duration = Toast.LENGTH_SHORT;

                            //Show a toast to inform the user
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            check = 1;
                            toast.show();
                        }
                        if (check == 0) {


                                boolean re = gd.manageaccounts("Update",currUser,currPass,newPass);


                                 if(re)
                                 {
                                                    Context context = getApplicationContext();
                                                    CharSequence text = "User Updated";
                                                    int duration = Toast.LENGTH_SHORT;

                                                    //Show a toast to inform the user
                                                    Toast toast = Toast.makeText(context, text, duration);
                                                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                                    toast.show();
                                                } else {
                                                    Context context = getApplicationContext();
                                                    CharSequence text = "Error";
                                                    int duration = Toast.LENGTH_SHORT;

                                                    //Show a toast to inform the user
                                                    Toast toast = Toast.makeText(context, text, duration);
                                                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                                    toast.show();
                                                }


                                            }

                        }
                    }


            });
        }
        if(here ==1 && Mycase.equals("2"))
        {
            Button add = (Button) findViewById(R.id.button23);
            final EditText name = (EditText)findViewById(R.id.editText6);
            final EditText pass = (EditText) findViewById(R.id.editText12);


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = name.getText().toString();
                    //get the password as entered by the user
                    //encrypt the password received


                    String password = pass.getText().toString();

                    //gets the list of all the users present to inform the current user
                    //whether the user name has already been taken

                   boolean res = gd.manageaccounts("Add",username,pass.getText().toString(),"");




                                    if (res) {

                                        Context context = getApplicationContext();
                                        CharSequence text = "User Created";
                                        int duration = Toast.LENGTH_SHORT;

                                        //Show a toast to inform the user
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                        toast.show();
                                    } else {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Error";
                                        int duration = Toast.LENGTH_SHORT;

                                        //Show a toast to inform the user
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                        toast.show();
                                    }
                                }


            });
        }

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        setUpDrawer();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_accounts, menu);
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
}
