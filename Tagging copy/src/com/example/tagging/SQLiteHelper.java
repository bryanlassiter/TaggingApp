package com.example.tagging;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {


public static final String TABLE_USERS = "users";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_USERNAME = "userName";
  public static final String COLUMN_PASSWORD = "password";
  public static final String COLUMN_VERIFYPASS = "verifyPass";
  public static final String COLUMN_FIRSTNAME = "firstName";
  public static final String COLUMN_LASTNAME = "lastName";
  
  public static final String TABLE_TAGS = "tags";
  public static final String COLUMN_TAGID = "tagId";
  public static final String COLUMN_TAGNAME = "tagName";
  public static final String COLUMN_CREATOR = "creator";
  public static final String COLUMN_DESC = "description";
   
  public static final String TABLE_PHOTOS = "photos";
  public static final String COLUMN_PHOTOID = "photoId";
  public static final String COLUMN_FILENAME = "fileName";
  public static final String COLUMN_PHOTOTAG = "photoTag";
  public static final String COLUMN_PHOTODATE = "photoDate";
  public static final String COLUMN_PHOTODESC = "photoDesc";

  private static final String DATABASE_NAME = "users.db";
  private static final int DATABASE_VERSION = 12;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table if not exists "
      + TABLE_USERS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_USERNAME
      + " text not null, " + COLUMN_PASSWORD 
      + " text not null, " + COLUMN_VERIFYPASS 
      + " text not null, " + COLUMN_FIRSTNAME
      + " text not null, " + COLUMN_LASTNAME
      + " text not null);";
  
  private static final String DATABASE_TAG = "create table if not exists "
	  + TABLE_TAGS + "(" + COLUMN_TAGID
	  + " integer primary key autoincrement, " + COLUMN_TAGNAME
	  + " text not null, " + COLUMN_CREATOR
	  + " text not null, " + COLUMN_DESC
	  + " text not null);";
  
  private static final String DATABASE_PHOTO = "create table if not exists "
		  + TABLE_PHOTOS + "(" + COLUMN_PHOTOID
		  + " integer primary key autoincrement, " + COLUMN_FILENAME
		  + " text not null, " + COLUMN_PHOTOTAG
		  + " text not null, " + COLUMN_PHOTODATE
		  + " text not null, " + COLUMN_PHOTODESC
		  + " text not null);";
  
  public SQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
    database.execSQL(DATABASE_TAG);
    database.execSQL(DATABASE_PHOTO);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(SQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAGS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS);
    onCreate(db);
  }

} 