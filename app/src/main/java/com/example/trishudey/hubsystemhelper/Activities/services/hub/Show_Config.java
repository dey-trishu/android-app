package com.example.trishudey.hubsystemhelper.Activities.services.hub;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.abishekkrishnan.hubsystemhelper.R;

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

public class Show_Config extends Activity {

    String hub;
    String selectedSlot;
    String selectedPA;
    String facilityId;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Spinner subPa;
    String prev="";
    JSONObject jsonObject1[];
    TextView text[]= new TextView[284];

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__config);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        Intent intent = getIntent();
        hub = intent.getStringExtra("com.example.trishudey.MESSAGE");
        selectedSlot = intent.getStringExtra("selected time");
        selectedPA = intent.getStringExtra("selected pa");
        facilityId = intent.getStringExtra("facility id");
        text[0] = (TextView)findViewById(R.id.text1);
        text[1] = (TextView)findViewById(R.id.text2);
        text[2] = (TextView)findViewById(R.id.text3);
        text[3] = (TextView)findViewById(R.id.text4);
        text[4] = (TextView)findViewById(R.id.text5);
        text[5] = (TextView)findViewById(R.id.text6);
        text[6] = (TextView)findViewById(R.id.text7);
        text[7] = (TextView)findViewById(R.id.text8);
        text[8] = (TextView)findViewById(R.id.text9);
        text[9] = (TextView)findViewById(R.id.text10);
        text[10] = (TextView)findViewById(R.id.text11);
        text[11] = (TextView)findViewById(R.id.text12);
        text[12] = (TextView)findViewById(R.id.text13);
        text[13] = (TextView)findViewById(R.id.text14);
        text[14] = (TextView)findViewById(R.id.text15);
        text[15] = (TextView)findViewById(R.id.text16);
        text[16] = (TextView)findViewById(R.id.text17);
        text[17] = (TextView)findViewById(R.id.text18);
        text[18] = (TextView)findViewById(R.id.text19);
        text[19] = (TextView)findViewById(R.id.text20);
        text[20] = (TextView)findViewById(R.id.text21);
        text[21] = (TextView)findViewById(R.id.text22);
        text[22] = (TextView)findViewById(R.id.text23);
        text[23] = (TextView)findViewById(R.id.text24);
        text[24] = (TextView)findViewById(R.id.text25);
        text[25] = (TextView)findViewById(R.id.text26);
        text[26] = (TextView)findViewById(R.id.text27);
        text[27] = (TextView)findViewById(R.id.text28);
        text[28] = (TextView)findViewById(R.id.text29);
        text[29] = (TextView)findViewById(R.id.text30);
        text[30] = (TextView)findViewById(R.id.text31);
        text[31] = (TextView)findViewById(R.id.text32);
        text[32] = (TextView)findViewById(R.id.text34);
        text[33] = (TextView)findViewById(R.id.text35);
        text[34] = (TextView)findViewById(R.id.text36);
        text[35] = (TextView)findViewById(R.id.text37);
        text[36] = (TextView)findViewById(R.id.text38);
        text[37] = (TextView)findViewById(R.id.text39);
        text[38] = (TextView)findViewById(R.id.text40);
        text[39] = (TextView)findViewById(R.id.text41);
        text[40] = (TextView)findViewById(R.id.text42);
        text[41] = (TextView)findViewById(R.id.text43);
        text[42] = (TextView)findViewById(R.id.text44);
        text[43] = (TextView)findViewById(R.id.text45);
        text[44] = (TextView)findViewById(R.id.text46);
        text[45] = (TextView)findViewById(R.id.text47);
        text[46] = (TextView)findViewById(R.id.text48);
        text[47] = (TextView)findViewById(R.id.text49);
        text[48] = (TextView)findViewById(R.id.text50);
        text[49] = (TextView)findViewById(R.id.text51);
        text[50] = (TextView)findViewById(R.id.text52);
        text[51] = (TextView)findViewById(R.id.text53);
        text[52] = (TextView)findViewById(R.id.text54);
        text[53] = (TextView)findViewById(R.id.text55);
        text[54] = (TextView)findViewById(R.id.text56);
        text[55] = (TextView)findViewById(R.id.text57);
        text[56] = (TextView)findViewById(R.id.text58);
        text[57] = (TextView)findViewById(R.id.text59);
        text[58] = (TextView)findViewById(R.id.text60);
        text[59] = (TextView)findViewById(R.id.text61);
        text[60] = (TextView)findViewById(R.id.text62);
        text[61] = (TextView)findViewById(R.id.text63);
        text[62] = (TextView)findViewById(R.id.text64);
        text[63] = (TextView)findViewById(R.id.text65);
        text[64] = (TextView)findViewById(R.id.text66);
        text[65] = (TextView)findViewById(R.id.text67);
        text[66] = (TextView)findViewById(R.id.text68);
        text[67] = (TextView)findViewById(R.id.text69);
        text[68] = (TextView)findViewById(R.id.text70);
        text[69] = (TextView)findViewById(R.id.text71);
        text[70] = (TextView)findViewById(R.id.text72);
        text[71] = (TextView)findViewById(R.id.text73);
        text[72] = (TextView)findViewById(R.id.text74);
        text[73] = (TextView)findViewById(R.id.text75);
        text[74] = (TextView)findViewById(R.id.text76);
        text[75] = (TextView)findViewById(R.id.text77);
        text[76] = (TextView)findViewById(R.id.text78);
        text[77] = (TextView)findViewById(R.id.text79);
        text[78] = (TextView)findViewById(R.id.text80);
        text[79] = (TextView)findViewById(R.id.text81);
        text[80] = (TextView)findViewById(R.id.text82);
        text[81] = (TextView)findViewById(R.id.text83);
        text[82] = (TextView)findViewById(R.id.text84);
        text[83] = (TextView)findViewById(R.id.text85);
        text[84] = (TextView)findViewById(R.id.text86);
        text[85] = (TextView)findViewById(R.id.text87);
        text[86] = (TextView)findViewById(R.id.text88);
        text[87] = (TextView)findViewById(R.id.text89);
        text[88] = (TextView)findViewById(R.id.text90);
        text[89] = (TextView)findViewById(R.id.text91);
        text[90] = (TextView)findViewById(R.id.text92);
        text[91] = (TextView)findViewById(R.id.text93);
        text[92] = (TextView)findViewById(R.id.text94);
        text[93] = (TextView)findViewById(R.id.text95);
        text[94] = (TextView)findViewById(R.id.text96);
        text[95] = (TextView)findViewById(R.id.text98);
        text[96] = (TextView)findViewById(R.id.text99);
        text[97] = (TextView)findViewById(R.id.text100);
        text[98] = (TextView)findViewById(R.id.text101);
        text[99] = (TextView)findViewById(R.id.text102);
        text[100] = (TextView)findViewById(R.id.text103);
        text[101] = (TextView)findViewById(R.id.text104);
        text[102] = (TextView)findViewById(R.id.text105);
        text[103] = (TextView)findViewById(R.id.text106);
        text[104] = (TextView)findViewById(R.id.text107);
        text[105] = (TextView)findViewById(R.id.text108);
        text[106] = (TextView)findViewById(R.id.text109);
        text[107] = (TextView)findViewById(R.id.text110);
        text[108] = (TextView)findViewById(R.id.text111);
        text[109] = (TextView)findViewById(R.id.text112);
        text[110] = (TextView)findViewById(R.id.text113);
        text[111] = (TextView)findViewById(R.id.text114);
        text[112] = (TextView)findViewById(R.id.text115);
        text[113] = (TextView)findViewById(R.id.text116);
        text[114] = (TextView)findViewById(R.id.text117);
        text[115] = (TextView)findViewById(R.id.text118);
        text[116] = (TextView)findViewById(R.id.text119);
        text[117] = (TextView)findViewById(R.id.text120);
        text[118] = (TextView)findViewById(R.id.text121);
        text[119] = (TextView)findViewById(R.id.text122);
        text[120] = (TextView)findViewById(R.id.text123);
        text[121] = (TextView)findViewById(R.id.text124);
        text[122] = (TextView)findViewById(R.id.text125);
        text[123] = (TextView)findViewById(R.id.text126);
        text[124] = (TextView)findViewById(R.id.text127);
        text[125] = (TextView)findViewById(R.id.text128);
        text[126] = (TextView)findViewById(R.id.text129);
        text[127] = (TextView)findViewById(R.id.text130);
        text[128] = (TextView)findViewById(R.id.text131);
        text[129] = (TextView)findViewById(R.id.text132);
        text[130] = (TextView)findViewById(R.id.text133);
        text[131] = (TextView)findViewById(R.id.text134);
        text[132] = (TextView)findViewById(R.id.text135);
        text[133] = (TextView)findViewById(R.id.text136);
        text[134] = (TextView)findViewById(R.id.text137);
        text[135] = (TextView)findViewById(R.id.text138);
        text[136] = (TextView)findViewById(R.id.text139);
        text[137] = (TextView)findViewById(R.id.text140);
        text[138] = (TextView)findViewById(R.id.text141);
        text[139] = (TextView)findViewById(R.id.text142);
        text[140] = (TextView)findViewById(R.id.text143);
        text[141] = (TextView)findViewById(R.id.text144);
        text[142] = (TextView)findViewById(R.id.text145);
        text[143] = (TextView)findViewById(R.id.text146);
        text[144] = (TextView)findViewById(R.id.text147);
        text[145] = (TextView)findViewById(R.id.text148);
        text[146] = (TextView)findViewById(R.id.text149);
        text[147] = (TextView)findViewById(R.id.text150);
        text[148] = (TextView)findViewById(R.id.text151);
        text[149] = (TextView)findViewById(R.id.text152);
        text[150] = (TextView)findViewById(R.id.text153);
        text[151] = (TextView)findViewById(R.id.text154);
        text[152] = (TextView)findViewById(R.id.text155);
        text[153] = (TextView)findViewById(R.id.text156);
        text[154] = (TextView)findViewById(R.id.text157);
        text[155] = (TextView)findViewById(R.id.text158);
        text[156] = (TextView)findViewById(R.id.text159);
        text[157] = (TextView)findViewById(R.id.text160);
        text[158] = (TextView)findViewById(R.id.text161);
        text[159] = (TextView)findViewById(R.id.text162);
        text[160] = (TextView)findViewById(R.id.text163);
        text[161] = (TextView)findViewById(R.id.text164);
        text[162] = (TextView)findViewById(R.id.text165);
        text[163] = (TextView)findViewById(R.id.text166);
        text[164] = (TextView)findViewById(R.id.text167);
        text[165] = (TextView)findViewById(R.id.text168);
        text[166] = (TextView)findViewById(R.id.text169);
        text[167] = (TextView)findViewById(R.id.text170);
        text[168] = (TextView)findViewById(R.id.text171);
        text[169] = (TextView)findViewById(R.id.text172);
        text[170] = (TextView)findViewById(R.id.text173);
        text[171] = (TextView)findViewById(R.id.text174);
        text[172] = (TextView)findViewById(R.id.text175);
        text[173] = (TextView)findViewById(R.id.text176);
        text[174] = (TextView)findViewById(R.id.text177);
        text[175] = (TextView)findViewById(R.id.text178);
        text[176] = (TextView)findViewById(R.id.text179);
        text[177] = (TextView)findViewById(R.id.text180);
        text[178] = (TextView)findViewById(R.id.text181);
        text[179] = (TextView)findViewById(R.id.text182);
        text[180] = (TextView)findViewById(R.id.text183);
        text[181] = (TextView)findViewById(R.id.text184);
        text[182] = (TextView)findViewById(R.id.text185);
        text[183] = (TextView)findViewById(R.id.text186);
        text[184] = (TextView)findViewById(R.id.text187);
        text[185] = (TextView)findViewById(R.id.text188);
        text[186] = (TextView)findViewById(R.id.text189);
        text[187] = (TextView)findViewById(R.id.text190);
        text[188] = (TextView)findViewById(R.id.text191);
        text[189] = (TextView)findViewById(R.id.text192);
        text[190] = (TextView)findViewById(R.id.text193);
        text[191] = (TextView)findViewById(R.id.text194);
        text[192] = (TextView)findViewById(R.id.text195);
        text[193] = (TextView)findViewById(R.id.text196);
        text[194] = (TextView)findViewById(R.id.text197);
        text[195] = (TextView)findViewById(R.id.text198);
        text[196] = (TextView)findViewById(R.id.text199);
        text[197] = (TextView)findViewById(R.id.text200);
        text[198] = (TextView)findViewById(R.id.text201);
        text[199] = (TextView)findViewById(R.id.text202);
        text[200] = (TextView)findViewById(R.id.text203);
        text[201] = (TextView)findViewById(R.id.text204);
        text[202] = (TextView)findViewById(R.id.text205);
        text[203] = (TextView)findViewById(R.id.text206);
        text[204] = (TextView)findViewById(R.id.text207);
        text[205] = (TextView)findViewById(R.id.text208);
        text[206] = (TextView)findViewById(R.id.text209);
        text[207] = (TextView)findViewById(R.id.text210);
        text[208] = (TextView)findViewById(R.id.text211);
        text[209] = (TextView)findViewById(R.id.text212);
        text[210] = (TextView)findViewById(R.id.text213);
        text[211] = (TextView)findViewById(R.id.text214);
        text[212] = (TextView)findViewById(R.id.text215);
        text[213] = (TextView)findViewById(R.id.text216);
        text[214] = (TextView)findViewById(R.id.text217);
        text[215] = (TextView)findViewById(R.id.text218);
        text[216] = (TextView)findViewById(R.id.text219);
        text[217] = (TextView)findViewById(R.id.text220);
        text[218] = (TextView)findViewById(R.id.text221);
        text[219] = (TextView)findViewById(R.id.text222);
        text[220] = (TextView)findViewById(R.id.text223);
        text[221] = (TextView)findViewById(R.id.text224);
        text[222] = (TextView)findViewById(R.id.text226);
        text[223] = (TextView)findViewById(R.id.text228);
        text[224] = (TextView)findViewById(R.id.text229);
        text[225] = (TextView)findViewById(R.id.text230);
        text[226] = (TextView)findViewById(R.id.text231);
        text[227] = (TextView)findViewById(R.id.text232);
        text[228] = (TextView)findViewById(R.id.text233);
        text[229] = (TextView)findViewById(R.id.text234);
        text[230] = (TextView)findViewById(R.id.text235);
        text[231] = (TextView)findViewById(R.id.text236);
        text[232] = (TextView)findViewById(R.id.text237);
        text[233] = (TextView)findViewById(R.id.text238);
        text[234] = (TextView)findViewById(R.id.text239);
        text[235] = (TextView)findViewById(R.id.text240);
        text[236] = (TextView)findViewById(R.id.text241);
        text[237] = (TextView)findViewById(R.id.text242);
        text[238] = (TextView)findViewById(R.id.text243);
        text[239] = (TextView)findViewById(R.id.text244);
        text[240] = (TextView)findViewById(R.id.text245);
        text[241] = (TextView)findViewById(R.id.text246);
        text[242] = (TextView)findViewById(R.id.text247);
        text[243] = (TextView)findViewById(R.id.text248);
        text[244] = (TextView)findViewById(R.id.text249);
        text[245] = (TextView)findViewById(R.id.text250);
        text[246] = (TextView)findViewById(R.id.text251);
        text[247] = (TextView)findViewById(R.id.text252);
        text[248] = (TextView)findViewById(R.id.text253);
        text[249] = (TextView)findViewById(R.id.text254);
        text[250] = (TextView)findViewById(R.id.text255);
        text[251] = (TextView)findViewById(R.id.text256);
        text[252] = (TextView)findViewById(R.id.text257);
        text[253] = (TextView)findViewById(R.id.text258);
        text[254] = (TextView)findViewById(R.id.text259);
        text[255] = (TextView)findViewById(R.id.text260);
        text[256] = (TextView)findViewById(R.id.text261);
        text[257] = (TextView)findViewById(R.id.text262);
        text[258] = (TextView)findViewById(R.id.text263);
        text[259] = (TextView)findViewById(R.id.text264);
        text[260] = (TextView)findViewById(R.id.text265);
        text[261] = (TextView)findViewById(R.id.text266);
        text[262] = (TextView)findViewById(R.id.text267);
        text[263] = (TextView)findViewById(R.id.text268);
        text[264] = (TextView)findViewById(R.id.text269);
        text[265] = (TextView)findViewById(R.id.text270);
        text[266] = (TextView)findViewById(R.id.text271);
        text[267] = (TextView)findViewById(R.id.text272);
        text[268] = (TextView)findViewById(R.id.text273);
        text[269] = (TextView)findViewById(R.id.text274);
        text[270] = (TextView)findViewById(R.id.text275);
        text[271] = (TextView)findViewById(R.id.text276);
        text[272] = (TextView)findViewById(R.id.text277);
        text[273] = (TextView)findViewById(R.id.text278);
        text[274] = (TextView)findViewById(R.id.text279);
        text[275] = (TextView)findViewById(R.id.text280);
        text[276] = (TextView)findViewById(R.id.text281);
        text[277] = (TextView)findViewById(R.id.text282);
        text[278] = (TextView)findViewById(R.id.text283);
        text[279] = (TextView)findViewById(R.id.text284);
        text[280] = (TextView)findViewById(R.id.text285);
        text[281] = (TextView)findViewById(R.id.text286);
        text[282] = (TextView)findViewById(R.id.text287);
        text[283] = (TextView)findViewById(R.id.text288);








        subPa = (Spinner)findViewById(R.id.subPa);

        String url = "http://hubsystem-app.nm.flipkart.com/v1/config/fetch?facilityId="+facilityId+"&slotId="+selectedSlot+"&processingAreaId="+selectedPA;

        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    String json = sb.toString();
                    JSONObject jObj = new JSONObject(json);
                    //select only first child of hub to display
                    String st = jObj.getString("status");
                    if (st.equals("200")) {
                        jsonObject = jObj.getJSONObject("data");
                        jsonArray = jsonObject.getJSONArray("config");
                        jsonObject1 = new JSONObject[jsonArray.length()];
                        String items[] = new String[jsonArray.length()+1];

                        int j = 0;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject1[i] = jsonArray.getJSONObject(i);
                            if(!prev.equals(jsonObject1[i].getString("processingAreaId")))
                            {
                                items[j+1] = jsonObject1[i].getString("processingAreaId");
                                j++;
                            }
                            prev = jsonObject1[i].getString("processingAreaId");

                        }
                        items[0] ="Select Processing area";
                        String put[] = new String [j+1];
                        for(int i=0;i<=j;i++)
                        {
                            put[i] = items[i];
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, put);
                        subPa.setAdapter(adapter);
                        subPa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String pa = (String) parent.getItemAtPosition(position);
                                if(!pa.equals("Select Processing area"))
                                {
                                    int j = 0;
                                    for(int i=0;i<jsonArray.length();i++)
                                    {

                                        String add="";
                                        try {
                                            if(pa.equals(jsonObject1[i].getString("processingAreaId")))
                                            {
                                                if(jsonObject1[i].getString("processingAreaId")!="null")
                                                    add = add+ "\nID : "+jsonObject1[i].getString("processingAreaId");
                                                if(jsonObject1[i].getString("destinationCoC")!="null")
                                                    add = add+"\nDESTINATION : "+jsonObject1[i].getString("destinationCoC");
                                                if(jsonObject1[i].getString("vendor")!="null")
                                                    add = add  +"\nVENDOR : "+jsonObject1[i].getString("vendor");
                                                if(jsonObject1[i].getString("goodType")!="null")
                                                    add = add +"\nGOODS TYPE : "+jsonObject1[i].getString("goodType");
                                                if(jsonObject1[i].getString("flow")!="null")
                                                    add = add +"\nFLOW : "+jsonObject1[i].getString("flow");
                                                add = add+"\n-------------------------------------------";

                                                text[j].setText(add);
                                                j++;
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



                                    }
                                    for(int i = j;i<221;i++)
                                    {
                                        text[i].setText("");
                                    }

                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show__config, menu);
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
