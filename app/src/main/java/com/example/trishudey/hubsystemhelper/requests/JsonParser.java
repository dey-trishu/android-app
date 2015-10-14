package com.example.trishudey.hubsystemhelper.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {
    JSONArray jArray = null;
    JSONObject jObj[];
    public static int status;
    // constructor
    public JSONObject[] getJSONFromUrl(String url) {

        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setConnectTimeout(1000);
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            System.out.print(c.getResponseMessage());
            String m = c.getResponseMessage();
            System.out.println(m);
            status = c.getResponseCode();

            System.out.println(status);

            switch (status) {
                case 200:
                case 201:
//                    InputStreamReader inputStreamReader = new InputStreamReader(c.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
//                    inputStreamReader.close();
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    String json = sb.toString();
                    try {
                        jArray = new JSONArray(json);
                        jObj = new JSONObject[jArray.length()];
                       //Get each object from JSON Array
                        for(int i=0;i<jArray.length();i++) {
                            jObj[i] = jArray.getJSONObject(i);
                        }
                    } catch (JSONException e) {
                        Log.e("JSON Parser", "Error parsing data " + e.toString());
                    } catch (Exception e){
                        Log.e("Something wrong",""+e.toString());
                    }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return jObj;
    }
}

