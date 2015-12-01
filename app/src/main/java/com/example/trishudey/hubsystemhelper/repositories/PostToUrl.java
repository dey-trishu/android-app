package com.example.trishudey.hubsystemhelper.repositories;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by trishu.dey on 28/09/15.
 */
public class PostToUrl {
    public static int post(String url1, String param) {
        int code = 0;
        try {


            URL url = new URL(url1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", "" +
                    Integer.toString(param.getBytes().length));
            conn.setRequestProperty("Content-Language", "en-US");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(param);
            writer.flush();
            writer.close();
            os.close();

             code = conn.getResponseCode();
        } catch (MalformedURLException e) {
            Log.d("Malformed Url", "Exception");
        } catch (ProtocolException e) {
            Log.d("Protocol", "Exception");
        } catch (IOException e) {
            Log.d("I/O", "Exception");
        }

        return code;
    }
}
