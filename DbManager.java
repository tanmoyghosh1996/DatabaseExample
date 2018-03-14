package com.example.root.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by root on 1/2/18.
 */
/*
    We need to call the super i.e. parent class constructur
    And we need to pass 4 parameters
    1. Context context -> It is the context object we will get it from the activity while creating the instance
    2. String databasename -> It is the name of the database and here we are passing the constant that we already defined
    3. CursorFactory cursorFactory -> If we want a cursor to be initialized on the creation we can use cursor factory, it is optionall and that is why we passed null here
    4. int version -> It is an int defining our database version
  */
public class DbManager extends SQLiteOpenHelper {                          //A helper class to manage database creation and version management.
    public DbManager(Context context) {

        super(context,"Employee.DB",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Employee"+"(Empid integer primary key autoincrement,Empname text,Empmail text,Empphone text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Employee");
        onCreate(db);
    }
    public boolean Insertdata(String name,String email,String phone)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv=new ContentValues();      //This class is used to store a set of values
        cv.put("Empname",name);
        cv.put("Empmail",email);
        cv.put("Empphone",phone);
        long sucess=-1;                      //return the row ID of the newly inserted row, or -1 if an error occurred
        try {
          sucess= database.insert("Employee", null, cv);    //public long insert(String table, String nullColumnHack, ContentValues values)

        }catch (Exception e)
        {
            e.printStackTrace();
            sucess=-1;
        }
        if(sucess>-1)
        {
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<SetGet> getAllData()
    {
        ArrayList<SetGet> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from Employee",null);     //This interface provides random read-write access to the result set returned
        res.moveToFirst();                        //Move the cursor to the first row.

        while (res.isAfterLast()==false)
        {
            SetGet setGet=new SetGet();
            setGet.setEmpid(res.getString(res.getColumnIndex("Empid")));
            setGet.setEmpname(res.getString(res.getColumnIndex("Empname")));
            setGet.setEmpmail(res.getString(res.getColumnIndex("Empmail")));
            setGet.setEmpphone(res.getString(res.getColumnIndex("Empphone")));

            arrayList.add(setGet);
            res.moveToNext();   //Move the cursor to the next row.
        }
        res.close();
        return arrayList;
    }

    public boolean UpdateData(String empid,String name,String email,String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Empname",name);
        values.put("Empmail",email);
        values.put("Empphone",phone);
        long Sucess=-1;
        try {
            Sucess = db.update("Employee", values, "Empid = ?", new String[]{empid});   //public int update(String table, ContentValues values, String whereClause, String[] whereArgs)
        }
        catch (Exception e)
        {
            Sucess=-1;
        }
        if(Sucess>-1)
        {
            return true;
        }else {
            return false;
        }
    }

    public void DeleteData(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete("Employee","Empid=?",new String[]{id});     //public int delete(String table, String whereClause, String[] whereArgs)
       }
    }

