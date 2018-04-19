package com.example.yomna.symptomate;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Chat extends AsyncTask<String, Void, JSONObject> {
    private Context mContext;
    public Chat(Context mContext) {
        this.mContext = mContext;
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jobj=null;
        try {
            String text = "";
            BufferedReader reader = null;
            URL url = new URL("https://young-retreat-26153.herokuapp.com/chat");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization",strings[0]);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(strings[1]);
            writer.flush();
            writer.close();
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
        cont.ServerChat(result);
    }




}