package com.example.trishudey.hubsystemhelper.Activities.main;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.trishudey.hubsystemhelper.R;

import java.util.Arrays;

public class PieChart extends Fragment {

    private View topLevelView;


    // save a reference to show the pie chart
    private WebView webview;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState)

    {
        super.onCreateView(inflater, container,
                savedInstanceState);

        boolean attachToRoot = false;
        topLevelView = inflater.inflate(
                R.layout.activity_pie_chart,
                container,
                attachToRoot);

        // call now or after some condition is met
        initPieChart();

        return topLevelView;
    }

    public void initPieChart()
    {
        View stub = topLevelView.findViewById(
                R.id.pie_chart_stub);

        if (stub instanceof ViewStub)
        {
            ((ViewStub)stub).setVisibility(View.VISIBLE);

            webview = (WebView)topLevelView.findViewById(
                    R.id.pie_chart_webview);

            WebSettings webSettings =
                    webview.getSettings();

            webSettings.setJavaScriptEnabled(true);

            webview.setWebChromeClient(
                    new WebChromeClient());

            webview.setWebViewClient(new WebViewClient()
            {
                @Override
                public void onPageFinished(
                        WebView view,
                        String url)
                {

                    // after the HTML page loads,
                    loadPieChart();

                }
            });

            // note the mapping
            // from  file:///android_asset
            // to PieChartExample/assets

            webview.loadUrl("file:///android_asset/" +
                    "piechart.html");


        }
    }

    public void loadPieChart()
    {
        String text = "http://hubsystem-app.nm.flipkart.com/v1/config/fetch?facilityId=651&slotId=3&processingAreaId=733";

        // use java.util.Arrays to format
        // the array as text

        System.out.println(text);
        // pass the array to the JavaScript function
        webview.loadUrl("javascript:loadPieChart(" +
                text + ")");

    }

}
