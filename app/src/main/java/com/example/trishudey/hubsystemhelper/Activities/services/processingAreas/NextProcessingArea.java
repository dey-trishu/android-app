package com.example.trishudey.hubsystemhelper.Activities.services.processingAreas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.repositories.JsonGetResponse;

import org.w3c.dom.Text;

public class NextProcessingArea extends Activity {
    TextView textView;
    String facility;
    String area;
    String data[] = new String[4];
    JsonGetResponse jsonGetResponse = new JsonGetResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_processing_area);
        Intent intent = getIntent();
        facility = intent.getStringExtra("facility");
        area = intent.getStringExtra("processArea");
       // String barcode = intent.getStringExtra("barcode");
        textView = (TextView)findViewById(R.id.nextArea);
        //textView.setText(facility);
        //textView.setText(textView.getText()+" "+ area);
        Button scan = (Button) findViewById(R.id.button3);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.setPackage("com.google.zxing.client.android");
                intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");//for Qr code, its "QR_CODE_MODE" , for barcode "PRODUCT_MODE"
                intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Log.d("SEARCH_EAN", "OK, EAN: " + contents + ", FORMAT: " + format);//this is the result
               // textView.setText(contents);

                    data = jsonGetResponse.getAreaDetails(contents,facility,area);
                if(data[0] != null)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "DESTINATION : "+ data[0]+"\n"+"NEXT PROCESSING AREA NAME : "+ data[3]+"\n";
                    int duration = Toast.LENGTH_SHORT;
                    //Show a toast to inform the user
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else
                {
                    data = jsonGetResponse.get2Ddetails(contents,area);
                    if(data[0] != null)
                    {

                        Context context = getApplicationContext();
                        CharSequence text = "DESTINATION : "+ data[0]+"\n"+"NEXT PROCESSING AREA NAME : "+ data[3]+"\n";
                        int duration = Toast.LENGTH_SHORT;
                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    else {
                        Context context = getApplicationContext();
                        CharSequence text = "DATA UNAVAILABLE";
                        int duration = Toast.LENGTH_SHORT;
                        //Show a toast to inform the user
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }


               // startActivity(in);
                 } else
            if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Log.d("SEARCH_EAN", "CANCEL");
               // jsonGetResponse.getAreaDetails("FMPP1558578337",facility,area);


            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_next_processing_area, menu);
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
