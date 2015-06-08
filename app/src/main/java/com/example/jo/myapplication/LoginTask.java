package com.example.jo.myapplication;

import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jo on 16/03/2015.
 */
public class LoginTask extends AsyncTask<String, Void, Boolean> {

    LoginListener listener;

    Status status;

    LoginTask(LoginListener listener){
        status = Status.PENDING;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(String... params) {

        status = Status.RUNNING;

        Log.i(MainActivity.TAG, "Execution de la requete avec les parametres " + params[0] + " - " + params[1]);

        int status=0;

        String stringResponse = InputStreamToString.getResponse("http://training.loicortola.com/parlez-vous-android/connect/" + params[0] + "/" + params[1]);


        //ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        //String user = mapper.readValue(content, String.class);
        //JSON
        //

        try {
            JSONObject o = new JSONObject(stringResponse);
            status = o.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(status == 200){
            return true;
        }
        else{
            return false;
        }

        //return false;
    }



    //UIThread

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if(aBoolean){
            listener.onSuccess();
        }
        else{
            listener.onFail();
        }

        status = Status.FINISHED;
    }

    //UIThread

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
