package com.example.krudeboy.kloud9.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.krudeboy.kloud9.R;
import com.example.krudeboy.kloud9.dataHandler.UserHandler;

public class MainActivity extends AppCompatActivity {

    UserHandler user;
    EditText email_tv;
    EditText pass_tv;
    Button login_btn;
    Button reg_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        String mResponse = m.getString("auth", "");

        if (mResponse != ""){

        }

        user = new UserHandler(this);

        email_tv = (EditText) findViewById(R.id.log_email);
        pass_tv = (EditText) findViewById(R.id.log_pwd);
        login_btn = (Button) findViewById(R.id.log_login_btn);
        reg_btn = (Button) findViewById(R.id.log_reg_btn);

        login_btn.setOnClickListener(login_listen);
        reg_btn.setOnClickListener(reg_listen);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//               // user.register("1234567893@123.com","12345678");
//             //   user.userLogin("12345678@123.com", "12345678", new UserHandler.callback() {
//                //    @Override
//                 //   public void onSuccess(boolean success, String res) {
////                Log.i("LOGGING IN", "TRUE");
////                        user.userLogOut(new UserHandler.callback() {
////
////                            @Override
////                            public void onSuccess(boolean success, String res) {
////                                Log.i("USER LOGGED OUT", res);
////                            }
////                        });
//                 //   }
//              //  });
////                user.isLoggedin(new UserHandler.callback() {
////                    @Override
////                    public void onSuccess(boolean success, String res) {
////                        Log.i(success+"", res);
////                    }
////                });
//            }
//        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private View.OnClickListener reg_listen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            changeScreen(RegisterActivity.class);
        }
    };

    private View.OnClickListener login_listen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (email_tv.getText().toString() == "") {
                toastDisp("please enter email!");
                return;
            }

            if(pass_tv.getText().toString() == "") {
                toastDisp("please enter password!");
                return;
            }

            String email = email_tv.getText().toString();
            String pwd = pass_tv.getText().toString();

            user.userLogin(email, pwd, new UserHandler.callback() {
                @Override
                public void onSuccess(boolean success, String res) {
                    if(success){
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                        changeScreen(HomeScreen.class);
                    } else {
                        toastDisp(res);
                    }

                }
            });

        }
    };

    public void changeScreen(Class clazz){
        Intent i = new Intent(getApplicationContext(), clazz);
        startActivity(i);
    }

    public void toastDisp(String err){
        Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
    }
}
