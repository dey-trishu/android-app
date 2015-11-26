package com.example.trishudey.hubsystemhelper.repositories;

import android.util.Log;

import com.example.trishudey.hubsystemhelper.Activities.services.hub.Config;

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

/**
 * Created by trishu.dey on 26/11/15.
 */
public class JsonGetResponse {
    public static JSONObject jObj;
    public static String password;
    public static JSONArray jsonArray;
    public static String time[];
    public static String PA[];
    public static JSONArray jsonArray1;
    public static JSONObject jsonObject2[];
    public static String code[];
    public static JSONObject jsonObject1[];
    public static String prev = "";
    public static JSONObject jsonObject;
    public static String items[];
    public static int j = 0;
    public static JSONArray jArray;

    public static String validateUser(String url) {
        try {
            URL u = new URL(url);
            HttpURLConnection c = null;
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            System.out.print(c.getResponseMessage());
            String m = c.getResponseMessage();
            System.out.println(m);
            int status = c.getResponseCode();
            if (status == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                String json = sb.toString();

                jObj = new JSONObject(json);
                password = jObj.getString("password");
                c.disconnect();
            }
        } catch (MalformedURLException e) {
            Log.d("Malformed Url", "Exception");
        } catch (ProtocolException e) {
            Log.d("Protocol", "Exception");
        } catch (JSONException e) {
            Log.d("Json", "Exception");
        } catch (IOException e) {
            Log.d("I/O", "Exception");
        }
        return password;
    }

    public static boolean deleteUser(String url) {
        try {
            URL u = new URL(url);
            HttpURLConnection c = null;
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            System.out.print(c.getResponseMessage());
            String m = c.getResponseMessage();
            System.out.println(m);
            int status = c.getResponseCode();
            if (status == 200)
                return true;
            c.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUser(String url, String currUser, String currPass, String newPass) {
        URL u = null;
        try {
            u = new URL(url);
            HttpURLConnection c = null;
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            System.out.print(c.getResponseMessage());
            String m = c.getResponseMessage();
            System.out.println(m);
            int status = c.getResponseCode();
            if (status == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
//                    inputStreamReader.close();
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                String json = sb.toString();

                jObj = new JSONObject(json);
                String password = jObj.getString("password");
                if (password.equals(currPass)) {

                    URL u1 = null;
                    try {
                        u1 = new URL("http://10.0.2.2:8082/v1/admin/deleteUser/" + currUser);
                        HttpURLConnection c1 = null;
                        c1 = (HttpURLConnection) u1.openConnection();
                        c1.setRequestMethod("GET");
                        c1.setRequestProperty("Content-length", "0");
                        c1.setUseCaches(false);
                        c1.setAllowUserInteraction(false);
                        c1.connect();
                        System.out.print(c1.getResponseMessage());
                        String m1 = c1.getResponseMessage();
                        System.out.println(m1);
                        int status1 = c1.getResponseCode();
                        if (status1 == 200) {


                            String url1 = "http://10.0.2.2:8082/v1/admin/addCredentials";
                            String params = "&name=" + currUser + "&password=" + newPass;
                            PostToUrl postToUrl = new PostToUrl();
                            int code = postToUrl.post(url1, params);
                            if (code == 200) {
                                return true;
                            }
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            c.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addUser(String getUrl, String addUrl, String username, String password) {
        try {
            URL u = new URL(getUrl);
            HttpURLConnection c = null;
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            System.out.print(c.getResponseMessage());
            String m = c.getResponseMessage();
            System.out.println(m);
            int status = c.getResponseCode();
            if (status != 200) {
                if (!username.isEmpty() && !password.isEmpty() && password.length() >= 8) {
                    String url = addUrl;
                    String params = "&name=" + username + "&password=" + password;
                    PostToUrl postToUrl = new PostToUrl();
                    int code = postToUrl.post(url, params);

                    if (code == 200) {
                        return true;
                    }
                }
                c.disconnect();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[] slots(String url) {
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
                        jsonArray = jObj.getJSONArray("data");
                        jsonObject1 = new JSONObject[jsonArray.length()];
                        code = new String[jsonArray.length()];
                        time = new String[jsonArray.length() + 1];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject1[i] = jsonArray.getJSONObject(i);
                            code[i] = jsonObject1[i].getString("code");
                            time[i + 1] = code[i];


                        }
                        time[0] = "Select time slot";
                    }
            }
            c.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String[] pa(String url) {
        HttpURLConnection c1 = null;
        try {
            URL u1 = new URL(url);
            c1 = (HttpURLConnection) u1.openConnection();
            c1.setRequestMethod("GET");
            c1.setRequestProperty("Content-length", "0");
            c1.setUseCaches(false);
            c1.setAllowUserInteraction(false);
            c1.connect();
            int status = c1.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    InputStreamReader input = new InputStreamReader(c1.getInputStream());
                    BufferedReader br1 = new BufferedReader(input);
                    StringBuilder sb1 = new StringBuilder();
                    String line;
                    while ((line = br1.readLine()) != null) {
                        sb1.append(line + "\n");
                    }
                    br1.close();
                    String json = sb1.toString();
                    JSONObject jObj = new JSONObject(json);
                    Config cn = new Config();
                    //select only first child of hub to display
                    jsonArray1 = jObj.getJSONArray("processingAreas");
                    jsonObject2 = new JSONObject[jsonArray1.length()];
                    PA = new String[jsonArray1.length() + 1];
                    cn.PAID = new String[jsonArray1.length() + 1];
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        jsonObject2[i] = jsonArray1.getJSONObject(i);
                        PA[i + 1] = jsonObject2[i].getString("name");
                        cn.PAID[i + 1] = jsonObject2[i].getString("id");
                    }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PA;
    }

    public static JSONObject[] getPrArea(String url) {
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
                        items = new String[jsonArray.length() + 1];

                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject1[i] = jsonArray.getJSONObject(i);
                            if (!prev.equals(jsonObject1[i].getString("processingAreaId"))) {
                                items[j + 1] = jsonObject1[i].getString("processingAreaId");
                                j++;
                            }
                            prev = jsonObject1[i].getString("processingAreaId");

                        }
                    }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject1;
    }

    public static JSONArray getElements(String url)
    {
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
                    jArray = jObj.getJSONArray("processingAreas");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jArray;
    }
}