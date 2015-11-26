package com.example.trishudey.hubsystemhelper.Activities.services.resources;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.repositories.PostToUrl;

public class MoveResource extends Activity {

    String resourceId="";
    String processingAreaId;
    EditText pId;
    Button send;
    String data;
    String hub="";
    String PAid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_resource);
        Intent in = getIntent();
        data = in.getStringExtra("com.example.trishudey.MESSAGE");
        int i = 0;
        while(data.charAt(i)!='+')
        {

            hub = hub + data.charAt(i);
            i++;
        }
        i++;
        while(data.charAt(i)!='+')
        {
            PAid = PAid  + data.charAt(i);
            i++;
        }
        i++;
        while(i<data.length())
        {
            resourceId = resourceId + data.charAt(i);
            i++;
        }

        pId = (EditText)findViewById(R.id.editText6);


        send = (Button)findViewById(R.id.button14);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processingAreaId = pId.getText().toString();
                String param = "&resourceId="+resourceId+"&processingAreaId="+processingAreaId;
                String url = "http://hubsystem-app.nm.flipkart.com/v1/admin/moveResource";
                PostToUrl post = new PostToUrl();
                int code  = post.post(url,param);
                    if(code == 200)
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Changes Saved";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(MoveResource.this,ShowResource.class);
                        intent.putExtra("hub name",hub+"+"+PAid);
                        startActivity(intent);
                        finish();
                    }




                }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_move_resource, menu);
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
