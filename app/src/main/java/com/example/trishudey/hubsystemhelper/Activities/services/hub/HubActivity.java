package com.example.trishudey.hubsystemhelper.Activities.services.hub;
/*
for drag and set slot : not being used currently
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trishudey.hubsystemhelper.R;


public class HubActivity extends Activity {

    SeekBar slot1 ;
    SeekBar slot2;
    SeekBar slot3 ;
    SeekBar slot4;
    Button ok;

    int max1 = (24-9)*4;
    int max2 = 24*4 - max1;
    int max3 = 24*4 - max2;
    int max4 = 24*4 - max3;
    int hours;
    int minutes;
    int hours1;
    int minutes1;
    int hours2;
    int minutes2;
    int hours3;
    int minutes3;
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        slot1 = (SeekBar) findViewById(R.id.seekBar1);
        slot2 = (SeekBar) findViewById(R.id.seekBar2);
        slot3 = (SeekBar) findViewById(R.id.seekBar3);
        slot4 = (SeekBar) findViewById(R.id.seekBar4);

        slot1.setMax(max1);
        slot2.setMax(max2);
        slot3.setMax(max3);
        slot4.setMax(max4);

        final TextView slotName1 = (TextView)findViewById(R.id.slot3);
        final TextView slotName2 = (TextView)findViewById(R.id.slot4);
        final TextView seekBarValue = (TextView)findViewById(R.id.seekBarValue);
        final TextView seekBarValue1 = (TextView)findViewById(R.id.seekBarValue2);
        final TextView seekBarValue2 = (TextView)findViewById(R.id.seekBarValue3);
        final TextView seekBarValue3 = (TextView)findViewById(R.id.seekBarValue4);

        slot1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hours = 9+progress/4;
                minutes = (progress%4)*15;
                seekBarValue.setText(String.valueOf(hours)+" : " +String.valueOf(minutes));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        slot2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(hours>=24)
                {
                    slot2.setEnabled(false);
                    toast();
                }
                 hours1 = hours+ progress/4;
                if(hours1==24){
                    slot2.setEnabled(false);
                    toast();
                }

                 minutes1= minutes +(progress%4)*15;
                seekBarValue1.setText(String.valueOf(hours1)+" : " +String.valueOf(minutes1));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        slot3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(hours1>=23)
                {
                    slot3.setEnabled(false);
                    toast();
                }
                 hours2 = hours1+ progress/4;
                    if(hours2==24)
                    {
                        slot3.setEnabled(false);
                        toast();
                    }
                 minutes2= minutes1 +(progress%4)*15;
                seekBarValue2.setText(String.valueOf(hours2)+" : " +String.valueOf(minutes2));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        slot4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(hours2>=23)
                {
                    slot4.setEnabled(false);
                    toast();
                }
                hours3 = hours2+ progress/4;
                if(hours3==24)
                {
                    slot4.setEnabled(false);
                    toast();
                }
                minutes3= minutes2 +(progress%4)*15;
                seekBarValue3.setText(String.valueOf(hours3)+" : " +String.valueOf(minutes3));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ok = (Button)findViewById(R.id.button2);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            click = click+1;
                if(click == 1 && hours1<24)
                {
                    slot3.setVisibility(v.VISIBLE);
                    slotName1.setVisibility(v.VISIBLE);

                }
                if(click == 2 && hours1<24 && hours2<24)
                {
                    slot4.setVisibility(v.VISIBLE);
                    slotName2.setVisibility(v.VISIBLE);

                }
                if((click==1 || click >=2) && (hours1>=24 || hours2>=24))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "No more slots available";
                    int duration = Toast.LENGTH_SHORT;

                    //Show a toast to inform the user
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

                    toast.show();
                }





            }
        });


    }

    public void toast()
    {
        Context context = getApplicationContext();
        CharSequence text = "Time Limit Exceeded : Slots Disabled";
        int duration = Toast.LENGTH_SHORT;

        //Show a toast to inform the user
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);

        toast.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hub, menu);
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
