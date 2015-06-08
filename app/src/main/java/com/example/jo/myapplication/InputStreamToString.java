package com.example.jo.myapplication;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Jo on 16/03/2015.
 */
public class InputStreamToString {

    public static String getResponse(String url){

        String stringResponse="";

        try{
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            //request.setHeader("Content-Type", "application/json");
            HttpResponse response = client.execute(request);

            InputStream content = response.getEntity().getContent();

            stringResponse = InputStreamToString.convert(content);
            Log.i(MainActivity.TAG, "str : " + stringResponse);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringResponse;
    }

    public static String convert(InputStream is) {
        String line = "";
        StringBuilder builder = new StringBuilder();
        BufferedReader rd=new BufferedReader(new InputStreamReader(is));

        try {
            while ((line = rd.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
