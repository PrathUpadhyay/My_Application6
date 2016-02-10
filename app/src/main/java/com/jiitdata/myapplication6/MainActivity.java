package com.jiitdata.myapplication6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.StringWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;

public class MainActivity extends AppCompatActivity {
    EditText respText;
    private ProgressDialog pDialog;
//    private String urlJsonObj = "http://lazeez.pythonanywhere.com/order";



    private String urlJsonObj = "https://172.16.68.6/corporate/Controller";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText edtUrl = (EditText) findViewById(R.id.edtURL);
        Button btnGo = (Button) findViewById(R.id.btnGo);
        respText = (EditText) findViewById(R.id.edtResp);
//        btnGo.setOnClickListener(new View.OnClickListener()
//        { @Override public void onClick(View view) {
//                String siteUrl = edtUrl.getText().toString();
//                ( new ParseURL() ).execute
//                        (new String[]{siteUrl}
//
//                )
//                ;
//            }
//        });
//        - See more at: http://www.survivingwithandroid.com/2014/04/parsing-html-in-android-with-jsoup.html#sthash.8VwR7hAN.dpuf

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        btnGo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeJsonObjectRequest();
                    }
                }
        );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void makeJsonObjectRequest() {
//        Toast.makeText(getApplication(),fname + "," + lname + "," + uname + "," + email + "," + pass + "," + cpass + "," +
//                sex + "," + dob + "," + coun + "," + desc,Toast.LENGTH_LONG).show();

        showpDialog();


        HttpsTrustManager.allowAllSSL();

        StringRequest sr = new StringRequest(Request.Method.POST, urlJsonObj, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {


                    Document doc = Jsoup.parse(response);

//                    JSONObject jsonObject = new JSONObject(response);

//                    Toast.makeText(getApplication(), doc.toString(), Toast.LENGTH_LONG).show();

                    Toast.makeText(getApplication(), response, Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    Toast.makeText(getApplication(), "404 :(", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }

                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), "Holaaa connection", Toast.LENGTH_SHORT).show();
                System.out.println(error);
                hidepDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("mode", "451");
                params.put("json", "{\"username\":\"13103431\",\"password\":\"4de4de\",\"languageid\":\"1\",\"browser\":\"Chrome_48\"}");
                params.put("__RequestType", "ajax");
                params.put("t", "Wed Feb 10 2016 23:52:47 GMT 0530 (India Standard Time)");







//
//                params.put("email","lovingyugs95@gmail.com");
//                params.put("password","1234");

                return params;
            }

//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                // since we don't know which of the two underlying network vehicles
//                // will Volley use, we have to handle and store session cookies manually
//                MyApp_Cookie.get().checkSessionCookie(response.headers);
//
//                return super.parseNetworkResponse(response);
//            }




            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


//                params.put("Content-Type", "application/x-www-form-urlencoded");


                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(sr);
    }


}
