package de.neuendorf_online.garagedoorremote;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by michael on 14.11.15.
 */
public class DynDNS {
    public interface Callback {
        void onUrlResolved();
    }

    private String ipAddress;
    private int port;
    private Callback resolveCallback;


    public int getPort() {
        return port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    private class ReadIPAddressTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder stringBuilder = new StringBuilder();
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(params[0]);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                } else {
                    Log.d("resolve", "Failed to download file");
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                ipAddress = jsonObject.getString("ip-address");
                port = jsonObject.getInt("port");

                resolveCallback.onUrlResolved();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("resolve", e.getLocalizedMessage());
            }
        }
    }

    public void resolve(String url, Callback callback) {
        resolveCallback = callback;

        new ReadIPAddressTask().execute(url);
    }
}
