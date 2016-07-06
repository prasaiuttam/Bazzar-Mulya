package com.example.prashant.bazzarmulya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by uttam on 6/30/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="product.db";
    public static final String TABLE_NAME="product_table";
    public static final String COL_1="NAME";
    public static final String COL_2="LOCATION";
    public static final String COL_3="PRICE";

   // public static final String TABLE_NAME2="location_table";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 18);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TABLE_NAME = "CREATE TABLE " + TABLE_NAME + " ("+
                COL_1 + " TEXT NOT NULL, " +
                COL_2 + " TEXT NOT NULL, " +
                COL_3 + " INTEGER NOT NULL " +
                " )";
//
//        String SQL_CREATE_TABLE_NAME2 = "CREATE TABLE " + TABLE_NAME2 + " ("+
//                COL_2 + " INTEGER NOT NULL PRIMARY KEY, " +
//                COL_LOCATION_NAME + " TEXT NOT NULL )";

        //db.execSQL("create table " + TABLE_NAME +" (NAME TEXT,LOCATION TEXT,PRICE INTEGER)");
        db.execSQL(SQL_CREATE_TABLE_NAME);
//        db.execSQL(SQL_CREATE_TABLE_NAME2);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String NAME,String LOCATION,int PRICE) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + "='" + NAME + "' AND " + COL_2 + "='" + LOCATION + "'",null);

        int hasNameLocation = cursor.getCount();

        Log.e("APP","Count is " + Integer.toString(hasNameLocation));

        if(hasNameLocation == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1,NAME);
            contentValues.put(COL_2,LOCATION);
            contentValues.put(COL_3,PRICE);
            db.insert(TABLE_NAME, null, contentValues);
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_3,PRICE);
            db.update(TABLE_NAME,contentValues,COL_1 + "='" + NAME + "' AND " + COL_2 + "='" + LOCATION + "'",null);
        }


        //db.insert(TABLE_NAME2, null, contentValues);
        return true;
//
//        long result = db.insert(TABLE_NAME,null ,contentValues);
//        if(result == -1)
//            return false;
//        else
//            return true;
    }


    public Cursor getDataBylocation(String location){
        SQLiteDatabase db = this.getReadableDatabase();

        String SQL_STR = "SELECT * FROM " + TABLE_NAME + " WHERE " + this.COL_2 + "=\"" + location + "\"";
        Cursor cursor = db.rawQuery(SQL_STR,null);

        return cursor;
    }
    public Cursor getDataBylocation() {
        SQLiteDatabase database = this.getReadableDatabase();

        String SQL_READ_TABLE_NAME = "SELECT * FROM " + TABLE_NAME + " GROUP BY LOCATION";
        Cursor cursor = database.rawQuery(SQL_READ_TABLE_NAME, null);

        return cursor;
    }

    public Cursor getDataByProductName(String productName){
        SQLiteDatabase db = this.getReadableDatabase();

        String SQL_STR = "SELECT * FROM " + TABLE_NAME + " WHERE " + this.COL_1 + "=\"" + productName + "\"";
        Cursor cursor = db.rawQuery(SQL_STR,null);

        return cursor;
    }
    public Cursor getDataByProductName() {
        SQLiteDatabase database = this.getReadableDatabase();

        String SQL_READ_TABLE_NAME = "SELECT * FROM " + TABLE_NAME + " GROUP BY NAME";
        Cursor cursor = database.rawQuery(SQL_READ_TABLE_NAME, null);

        return cursor;

    }

    public Cursor getDataByprice(String price) {
        SQLiteDatabase database = this.getReadableDatabase();

        String SQL_READ_TABLE_NAME = "SELECT * FROM " + TABLE_NAME ;
        Cursor cursor = database.rawQuery(SQL_READ_TABLE_NAME, new String[]{price});

        return cursor;
    }


    /**
     * Debugging purpose
     *
     * Can be remove after project completion
     */
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex)
        {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }


}
