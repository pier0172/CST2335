package com.example.student.andoid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Student on 2017-02-27.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    protected static final String ACTIVITY_NAME = "ChatDatabaseHelper";
    private static final int VERSION_NUM = 2;
    public static final String TABLE_NAME = "Chats";
    public static final String DATABASE_NAME = "Chats.db";
    public static final String NAME_COLUMN = "NAME";
    public static final String KEY_ID = "KEYID";
    public static final String KEY_MESSAGE = "MESSAGE";


    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db) //only called if not yet created
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("  + KEY_ID + " integer primary key autoincrement, " +  KEY_MESSAGE + " TEXT);" );   //AUTOINCREMENT


        Log.i(ACTIVITY_NAME, "Calling onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
       Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }



//    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
//    }

}
