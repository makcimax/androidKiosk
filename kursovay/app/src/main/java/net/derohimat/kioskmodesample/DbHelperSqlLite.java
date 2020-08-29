package net.derohimat.kioskmodesample;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.HashMap;
import java.util.UUID;

public class DbHelperSqlLite  extends SQLiteOpenHelper implements  DbHelper{

    IgetHashOfPassword hashFunc;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AndroidLogin";

    // Login table name
    private static final String TABLE_LOGIN = "login";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_UID = "uid";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";




   /* @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }*/

    @Override
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] { KEY_NAME};

        Cursor cursor = db.query(TABLE_LOGIN, columns, null,
                null, null, null, null);
        return cursor;
    }

    public DbHelperSqlLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        hashFunc = new getSimpleHash();
    }

    // Table Create Statements
    private static final String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_UID + " TEXT,"
            + KEY_NAME  + " TEXT UNIQUE,"
            + KEY_PASSWORD + " INTEGER" + ")";

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String uid,String name, String hashOfPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UID, uid); // uid
        values.put(KEY_NAME, name); // FirstName

        values.put(KEY_PASSWORD, hashOfPassword); // Created At

        // Inserting Row
        db.insert(TABLE_LOGIN, null, values);

        db.close(); // Closing database connection
    }

    /**
     * Storing user details in database
     * *
     public void updateProfile(String fname, String lname, String email, String mobile, String aclass,
     String school, String profile_pic, String uid) {
     SQLiteDatabase db = this.getWritableDatabase();
     ContentValues updateValues = new ContentValues();
     updateValues.put(KEY_FIRSTNAME, fname); // FirstName
     updateValues.put(KEY_LASTNAME, lname); // LastName
     updateValues.put(KEY_EMAIL, email); // Email
     updateValues.put(KEY_MOBILE, mobile); // Mobile Number
     updateValues.put(KEY_CLASS, aclass); // Class
     updateValues.put(KEY_SCHOOL, school); // School
     updateValues.put(KEY_PROFILE_PIC, profile_pic);
     db.update(TABLE_LOGIN, updateValues, KEY_UID + "=?", new String[] { String.valueOf(uid) });
     db.close();
     }
     /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("uid", cursor.getString(1));
            user.put("name", cursor.getString(2));
            user.put("password", cursor.getString(3));

        }
        cursor.close();
        db.close();
        // return user
        return user;
    }



    public void updateUserData(String name,String  hashOfPassword)
    {
        boolean res = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LOGIN + "where name= '"+name+"''";

        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            ContentValues updateValues = new ContentValues();
            updateValues.put(KEY_NAME,name); // FirstName
            updateValues.put(KEY_PASSWORD, hashOfPassword); // LastName
            db.update(TABLE_LOGIN, updateValues, KEY_NAME + "=?", new String[] { String.valueOf(cursor.getString(2)) });
        }
        cursor.close();
db.close();
    }



    public boolean isPasswordTrue(String name,String  hashOfPassword)
    {
        boolean res = false;
        SQLiteDatabase db = this.getWritableDatabase();


        String selectQuery = "SELECT * FROM " + TABLE_LOGIN + " WHERE name = '"+name+"'";


        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {


            res = (hashOfPassword).equals( cursor.getString(3));
        }

        cursor.close();
        db.close();
        return res;

    }


    public boolean isUserExist(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       boolean o = db.isOpen();
        String selectQuery = "SELECT * FROM " + TABLE_LOGIN + " WHERE name = '"+name+"'";

        Cursor cursor =  db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        boolean res = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return res;
    }





    /**
     * Getting user login status
     * return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
    }



    public int  getUidByExistName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String uid = "0";
        String selectQuery = "SELECT * FROM " + TABLE_LOGIN + " WHERE name = '"+name+"'";


        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            uid =  cursor.getString(1);
        }

        cursor.close();
        db.close();
        return Integer.valueOf(uid);
    }


    /**
     * Recreate database
     * Delete all tables and create them again
     * */
    public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.close();
    }

    @Override
    public void deleteAllUsers() {
        resetTables();
    }
}