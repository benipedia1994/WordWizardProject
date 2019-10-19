package edu.gatech.seclass.words6300;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "gameDatabase.db";
    public static final String COL_1="Turns";
    public static final String COL_2="Score";
    public static final String COL_3="Pool";
    public static final String Col_4="Rack";
    public static final String col_5="Board";



    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
