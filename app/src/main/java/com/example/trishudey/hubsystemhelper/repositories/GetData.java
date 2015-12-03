package com.example.trishudey.hubsystemhelper.repositories;

import android.content.Intent;

import com.example.trishudey.hubsystemhelper.Activities.main.Options_Page_User;
import com.example.trishudey.hubsystemhelper.encryption.MD5Encryption;
import com.example.trishudey.hubsystemhelper.repositories.JsonGetResponse;
import com.example.trishudey.hubsystemhelper.repositories.JsonParser;
import com.example.trishudey.hubsystemhelper.repositories.PostToUrl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by trishu.dey on 26/11/15.
 */
public class GetData {
    static JsonGetResponse jsonGetResponse = new JsonGetResponse();
    static String time[];
    public static JSONArray jArray;
    static JSONObject jsonObject[];
    public static boolean validate(String user, String password)
    {
        MD5Encryption md5Encryption = new MD5Encryption();
        // DELETE IN FINAL COPY
        if(user.equals("hubuser") && password.equals(md5Encryption.MD5("hubuser123")))
        {
            return true;
        }
        else if(user.equals("admin") && password.equals(md5Encryption.MD5("adminfkart")))
        {
            return true;
        }
        else {
            String validationUrl = "http://10.0.2.2:8082/v1/admin/getCredentials/" + user;


            String response = jsonGetResponse.validateUser(validationUrl);
            if (response != null && response.equals(password)) {
                return true;
            }
        }
       return false;
    }
    public static JSONObject[] getallHubs()
    {
        JsonParser jParser = new JsonParser();
        JSONObject jsonObject[];
        jsonObject = jParser.getJSONFromUrl("\n" +
                "http://hubsystem-app.nm.flipkart.com/v1/hub/all");
        return jsonObject;
    }

    public static boolean manageaccounts(String action , String user, String pass, String newPass)
    {
        String url;
        boolean response = false;
        switch(action)
        {
            case "Delete": url = "http://10.0.2.2:8082/v1/admin/deleteUser/" + user;
                response = jsonGetResponse.deleteUser(url);
                break;

            case "Update" : url = "http://10.0.2.2:8082/v1/admin/getCredentials/" + user;
                response = jsonGetResponse.updateUser(url,user,pass,newPass);
                break;
            case "Add" : url = "http://10.0.2.2:8082/v1/admin/getCredentials/" + user;
                String addUrl = "http://10.0.2.2:8082/v1/admin/addCredentials";
                response = jsonGetResponse.addUser(url,addUrl,user,pass);
                break;
        }
        return response;
    }

    public static boolean addBusinessPartner(String selectedPartner,String id,String data_entered)
    {
        String url = "http://hubsystem-app.nm.flipkart.com/v1/admin/addProvider";
        String params = "&type="+selectedPartner+"&installationId="+id+"&data="+data_entered;

        PostToUrl postToUrl = new PostToUrl();
        int code = postToUrl.post(url,params);
        if(code == 200)
        {
            return true;
        }
        return false;
    }

    public static boolean createHub(String Hname,String Hcoc,String Htype,String Hzone,String Hfacility)
    {
        String url ="http://hubsystem-app.nm.flipkart.com/v1/admin/addHub";
        String params = "&name="+Hname+"&coc="+Hcoc+"&type="+Htype+"&zone="+Hzone+"&facilityServiceHubId="+Hfacility;

        PostToUrl postToUrl = new PostToUrl();
        int code = postToUrl.post(url,params);
        if(code == 200)
            return true;
        return false;
    }

    public static String[] getSlots(String hubFacilityId)
    {
        String url_slots = "http://hubsystem-app.nm.flipkart.com/v1/slots?facilityId="+hubFacilityId;

        time = jsonGetResponse.slots(url_slots);
        return time;
    }

    public static String[] getPA(String hubId)
    {
        String url = "http://hubsystem-app.nm.flipkart.com/v1/hub/" + hubId+"/config?task=sortation";

        time = jsonGetResponse.pa(url);
        return time;
    }

    public static JSONObject[] getProcessArea(String facilityId,String selectedSlot,String selectedPA)
    {
        String url = "http://hubsystem-app.nm.flipkart.com/v1/config/fetch?facilityId="+facilityId+"&slotId="+selectedSlot+"&processingAreaId="+selectedPA;
        jsonObject = jsonGetResponse.getPrArea(url);
        return jsonObject;
    }

    public static JSONArray gethubElement(String hubId)
    {
        String url = "http://hubsystem-app.nm.flipkart.com/v1/hub/" + hubId+"/processingAreas?task=sortation&hubId="+hubId;
        jArray = jsonGetResponse.getElements(url);
        return jArray;
    }
}
