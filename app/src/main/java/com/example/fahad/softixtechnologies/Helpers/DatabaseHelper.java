package com.example.fahad.softixtechnologies.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.fahad.softixtechnologies.models.ModelAccounts;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper" ;
    private static final String TAG1 = "DatabaseHelper" ;

    public static final String DB_NAME = "softix.db" ;
    public static final String TABLE_NAME = "Account_Master" ;
    public static final String TABLE_COL4 = "op_blnc" ;
    public static final String TABLE_COL1 = "acc_id" ;
    public static final String TABLE_COL2 = "acc_title" ;
    public static final String TABLE_COL3 = "group_id" ;
    public static final String TABLE1_NAME = "groups" ;
    public static final String TABLE2_NAME = "users" ;
    public static final int DB_VERSION = 2 ;
    private final Context context ;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String groupsTabledb = "CREATE TABLE " + TABLE1_NAME + " (group_id INTEGER PRIMARY KEY AUTOINCREMENT, group_title VARCHAR(30))" ;
        String accountsTabledb = "CREATE TABLE " + TABLE_NAME + " (" + TABLE_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_COL2 + " VARCHAR(50), " +
                TABLE_COL3 + " INTEGER, " + TABLE_COL4 + " VARCHAR(50))" ;
        String usersTabledb = "CREATE TABLE " + TABLE2_NAME + " (user_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)" ;

        try {
            db.execSQL(groupsTabledb);
            db.execSQL(accountsTabledb);
            db.execSQL(usersTabledb);
        }catch (Exception e){
            Log.d(TAG1, e.getMessage());
        }
    }

    public boolean addUser(String name, String password){
        SQLiteDatabase db = getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("username", name);
        contentValues.put("password", password);
        long result = db.insert(TABLE2_NAME,null, contentValues) ;

        if (result == -1){
            return false ;
        }else {
            return true ;
        }
    }

    public boolean addDataInAccounts(String accountName, String opBlnc){
        SQLiteDatabase db1 = this.getWritableDatabase() ;
        ContentValues contentValues1 = new ContentValues() ;
        contentValues1.put(TABLE_COL2, accountName);
        contentValues1.put(TABLE_COL4, opBlnc);

        Log.d(TAG, "Add Data : Adding " + accountName + " and " + opBlnc + " to " + TABLE_NAME) ;

        long result = db1.insert(TABLE_NAME, null, contentValues1) ;

        if (result == -1){
            return false ;
        }else {
            return true ;
        }
    }
    public boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor = db.rawQuery("Select * from " + TABLE2_NAME + " where username=? and password=?", new String[]{username,password}) ;
        if (cursor.getCount()>0){return true;}else {return false;}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String groupsTabledb = "DROP TABLE IF EXISTS " + TABLE1_NAME ;
        String accountsTabledb = "DROP TABLE IF EXISTS " + TABLE_NAME ;
        String usersTabledb = "DROP TABLE IF EXISTS " + TABLE2_NAME ;
        if (oldVersion == 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + TABLE_COL4 + " VARCHAR(60)");
        }else{
            Toast.makeText(context, "Data no upgraded", Toast.LENGTH_SHORT).show();
        }
        db.execSQL(groupsTabledb);
        db.execSQL(accountsTabledb);
        db.execSQL(usersTabledb);

        onCreate(db);
    }
    public Cursor getData(){
        SQLiteDatabase database = this.getWritableDatabase() ;
        String query = "SELECT * FROM " + TABLE_NAME ;
        Cursor datas = database.rawQuery(query, null) ;
        return datas ;
    }
    public void deleteRow(ModelAccounts nameAcc){
        SQLiteDatabase database = this.getWritableDatabase() ;
        database.delete(TABLE_NAME, TABLE_COL2 + "= ?", new String[]{String.valueOf(nameAcc.getAccName())});

        database.close();

    }
}

