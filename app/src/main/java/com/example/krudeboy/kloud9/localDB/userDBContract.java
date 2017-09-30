package com.example.krudeboy.kloud9.localDB;

import android.provider.BaseColumns;

/**
 * Created by Krudeboy on 9/29/2017.
 */

public class userDBContract {

    private userDBContract(){}

    public static class userEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COL_AUTH = "auth";

    }

}
