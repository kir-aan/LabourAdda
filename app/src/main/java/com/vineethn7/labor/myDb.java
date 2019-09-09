package com.vineethn7.labor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDb extends SQLiteOpenHelper {
    public static final String L_TAB_NAME="Labour";
    public static final String DB_NAME="Labour_adda_DB.db";
    public static final String C_TAB_NAME="Contractor";
    public static final int CURRENT_VERSION=1;

    public myDb(Context context) {

        super(context, DB_NAME, null,CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DBHELPER", "DBHELPERonCreate: ");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+L_TAB_NAME+" (Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,PhoneNo TEXT,Age INTEGER,Address TEXT,Skill TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+C_TAB_NAME+" (Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,PhoneNo TEXT,Age INTEGER,Address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+L_TAB_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+C_TAB_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertIntoL(String Name,String PhoneNo,int age,String address,String Skill){
        Log.d("DBHELPERinsert", "DBHELPERinsertonCreate: ");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Name",Name);
        cv.put("PhoneNo",PhoneNo);
        cv.put("Age",age);
        cv.put("Skill",Skill);
        long r=db.insert(L_TAB_NAME,null,cv);
        if(r==-1)
            return false;
        else
            return true;
    }

    public boolean insertIntoC(String Name,String PhoneNo,int age,String address){
        Log.d("DBHELPERinsert", "DBHELPERinsertonCreate: ");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Name",Name);
        cv.put("PhoneNo",PhoneNo);
        cv.put("Age",age);
        long r=db.insert(L_TAB_NAME,null,cv);
        if(r==-1)
            return false;
        else
            return true;

    }
    public int existsInWhichTAB(String phoneNum){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM "+ L_TAB_NAME+" WHERE phoneNo="+phoneNum,null);
        if(result.getCount()!=0){
            return 2;   //2-->labour table
        }
        result=db.rawQuery("SELECT * FROM "+ C_TAB_NAME+" WHERE phoneNo = "+phoneNum,null);
        if(result.getCount()!=0){
            return 1;   //1-->Contractor table
        }
        return 0;   //0-->not found
    }
}
