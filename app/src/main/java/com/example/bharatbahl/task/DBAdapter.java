package com.example.bharatbahl.task;

/**
 * Created by BharatBahl on 01-05-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by BharatBahl on 25-04-2017.
 */

public class DBAdapter
{
    MyHelper helper;
    Context context;
    DBAdapter(Context context){
        this.context=context;
        helper=new MyHelper(context);
    }
    public boolean updaterecord(String un, String pass, String gender, String email)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MyHelper.USERNAME,un);
        values.put(MyHelper.PASSWORD,pass);
        values.put(MyHelper.GENDER,gender);
        values.put(MyHelper.EMAIL,email);
        String whereClause=MyHelper.USERNAME+" =?";
        String whereArgs[]={String.valueOf(un)};
        int i=db.update(MyHelper.TABLE_NAME,values,whereClause,whereArgs);
        return i>0;
    }
    public Cursor searchById(String un)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        String whereClause=MyHelper.USERNAME+" = ?";
        String whereArgs[]={String.valueOf(un)};
        Cursor c=db.query(MyHelper.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
        return c;
    }
    /*public boolean deleterecord(int id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        String whereClause=MyHelper.ID+" = ?";
        String whereArgs[]={String.valueOf(id)};
        int i=db.delete(MyHelper.TABLE_NAME,whereClause,whereArgs);
        return i>0;
    }*/


    public Cursor showAllRecords(){
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor c=db.query(MyHelper.TABLE_NAME,null,null,null,null,null,null);
        return c;
    }
    public Cursor showRecord(String s){
        SQLiteDatabase db=helper.getWritableDatabase();
        String s1[]={s};
        String[] columns = {"GENDER", "EMAIL"};
        String selection = "USERNAME =?";
        Cursor c=db.query(MyHelper.TABLE_NAME,columns,selection,s1,null,null,null);
        return c;
    }


    public boolean validate(String username, String password)
    {
        boolean state=false;
        SQLiteDatabase db=helper.getWritableDatabase();
        String col[]={MyHelper.USERNAME,MyHelper.PASSWORD};
        String whereClause=MyHelper.USERNAME+" = ? and "+MyHelper.PASSWORD+" = ?";
        String whereArgs[]={username,password};
        Cursor c=db.query(MyHelper.TABLE_NAME,col,whereClause,whereArgs,null,null,null);
        if(c.moveToNext())
        {
            state=true;
        }
        return state;
    }

    /*public Cursor showById(int id)
    {

    }*/

    //Creating method for inserting data into the table
    public long insertData(String username,String password,String gender,String email)
    {
        //Getting Instance of SQLite Database Class
        SQLiteDatabase db=helper.getWritableDatabase();
        //Inserting data into table
        ContentValues values=new ContentValues();

        values.put(MyHelper.USERNAME,username);
        values.put(MyHelper.PASSWORD,password);
        values.put(MyHelper.GENDER,gender);
        values.put(MyHelper.EMAIL,email);


        long l=db.insert(MyHelper.TABLE_NAME,null,values);
        return l;
    }

    class MyHelper extends SQLiteOpenHelper
    {
        private static  final String DB_NAME="fitnessdb";
        private static final int DB_VERSION=1;
        static final String TABLE_NAME="userlogin";
        static final String USERNAME="username";
        static final String PASSWORD="password";
        static final String GENDER="gender";
        static final String EMAIL="email";
        //QUERY FOR CREATE TABLE
        private static final String TABLE_CREATE="create table if not exists "+TABLE_NAME+" ("+USERNAME+"  varchar(20) ,"+PASSWORD+"  varchar(20), "+GENDER+" varchar(10) ,"+EMAIL+"  varchar(20) , PRIMARY KEY("+USERNAME+"));";
        private static final String TABLE_DROP="drop table "+TABLE_NAME;

        public MyHelper(Context context) {
            super(context,DB_NAME, null, DB_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(TABLE_DROP);
            onCreate(db);
        }
    }
}
