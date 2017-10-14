package com.example.krudeboy.kloud9.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.krudeboy.kloud9.R;
import com.example.krudeboy.kloud9.dataHandler.UserHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kking on 10/12/2017.
 */

public class RegisterActivity extends AppCompatActivity{

    UserHandler user;
    EditText email_tv;
    EditText pass_tv;
    EditText cpass_tv;
    Button login_btn;
    Button reg_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        user = new UserHandler(this);

        email_tv = (EditText) findViewById(R.id.reg_email);
        pass_tv = (EditText) findViewById(R.id.reg_pwd);
        cpass_tv = (EditText) findViewById(R.id.reg_cpwd);
        login_btn = (Button) findViewById(R.id.reg_login_btn);
        reg_btn = (Button) findViewById(R.id.reg_reg_btn);

        login_btn.setOnClickListener(login_listen);
        reg_btn.setOnClickListener(reg_listen);

    }

    private View.OnClickListener reg_listen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (email_tv.getText().toString() == "") {
                toastDisp("please enter email!");
                return;
            }

            String email = email_tv.getText().toString();

            if (!isEmailValid(email)){
                toastDisp("please enter a valid email!");
                return ;
            }

            if(pass_tv.getText().toString().matches("")) {
                toastDisp("please enter password!");
                return;
            }

            String pwd = pass_tv.getText().toString();

            Log.i("PASSWORD", pwd);

            if(cpass_tv.getText().toString().matches("")) {
                toastDisp("please confirm password!");
                return;
            }else if(!cpass_tv.getText().toString().matches(pwd)) {
                toastDisp("passwords must match!");
                return;
            }




            user.register(email, pwd, new UserHandler.callback() {
                @Override
                public void onSuccess(boolean success, String res) {
                    if(success) {
                        toastDisp("Success");
                        changeScreen(MainActivity.class);
                    }else {
                        try {
                            JSONObject obj = new JSONObject(res);
                            toastDisp("Failed: " + obj.getString("error"));
                        }catch(JSONException e){
                            toastDisp("unavailable");
                        }
                    }
                }
            });
        }
    };

    private View.OnClickListener login_listen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            changeScreen(MainActivity.class);
        }
    };

    public void changeScreen(Class clazz){
        Intent i = new Intent(getApplicationContext(), clazz);
        startActivity(i);
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void toastDisp(String err){
        Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
    }
}
