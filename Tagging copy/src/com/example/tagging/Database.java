package com.example.tagging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Database {

  // Database fields
  private SQLiteDatabase database;
  private SQLiteHelper dbHelper;
  private String[] allColumns = { SQLiteHelper.COLUMN_ID,
      SQLiteHelper.COLUMN_USERNAME, SQLiteHelper.COLUMN_PASSWORD,
      SQLiteHelper.COLUMN_VERIFYPASS, SQLiteHelper.COLUMN_FIRSTNAME,
      SQLiteHelper.COLUMN_LASTNAME};
  private String[] tagColumns = { SQLiteHelper.COLUMN_TAGID,
	  SQLiteHelper.COLUMN_TAGNAME, SQLiteHelper.COLUMN_CREATOR,
	  SQLiteHelper.COLUMN_DESC};
  private String[] photoColumns = { SQLiteHelper.COLUMN_PHOTOID, 
		  SQLiteHelper.COLUMN_FILENAME, SQLiteHelper.COLUMN_PHOTOTAG,
		  SQLiteHelper.COLUMN_PHOTODATE, SQLiteHelper.COLUMN_PHOTODESC};
  
  public Database(Context context) {
    dbHelper = new SQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }
  public boolean validateUser(String userName, String password){
	  Cursor c = database.rawQuery("Select * FROM users WHERE userName = '" + userName + "' AND password = '" + password + "'", null);
	  if (c.getCount()>0){
		  return true;
	  }
	  return false;
  }
  
  public String numberOfPhotos(String tagName){
	  Cursor c = database.rawQuery("Select fileName FROM photos WHERE photoTag = '" + tagName + "'", null);
	  return Integer.toString(c.getCount());
  }
  
  public String getPhotoUri(long id, String tagName){
	  Cursor c = database.rawQuery("Select fileName FROM photos WHERE photoTag = '" + tagName + "' AND photoId = '" + id + "'", null);
	  c.moveToFirst();
	  return c.getString(0);
  }
  
  public String getData(String fileName) {
	  Cursor cursor = database.rawQuery("Select photoTag FROM photos WHERE fileName = '" + fileName + "'", null);
	  cursor.moveToFirst();
	  return cursor.getString(0); 
  }
  
  public void deletePhoto(String tagName) {
	  database.execSQL("Delete from photos WHERE fileName = '" + tagName + "'");
	  database.execSQL("UPDATE photos SET photoId = photoId + 10000000");
	  database.execSQL("UPDATE photos SET photoId = photoId + (10000000 - 1)");
  }
  
  public String getPhotoDesc(String fileName) {
	  Cursor cursor = database.rawQuery("Select photoDesc FROM photos WHERE fileName = '" + fileName + "'", null);
	  cursor.moveToFirst();
	  return cursor.getString(0); 
  }
  public String getTagDesc(String tagName) {
	  Cursor cursor = database.rawQuery("Select description FROM tags WHERE tagName = '" + tagName + "'", null);
	  cursor.moveToFirst();
	  return cursor.getString(0);
  }
  
  public List<String> getAllUserNames() {
	    List<String> users = new ArrayList<String>();

	    Cursor cursor = database.query(SQLiteHelper.TABLE_USERS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      User user = cursorToUser(cursor);
	      users.add(user.getFirstName());
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return users;
  }

  public User createUser(String userName, String password, String verifyPass, String firstName, String lastName) {
    ContentValues values = new ContentValues();
    values.put(SQLiteHelper.COLUMN_USERNAME, userName);
    values.put(SQLiteHelper.COLUMN_PASSWORD, password);
    values.put(SQLiteHelper.COLUMN_VERIFYPASS, verifyPass);
    values.put(SQLiteHelper.COLUMN_FIRSTNAME, firstName);
    values.put(SQLiteHelper.COLUMN_LASTNAME, lastName);
    long insertId = database.insert(SQLiteHelper.TABLE_USERS, null,
        values);
    Cursor cursor = database.query(SQLiteHelper.TABLE_USERS,
        allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    User newUser = cursorToUser(cursor);
    cursor.close();
    return newUser;
  }
  
  public Tag createTag(String tagName, String creator, String desc){
	  ContentValues values = new ContentValues();
	  values.put(SQLiteHelper.COLUMN_TAGNAME, tagName);
	  values.put(SQLiteHelper.COLUMN_CREATOR, creator);
	  values.put(SQLiteHelper.COLUMN_DESC, desc);
	  long insertId = database.insert(SQLiteHelper.TABLE_TAGS, null,
		values);
	  Cursor cursor = database.query(SQLiteHelper.TABLE_TAGS,
		tagColumns, SQLiteHelper.COLUMN_TAGID + " = " + insertId, null,
		null, null, null);
	  cursor.moveToFirst();
	  Tag newTag = cursorToTag(cursor);
	  cursor.close();
	  return newTag;
  }
  
  public Photo createPhoto(String fileName, String tagName, String photoDesc){
	  ContentValues values = new ContentValues();
	  values.put(SQLiteHelper.COLUMN_FILENAME, fileName);
	  values.put(SQLiteHelper.COLUMN_PHOTOTAG, tagName);
	  values.put(SQLiteHelper.COLUMN_PHOTODESC, photoDesc);
	  Date date = new Date();
	  String dateString = date.toString();
	  values.put(SQLiteHelper.COLUMN_PHOTODATE, dateString);
	  long insertId = database.insert(SQLiteHelper.TABLE_PHOTOS, null, values);
	  Cursor cursor = database.query(SQLiteHelper.TABLE_PHOTOS,
			  photoColumns, SQLiteHelper.COLUMN_PHOTOID + " = " + insertId, null,
			  null, null, null);
	  cursor.moveToFirst();
	  Photo newPhoto = cursorToPhoto(cursor);
	  cursor.close();
	  return newPhoto;
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<User>();

    Cursor cursor = database.query(SQLiteHelper.TABLE_USERS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      User user = cursorToUser(cursor);
      users.add(user);
      cursor.moveToNext();
    }
    cursor.close();
    return users;
  }

  public List<Tag> getAllTags() {
	  List<Tag> tags = new ArrayList<Tag>();
	  
	  Cursor cursor = database.query(SQLiteHelper.TABLE_TAGS, 
		   tagColumns, null, null, null, null, null);
	  
	  cursor.moveToFirst();
	  while (!cursor.isAfterLast()) {
		  Tag tag = cursorToTag(cursor);
		  tags.add(tag);
		  cursor.moveToNext();
	  }
	  cursor.close();
	  return tags;
  }
  
  public List<String> getAllTagNames() {
	  List<String> tags = new ArrayList<String>();
	  
	  Cursor cursor = database.rawQuery("Select tagName FROM tags", null);
	  cursor.moveToFirst();
	  while (!cursor.isAfterLast()) {
		  tags.add(cursor.getString(0));
		  cursor.moveToNext();
	  }
	  cursor.close();
	  return tags;
  }
  
  public List<String> getAllPhotos(String tagName) {
	  List<String> photos = new ArrayList<String>();
	  
	  
	  Cursor cursor = database.rawQuery("Select fileName FROM photos WHERE photoTag = '" + tagName + "'", null);
	  cursor.moveToFirst();
	  while (!cursor.isAfterLast()) {
		  photos.add(cursor.getString(0));
		  Log.d("blah", cursor.getString(0));
		  cursor.moveToNext();
	  }
	  cursor.close(); 
	  return photos;
  }
  
  private User cursorToUser(Cursor cursor) {
	  User user = new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
	  return user;
  }
  
  private Tag cursorToTag(Cursor cursor) {
	  Tag tag = new Tag(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
	  return tag;
  }
  
  private Photo cursorToPhoto(Cursor cursor) {
	  Photo photo = new Photo(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
	  return photo;
  }
} 