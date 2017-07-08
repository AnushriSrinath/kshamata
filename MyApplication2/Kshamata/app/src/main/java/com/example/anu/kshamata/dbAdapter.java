package com.example.anu.kshamata;

/**
 * Created by anu on 08-07-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by anu on 01-12-2016.
 */
public class dbAdapter extends SQLiteOpenHelper {

    public static final int database_version = 1;
    static final String TAG = "dbAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "contacts";
    static final int DATABASE_VERSION = 1;
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_DOB = "dob";
    static final String KEY_AGE = "age";
    static final String KEY_PHONE = "phone";
    public static String [] DBString;
    public static int i = 0;


    public dbAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        String query = "CREATE TABLE " + DATABASE_TABLE + " ("+
                KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT," +
                KEY_DOB + "TEXT," +
                KEY_PHONE + " TEXT" +

                " )";

        sdb.execSQL(query);
        Log.d("Database Operations", "Table created");

        //Log.d("Creating database ", "MyDBHandler.onCreate");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    //database putInformatin

//    public void putInformation(String name){
//        SQLiteDatabase SQ = getReadableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_NAME2, name);
//        //long k = SQ.insert(SearchInfo.TABLE_NAME, null, cv);
//
//        SQ.insert(TABLE_NAME,null,cv);
//        SQ.close();
//
//        Log.d("Database Operations", "One row inserted");
//        //Log.d("BloodBank","Info Inserted",name);
//        //Log.d("THE DATABASE", pname);
//    }

    public boolean addDataToTable(String pname)//,String pdob,String padr,String pph,String pemail,String pbg,String pwt,String phi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME,pname);
//        contentValues.put(COLUMN_DOB,pdob);
//        contentValues.put(COLUMN_ADDRESS,padr);
//        contentValues.put(COLUMN_PHONE,pph);
//        contentValues.put(COLUMN_EMAIL,pemail);
//        contentValues.put(COLUMN_BG,pbg);
//        contentValues.put(COLUMN_WEIGHT,pwt);
//        contentValues.put(COLUMN_HEALTHISSUES,phi);


        // db.insert(TABLE_NAME, null, contentValues);




        long result = db.insert(DATABASE_TABLE,"",contentValues);
        //db.close();


        if(result == -1)
        {
            return false;

        }

        else
        {

            return true;
        }




    }
    public boolean open() throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db = this.getWritableDatabase();

        return true;
    }
    //---closes the database---
    public void close()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.close();
    }



    public long insertContact(String name, String dob, String age, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_DOB, dob);
        initialValues.put(KEY_AGE, age);
        initialValues.put(KEY_PHONE, phone);
        return db.insert(DATABASE_TABLE,"",initialValues);
    }


    public Cursor getListContents() {


        SQLiteDatabase SQ = this.getWritableDatabase();

        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE 1";

        //String query2 = "SELECT * FROM " + TABLE_NAME + "WHERE " + COLUMN_BG + " = 'B+'";



        //Using cursors

        Cursor CR = SQ.rawQuery(query, null);
        return CR;

    }



    public void deleteHistory2(){
        String query = "DROP TABLE IF EXISTS " + DATABASE_TABLE;
        SQLiteDatabase SQ = getWritableDatabase();
        SQ.execSQL(query);
        onCreate(SQ);
    }



}
