package com.example.trishudey.hubsystemhelper.Activities.services.hub;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.trishudey.hubsystemhelper.R;

public class D3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d3);
        Intent intent1 = getIntent();
        String facility = intent1.getStringExtra("facility id");
        Intent intent2 = getIntent();
        String slot = intent2.getStringExtra("selected time");
        Intent intent3 = getIntent();
        String pa = intent3.getStringExtra("selected pa");
        Intent intent4 = getIntent();
        String hubId = intent4.getStringExtra("hubId");
        Intent intent5 = getIntent();
        final int pos = intent5.getIntExtra("position",0);

        final String url1 = "http://hubsystem-app.nm.flipkart.com/v1/config/fetch?facilityId="+facility+"&slotId="+slot+"&processingAreaId="+pa;
        final String url2 = "http://hubsystem-app.nm.flipkart.com/v1/hub/"+hubId+"/processingAreas?task=sortation&hubId="+hubId;
        final String text = url1+"|"+url2+"|";
        //define webview
        final WebView webview = (WebView)findViewById(R.id.webview);
        webview.addJavascriptInterface(this, "android");
        webview.setHorizontalScrollBarEnabled(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(
                new WebChromeClient());


        webview.getSettings().setBuiltInZoomControls(true);
        webview.loadUrl("file:///android_asset/" +
                "piechart.html");
        webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                webview.loadUrl("javascript:android.onData(loadPieChart('" + text +"','"+pos+"'))");
            }
        });
        //setContentView(webview);
        // - See more at: http://blog.kerul.net/2011/07/webview-for-html-page-display-in.html#sthash.qnm0vbWY.dpuf
    }

    @JavascriptInterface
    public void onData(String value) {
        //.. do something with the data
        if(value.equals("1"))
        {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.setPackage("com.google.zxing.client.android");
            intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");//for Qr code, its "QR_CODE_MODE" , for barcode "PRODUCT_MODE"
            intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
            startActivityForResult(intent, 0);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_d3, menu);
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
class MyJavascriptInterface {
    private D3 activity;

    public MyJavascriptInterface(D3 activity) {
        this.activity = activity;
    }

    // this annotation is required in Jelly Bean and later:
    @JavascriptInterface
    public void onData(String value) {
        //.. do something with the data
        Log.d("LogTag", value);

    }
}