package com.example.trishudey.hubsystemhelper.Activities.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.database.DBHelper;
import com.example.trishudey.hubsystemhelper.encryption.MD5Encryption;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Shows the Login Page to the user .
 * Gets username and password , if valid proceed to next screen.
 * Created by trishu.dey on 26/08/15.
 */

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class LoginPage extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.trishudey.MESSAGE";
    public Button Btngetdata1;
    EditText username;
    EditText password;
    public static String user = null;
    public static String pass = null;
    public static ProgressBar progressBar ;
    JSONObject jObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        //link to the username edit text box
        username = (EditText) findViewById(R.id.username);
        //link to the password edit text box
        password = (EditText) findViewById(R.id.password);

//        userInformation = new DBHelper(this);
//        // Gets the data repository in write mode
//        SQLiteDatabase db = userInformation.getWritableDatabase();

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        //button to proceed

        //adding action listener to button

        //button to proceed
        Btngetdata1 = (Button) findViewById(R.id.signIn);
        //adding action listener to button
        Btngetdata1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //get the username as entered by the user
                user = username.getText().toString();
                //get the password as entered by the user
                //encrypt the password received

                MD5Encryption md5Encryption = new MD5Encryption();
                pass = md5Encryption.MD5(password.getText().toString());
                System.out.println(pass);
                // Create a new map of values, where column names are the keys

                try {
                    URL u = new URL("http://10.0.2.2:8082/v1/admin/getCredentials/"+user);
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
                    if(status == 200)
                    {
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line+"\n");
                        }
                        br.close();
                        String json = sb.toString();

                            jObj = new JSONObject(json);
                        String password = jObj.getString("password");

                        if(password.equals(pass))
                        {
                            progressBar.setVisibility(View.VISIBLE);
                            /**
                             * separate pages for admin and normal user
                             * more facilities to admin
                             */
                            if(user.equals("admin"))
                            {
                                Intent next_intent = new Intent(LoginPage.this, Options_Page_Admin.class);
                                next_intent.putExtra(EXTRA_MESSAGE, user + "+" + pass);
                                startActivity(next_intent);
                                finish();
                            }
                            else
                            {
                                Intent next_intent = new Intent(LoginPage.this, Options_Page_User.class);
                                next_intent.putExtra(EXTRA_MESSAGE, user + "+" + pass);
                                startActivity(next_intent);
                                finish();

                            }
                        }
                        else{
                            Context context = getApplicationContext();
                            CharSequence text = "Wrong username or password";
                            int duration = Toast.LENGTH_SHORT;
                            //Show a toast to inform the user
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                    else{
                        Context context = getApplicationContext();
                        CharSequence text = "Wrong username or password";
                        int duration = Toast.LENGTH_SHORT;
                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                        progressBar.setVisibility(View.GONE);
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
//                Cursor cursor = userInformation.getData(user, pass);
//                int numRows = cursor.getCount();
//
//                if(numRows == 0)
//                {
//                    Context context = getApplicationContext();
//                    CharSequence text = "Wrong username or password";
//                    int duration = Toast.LENGTH_SHORT;
//                    //Show a toast to inform the user
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
//                    toast.show();
//                    progressBar.setVisibility(View.GONE);
//                }
//                else {
//                cursor.moveToFirst();
//                int colmIndex = cursor.getColumnIndex("password");
//                String x = cursor.getString(colmIndex);
//
//                System.out.println(pass);
//                if (x.equals(pass)) {
//
//                    progressBar.setVisibility(View.VISIBLE);
//                    /**
//                     * separate pages for admin and normal user
//                     * more facilities to admin
//                     */
//                    if(user.equals("admin"))
//                    {
//                        Intent next_intent = new Intent(LoginPage.this, Options_Page_Admin.class);
//                        next_intent.putExtra(EXTRA_MESSAGE, user + "+" + pass);
//                        startActivity(next_intent);
//                        finish();
//                    }
//                    else
//                    {
//                        Intent next_intent = new Intent(LoginPage.this, Options_Page_User.class);
//                        next_intent.putExtra(EXTRA_MESSAGE, user + "+" + pass);
//                        startActivity(next_intent);
//                        finish();
//
//                    }
//
//                }
//
//                }

            }

        });


    }

}



