package ept.dic2.crud_person_app.tasks;

import static android.view.View.GONE;

import static ept.dic2.crud_person_app.activities.MainActivity.CODE_GET_REQUEST;;
import static ept.dic2.crud_person_app.activities.MainActivity.CODE_PUT_REQUEST;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ProgressBar;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.logging.Logger;

import ept.dic2.crud_person_app.R;
import ept.dic2.crud_person_app.activities.MainActivity;
import ept.dic2.crud_person_app.requests.CallAPI;

//inner class to perform network request extending an AsyncTask
public class CrudPerson extends AsyncTask<Void, Void, String> {


    ProgressBar progressBar;


    //the url where we need to send the request
    String url;

    //the parameters
    HashMap<String, String> params;

    //the request code to definrequestURLe whether it is a GET or POST
    int requestCode;

    private final MainActivity context;

    //constructor to initialize values
    public  CrudPerson(MainActivity context, String url, HashMap<String, String> params, int requestCode) {
        this.url = url;
        this.params = params;
        this.requestCode = requestCode;
        this.context = context;
    }



    //when the task started displaying a progressbar
    @Override
    protected void onPreExecute() {
//        progressBar = (ProgressBar) MainActivity.findViewById(R.id.progressBar);
        super.onPreExecute();
//        progressBar.setVisibility(View.VISIBLE);
    }


    //this method will give the response from the request
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


//        progressBar.setVisibility(GONE);
//        try {
//            JSONObject object = new JSONObject(s);
//
//            if (!object.getBoolean("error")) {
//                Toast.makeText(context, object.getString("message"), Toast.LENGTH_SHORT).show();
//                //refreshing the herolist after every operation
//                //so we get an updated list
////                MainActivity.refreshPersonList(object.getJSONArray("persons"));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }

    //the network operation will be performed in background
    @Override
    protected String doInBackground(Void... voids) {
        CallAPI callAPI = new CallAPI();

        if (requestCode == CODE_PUT_REQUEST)
            return callAPI.sendPutRequest(url, params);


        if (requestCode == CODE_GET_REQUEST)
            return callAPI.sendGetRequest(url);

        return null;
    }
}