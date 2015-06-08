package com.example.jo.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jo.myapplication.model.Message;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ListActivity extends ActionBarActivity implements GetMessagesListener {

    ListView listView;
    private EditText inputField;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.list_view_messages);
        inputField = (EditText) findViewById(R.id.input_send);
        sendButton = (Button) findViewById(R.id.input_send_button);

        GetMessagesTask task = new GetMessagesTask(ListActivity.this);
        task.execute("jojo", "jojo");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String stringResponse="";

                try{
                    DefaultHttpClient client = new DefaultHttpClient();
                    HttpPut request = new HttpPut("http://training.loicortola.com/parlez-vous-android/message/" + "jojo" + "/" + "jojo");
                    request.setHeader("Content-Type", "application/json");
                    request.setHeader("Accept", "application/json");
                    request.setEntity(new StringEntity("{\"uuid\":\""+ UUID.randomUUID()+"\",\"login\":\"jojo\",\"message\":\"yo yo yo!\"}"));
                    HttpResponse response = client.execute(request);

                    InputStream content = response.getEntity().getContent();

                    stringResponse = InputStreamToString.convert(content);
                    Log.i(MainActivity.TAG, "str : " + stringResponse);

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }








            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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

    @Override
    public void onFinish(String stringResponse) {

        List list = new ArrayList<Message>();

        try {
            JSONArray jsonArray = new JSONArray(stringResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                Log.i(MainActivity.TAG, "uuid : " + o.getString("uuid"));
                Log.i(MainActivity.TAG, "login : "+o.getString("login"));
                Log.i(MainActivity.TAG, "message : "+o.getString("message"));

                list.add(new Message(o.getString("login"), o.getString("message")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView.setAdapter(new MyArrayAdapter(this, R.layout.text_view_for_list, list));

    }
}
