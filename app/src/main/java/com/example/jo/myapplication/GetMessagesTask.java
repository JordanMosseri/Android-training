package com.example.jo.myapplication;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Jo on 16/03/2015.
 */
public class GetMessagesTask extends AsyncTask<String, Void, String> {

    GetMessagesListener listener;

    GetMessagesTask(GetMessagesListener listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        Log.i(MainActivity.TAG, "Execution de la requete avec les parametres " + params[0] + " - " + params[1]);

        String stringResponse = InputStreamToString.getResponse("http://training.loicortola.com/parlez-vous-android/message/" + params[0] + "/" + params[1]);

        return stringResponse;
    }



    //UIThread

    @Override
    protected void onPostExecute(String stringResponse) {
        //super.onPostExecute(aBoolean);


        listener.onFinish(stringResponse);

    }

    //UIThread

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
