package com.example.trishudey.hubsystemhelper.Activities.services.resources;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.repositories.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class AllResources extends Activity {
    public static Spinner dropdown;
    public static JSONObject jsonObject[];
    public static TextView text[] = new TextView[140];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_resources);
        String resources[] = {"Select provider","ARM","MANUAL_STATION","PPTL","WEIGHING_MACHINE"};
        dropdown = (Spinner) findViewById(R.id.allResources);
        JsonParser parser = new JsonParser();
        jsonObject = parser.getJSONFromUrl("http://hubsystem-app.nm.flipkart.com/v1/admin/getResources");
        text[0] = (TextView) findViewById(R.id.text1);
        text[1] = (TextView) findViewById(R.id.text2);
        text[2] = (TextView) findViewById(R.id.text3);
        text[3] = (TextView) findViewById(R.id.text4);
        text[4] = (TextView) findViewById(R.id.text5);
        text[5] = (TextView) findViewById(R.id.text6);
        text[6] = (TextView) findViewById(R.id.text7);
        text[7] = (TextView) findViewById(R.id.text8);
        text[8] = (TextView) findViewById(R.id.text9);
        text[9] = (TextView) findViewById(R.id.text10);
        text[10] = (TextView) findViewById(R.id.text11);
        text[11] = (TextView) findViewById(R.id.text12);
        text[12] = (TextView) findViewById(R.id.text13);
        text[13] = (TextView) findViewById(R.id.text14);
        text[14] = (TextView) findViewById(R.id.text15);
        text[15] = (TextView) findViewById(R.id.text16);
        text[16] = (TextView) findViewById(R.id.text17);
        text[17] = (TextView) findViewById(R.id.text18);
        text[18] = (TextView) findViewById(R.id.text19);
        text[19] = (TextView) findViewById(R.id.text20);
        text[20] = (TextView) findViewById(R.id.text21);
        text[21] = (TextView) findViewById(R.id.text22);
        text[22] = (TextView) findViewById(R.id.text23);
        text[23] = (TextView) findViewById(R.id.text24);
        text[24] = (TextView) findViewById(R.id.text25);
        text[25] = (TextView) findViewById(R.id.text26);
        text[26] = (TextView) findViewById(R.id.text27);
        text[27] = (TextView) findViewById(R.id.text28);
        text[28] = (TextView) findViewById(R.id.text29);
        text[29] = (TextView) findViewById(R.id.text30);
        text[30] = (TextView) findViewById(R.id.text31);
        text[31] = (TextView) findViewById(R.id.text32);
        text[32] = (TextView) findViewById(R.id.text33);
        text[33] = (TextView) findViewById(R.id.text34);
        text[34] = (TextView) findViewById(R.id.text35);
        text[35] = (TextView) findViewById(R.id.text36);
        text[36] = (TextView) findViewById(R.id.text37);
        text[37] = (TextView) findViewById(R.id.text38);
        text[38] = (TextView) findViewById(R.id.text39);
        text[39] = (TextView) findViewById(R.id.text40);
        text[40] = (TextView) findViewById(R.id.text41);
        text[41] = (TextView) findViewById(R.id.text42);
        text[42] = (TextView) findViewById(R.id.text43);
        text[43] = (TextView) findViewById(R.id.text44);
        text[44] = (TextView) findViewById(R.id.text45);
        text[45] = (TextView) findViewById(R.id.text46);
        text[46] = (TextView) findViewById(R.id.text47);
        text[47] = (TextView) findViewById(R.id.text48);
        text[48] = (TextView) findViewById(R.id.text49);
        text[49] = (TextView) findViewById(R.id.text50);
        text[50] = (TextView) findViewById(R.id.text51);
        text[51] = (TextView) findViewById(R.id.text52);
        text[52] = (TextView) findViewById(R.id.text53);
        text[53] = (TextView) findViewById(R.id.text54);
        text[54] = (TextView) findViewById(R.id.text55);
        text[55] = (TextView) findViewById(R.id.text56);
        text[56] = (TextView) findViewById(R.id.text57);
        text[57] = (TextView) findViewById(R.id.text58);
        text[58] = (TextView) findViewById(R.id.text59);
        text[59] = (TextView) findViewById(R.id.text60);
        text[60] = (TextView) findViewById(R.id.text61);
        text[61] = (TextView) findViewById(R.id.text62);
        text[62] = (TextView) findViewById(R.id.text63);
        text[63] = (TextView) findViewById(R.id.text64);
        text[64] = (TextView) findViewById(R.id.text65);
        text[65] = (TextView) findViewById(R.id.text66);
        text[66] = (TextView) findViewById(R.id.text67);
        text[67] = (TextView) findViewById(R.id.text68);
        text[68] = (TextView) findViewById(R.id.text69);
        text[69] = (TextView) findViewById(R.id.text70);
        text[70] = (TextView) findViewById(R.id.text71);
        text[71] = (TextView) findViewById(R.id.text72);
        text[72] = (TextView) findViewById(R.id.text73);
        text[73] = (TextView) findViewById(R.id.text74);
        text[74] = (TextView) findViewById(R.id.text75);
        text[75] = (TextView) findViewById(R.id.text76);
        text[76] = (TextView) findViewById(R.id.text77);
        text[77] = (TextView) findViewById(R.id.text78);
        text[78] = (TextView) findViewById(R.id.text79);
        text[79] = (TextView) findViewById(R.id.text80);
        text[80] = (TextView) findViewById(R.id.text81);
        text[81] = (TextView) findViewById(R.id.text82);
        text[82] = (TextView) findViewById(R.id.text83);
        text[83] = (TextView) findViewById(R.id.text84);
        text[84] = (TextView) findViewById(R.id.text85);
        text[85] = (TextView) findViewById(R.id.text86);
        text[86] = (TextView) findViewById(R.id.text87);
        text[87] = (TextView) findViewById(R.id.text88);
        text[88] = (TextView) findViewById(R.id.text89);
        text[89] = (TextView) findViewById(R.id.text90);
        text[90] = (TextView) findViewById(R.id.text91);
        text[91] = (TextView) findViewById(R.id.text92);
        text[92] = (TextView) findViewById(R.id.text93);
        text[93] = (TextView) findViewById(R.id.text94);
        text[94] = (TextView) findViewById(R.id.text95);
        text[95] = (TextView) findViewById(R.id.text96);
        text[96] = (TextView) findViewById(R.id.text97);
        text[97] = (TextView) findViewById(R.id.text98);
        text[98] = (TextView) findViewById(R.id.text99);
        text[99] = (TextView) findViewById(R.id.text100);
        text[100] = (TextView) findViewById(R.id.text101);
        text[101] = (TextView) findViewById(R.id.text102);
        text[102] = (TextView) findViewById(R.id.text103);
        text[103] = (TextView) findViewById(R.id.text104);
        text[104] = (TextView) findViewById(R.id.text105);
        text[105] = (TextView) findViewById(R.id.text106);
        text[106] = (TextView) findViewById(R.id.text107);
        text[107] = (TextView) findViewById(R.id.text108);
        text[108] = (TextView) findViewById(R.id.text109);
        text[109] = (TextView) findViewById(R.id.text110);
        text[110] = (TextView) findViewById(R.id.text111);
        text[111] = (TextView) findViewById(R.id.text112);
        text[112] = (TextView) findViewById(R.id.text113);
        text[113] = (TextView) findViewById(R.id.text114);
        text[114] = (TextView) findViewById(R.id.text115);
        text[115] = (TextView) findViewById(R.id.text116);
        text[116] = (TextView) findViewById(R.id.text117);
        text[117] = (TextView) findViewById(R.id.text118);
        text[118] = (TextView) findViewById(R.id.text119);
        text[119] = (TextView) findViewById(R.id.text120);
        text[120] = (TextView) findViewById(R.id.text121);
        text[121] = (TextView) findViewById(R.id.text122);
        text[122] = (TextView) findViewById(R.id.text123);
        text[123] = (TextView) findViewById(R.id.text124);
        text[124] = (TextView) findViewById(R.id.text125);
        text[125] = (TextView) findViewById(R.id.text126);
        text[126] = (TextView) findViewById(R.id.text127);
        text[127] = (TextView) findViewById(R.id.text128);
        text[128] = (TextView) findViewById(R.id.text129);
        text[129] = (TextView) findViewById(R.id.text130);
        text[130] = (TextView) findViewById(R.id.text131);
        text[131] = (TextView) findViewById(R.id.text132);
        text[132] = (TextView) findViewById(R.id.text133);
        text[133] = (TextView) findViewById(R.id.text134);
        text[134] = (TextView) findViewById(R.id.text135);
        text[135] = (TextView) findViewById(R.id.text136);
        text[136] = (TextView) findViewById(R.id.text137);
        text[137] = (TextView) findViewById(R.id.text138);
        text[138] = (TextView) findViewById(R.id.text139);
        text[139] = (TextView) findViewById(R.id.text140);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resources);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String) parent.getItemAtPosition(position);
                if(select != "Select provider")
                {
                    for(int i=0;i<139;i++)
                    {
                        text[i].setText("");
                    }
                    int k = 0;
                    int time =0;
                    for(int i =0;i<jsonObject.length;i++)
                    {
                        try {
                            if(jsonObject[i].getString("type").equals(select))
                            {
                                    text[k].setVisibility(view.VISIBLE);
                                    text[k].setText(text[k].getText()+"\nProvider ID : " + jsonObject[i].getString("providerId")+"\nProcessing area :"+jsonObject[i].getString("processingAreaId")+"\n----------------------------------------------");
                                time++;
                                if(time==7)
                                {
                                    k++;
                                    time = 0;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    for(int j=k+1;j<140;j++)
                    {
                        text[j].setVisibility(view.INVISIBLE);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all_resources, menu);
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
