package com.example.trishudey.hubsystemhelper.Activities.services.processingAreas;
/*
This class adds a processing area/sub-processing area(s) to the hub /processing area
that the user has previously selected.
It makes the previously selected processing area the parent of the newly created processing
area under the hub being queried.
 */
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



public class AddProcessingArea extends Activity {

    EditText name;
    EditText type;
    EditText constraints;
    EditText priority;
    Button save;
    String name1;
    String type1;
    String hub1="";
    String constraints1;
    String priority1;
    String hub="";
    String data;
    String PAid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_processing_area);

        Intent in = getIntent();
        data = in.getStringExtra("hub name");
        int i = 0;
        // extract the hub name from the intent
        while(data.charAt(i)!='+')
        {

            hub = hub + data.charAt(i);
            i++;
        }
        i++;
        //extract the processing area id from the hub .
        //Note : the PAid = 0 if it is the first child of the hub else it returns the id of selected processing area
        while(data.charAt(i)!='+')
        {
            PAid = PAid  + data.charAt(i);
            i++;

        }
        i++;
        while(i<data.length())
        {
            hub1 = hub1 + data.charAt(i);
            i++;
        }
        name = (EditText)findViewById(R.id.namePA);
        type = (EditText)findViewById(R.id.typePA);
        constraints = (EditText)findViewById(R.id.consPA);
        priority = (EditText)findViewById(R.id.priorityPA);
        save = (Button)findViewById(R.id.button10);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = name.getText().toString(); //get the name entered by user
                type1 = type.getText().toString(); //get type of PA entered by user
                constraints1 = constraints.getText().toString(); //get the constraints (if any) for PA . This value may be null
                priority1 = priority.getText().toString(); //priority of PA (may be null)


                //create a http client
               // HttpClient client = new DefaultHttpClient();
                String url = "http://hubsystem-app.nm.flipkart.com/v1/admin/addProcessingArea";
                //httpPost to post new data
              //  HttpPost httpPost = new HttpPost(url);
                String params = "";
                if (!name1.isEmpty())
                    params = params + "&name=" + name1;
                if (!type1.isEmpty())
                    params = params + "&type=" + type1;
                if (!hub1.isEmpty())
                    params = params + "&hubId=" + hub1;
                if (!constraints1.isEmpty())
                    params = params + "&constraints=" + constraints1;
                if (!PAid.equals("0"))
                    params = params + "&parentProcessingAreaId=" + PAid;
                if (!priority1.isEmpty())
                    params = params + "&priority=" + priority1;


                    //if code = 200  OK proceed and create processing area
                PostToUrl postToUrl = new PostToUrl();
                int code = postToUrl.post(url,params);
                    if(code == 200)
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Changes Saved";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(AddProcessingArea.this,HubElements.class);
                        intent.putExtra("com.example.trishudey.MESSAGE",hub);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Context context = getApplicationContext();
                        CharSequence text = "Error ! Couldn't save ! Try agains";
                        int duration = Toast.LENGTH_SHORT;

                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                        toast.show();
                        Intent intent = new Intent(AddProcessingArea.this,HubElements.class);
                        intent.putExtra("com.example.trishudey.MESSAGE",hub);
                        startActivity(intent);
                        finish();
                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_processing_area, menu);
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
