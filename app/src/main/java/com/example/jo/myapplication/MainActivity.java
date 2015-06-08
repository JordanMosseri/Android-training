package com.example.jo.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;


public class MainActivity extends ActionBarActivity implements LoginListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private EditText usernameField;
    private EditText passwordField;
    private Button sendButton;
    private Button emptyButton;

    LoginTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate!");
        setContentView(R.layout.activity_main);

        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        sendButton = (Button) findViewById(R.id.send_button);
        emptyButton = (Button) findViewById(R.id.empty_button);

        task = new LoginTask(MainActivity.this);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!task.status.equals(AsyncTask.Status.RUNNING)) {
                    task.execute(usernameField.getText().toString(), passwordField.getText().toString());
                    //task.execute("jojo", "jojo");
                }
            }
        });

        emptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    //onCreate, onDestroy, onPause, onResume, onSaveInstanceState, onRestoreInstanceState


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState!");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState!");
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

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFail() {
        ((TextView) findViewById(R.id.error)).setVisibility(View.VISIBLE);

        //Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
    }
}
