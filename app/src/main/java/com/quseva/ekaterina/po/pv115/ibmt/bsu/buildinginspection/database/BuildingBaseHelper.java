package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.Building;
import com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database.BuildingDbSchema.BuildingTable;

/**
 * Created by Job on 12.12.2016.
 */

public class BuildingBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION=1;
    private static final String DATABASE_NAME="buildingBase.db";

    public BuildingBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BuildingTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                BuildingTable.Cols.UUID + ", " +
                BuildingTable.Cols.TITLE + ", " +
                BuildingTable.Cols.DESCRIPTION + ", " +
                BuildingTable.Cols.DATE + ", " +
                BuildingTable.Cols.SOLVED +
                ")"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
