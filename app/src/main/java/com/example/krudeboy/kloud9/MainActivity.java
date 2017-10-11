package com.example.krudeboy.kloud9;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.krudeboy.kloud9.dataHandler.UserHandler;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    UserHandler user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = new UserHandler(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
               // user.register("1234567893@123.com","12345678");
             //   user.userLogin("12345678@123.com", "12345678", new UserHandler.callback() {
                //    @Override
                 //   public void onSuccess(boolean success, String res) {
                Log.i("LOGGING IN", "TRUE");
                        user.userLogOut(new UserHandler.callback() {

                            @Override
                            public void onSuccess(boolean success, String res) {
                                Log.i("USER LOGGED OUT", res);
                            }
                        });
                 //   }
              //  });
//                user.isLoggedin(new UserHandler.callback() {
//                    @Override
//                    public void onSuccess(boolean success, String res) {
//                        Log.i(success+"", res);
//                    }
//                });
            }
        });
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
}
