package com.example.trishudey.hubsystemhelper.Activities.services.sortation;
/*
Get sortation factor for supplied data
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abishekkrishnan.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.main.LoginPage;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_Admin;
import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_User;

public class SortationFactor extends Activity {

    Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortation_factor);
        Intent intent = getIntent();
        String message = intent.getStringExtra(SortationRule.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(message);
        //click buttton to return to options
        goBack = (Button) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginPage.user.equals("admin"))
                {
                    Intent intent = new Intent(SortationFactor.this,Options_Page_Admin.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SortationFactor.this,Options_Page_User.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sortation_factor, menu);
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
