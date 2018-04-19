package com.example.yomna.symptomate;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by mohamed-soliman1 on 7/10/2017.
 */

public class Welcome extends AsyncTask<String, Void, JSONObject> {
    private Context mContext;
    public Welcome(Context mContext) {
        this.mContext = mContext;
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jobj=null;
        try {
            String text = "";
            BufferedReader reader = null;
            URL url = new URL("https://young-retreat-26153.herokuapp.com/welcome");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }
            text = sb.toString();
            reader.close();
            jobj = new JSONObject(text);
            return jobj;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onPostExecute(JSONObject result) {
        // result holds what you return from doInBackground;
        MainActivity cont = (MainActivity) mContext;
        cont.ServerWelcome(result);
    }
}

