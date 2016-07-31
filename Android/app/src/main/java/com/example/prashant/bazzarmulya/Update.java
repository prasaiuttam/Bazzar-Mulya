package com.example.prashant.bazzarmulya;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prashant on 6/29/16.
 */
public class Update extends AsyncTask<Void, Void, Void> {
    private RequestQueue requestQueue=null;
    private JSONObject jsonObject;
    private JSONArray prices;
    private  StringRequest request=null;
   // private static  final  String url="http://192.167.0.3/apiprice/";
    private  static final String url="http://192.168.1.114/apiprice/";
    private boolean requestComplete=true;
    private String itrUrl;
    private int count=0;
    boolean isComplete;
    Activity activity=new Activity();
    int i=1;

    public Update(Activity activity){
        this.activity=activity;
        requestQueue= Volley.newRequestQueue(activity);


    }
    @Override
    protected Void doInBackground(Void... params) {
        itrUrl = Update.url;
        //DeleteCurrentDataBase
        while(itrUrl!="null") {
            if(requestComplete==false){
                System.out.print(".");
                continue;
            }
            requestComplete=false;
            request = new StringRequest(Request.Method.GET, itrUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("Hello Async Task");
                    try{
                        jsonObject=new JSONObject(response);
                        itrUrl=jsonObject.getString("next");
                        System.out.println("next url"+itrUrl);
                        JSONArray price=jsonObject.getJSONArray("results");
                        i+=1;
                        requestComplete=true;
                        updateDataBase(price);
                    }catch (JSONException e){
                        System.out.println("Json Exception");

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("username", "prashant");
                    hashMap.put("password", "mintmachine");
                    //TODO SAVE THIS TO DATABASE
                    return hashMap;
                }
            };
            requestQueue.add(request);
        }
        return  null;
    }

    private void updateDataBase(JSONArray json) {
        //Uttam Here is whare you palce all the info in the database
        for(int i=0;i<json.length();i++){
        try{
            count+=1;
            String product=json.getJSONObject(i).getString("product");
            String area=json.getJSONObject(i).getString("area");
            String price="Price"+json.getJSONObject(i).getString("price");
            int p=23;//Integer.valueOf(price);
            System.out.println("******************************");
            DatabaseHelper myDB=new DatabaseHelper(this.activity);

            myDB.insertData(product,area,p);


        }catch (JSONException e){
            //Failed
        }
            System.out.println("Total="+count);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate();

//        Toast toast=new Toast(activity);
//        toast.setText("Update SucessFul");
//        toast.show();


    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Toast.makeText(activity, "Update Successfull", Toast.LENGTH_SHORT).show();
        super.onPostExecute(aVoid);
    }
}


