package com.example.trishudey.hubsystemhelper.Activities.main;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.encryption.MD5Encryption;
import com.example.trishudey.hubsystemhelper.repositories.GetData;


/**
 * Shows the Login Page to the user .
 * Gets username and password , if valid proceed to next screen.
 * Created by trishu.dey on 08/09/15.
 */


public class LoginPage extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.trishudey.MESSAGE";
    public Button Btngetdata1;
    EditText username;
    EditText password;
    public static String user = null;
    public static String pass = null;
    public static ProgressBar progressBar ;

    private static TextInputLayout idLayout;
    private static TextInputLayout passLayout;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //Changes 'back' button action
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            LoginPage.this.finish();

        }
        return true;
    }

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

                GetData gd = new GetData();
                boolean response = gd.validate(user,pass);

                if(response)
                {

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


    });

}
}



