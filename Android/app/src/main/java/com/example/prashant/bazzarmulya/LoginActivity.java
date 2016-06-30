package com.example.prashant.bazzarmulya;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView email=null;
    LongIO async=null;
    Button button=null;
    EditText password=null;
    private static  final  String url="http://192.167.0.3/api/token/";
    private RequestQueue queue;
    private StringRequest json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         final Activity a=this;
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        button=(Button) findViewById(R.id.email_sign_in_button);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.print("Hello!\n");
                        if(async==null){
                            async=new LongIO();
                            async.attach(a);
                            async.execute();

                        }else if(async.complete()==true){
                            async=new LongIO();
                            async.attach(a);
                            async.execute();

                        }
                    }
                }
        );


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private class LongIO extends AsyncTask<Void,Integer,Void>{
        public boolean isComplete=false;
        private  Activity activity;
        public boolean complete(){
            return  isComplete;
        }
        @Override
        protected Void doInBackground(Void... para) {

            System.out.println("DoInBackGround");
            System.out.println("Done in background");
            if (queue==null) {
                queue = Volley.newRequestQueue(activity);
            }
            json = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.print("Hello World! ");
                    System.out.println("Prashant is Great " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("ASDF"+error);
                    System.out.println("Hello Error");
                    isComplete=true;
                }
            }) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    System.out.println("asdf"+password.getText());
                    hashMap.put("username", ""+email.getText());
                    hashMap.put("password", ""+""+password.getText());
                    //TODO SAVE THIS TO DATABASE
                    return hashMap;
                }
            };
            System.out.println("Prashant");
            queue.add(json);
            json=null;detach();
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        public void attach(Activity activity){
            this.activity=activity;
        }
        public   void detach(){
            this.activity=null;
        }


    }
}
