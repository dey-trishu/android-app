package com.example.trishudey.hubsystemhelper.Activities.services.accounts;
/*
Two options provided to user : 1. delete 2.change password
 */
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.services.service.AddService;
import com.example.trishudey.hubsystemhelper.Activities.services.service.GetService;
import com.example.trishudey.hubsystemhelper.Activities.services.service.RemoveService;
import com.example.trishudey.hubsystemhelper.Activities.services.sortation.SortationRule;
import com.example.trishudey.hubsystemhelper.database.DBHelper;
import com.example.trishudey.hubsystemhelper.encryption.MD5Encryption;
import com.example.trishudey.hubsystemhelper.requests.PostToUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class ManageAccounts extends Activity {
    String delUser;
    String message;
    String user="";
    String pass="";
    String Mycase="";
    int here = 0;
    DBHelper userInformation;
    JSONObject jObj;

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
                        URL u = null;
                        try {
                            u = new URL("http://10.0.2.2:8082/v1/admin/deleteUser/" + delUser);
                            HttpURLConnection c = null;
                            c = (HttpURLConnection) u.openConnection();
                            c.setRequestMethod("GET");
                            c.setRequestProperty("Content-length", "0");
                            c.setUseCaches(false);
                            c.setAllowUserInteraction(false);
                            c.connect();
                            System.out.print(c.getResponseMessage());
                            String m = c.getResponseMessage();
                            System.out.println(m);
                            int status = c.getResponseCode();
                            if (status == 200) {
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
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
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
                        MD5Encryption md5Encryption = new MD5Encryption();
                        String currPass = md5Encryption.MD5(c_pass.getText().toString());
                        String newPass = md5Encryption.MD5(n_pass.getText().toString());
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
                            URL u = null;
                            try {
                                u = new URL("http://10.0.2.2:8082/v1/admin/getCredentials/" + currUser);
                                HttpURLConnection c = null;
                                c = (HttpURLConnection) u.openConnection();
                                c.setRequestMethod("GET");
                                c.setRequestProperty("Content-length", "0");
                                c.setUseCaches(false);
                                c.setAllowUserInteraction(false);
                                c.connect();
                                System.out.print(c.getResponseMessage());
                                String m = c.getResponseMessage();
                                System.out.println(m);
                                int status = c.getResponseCode();
                                if (status == 200) {
                                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
//                    inputStreamReader.close();
                                    StringBuilder sb = new StringBuilder();
                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        sb.append(line + "\n");
                                    }
                                    br.close();
                                    String json = sb.toString();

                                    jObj = new JSONObject(json);
                                    String password = jObj.getString("password");

                                    if (password.equals(currPass)) {

                                        URL u1 = null;
                                        try {
                                            u1 = new URL("http://10.0.2.2:8082/v1/admin/deleteUser/" + currUser);
                                            HttpURLConnection c1 = null;
                                            c1 = (HttpURLConnection) u1.openConnection();
                                            c1.setRequestMethod("GET");
                                            c1.setRequestProperty("Content-length", "0");
                                            c1.setUseCaches(false);
                                            c1.setAllowUserInteraction(false);
                                            c1.connect();
                                            System.out.print(c1.getResponseMessage());
                                            String m1 = c1.getResponseMessage();
                                            System.out.println(m1);
                                            int status1 = c1.getResponseCode();
                                            if (status1 == 200) {


                                                String url = "http://10.0.2.2:8082/v1/admin/addCredentials";
                                                String params = "&name=" + currUser + "&password=" + newPass;
                                                PostToUrl postToUrl = new PostToUrl();
                                                int code = postToUrl.post(url, params);

                                                if (code == 200) {

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

                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        } catch (ProtocolException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Current Password Invalid";
                                        int duration = Toast.LENGTH_SHORT;

                                        //Show a toast to inform the user
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                        toast.show();
                                    }
                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text = "No such user";
                                    int duration = Toast.LENGTH_SHORT;

                                    //Show a toast to inform the user
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                    toast.show();
                                }
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (ProtocolException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
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

                    MD5Encryption md5Encryption = new MD5Encryption();
                    String password = md5Encryption.MD5(pass.getText().toString());

                    //gets the list of all the users present to inform the current user
                    //whether the user name has already been taken

                    URL u = null;
                    try {
                        u = new URL("http://10.0.2.2:8082/v1/admin/getCredentials/" + username);
                        HttpURLConnection c = null;
                        c = (HttpURLConnection) u.openConnection();
                        c.setRequestMethod("GET");
                        c.setRequestProperty("Content-length", "0");
                        c.setUseCaches(false);
                        c.setAllowUserInteraction(false);
                        c.connect();
                        System.out.print(c.getResponseMessage());
                        String m = c.getResponseMessage();
                        System.out.println(m);
                        int status = c.getResponseCode();
                        if (user.equals("admin")) {
                            if (status == 200) {
                                Context context = getApplicationContext();
                                CharSequence text = "User Exists";
                                int duration = Toast.LENGTH_SHORT;

                                //Show a toast to inform the user
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                toast.show();
                            } else {
                                // Create a new map of values, where user names are the keys
                                if (!username.isEmpty() && !pass.getText().toString().isEmpty() && pass.getText().toString().length() >= 8) {
                                    String url = "http://10.0.2.2:8082/v1/admin/addCredentials";
                                    String params = "&name=" + username + "&password=" + password;
                                    PostToUrl postToUrl = new PostToUrl();
                                    int code = postToUrl.post(url, params);

                                    if (code == 200) {

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
                                } else {
                                    if (username.isEmpty()) {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Username missing";
                                        int duration = Toast.LENGTH_SHORT;

                                        //Show a toast to inform the user
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                        toast.show();
                                    }
                                    if (pass.getText().toString().length() < 8) {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Password must be at least 8 characters long";
                                        int duration = Toast.LENGTH_SHORT;

                                        //Show a toast to inform the user
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                                        toast.show();
                                    }

                                }

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


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
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
