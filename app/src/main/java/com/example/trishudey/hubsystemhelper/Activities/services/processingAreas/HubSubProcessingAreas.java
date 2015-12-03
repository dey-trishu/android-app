package com.example.trishudey.hubsystemhelper.Activities.services.processingAreas;
/*
Show the sub processing areas within a processing area
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trishudey.hubsystemhelper.R;
import com.example.trishudey.hubsystemhelper.Activities.main.LoginPage;
import com.example.trishudey.hubsystemhelper.Activities.services.resources.ShowResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HubSubProcessingAreas extends Activity {

    ImageButton image[] = new ImageButton[304];
    CheckBox check[] = new CheckBox[304];
    TextView text[] = new TextView[304];
    JSONArray jsonArray;
    JSONObject jsonObject1[];
    JSONObject jObj[];
    Button get;
    String subarea="";
    String PAid="";
    String hubId="";
    public static String task;
    public static String facility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_sub_processing_areas);
        image[0] = (ImageButton)findViewById(R.id.imageButton16);
        image[1] = (ImageButton)findViewById(R.id.imageButton17);
        image[2] = (ImageButton)findViewById(R.id.imageButton18);
        image[3] = (ImageButton)findViewById(R.id.imageButton19);
        image[4] = (ImageButton)findViewById(R.id.imageButton20);
        image[5] = (ImageButton)findViewById(R.id.imageButton21);
        image[6] = (ImageButton)findViewById(R.id.imageButton22);
        image[7] = (ImageButton)findViewById(R.id.imageButton23);
        image[8] = (ImageButton)findViewById(R.id.imageButton24);
        image[9] = (ImageButton)findViewById(R.id.imageButton13);
        image[10] = (ImageButton)findViewById(R.id.imageButton14);
        image[11] = (ImageButton)findViewById(R.id.imageButton15);
        image[12] = (ImageButton)findViewById(R.id.imageButton25);
        image[13] = (ImageButton)findViewById(R.id.imageButton26);
        image[14] = (ImageButton)findViewById(R.id.imageButton27);
        image[15] = (ImageButton)findViewById(R.id.imageButton28);
        image[16] = (ImageButton)findViewById(R.id.imageButton31);
        image[17] = (ImageButton)findViewById(R.id.imageButton32);
        image[18] = (ImageButton)findViewById(R.id.imageButton33);
        image[19] = (ImageButton)findViewById(R.id.imageButton34);
        image[20] = (ImageButton)findViewById(R.id.imageButton35);
        image[21] = (ImageButton)findViewById(R.id.imageButton36);
        image[22] = (ImageButton)findViewById(R.id.imageButton37);
        image[23] = (ImageButton)findViewById(R.id.imageButton38);
        image[24] = (ImageButton)findViewById(R.id.imageButton39);
        image[25] = (ImageButton)findViewById(R.id.imageButton40);
        image[26] = (ImageButton)findViewById(R.id.imageButton41);
        image[27] = (ImageButton)findViewById(R.id.imageButton42);
        image[28] = (ImageButton)findViewById(R.id.imageButton43);
        image[29] = (ImageButton)findViewById(R.id.imageButton44);
        image[30] = (ImageButton)findViewById(R.id.imageButton45);
        image[31] = (ImageButton)findViewById(R.id.imageButton46);
        image[32] = (ImageButton)findViewById(R.id.imageButton47);
        image[33] = (ImageButton)findViewById(R.id.imageButton48);
        image[34] = (ImageButton)findViewById(R.id.imageButton49);
        image[35] = (ImageButton)findViewById(R.id.imageButton50);
        image[36] = (ImageButton)findViewById(R.id.imageButton51);
        image[37] = (ImageButton)findViewById(R.id.imageButton52);
        image[38] = (ImageButton)findViewById(R.id.imageButton53);
        image[39] = (ImageButton)findViewById(R.id.imageButton54);
        image[40] = (ImageButton)findViewById(R.id.imageButton55);
        image[41] = (ImageButton)findViewById(R.id.imageButton56);
        image[42] = (ImageButton)findViewById(R.id.imageButton57);
        image[43] = (ImageButton)findViewById(R.id.imageButton58);
        image[44] = (ImageButton)findViewById(R.id.imageButton59);
        image[45] = (ImageButton)findViewById(R.id.imageButton60);
        image[46] = (ImageButton)findViewById(R.id.imageButton61);
        image[47] = (ImageButton)findViewById(R.id.imageButton62);
        image[48] = (ImageButton)findViewById(R.id.imageButton63);
        image[49] = (ImageButton)findViewById(R.id.imageButton64);
        image[50] = (ImageButton)findViewById(R.id.imageButton65);
        image[51] = (ImageButton)findViewById(R.id.imageButton66);
        image[52] = (ImageButton)findViewById(R.id.imageButton67);
        image[53] = (ImageButton)findViewById(R.id.imageButton68);
        image[54] = (ImageButton)findViewById(R.id.imageButton69);
        image[55] = (ImageButton)findViewById(R.id.imageButton70);
        image[56] = (ImageButton)findViewById(R.id.imageButton71);
        image[57] = (ImageButton)findViewById(R.id.imageButton72);
        image[58] = (ImageButton)findViewById(R.id.imageButton73);
//        image[59] = (ImageButton)findViewById(R.id.imageButton74);
//        image[60] = (ImageButton)findViewById(R.id.imageButton75);
//        image[61] = (ImageButton)findViewById(R.id.imageButton76);
//        image[62] = (ImageButton)findViewById(R.id.imageButton77);
//        image[63] = (ImageButton)findViewById(R.id.imageButton78);
//        image[64] = (ImageButton)findViewById(R.id.imageButton79);
//        image[65] = (ImageButton)findViewById(R.id.imageButton80);
//        image[66] = (ImageButton)findViewById(R.id.imageButton81);
//        image[67] = (ImageButton)findViewById(R.id.imageButton82);
//        image[68] = (ImageButton)findViewById(R.id.imageButton83);
//        image[69] = (ImageButton)findViewById(R.id.imageButton84);
//        image[70] = (ImageButton)findViewById(R.id.imageButton85);
//        image[71] = (ImageButton)findViewById(R.id.imageButton86);
//        image[72] = (ImageButton)findViewById(R.id.imageButton87);
//        image[73] = (ImageButton)findViewById(R.id.imageButton88);
//        image[74] = (ImageButton)findViewById(R.id.imageButton89);
//        image[75] = (ImageButton)findViewById(R.id.imageButton90);
//        image[76] = (ImageButton)findViewById(R.id.imageButton91);
//        image[77] = (ImageButton)findViewById(R.id.imageButton92);
//        image[78] = (ImageButton)findViewById(R.id.imageButton93);
//        image[79] = (ImageButton)findViewById(R.id.imageButton94);
//        image[80] = (ImageButton)findViewById(R.id.imageButton95);
//        image[81] = (ImageButton)findViewById(R.id.imageButton96);
//        image[82] = (ImageButton)findViewById(R.id.imageButton97);
//        image[83] = (ImageButton)findViewById(R.id.imageButton98);
//        image[84] = (ImageButton)findViewById(R.id.imageButton99);
//        image[85] = (ImageButton)findViewById(R.id.imageButton100);
//        image[86] = (ImageButton)findViewById(R.id.imageButton101);
//        image[87] = (ImageButton)findViewById(R.id.imageButton102);
//        image[88] = (ImageButton)findViewById(R.id.imageButton103);
//        image[89] = (ImageButton)findViewById(R.id.imageButton104);
//        image[90] = (ImageButton)findViewById(R.id.imageButton105);
//        image[91] = (ImageButton)findViewById(R.id.imageButton106);
//        image[92] = (ImageButton)findViewById(R.id.imageButton107);
//        image[93] = (ImageButton)findViewById(R.id.imageButton108);
//        image[94] = (ImageButton)findViewById(R.id.imageButton109);
//        image[95] = (ImageButton)findViewById(R.id.imageButton110);
//        image[96] = (ImageButton)findViewById(R.id.imageButton111);
//        image[97] = (ImageButton)findViewById(R.id.imageButton112);
//        image[98] = (ImageButton)findViewById(R.id.imageButton113);
//        image[99] = (ImageButton)findViewById(R.id.imageButton114);
//        image[100] = (ImageButton)findViewById(R.id.imageButton115);
//        image[101] = (ImageButton)findViewById(R.id.imageButton116);
//        image[102] = (ImageButton)findViewById(R.id.imageButton117);
//        image[103] = (ImageButton)findViewById(R.id.imageButton118);
//        image[104] = (ImageButton)findViewById(R.id.imageButton119);
//        image[105] = (ImageButton)findViewById(R.id.imageButton120);
//        image[106] = (ImageButton)findViewById(R.id.imageButton121);
//        image[107] = (ImageButton)findViewById(R.id.imageButton122);
//        image[108] = (ImageButton)findViewById(R.id.imageButton123);
//        image[109] = (ImageButton)findViewById(R.id.imageButton124);
//        image[110] = (ImageButton)findViewById(R.id.imageButton125);
//        image[111] = (ImageButton)findViewById(R.id.imageButton126);
//        image[112] = (ImageButton)findViewById(R.id.imageButton127);
//        image[113] = (ImageButton)findViewById(R.id.imageButton128);
//        image[114] = (ImageButton)findViewById(R.id.imageButton129);
//        image[115] = (ImageButton)findViewById(R.id.imageButton130);
//        image[116] = (ImageButton)findViewById(R.id.imageButton131);
//        image[117] = (ImageButton)findViewById(R.id.imageButton132);
//        image[118] = (ImageButton)findViewById(R.id.imageButton133);
//        image[119] = (ImageButton)findViewById(R.id.imageButton134);
//        image[120] = (ImageButton)findViewById(R.id.imageButton135);
//        image[121] = (ImageButton)findViewById(R.id.imageButton136);
//        image[122] = (ImageButton)findViewById(R.id.imageButton137);
//        image[123] = (ImageButton)findViewById(R.id.imageButton138);
//        image[124] = (ImageButton)findViewById(R.id.imageButton139);
//        image[125] = (ImageButton)findViewById(R.id.imageButton140);
//        image[126] = (ImageButton)findViewById(R.id.imageButton141);
//        image[127] = (ImageButton)findViewById(R.id.imageButton142);
//        image[128] = (ImageButton)findViewById(R.id.imageButton143);
//        image[129] = (ImageButton)findViewById(R.id.imageButton144);
//        image[130] = (ImageButton)findViewById(R.id.imageButton145);
//        image[131] = (ImageButton)findViewById(R.id.imageButton146);
//        image[132] = (ImageButton)findViewById(R.id.imageButton147);
//        image[133] = (ImageButton)findViewById(R.id.imageButton148);
//        image[134] = (ImageButton)findViewById(R.id.imageButton149);
//        image[135] = (ImageButton)findViewById(R.id.imageButton150);
//        image[136] = (ImageButton)findViewById(R.id.imageButton151);
//        image[137] = (ImageButton)findViewById(R.id.imageButton152);
//        image[138] = (ImageButton)findViewById(R.id.imageButton153);
//        image[139] = (ImageButton)findViewById(R.id.imageButton154);
//        image[140] = (ImageButton)findViewById(R.id.imageButton155);
//        image[141] = (ImageButton)findViewById(R.id.imageButton156);
//        image[142] = (ImageButton)findViewById(R.id.imageButton157);
//        image[143] = (ImageButton)findViewById(R.id.imageButton158);
//        image[144] = (ImageButton)findViewById(R.id.imageButton159);
//        image[145] = (ImageButton)findViewById(R.id.imageButton160);
//        image[146] = (ImageButton)findViewById(R.id.imageButton161);
//        image[147] = (ImageButton)findViewById(R.id.imageButton162);
//        image[148] = (ImageButton)findViewById(R.id.imageButton163);
//        image[149] = (ImageButton)findViewById(R.id.imageButton164);
//        image[150] = (ImageButton)findViewById(R.id.imageButton165);
//        image[151] = (ImageButton)findViewById(R.id.imageButton166);
//        image[152] = (ImageButton)findViewById(R.id.imageButton167);
//        image[153] = (ImageButton)findViewById(R.id.imageButton168);
//        image[154] = (ImageButton)findViewById(R.id.imageButton169);
//        image[155] = (ImageButton)findViewById(R.id.imageButton170);
//        image[156] = (ImageButton)findViewById(R.id.imageButton171);
//        image[157] = (ImageButton)findViewById(R.id.imageButton172);
//        image[158] = (ImageButton)findViewById(R.id.imageButton173);
//        image[159] = (ImageButton)findViewById(R.id.imageButton174);
//        image[160] = (ImageButton)findViewById(R.id.imageButton175);
//        image[161] = (ImageButton)findViewById(R.id.imageButton176);
//        image[162] = (ImageButton)findViewById(R.id.imageButton177);
//        image[163] = (ImageButton)findViewById(R.id.imageButton178);
//        image[164] = (ImageButton)findViewById(R.id.imageButton179);
//        image[165] = (ImageButton)findViewById(R.id.imageButton180);
//        image[166] = (ImageButton)findViewById(R.id.imageButton181);
//        image[167] = (ImageButton)findViewById(R.id.imageButton182);
//        image[168] = (ImageButton)findViewById(R.id.imageButton183);
//        image[169] = (ImageButton)findViewById(R.id.imageButton184);
//        image[170] = (ImageButton)findViewById(R.id.imageButton185);
//        image[171] = (ImageButton)findViewById(R.id.imageButton186);
//        image[172] = (ImageButton)findViewById(R.id.imageButton187);
//        image[173] = (ImageButton)findViewById(R.id.imageButton188);
//        image[174] = (ImageButton)findViewById(R.id.imageButton189);
//        image[175] = (ImageButton)findViewById(R.id.imageButton190);
//        image[176] = (ImageButton)findViewById(R.id.imageButton191);
//        image[177] = (ImageButton)findViewById(R.id.imageButton192);
//        image[178] = (ImageButton)findViewById(R.id.imageButton193);
//        image[179] = (ImageButton)findViewById(R.id.imageButton194);
//        image[180] = (ImageButton)findViewById(R.id.imageButton195);
//        image[181] = (ImageButton)findViewById(R.id.imageButton196);
//        image[182] = (ImageButton)findViewById(R.id.imageButton197);
//        image[183] = (ImageButton)findViewById(R.id.imageButton198);
//        image[184] = (ImageButton)findViewById(R.id.imageButton199);
//        image[185] = (ImageButton)findViewById(R.id.imageButton200);
//        image[186] = (ImageButton)findViewById(R.id.imageButton201);
//        image[187] = (ImageButton)findViewById(R.id.imageButton202);
//        image[188] = (ImageButton)findViewById(R.id.imageButton203);
//        image[189] = (ImageButton)findViewById(R.id.imageButton204);
//        image[190] = (ImageButton)findViewById(R.id.imageButton205);
//        image[191] = (ImageButton)findViewById(R.id.imageButton206);
//        image[192] = (ImageButton)findViewById(R.id.imageButton207);
//        image[193] = (ImageButton)findViewById(R.id.imageButton208);
//        image[194] = (ImageButton)findViewById(R.id.imageButton209);
//        image[195] = (ImageButton)findViewById(R.id.imageButton210);
//        image[196] = (ImageButton)findViewById(R.id.imageButton211);
//        image[197] = (ImageButton)findViewById(R.id.imageButton212);
//        image[198] = (ImageButton)findViewById(R.id.imageButton213);
//        image[199] = (ImageButton)findViewById(R.id.imageButton214);
//        image[200] = (ImageButton)findViewById(R.id.imageButton215);
//        image[201] = (ImageButton)findViewById(R.id.imageButton216);
//        image[202] = (ImageButton)findViewById(R.id.imageButton217);
//        image[203] = (ImageButton)findViewById(R.id.imageButton218);
//        image[204] = (ImageButton)findViewById(R.id.imageButton219);
//        image[205] = (ImageButton)findViewById(R.id.imageButton220);
//        image[206] = (ImageButton)findViewById(R.id.imageButton221);
//        image[207] = (ImageButton)findViewById(R.id.imageButton222);
//        image[208] = (ImageButton)findViewById(R.id.imageButton223);
//        image[209] = (ImageButton)findViewById(R.id.imageButton224);
//        image[210] = (ImageButton)findViewById(R.id.imageButton225);
//        image[211] = (ImageButton)findViewById(R.id.imageButton226);
//        image[212] = (ImageButton)findViewById(R.id.imageButton227);
//        image[213] = (ImageButton)findViewById(R.id.imageButton228);
//        image[214] = (ImageButton)findViewById(R.id.imageButton229);
//        image[215] = (ImageButton)findViewById(R.id.imageButton230);
//        image[216] = (ImageButton)findViewById(R.id.imageButton231);
//        image[217] = (ImageButton)findViewById(R.id.imageButton232);
//        image[218] = (ImageButton)findViewById(R.id.imageButton233);
//        image[219] = (ImageButton)findViewById(R.id.imageButton234);
//        image[220] = (ImageButton)findViewById(R.id.imageButton235);
//        image[221] = (ImageButton)findViewById(R.id.imageButton236);
//        image[222] = (ImageButton)findViewById(R.id.imageButton237);
//        image[223] = (ImageButton)findViewById(R.id.imageButton238);
//        image[224] = (ImageButton)findViewById(R.id.imageButton239);
//        image[225] = (ImageButton)findViewById(R.id.imageButton240);
//        image[226] = (ImageButton)findViewById(R.id.imageButton241);
//        image[227] = (ImageButton)findViewById(R.id.imageButton242);
//        image[228] = (ImageButton)findViewById(R.id.imageButton243);
//        image[229] = (ImageButton)findViewById(R.id.imageButton244);
//        image[230] = (ImageButton)findViewById(R.id.imageButton245);
//        image[231] = (ImageButton)findViewById(R.id.imageButton246);
//        image[232] = (ImageButton)findViewById(R.id.imageButton247);
//        image[233] = (ImageButton)findViewById(R.id.imageButton248);
//        image[234] = (ImageButton)findViewById(R.id.imageButton249);
//        image[235] = (ImageButton)findViewById(R.id.imageButton240);
//        image[236] = (ImageButton)findViewById(R.id.imageButton241);
//        image[237] = (ImageButton)findViewById(R.id.imageButton242);
//        image[238] = (ImageButton)findViewById(R.id.imageButton243);
//        image[239] = (ImageButton)findViewById(R.id.imageButton244);
//        image[240] = (ImageButton)findViewById(R.id.imageButton245);
//        image[241] = (ImageButton)findViewById(R.id.imageButton246);
//        image[242] = (ImageButton)findViewById(R.id.imageButton247);
//        image[243] = (ImageButton)findViewById(R.id.imageButton248);
//        image[244] = (ImageButton)findViewById(R.id.imageButton249);
//        image[245] = (ImageButton)findViewById(R.id.imageButton250);
//        image[246] = (ImageButton)findViewById(R.id.imageButton251);
//        image[247] = (ImageButton)findViewById(R.id.imageButton252);
//        image[248] = (ImageButton)findViewById(R.id.imageButton253);
//        image[249] = (ImageButton)findViewById(R.id.imageButton254);
//        image[250] = (ImageButton)findViewById(R.id.imageButton255);
//        image[251] = (ImageButton)findViewById(R.id.imageButton256);
//        image[252] = (ImageButton)findViewById(R.id.imageButton257);
//        image[253] = (ImageButton)findViewById(R.id.imageButton258);
//        image[254] = (ImageButton)findViewById(R.id.imageButton259);
//        image[255] = (ImageButton)findViewById(R.id.imageButton260);
//        image[256] = (ImageButton)findViewById(R.id.imageButton261);
//        image[257] = (ImageButton)findViewById(R.id.imageButton262);
//        image[258] = (ImageButton)findViewById(R.id.imageButton263);
//        image[259] = (ImageButton)findViewById(R.id.imageButton264);
//        image[260] = (ImageButton)findViewById(R.id.imageButton265);
//        image[261] = (ImageButton)findViewById(R.id.imageButton266);
//        image[262] = (ImageButton)findViewById(R.id.imageButton267);
//        image[263] = (ImageButton)findViewById(R.id.imageButton268);
//        image[264] = (ImageButton)findViewById(R.id.imageButton269);
//        image[265] = (ImageButton)findViewById(R.id.imageButton270);
//        image[266] = (ImageButton)findViewById(R.id.imageButton271);
//        image[267] = (ImageButton)findViewById(R.id.imageButton272);
//        image[268] = (ImageButton)findViewById(R.id.imageButton273);
//        image[269] = (ImageButton)findViewById(R.id.imageButton274);
//        image[270] = (ImageButton)findViewById(R.id.imageButton275);
//        image[271] = (ImageButton)findViewById(R.id.imageButton276);
//        image[272] = (ImageButton)findViewById(R.id.imageButton277);
//        image[273] = (ImageButton)findViewById(R.id.imageButton278);
//        image[274] = (ImageButton)findViewById(R.id.imageButton279);
//        image[275] = (ImageButton)findViewById(R.id.imageButton280);
//        image[276] = (ImageButton)findViewById(R.id.imageButton281);
//        image[277] = (ImageButton)findViewById(R.id.imageButton282);
//        image[278] = (ImageButton)findViewById(R.id.imageButton283);
//        image[279] = (ImageButton)findViewById(R.id.imageButton284);
//        image[280] = (ImageButton)findViewById(R.id.imageButton285);
//        image[281] = (ImageButton)findViewById(R.id.imageButton286);
//        image[282] = (ImageButton)findViewById(R.id.imageButton287);
//        image[283] = (ImageButton)findViewById(R.id.imageButton288);
//        image[284] = (ImageButton)findViewById(R.id.imageButton289);
//        image[285] = (ImageButton)findViewById(R.id.imageButton290);
//        image[286] = (ImageButton)findViewById(R.id.imageButton291);
//        image[287] = (ImageButton)findViewById(R.id.imageButton292);
//        image[288] = (ImageButton)findViewById(R.id.imageButton293);
//        image[289] = (ImageButton)findViewById(R.id.imageButton294);
//        image[290] = (ImageButton)findViewById(R.id.imageButton295);
//        image[291] = (ImageButton)findViewById(R.id.imageButton296);
//        image[292] = (ImageButton)findViewById(R.id.imageButton297);
//        image[293] = (ImageButton)findViewById(R.id.imageButton298);
//        image[294] = (ImageButton)findViewById(R.id.imageButton299);
//        image[295] = (ImageButton)findViewById(R.id.imageButton300);
//        image[296] = (ImageButton)findViewById(R.id.imageButton301);
//        image[297] = (ImageButton)findViewById(R.id.imageButton302);
//        image[298] = (ImageButton)findViewById(R.id.imageButton303);
//        image[299] = (ImageButton)findViewById(R.id.imageButton304);
//        image[300] = (ImageButton)findViewById(R.id.imageButton305);
//        image[301] = (ImageButton)findViewById(R.id.imageButton306);
//        image[302] = (ImageButton)findViewById(R.id.imageButton307);
//        image[303] = (ImageButton)findViewById(R.id.imageButton308);


        check[0] = (CheckBox)findViewById(R.id.checkBox10);
        check[1] = (CheckBox)findViewById(R.id.checkBox11);
        check[2] = (CheckBox)findViewById(R.id.checkBox12);
        check[3] = (CheckBox)findViewById(R.id.checkBox13);
        check[4] = (CheckBox)findViewById(R.id.checkBox14);
        check[5] = (CheckBox)findViewById(R.id.checkBox15);
        check[6] = (CheckBox)findViewById(R.id.checkBox16);
        check[7] = (CheckBox)findViewById(R.id.checkBox17);
        check[8] = (CheckBox)findViewById(R.id.checkBox18);
        check[9] = (CheckBox)findViewById(R.id.checkBox19);
        check[10] = (CheckBox)findViewById(R.id.checkBox20);
        check[11] = (CheckBox)findViewById(R.id.checkBox21);
        check[12] = (CheckBox)findViewById(R.id.checkBox22);
        check[13] = (CheckBox)findViewById(R.id.checkBox23);
        check[14] = (CheckBox)findViewById(R.id.checkBox24);
        check[15] = (CheckBox)findViewById(R.id.checkBox25);
        check[16] = (CheckBox)findViewById(R.id.checkBox31);
        check[17] = (CheckBox)findViewById(R.id.checkBox32);
        check[18] = (CheckBox)findViewById(R.id.checkBox33);
        check[19] = (CheckBox)findViewById(R.id.checkBox34);
        check[20] = (CheckBox)findViewById(R.id.checkBox35);
        check[21] = (CheckBox)findViewById(R.id.checkBox36);
        check[22] = (CheckBox)findViewById(R.id.checkBox37);
        check[23] = (CheckBox)findViewById(R.id.checkBox38);
        check[24] = (CheckBox)findViewById(R.id.checkBox39);
        check[25] = (CheckBox)findViewById(R.id.checkBox40);
        check[26] = (CheckBox)findViewById(R.id.checkBox41);
        check[27] = (CheckBox)findViewById(R.id.checkBox42);
        check[28] = (CheckBox)findViewById(R.id.checkBox43);
        check[29] = (CheckBox)findViewById(R.id.checkBox44);
        check[30] = (CheckBox)findViewById(R.id.checkBox45);
        check[31] = (CheckBox)findViewById(R.id.checkBox46);
        check[32] = (CheckBox)findViewById(R.id.checkBox47);
        check[33] = (CheckBox)findViewById(R.id.checkBox48);
        check[34] = (CheckBox)findViewById(R.id.checkBox49);
        check[35] = (CheckBox)findViewById(R.id.checkBox50);
        check[36] = (CheckBox)findViewById(R.id.checkBox51);
        check[37] = (CheckBox)findViewById(R.id.checkBox52);
        check[38] = (CheckBox)findViewById(R.id.checkBox53);
        check[39] = (CheckBox)findViewById(R.id.checkBox54);
        check[40] = (CheckBox)findViewById(R.id.checkBox55);
        check[41] = (CheckBox)findViewById(R.id.checkBox56);
        check[42] = (CheckBox)findViewById(R.id.checkBox57);
        check[43] = (CheckBox)findViewById(R.id.checkBox58);
        check[44] = (CheckBox)findViewById(R.id.checkBox59);
        check[45] = (CheckBox)findViewById(R.id.checkBox60);
        check[46] = (CheckBox)findViewById(R.id.checkBox61);
        check[47] = (CheckBox)findViewById(R.id.checkBox62);
        check[48] = (CheckBox)findViewById(R.id.checkBox63);
        check[49] = (CheckBox)findViewById(R.id.checkBox64);
        check[50] = (CheckBox)findViewById(R.id.checkBox65);
        check[51] = (CheckBox)findViewById(R.id.checkBox66);
        check[52] = (CheckBox)findViewById(R.id.checkBox67);
        check[53] = (CheckBox)findViewById(R.id.checkBox68);
        check[54] = (CheckBox)findViewById(R.id.checkBox69);
        check[55] = (CheckBox)findViewById(R.id.checkBox70);
        check[56] = (CheckBox)findViewById(R.id.checkBox71);
        check[57] = (CheckBox)findViewById(R.id.checkBox72);
        check[58] = (CheckBox)findViewById(R.id.checkBox73);
//        check[59] = (CheckBox)findViewById(R.id.checkBox74);
//        check[60] = (CheckBox)findViewById(R.id.checkBox75);
//        check[61] = (CheckBox)findViewById(R.id.checkBox76);
//        check[62] = (CheckBox)findViewById(R.id.checkBox77);
//        check[63] = (CheckBox)findViewById(R.id.checkBox78);
//        check[64] = (CheckBox)findViewById(R.id.checkBox79);
//        check[65] = (CheckBox)findViewById(R.id.checkBox80);
//        check[66] = (CheckBox)findViewById(R.id.checkBox81);
//        check[67] = (CheckBox)findViewById(R.id.checkBox82);
//        check[68] = (CheckBox)findViewById(R.id.checkBox83);
//        check[69] = (CheckBox)findViewById(R.id.checkBox84);
//        check[70] = (CheckBox)findViewById(R.id.checkBox85);
//        check[71] = (CheckBox)findViewById(R.id.checkBox86);
//        check[72] = (CheckBox)findViewById(R.id.checkBox87);
//        check[73] = (CheckBox)findViewById(R.id.checkBox88);
//        check[74] = (CheckBox)findViewById(R.id.checkBox89);
//        check[75] = (CheckBox)findViewById(R.id.checkBox90);
//        check[76] = (CheckBox)findViewById(R.id.checkBox91);
//        check[77] = (CheckBox)findViewById(R.id.checkBox92);
//        check[78] = (CheckBox)findViewById(R.id.checkBox93);
//        check[79] = (CheckBox)findViewById(R.id.checkBox94);
//        check[80] = (CheckBox)findViewById(R.id.checkBox95);
//        check[81] = (CheckBox)findViewById(R.id.checkBox96);
//        check[82] = (CheckBox)findViewById(R.id.checkBox97);
//        check[83] = (CheckBox)findViewById(R.id.checkBox98);
//        check[84] = (CheckBox)findViewById(R.id.checkBox99);
//        check[85] = (CheckBox)findViewById(R.id.checkBox100);
//        check[86] = (CheckBox)findViewById(R.id.checkBox101);
//        check[87] = (CheckBox)findViewById(R.id.checkBox102);
//        check[88] = (CheckBox)findViewById(R.id.checkBox103);
//        check[89] = (CheckBox)findViewById(R.id.checkBox104);
//        check[90] = (CheckBox)findViewById(R.id.checkBox105);
//        check[91] = (CheckBox)findViewById(R.id.checkBox106);
//        check[92] = (CheckBox)findViewById(R.id.checkBox107);
//        check[93] = (CheckBox)findViewById(R.id.checkBox108);
//        check[94] = (CheckBox)findViewById(R.id.checkBox109);
//        check[95] = (CheckBox)findViewById(R.id.checkBox110);
//        check[96] = (CheckBox)findViewById(R.id.checkBox111);
//        check[97] = (CheckBox)findViewById(R.id.checkBox112);
//        check[98] = (CheckBox)findViewById(R.id.checkBox113);
//        check[99] = (CheckBox)findViewById(R.id.checkBox114);
//        check[100] = (CheckBox)findViewById(R.id.checkBox115);
//        check[101] = (CheckBox)findViewById(R.id.checkBox116);
//        check[102] = (CheckBox)findViewById(R.id.checkBox117);
//        check[103] = (CheckBox)findViewById(R.id.checkBox118);
//        check[104] = (CheckBox)findViewById(R.id.checkBox119);
//        check[105] = (CheckBox)findViewById(R.id.checkBox120);
//        check[106] = (CheckBox)findViewById(R.id.checkBox121);
//        check[107] = (CheckBox)findViewById(R.id.checkBox122);
//        check[108] = (CheckBox)findViewById(R.id.checkBox123);
//        check[109] = (CheckBox)findViewById(R.id.checkBox124);
//        check[110] = (CheckBox)findViewById(R.id.checkBox125);
//        check[111] = (CheckBox)findViewById(R.id.checkBox126);
//        check[112] = (CheckBox)findViewById(R.id.checkBox127);
//        check[113] = (CheckBox)findViewById(R.id.checkBox128);
//        check[114] = (CheckBox)findViewById(R.id.checkBox129);
//        check[115] = (CheckBox)findViewById(R.id.checkBox130);
//        check[116] = (CheckBox)findViewById(R.id.checkBox131);
//        check[117] = (CheckBox)findViewById(R.id.checkBox132);
//        check[118] = (CheckBox)findViewById(R.id.checkBox133);
//        check[119] = (CheckBox)findViewById(R.id.checkBox134);
//        check[120] = (CheckBox)findViewById(R.id.checkBox135);
//        check[121] = (CheckBox)findViewById(R.id.checkBox136);
//        check[122] = (CheckBox)findViewById(R.id.checkBox137);
//        check[123] = (CheckBox)findViewById(R.id.checkBox138);
//        check[124] = (CheckBox)findViewById(R.id.checkBox139);
//        check[125] = (CheckBox)findViewById(R.id.checkBox140);
//        check[126] = (CheckBox)findViewById(R.id.checkBox141);
//        check[127] = (CheckBox)findViewById(R.id.checkBox142);
//        check[128] = (CheckBox)findViewById(R.id.checkBox143);
//        check[129] = (CheckBox)findViewById(R.id.checkBox144);
//        check[130] = (CheckBox)findViewById(R.id.checkBox145);
//        check[131] = (CheckBox)findViewById(R.id.checkBox146);
//        check[132] = (CheckBox)findViewById(R.id.checkBox147);
//        check[133] = (CheckBox)findViewById(R.id.checkBox148);
//        check[134] = (CheckBox)findViewById(R.id.checkBox149);
//        check[135] = (CheckBox)findViewById(R.id.checkBox150);
//        check[136] = (CheckBox)findViewById(R.id.checkBox151);
//        check[137] = (CheckBox)findViewById(R.id.checkBox152);
//        check[138] = (CheckBox)findViewById(R.id.checkBox153);
//        check[139] = (CheckBox)findViewById(R.id.checkBox154);
//        check[140] = (CheckBox)findViewById(R.id.checkBox155);
//        check[141] = (CheckBox)findViewById(R.id.checkBox156);
//        check[142] = (CheckBox)findViewById(R.id.checkBox157);
//        check[143] = (CheckBox)findViewById(R.id.checkBox158);
//        check[144] = (CheckBox)findViewById(R.id.checkBox159);
//        check[145] = (CheckBox)findViewById(R.id.checkBox150);
//        check[146] = (CheckBox)findViewById(R.id.checkBox151);
//        check[147] = (CheckBox)findViewById(R.id.checkBox152);
//        check[148] = (CheckBox)findViewById(R.id.checkBox153);
//        check[149] = (CheckBox)findViewById(R.id.checkBox154);
//        check[150] = (CheckBox)findViewById(R.id.checkBox155);
//        check[151] = (CheckBox)findViewById(R.id.checkBox156);
//        check[152] = (CheckBox)findViewById(R.id.checkBox157);
//        check[153] = (CheckBox)findViewById(R.id.checkBox158);
//        check[154] = (CheckBox)findViewById(R.id.checkBox159);
//        check[155] = (CheckBox)findViewById(R.id.checkBox160);
//        check[156] = (CheckBox)findViewById(R.id.checkBox161);
//        check[157] = (CheckBox)findViewById(R.id.checkBox162);
//        check[158] = (CheckBox)findViewById(R.id.checkBox163);
//        check[159] = (CheckBox)findViewById(R.id.checkBox164);
//        check[160] = (CheckBox)findViewById(R.id.checkBox165);
//        check[161] = (CheckBox)findViewById(R.id.checkBox166);
//        check[162] = (CheckBox)findViewById(R.id.checkBox167);
//        check[163] = (CheckBox)findViewById(R.id.checkBox168);
//        check[164] = (CheckBox)findViewById(R.id.checkBox169);
//        check[165] = (CheckBox)findViewById(R.id.checkBox170);
//        check[166] = (CheckBox)findViewById(R.id.checkBox171);
//        check[167] = (CheckBox)findViewById(R.id.checkBox172);
//        check[168] = (CheckBox)findViewById(R.id.checkBox173);
//        check[169] = (CheckBox)findViewById(R.id.checkBox174);
//        check[170] = (CheckBox)findViewById(R.id.checkBox175);
//        check[171] = (CheckBox)findViewById(R.id.checkBox176);
//        check[172] = (CheckBox)findViewById(R.id.checkBox177);
//        check[173] = (CheckBox)findViewById(R.id.checkBox178);
//        check[174] = (CheckBox)findViewById(R.id.checkBox179);
//        check[175] = (CheckBox)findViewById(R.id.checkBox180);
//        check[176] = (CheckBox)findViewById(R.id.checkBox181);
//        check[177] = (CheckBox)findViewById(R.id.checkBox182);
//        check[178] = (CheckBox)findViewById(R.id.checkBox183);
//        check[179] = (CheckBox)findViewById(R.id.checkBox184);
//        check[180] = (CheckBox)findViewById(R.id.checkBox185);
//        check[181] = (CheckBox)findViewById(R.id.checkBox186);
//        check[182] = (CheckBox)findViewById(R.id.checkBox187);
//        check[183] = (CheckBox)findViewById(R.id.checkBox188);
//        check[184] = (CheckBox)findViewById(R.id.checkBox189);
//        check[185] = (CheckBox)findViewById(R.id.checkBox190);
//        check[186] = (CheckBox)findViewById(R.id.checkBox191);
//        check[187] = (CheckBox)findViewById(R.id.checkBox192);
//        check[188] = (CheckBox)findViewById(R.id.checkBox193);
//        check[189] = (CheckBox)findViewById(R.id.checkBox194);
//        check[190] = (CheckBox)findViewById(R.id.checkBox195);
//        check[191] = (CheckBox)findViewById(R.id.checkBox196);
//        check[192] = (CheckBox)findViewById(R.id.checkBox197);
//        check[193] = (CheckBox)findViewById(R.id.checkBox198);
//        check[194] = (CheckBox)findViewById(R.id.checkBox199);
//        check[195] = (CheckBox)findViewById(R.id.checkBox200);
//        check[196] = (CheckBox)findViewById(R.id.checkBox201);
//        check[197] = (CheckBox)findViewById(R.id.checkBox202);
//        check[198] = (CheckBox)findViewById(R.id.checkBox203);
//        check[199] = (CheckBox)findViewById(R.id.checkBox204);
//        check[200] = (CheckBox)findViewById(R.id.checkBox205);
//        check[201] = (CheckBox)findViewById(R.id.checkBox206);
//        check[202] = (CheckBox)findViewById(R.id.checkBox207);
//        check[203] = (CheckBox)findViewById(R.id.checkBox208);
//        check[204] = (CheckBox)findViewById(R.id.checkBox209);
//        check[205] = (CheckBox)findViewById(R.id.checkBox210);
//        check[206] = (CheckBox)findViewById(R.id.checkBox211);
//        check[207] = (CheckBox)findViewById(R.id.checkBox212);
//        check[208] = (CheckBox)findViewById(R.id.checkBox213);
//        check[209] = (CheckBox)findViewById(R.id.checkBox214);
//        check[210] = (CheckBox)findViewById(R.id.checkBox215);
//        check[211] = (CheckBox)findViewById(R.id.checkBox216);
//        check[212] = (CheckBox)findViewById(R.id.checkBox217);
//        check[213] = (CheckBox)findViewById(R.id.checkBox218);
//        check[214] = (CheckBox)findViewById(R.id.checkBox219);
//        check[215] = (CheckBox)findViewById(R.id.checkBox210);
//        check[216] = (CheckBox)findViewById(R.id.checkBox211);
//        check[217] = (CheckBox)findViewById(R.id.checkBox212);
//        check[218] = (CheckBox)findViewById(R.id.checkBox213);
//        check[219] = (CheckBox)findViewById(R.id.checkBox214);
//        check[220] = (CheckBox)findViewById(R.id.checkBox215);
//        check[221] = (CheckBox)findViewById(R.id.checkBox216);
//        check[222] = (CheckBox)findViewById(R.id.checkBox217);
//        check[223] = (CheckBox)findViewById(R.id.checkBox218);
//        check[224] = (CheckBox)findViewById(R.id.checkBox219);
//        check[225] = (CheckBox)findViewById(R.id.checkBox220);
//        check[226] = (CheckBox)findViewById(R.id.checkBox221);
//        check[227] = (CheckBox)findViewById(R.id.checkBox222);
//        check[228] = (CheckBox)findViewById(R.id.checkBox223);
//        check[229] = (CheckBox)findViewById(R.id.checkBox224);
//        check[230] = (CheckBox)findViewById(R.id.checkBox225);
//        check[231] = (CheckBox)findViewById(R.id.checkBox226);
//        check[232] = (CheckBox)findViewById(R.id.checkBox227);
//        check[233] = (CheckBox)findViewById(R.id.checkBox228);
//        check[234] = (CheckBox)findViewById(R.id.checkBox229);
//        check[235] = (CheckBox)findViewById(R.id.checkBox220);
//        check[236] = (CheckBox)findViewById(R.id.checkBox221);
//        check[237] = (CheckBox)findViewById(R.id.checkBox222);
//        check[238] = (CheckBox)findViewById(R.id.checkBox223);
//        check[239] = (CheckBox)findViewById(R.id.checkBox224);
//        check[240] = (CheckBox)findViewById(R.id.checkBox225);
//        check[241] = (CheckBox)findViewById(R.id.checkBox226);
//        check[242] = (CheckBox)findViewById(R.id.checkBox227);
//        check[243] = (CheckBox)findViewById(R.id.checkBox228);
//        check[244] = (CheckBox)findViewById(R.id.checkBox229);
//        check[245] = (CheckBox)findViewById(R.id.checkBox230);
//        check[246] = (CheckBox)findViewById(R.id.checkBox231);
//        check[247] = (CheckBox)findViewById(R.id.checkBox232);
//        check[248] = (CheckBox)findViewById(R.id.checkBox233);
//        check[249] = (CheckBox)findViewById(R.id.checkBox234);
//        check[250] = (CheckBox)findViewById(R.id.checkBox235);
//        check[251] = (CheckBox)findViewById(R.id.checkBox236);
//        check[252] = (CheckBox)findViewById(R.id.checkBox237);
//        check[253] = (CheckBox)findViewById(R.id.checkBox238);
//        check[254] = (CheckBox)findViewById(R.id.checkBox239);
//        check[255] = (CheckBox)findViewById(R.id.checkBox240);
//        check[256] = (CheckBox)findViewById(R.id.checkBox241);
//        check[257] = (CheckBox)findViewById(R.id.checkBox242);
//        check[258] = (CheckBox)findViewById(R.id.checkBox243);
//        check[259] = (CheckBox)findViewById(R.id.checkBox244);
//        check[260] = (CheckBox)findViewById(R.id.checkBox245);
//        check[261] = (CheckBox)findViewById(R.id.checkBox246);
//        check[262] = (CheckBox)findViewById(R.id.checkBox247);
//        check[263] = (CheckBox)findViewById(R.id.checkBox248);
//        check[264] = (CheckBox)findViewById(R.id.checkBox249);
//        check[265] = (CheckBox)findViewById(R.id.checkBox250);
//        check[266] = (CheckBox)findViewById(R.id.checkBox251);
//        check[267] = (CheckBox)findViewById(R.id.checkBox252);
//        check[268] = (CheckBox)findViewById(R.id.checkBox253);
//        check[269] = (CheckBox)findViewById(R.id.checkBox254);
//        check[270] = (CheckBox)findViewById(R.id.checkBox255);
//        check[271] = (CheckBox)findViewById(R.id.checkBox256);
//        check[272] = (CheckBox)findViewById(R.id.checkBox257);
//        check[273] = (CheckBox)findViewById(R.id.checkBox258);
//        check[274] = (CheckBox)findViewById(R.id.checkBox259);
//        check[275] = (CheckBox)findViewById(R.id.checkBox260);
//        check[276] = (CheckBox)findViewById(R.id.checkBox261);
//        check[277] = (CheckBox)findViewById(R.id.checkBox262);
//        check[278] = (CheckBox)findViewById(R.id.checkBox263);
//        check[279] = (CheckBox)findViewById(R.id.checkBox264);
//        check[280] = (CheckBox)findViewById(R.id.checkBox265);
//        check[281] = (CheckBox)findViewById(R.id.checkBox266);
//        check[282] = (CheckBox)findViewById(R.id.checkBox267);
//        check[283] = (CheckBox)findViewById(R.id.checkBox268);
//        check[284] = (CheckBox)findViewById(R.id.checkBox269);
//        check[285] = (CheckBox)findViewById(R.id.checkBox270);
//        check[286] = (CheckBox)findViewById(R.id.checkBox271);
//        check[287] = (CheckBox)findViewById(R.id.checkBox272);
//        check[288] = (CheckBox)findViewById(R.id.checkBox273);
//        check[289] = (CheckBox)findViewById(R.id.checkBox274);
//        check[290] = (CheckBox)findViewById(R.id.checkBox275);
//        check[291] = (CheckBox)findViewById(R.id.checkBox276);
//        check[292] = (CheckBox)findViewById(R.id.checkBox277);
//        check[293] = (CheckBox)findViewById(R.id.checkBox278);
//        check[294] = (CheckBox)findViewById(R.id.checkBox279);
//        check[295] = (CheckBox)findViewById(R.id.checkBox270);
//        check[296] = (CheckBox)findViewById(R.id.checkBox271);
//        check[297] = (CheckBox)findViewById(R.id.checkBox272);
//        check[298] = (CheckBox)findViewById(R.id.checkBox273);
//        check[299] = (CheckBox)findViewById(R.id.checkBox274);
//        check[300] = (CheckBox)findViewById(R.id.checkBox275);
//        check[301] = (CheckBox)findViewById(R.id.checkBox276);
//        check[302] = (CheckBox)findViewById(R.id.checkBox277);
//        check[303] = (CheckBox)findViewById(R.id.checkBox278);




        text[0] = (TextView)findViewById(R.id.textView15);
        text[1] = (TextView)findViewById(R.id.textView16);
        text[2] = (TextView)findViewById(R.id.textView17);
        text[3] = (TextView)findViewById(R.id.textView18);
        text[4] = (TextView)findViewById(R.id.textView19);
        text[5] = (TextView)findViewById(R.id.textView20);
        text[6] = (TextView)findViewById(R.id.textView21);
        text[7] = (TextView)findViewById(R.id.textView22);
        text[8] = (TextView)findViewById(R.id.textView23);
        text[9] = (TextView)findViewById(R.id.textView24);
        text[10] = (TextView)findViewById(R.id.textView25);
        text[11] = (TextView)findViewById(R.id.textView26);
        text[12] = (TextView)findViewById(R.id.textView27);
        text[13] = (TextView)findViewById(R.id.textView28);
        text[14] = (TextView)findViewById(R.id.textView29);
        text[15] = (TextView)findViewById(R.id.textView30);
        text[16] = (TextView)findViewById(R.id.textView31);
        text[17] = (TextView)findViewById(R.id.textView32);
        text[18] = (TextView)findViewById(R.id.textView33);
        text[19] = (TextView)findViewById(R.id.textView34);
        text[20] = (TextView)findViewById(R.id.textView35);
        text[21] = (TextView)findViewById(R.id.textView36);
        text[22] = (TextView)findViewById(R.id.textView37);
        text[23] = (TextView)findViewById(R.id.textView38);
        text[24] = (TextView)findViewById(R.id.textView39);
        text[25] = (TextView)findViewById(R.id.textView40);
        text[26] = (TextView)findViewById(R.id.textView41);
        text[27] = (TextView)findViewById(R.id.textView42);
        text[28] = (TextView)findViewById(R.id.textView43);
        text[29] = (TextView)findViewById(R.id.textView44);
        text[30] = (TextView)findViewById(R.id.textView45);
        text[31] = (TextView)findViewById(R.id.textView46);
        text[32] = (TextView)findViewById(R.id.textView47);
        text[33] = (TextView)findViewById(R.id.textView48);
        text[34] = (TextView)findViewById(R.id.textView49);
        text[35] = (TextView)findViewById(R.id.textView50);
        text[36] = (TextView)findViewById(R.id.textView51);
        text[37] = (TextView)findViewById(R.id.textView52);
        text[38] = (TextView)findViewById(R.id.textView53);
        text[39] = (TextView)findViewById(R.id.textView54);
        text[40] = (TextView)findViewById(R.id.textView55);
        text[41] = (TextView)findViewById(R.id.textView56);
        text[42] = (TextView)findViewById(R.id.textView57);
        text[43] = (TextView)findViewById(R.id.textView58);
        text[44] = (TextView)findViewById(R.id.textView59);
        text[45] = (TextView)findViewById(R.id.textView60);
        text[46] = (TextView)findViewById(R.id.textView61);
        text[47] = (TextView)findViewById(R.id.textView62);
        text[48] = (TextView)findViewById(R.id.textView63);
        text[49] = (TextView)findViewById(R.id.textView64);
        text[50] = (TextView)findViewById(R.id.textView65);
        text[51] = (TextView)findViewById(R.id.textView66);
        text[52] = (TextView)findViewById(R.id.textView67);
        text[53] = (TextView)findViewById(R.id.textView68);
        text[54] = (TextView)findViewById(R.id.textView69);
        text[55] = (TextView)findViewById(R.id.textView70);
        text[56] = (TextView)findViewById(R.id.textView71);
        text[57] = (TextView)findViewById(R.id.textView72);
        text[58] = (TextView)findViewById(R.id.textView73);
//        text[59] = (TextView)findViewById(R.id.textView74);
//        text[60] = (TextView)findViewById(R.id.textView75);
//        text[61] = (TextView)findViewById(R.id.textView76);
//        text[62] = (TextView)findViewById(R.id.textView77);
//        text[63] = (TextView)findViewById(R.id.textView78);
//        text[64] = (TextView)findViewById(R.id.textView79);
//        text[65] = (TextView)findViewById(R.id.textView80);
//        text[66] = (TextView)findViewById(R.id.textView81);
//        text[67] = (TextView)findViewById(R.id.textView82);
//        text[68] = (TextView)findViewById(R.id.textView83);
//        text[69] = (TextView)findViewById(R.id.textView84);
//        text[70] = (TextView)findViewById(R.id.textView85);
//        text[71] = (TextView)findViewById(R.id.textView86);
//        text[72] = (TextView)findViewById(R.id.textView87);
//        text[73] = (TextView)findViewById(R.id.textView88);
//        text[74] = (TextView)findViewById(R.id.textView89);
//        text[75] = (TextView)findViewById(R.id.textView90);
//        text[76] = (TextView)findViewById(R.id.textView91);
//        text[77] = (TextView)findViewById(R.id.textView92);
//        text[78] = (TextView)findViewById(R.id.textView93);
//        text[79] = (TextView)findViewById(R.id.textView94);
//        text[80] = (TextView)findViewById(R.id.textView95);
//        text[81] = (TextView)findViewById(R.id.textView96);
//        text[82] = (TextView)findViewById(R.id.textView97);
//        text[83] = (TextView)findViewById(R.id.textView98);
//        text[84] = (TextView)findViewById(R.id.textView99);
//        text[85] = (TextView)findViewById(R.id.textView100);
//        text[86] = (TextView)findViewById(R.id.textView101);
//        text[87] = (TextView)findViewById(R.id.textView102);
//        text[88] = (TextView)findViewById(R.id.textView103);
//        text[89] = (TextView)findViewById(R.id.textView104);
//        text[90] = (TextView)findViewById(R.id.textView105);
//        text[91] = (TextView)findViewById(R.id.textView106);
//        text[92] = (TextView)findViewById(R.id.textView107);
//        text[93] = (TextView)findViewById(R.id.textView108);
//        text[94] = (TextView)findViewById(R.id.textView109);
//        text[95] = (TextView)findViewById(R.id.textView110);
//        text[96] = (TextView)findViewById(R.id.textView111);
//        text[97] = (TextView)findViewById(R.id.textView112);
//        text[98] = (TextView)findViewById(R.id.textView113);
//        text[99] = (TextView)findViewById(R.id.textView114);
//        text[100] = (TextView)findViewById(R.id.textView115);
//        text[101] = (TextView)findViewById(R.id.textView116);
//        text[102] = (TextView)findViewById(R.id.textView117);
//        text[103] = (TextView)findViewById(R.id.textView118);
//        text[104] = (TextView)findViewById(R.id.textView119);
//        text[105] = (TextView)findViewById(R.id.textView120);
//        text[106] = (TextView)findViewById(R.id.textView121);
//        text[107] = (TextView)findViewById(R.id.textView122);
//        text[108] = (TextView)findViewById(R.id.textView123);
//        text[109] = (TextView)findViewById(R.id.textView124);
//        text[110] = (TextView)findViewById(R.id.textView125);
//        text[111] = (TextView)findViewById(R.id.textView126);
//        text[112] = (TextView)findViewById(R.id.textView127);
//        text[113] = (TextView)findViewById(R.id.textView128);
//        text[114] = (TextView)findViewById(R.id.textView129);
//        text[115] = (TextView)findViewById(R.id.textView130);
//        text[116] = (TextView)findViewById(R.id.textView131);
//        text[117] = (TextView)findViewById(R.id.textView132);
//        text[118] = (TextView)findViewById(R.id.textView133);
//        text[119] = (TextView)findViewById(R.id.textView134);
//        text[120] = (TextView)findViewById(R.id.textView135);
//        text[121] = (TextView)findViewById(R.id.textView136);
//        text[122] = (TextView)findViewById(R.id.textView137);
//        text[123] = (TextView)findViewById(R.id.textView138);
//        text[124] = (TextView)findViewById(R.id.textView139);
//        text[125] = (TextView)findViewById(R.id.textView140);
//        text[126] = (TextView)findViewById(R.id.textView141);
//        text[127] = (TextView)findViewById(R.id.textView142);
//        text[128] = (TextView)findViewById(R.id.textView143);
//        text[129] = (TextView)findViewById(R.id.textView144);
//        text[130] = (TextView)findViewById(R.id.textView145);
//        text[131] = (TextView)findViewById(R.id.textView146);
//        text[132] = (TextView)findViewById(R.id.textView147);
//        text[133] = (TextView)findViewById(R.id.textView148);
//        text[134] = (TextView)findViewById(R.id.textView149);
//        text[135] = (TextView)findViewById(R.id.textView150);
//        text[136] = (TextView)findViewById(R.id.textView151);
//        text[137] = (TextView)findViewById(R.id.textView152);
//        text[138] = (TextView)findViewById(R.id.textView153);
//        text[139] = (TextView)findViewById(R.id.textView154);
//        text[140] = (TextView)findViewById(R.id.textView155);
//        text[141] = (TextView)findViewById(R.id.textView156);
//        text[142] = (TextView)findViewById(R.id.textView157);
//        text[143] = (TextView)findViewById(R.id.textView158);
//        text[144] = (TextView)findViewById(R.id.textView159);
//        text[145] = (TextView)findViewById(R.id.textView150);
//        text[146] = (TextView)findViewById(R.id.textView151);
//        text[147] = (TextView)findViewById(R.id.textView152);
//        text[148] = (TextView)findViewById(R.id.textView153);
//        text[149] = (TextView)findViewById(R.id.textView154);
//        text[150] = (TextView)findViewById(R.id.textView155);
//        text[151] = (TextView)findViewById(R.id.textView156);
//        text[152] = (TextView)findViewById(R.id.textView157);
//        text[153] = (TextView)findViewById(R.id.textView158);
//        text[154] = (TextView)findViewById(R.id.textView159);
//        text[155] = (TextView)findViewById(R.id.textView160);
//        text[156] = (TextView)findViewById(R.id.textView161);
//        text[157] = (TextView)findViewById(R.id.textView162);
//        text[158] = (TextView)findViewById(R.id.textView163);
//        text[159] = (TextView)findViewById(R.id.textView164);
//        text[160] = (TextView)findViewById(R.id.textView165);
//        text[161] = (TextView)findViewById(R.id.textView166);
//        text[162] = (TextView)findViewById(R.id.textView167);
//        text[163] = (TextView)findViewById(R.id.textView168);
//        text[164] = (TextView)findViewById(R.id.textView169);
//        text[165] = (TextView)findViewById(R.id.textView170);
//        text[166] = (TextView)findViewById(R.id.textView171);
//        text[167] = (TextView)findViewById(R.id.textView172);
//        text[168] = (TextView)findViewById(R.id.textView173);
//        text[169] = (TextView)findViewById(R.id.textView174);
//        text[170] = (TextView)findViewById(R.id.textView175);
//        text[171] = (TextView)findViewById(R.id.textView176);
//        text[172] = (TextView)findViewById(R.id.textView177);
//        text[173] = (TextView)findViewById(R.id.textView178);
//        text[174] = (TextView)findViewById(R.id.textView179);
//        text[175] = (TextView)findViewById(R.id.textView180);
//        text[176] = (TextView)findViewById(R.id.textView181);
//        text[177] = (TextView)findViewById(R.id.textView182);
//        text[178] = (TextView)findViewById(R.id.textView183);
//        text[179] = (TextView)findViewById(R.id.textView184);
//        text[180] = (TextView)findViewById(R.id.textView185);
//        text[181] = (TextView)findViewById(R.id.textView186);
//        text[182] = (TextView)findViewById(R.id.textView187);
//        text[183] = (TextView)findViewById(R.id.textView188);
//        text[184] = (TextView)findViewById(R.id.textView189);
//        text[185] = (TextView)findViewById(R.id.textView190);
//        text[186] = (TextView)findViewById(R.id.textView191);
//        text[187] = (TextView)findViewById(R.id.textView192);
//        text[188] = (TextView)findViewById(R.id.textView193);
//        text[189] = (TextView)findViewById(R.id.textView194);
//        text[190] = (TextView)findViewById(R.id.textView195);
//        text[191] = (TextView)findViewById(R.id.textView196);
//        text[192] = (TextView)findViewById(R.id.textView197);
//        text[193] = (TextView)findViewById(R.id.textView198);
//        text[194] = (TextView)findViewById(R.id.textView199);
//        text[195] = (TextView)findViewById(R.id.textView200);
//        text[196] = (TextView)findViewById(R.id.textView201);
//        text[197] = (TextView)findViewById(R.id.textView202);
//        text[198] = (TextView)findViewById(R.id.textView203);
//        text[199] = (TextView)findViewById(R.id.textView204);
//        text[200] = (TextView)findViewById(R.id.textView205);
//        text[201] = (TextView)findViewById(R.id.textView206);
//        text[202] = (TextView)findViewById(R.id.textView207);
//        text[203] = (TextView)findViewById(R.id.textView208);
//        text[204] = (TextView)findViewById(R.id.textView209);
//        text[205] = (TextView)findViewById(R.id.textView210);
//        text[206] = (TextView)findViewById(R.id.textView211);
//        text[207] = (TextView)findViewById(R.id.textView212);
//        text[208] = (TextView)findViewById(R.id.textView213);
//        text[209] = (TextView)findViewById(R.id.textView214);
//        text[210] = (TextView)findViewById(R.id.textView215);
//        text[211] = (TextView)findViewById(R.id.textView216);
//        text[212] = (TextView)findViewById(R.id.textView217);
//        text[213] = (TextView)findViewById(R.id.textView218);
//        text[214] = (TextView)findViewById(R.id.textView219);
//        text[215] = (TextView)findViewById(R.id.textView220);
//        text[216] = (TextView)findViewById(R.id.textView221);
//        text[217] = (TextView)findViewById(R.id.textView222);
//        text[218] = (TextView)findViewById(R.id.textView223);
//        text[219] = (TextView)findViewById(R.id.textView224);
//        text[220] = (TextView)findViewById(R.id.textView225);
//        text[221] = (TextView)findViewById(R.id.textView226);
//        text[222] = (TextView)findViewById(R.id.textView227);
//        text[223] = (TextView)findViewById(R.id.textView228);
//        text[224] = (TextView)findViewById(R.id.textView229);
//        text[225] = (TextView)findViewById(R.id.textView230);
//        text[226] = (TextView)findViewById(R.id.textView231);
//        text[227] = (TextView)findViewById(R.id.textView232);
//        text[228] = (TextView)findViewById(R.id.textView233);
//        text[229] = (TextView)findViewById(R.id.textView234);
//        text[230] = (TextView)findViewById(R.id.textView235);
//        text[231] = (TextView)findViewById(R.id.textView236);
//        text[232] = (TextView)findViewById(R.id.textView237);
//        text[233] = (TextView)findViewById(R.id.textView238);
//        text[234] = (TextView)findViewById(R.id.textView239);
//        text[235] = (TextView)findViewById(R.id.textView240);
//        text[236] = (TextView)findViewById(R.id.textView241);
//        text[237] = (TextView)findViewById(R.id.textView242);
//        text[238] = (TextView)findViewById(R.id.textView243);
//        text[239] = (TextView)findViewById(R.id.textView244);
//        text[240] = (TextView)findViewById(R.id.textView245);
//        text[241] = (TextView)findViewById(R.id.textView246);
//        text[242] = (TextView)findViewById(R.id.textView247);
//        text[243] = (TextView)findViewById(R.id.textView248);
//        text[244] = (TextView)findViewById(R.id.textView249);
//        text[245] = (TextView)findViewById(R.id.textView250);
//        text[246] = (TextView)findViewById(R.id.textView251);
//        text[247] = (TextView)findViewById(R.id.textView252);
//        text[248] = (TextView)findViewById(R.id.textView253);
//        text[249] = (TextView)findViewById(R.id.textView254);
//        text[250] = (TextView)findViewById(R.id.textView255);
//        text[251] = (TextView)findViewById(R.id.textView256);
//        text[252] = (TextView)findViewById(R.id.textView257);
//        text[253] = (TextView)findViewById(R.id.textView258);
//        text[254] = (TextView)findViewById(R.id.textView259);
//        text[255] = (TextView)findViewById(R.id.textView260);
//        text[256] = (TextView)findViewById(R.id.textView261);
//        text[257] = (TextView)findViewById(R.id.textView262);
//        text[258] = (TextView)findViewById(R.id.textView263);
//        text[259] = (TextView)findViewById(R.id.textView264);
//        text[260] = (TextView)findViewById(R.id.textView265);
//        text[261] = (TextView)findViewById(R.id.textView266);
//        text[262] = (TextView)findViewById(R.id.textView267);
//        text[263] = (TextView)findViewById(R.id.textView268);
//        text[264] = (TextView)findViewById(R.id.textView269);
//        text[265] = (TextView)findViewById(R.id.textView260);
//        text[266] = (TextView)findViewById(R.id.textView261);
//        text[267] = (TextView)findViewById(R.id.textView262);
//        text[268] = (TextView)findViewById(R.id.textView263);
//        text[269] = (TextView)findViewById(R.id.textView264);
//        text[270] = (TextView)findViewById(R.id.textView265);
//        text[271] = (TextView)findViewById(R.id.textView266);
//        text[272] = (TextView)findViewById(R.id.textView267);
//        text[273] = (TextView)findViewById(R.id.textView268);
//        text[274] = (TextView)findViewById(R.id.textView269);
//        text[275] = (TextView)findViewById(R.id.textView270);
//        text[276] = (TextView)findViewById(R.id.textView271);
//        text[277] = (TextView)findViewById(R.id.textView272);
//        text[278] = (TextView)findViewById(R.id.textView273);
//        text[279] = (TextView)findViewById(R.id.textView274);
//        text[280] = (TextView)findViewById(R.id.textView275);
//        text[281] = (TextView)findViewById(R.id.textView276);
//        text[282] = (TextView)findViewById(R.id.textView277);
//        text[283] = (TextView)findViewById(R.id.textView278);
//        text[284] = (TextView)findViewById(R.id.textView279);
//        text[285] = (TextView)findViewById(R.id.textView280);
//        text[286] = (TextView)findViewById(R.id.textView281);
//        text[287] = (TextView)findViewById(R.id.textView282);
//        text[288] = (TextView)findViewById(R.id.textView283);
//        text[289] = (TextView)findViewById(R.id.textView284);
//        text[290] = (TextView)findViewById(R.id.textView285);
//        text[291] = (TextView)findViewById(R.id.textView286);
//        text[292] = (TextView)findViewById(R.id.textView287);
//        text[293] = (TextView)findViewById(R.id.textView288);
//        text[294] = (TextView)findViewById(R.id.textView289);
//        text[295] = (TextView)findViewById(R.id.textView290);
//        text[296] = (TextView)findViewById(R.id.textView291);
//        text[297] = (TextView)findViewById(R.id.textView292);
//        text[298] = (TextView)findViewById(R.id.textView293);
//        text[299] = (TextView)findViewById(R.id.textView294);
//        text[300] = (TextView)findViewById(R.id.textView295);
//        text[301] = (TextView)findViewById(R.id.textView296);
//        text[302] = (TextView)findViewById(R.id.textView297);
//        text[303] = (TextView)findViewById(R.id.textView298);
        //get content from intent
        try {

            subarea = getIntent().getStringExtra("subarea");
            task = getIntent().getStringExtra("task");
            facility = getIntent().getStringExtra("facility");
            int i = 0;
            PAid = getIntent().getStringExtra("PAid");
            hubId = getIntent().getStringExtra("hubId1");

            jsonArray = new JSONArray(subarea);

            jsonObject1 = new JSONObject[jsonArray.length()];
            for (i = 0; i < jsonArray.length(); i++) {
                jsonObject1[i] = jsonArray.getJSONObject(i);

            }

        } catch (JSONException e) {
            Log.d("Exception", "JSON");
        }
        jObj = new JSONObject[jsonArray.length()];
        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                String PA = jsonObject1[i].getString("processingArea");
                jObj[i] = new JSONObject(PA);

            } catch (JSONException e) {
                Log.d("Exception", "JSON");
            }
        }
        get = (Button)findViewById(R.id.button9);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<jsonArray.length();i++)
                {
                    image[i].setVisibility(v.VISIBLE);
                    check[i].setVisibility(v.VISIBLE);
                    try {
                        if(jObj[i].getString("type").equals("SORTER"))
                        {
                            image[i].setBackgroundResource(R.drawable.sort);

                            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(HubSubProcessingAreas.this, R.anim.hyperspace_jump);
                            image[i].startAnimation(hyperspaceJumpAnimation);
                            text[i].setText(jObj[i].getString("name"));
                        }
                        if(jObj[i].getString("type").equals("BIN"))
                        {
                            image[i].setBackgroundResource(R.drawable.bin);
                            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(HubSubProcessingAreas.this, R.anim.hyperspace_jump);
                            image[i].startAnimation(hyperspaceJumpAnimation);
                            text[i].setText(jObj[i].getString("name"));
                        }
                        if(jObj[i].getString("type").equals("STAGE"))
                        {
                            image[i].setBackgroundResource(R.drawable.stage);
                            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(HubSubProcessingAreas.this, R.anim.hyperspace_jump);
                            image[i].startAnimation(hyperspaceJumpAnimation);
                            text[i].setText(jObj[i].getString("name"));
                        }
                        if(jObj[i].getString("type").equals("STATION"))
                        {
                            image[i].setBackgroundResource(R.drawable.station);
                            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(HubSubProcessingAreas.this, R.anim.hyperspace_jump);
                            image[i].startAnimation(hyperspaceJumpAnimation);
                            text[i].setText(jObj[i].getString("name"));
                        }
                    } catch (JSONException e) {
                        Log.d("Exception", "JSON");
                    }
                    final int finalI = i;
                    image[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(HubSubProcessingAreas.this, ShowResource.class);
                            try {
                                intent.putExtra("hub name", HubElements.hub + "+" + jObj[finalI].getString("id"));
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                Log.d("Exception", "JSON");
                            }

                        }
                    });

                    final int finalI1 = i;
                    check[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (check[finalI1].isChecked()) {
                                try {

                                    if(task.equals("Sortation"))
                                    {
                                        Intent intent1 = new Intent(HubSubProcessingAreas.this,NextProcessingArea.class);
                                        intent1.putExtra("facility",facility);
                                        intent1.putExtra("processArea",jObj[finalI].getString("id"));
                                        startActivity(intent1);

                                    }
                                    else{

                                        if (task.equals("Bagging") && jObj[finalI].getString("mappedProcessingAreas").toString().length() - 2 == 0) {

                                            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                                            intent.setPackage("com.google.zxing.client.android");
                                            intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");//for Qr code, its "QR_CODE_MODE" , for barcode "PRODUCT_MODE"
                                            intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
                                            startActivityForResult(intent, 0);



                                        } else {
                                            Intent intent = new Intent(HubSubProcessingAreas.this, HubSubProcessingAreas.class);
                                            try {
                                                intent.putExtra("subarea", jObj[finalI].getString("mappedProcessingAreas").toString() );
                                                intent.putExtra("PAid", jObj[finalI].getString("id"));
                                                intent.putExtra("hubId1",hubId);
                                                intent.putExtra("task","Bagging");
                                                startActivity(intent);
                                                finish();
                                            } catch (JSONException e) {
                                                Log.d("Exception", "JSON");
                                            }
                                        }
                                    }

                                } catch (JSONException e) {
                                    Log.d("Exception", "JSON");
                                }

                            }
                        }
                    });

                }

                if(LoginPage.user.equals("admin"))
                {
                    for(int i = jsonArray.length();i<58;i++)
                    {

                        check[i].setVisibility(v.VISIBLE);
                        image[i].setBackgroundResource(R.drawable.add);
                        image[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(HubSubProcessingAreas.this, AddProcessingArea.class);
                                intent.putExtra("hub name", HubElements.hub + "+" + PAid + "+" + hubId);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }
                else {
                    for(int i = jsonArray.length();i<16;i++)
                    {

                        image[i].setBackgroundResource(R.drawable.blank);
                        image[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Context context = getApplicationContext();
                                CharSequence text = "You are not an admin";
                                int duration = Toast.LENGTH_SHORT;
                                //Show a toast to inform the user
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        });
                    }
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hub_sub_processing_areas, menu);
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
