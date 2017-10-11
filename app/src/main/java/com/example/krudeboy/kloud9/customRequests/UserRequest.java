package com.example.krudeboy.kloud9.customRequests;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.krudeboy.kloud9.localDB.userDBHelper;
import com.example.krudeboy.kloud9.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Krudeboy on 9/29/2017.
 */

public class UserRequest<T> extends Request<T> {

    private final Gson gson;
    private final Class<T> clazz;
    private final JSONObject params;
    private final Response.Listener<T> listener;
    private userDBHelper db;
    private Context context;

    public UserRequest(int method,
                       String url,
                       Class<T> c,
                       JSONObject p,
                       Context con,
                       Response.ErrorListener elistener,
                       Response.Listener l) {


        super(method, url, elistener);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        listener = l;
        clazz = c;
        context = con;
        params = p;
    }


    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return  params == null ? null :params.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e){
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", params, "utf-8");
            return null;
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            JSONObject userObj = new JSONObject();
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            JsonParser p = new JsonParser();

            if(response.headers.get("x-auth") != null) {
                SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = m.edit();
                editor.putString("auth", response.headers.get("x-auth"));
                editor.commit();
            }
            return Response.success(gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }


    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
