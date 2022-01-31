package ept.dic2.crud_person_app.requests;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class CallAPI {
    //Method to send httpPostRequest
    //This method is taking two arguments
    //First argument is the URL of the script to which we will send the request
    //Other is an HashMap with name value pairs containing the data to be send with the request
    public String sendPutRequest(String requestURL,
                                  HashMap<String, String> postDataParams) {
//        //Creating a URL
//        URL url;
//
//        //StringBuilder object to store the message retrieved from the server
        StringBuilder sb = new StringBuilder();

        String responseAPI = null;
        try {
            URL url = new URL (requestURL);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            Gson gson=new Gson();
            String jsonInputString=gson.toJson(postDataParams);


            Log.e("POSTTT DATA JSON", jsonInputString);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
            Log.e("RESPONSE >>>>>>>>>>>>>", sb.toString());

            return responseAPI;
        } catch (Exception e) {
            e.printStackTrace();
        }

       return sb.toString();

    }

    public String sendUpdateRequest(String requestURL,
                                 HashMap<String, String> postDataParams) {
//        //Creating a URL
//        URL url;
//
//        //StringBuilder object to store the message retrieved from the server
        StringBuilder sb = new StringBuilder();

        String responseAPI = null;
        try {
            URL url = new URL (requestURL);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("UPDATE");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            Gson gson=new Gson();
            String jsonInputString=gson.toJson(postDataParams);


            Log.e("POSTTT DATA JSON", jsonInputString);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
            Log.e("RESPONSE >>>>>>>>>>>>>", sb.toString());

            return responseAPI;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

    public String sendGetRequest(String requestURL) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
        } catch (Exception e) {
        }

        return sb.toString();
    }


    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
