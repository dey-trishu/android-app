package com.example.trishudey.hubsystemhelper;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.abishekkrishnan.hubsystemhelper.R;

import java.net.CookieManager;
import java.util.List;

public class Displat_the_LDAP extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    public static String message;
    public static CookieManager cookieManager;
    static List l;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displat_the__ldap);

//        try {
//
//            HttpClient client = new DefaultHttpClient();
//
//            HttpGet request = new HttpGet("http://10.0.2.2:27015/");
//
//            // Get the response
//            ResponseHandler<String> responseHandler = new BasicResponseHandler();
//
//            String response_str = client.execute(request, responseHandler);
//            message = "fadS";
//
//        } catch (Exception e) {
//            e.printStackTrace(); }
        WebView mWebView = (WebView) findViewById(R.id.activity_main_webview);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setInitialScale(1);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(false);
        mWebView.loadUrl("http://10.0.2.2:27015/");


        mWebView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageFinished(WebView view, String url) {


//                    l= new LinkedList() ;
//                    cookieManager = new CookieManager();
//                    l = cookieManager.getCookieStore().getCookies();



                if (url.equals("http://10.0.2.2:27015/")) {


                        Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                        startActivity(intent);



                }
            }

    });

        Log.d(message, "OK");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_displat_the__lda, menu);
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
