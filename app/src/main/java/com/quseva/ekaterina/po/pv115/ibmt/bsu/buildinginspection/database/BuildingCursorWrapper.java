package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.Building;
import com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database.BuildingDbSchema.BuildingTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Job on 12.12.2016.
 */

public class BuildingCursorWrapper extends CursorWrapper{
    public BuildingCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Building getBuilding(){
        String uuidString = getString(getColumnIndex(BuildingTable.Cols.UUID));
        String title =getString(getColumnIndex(BuildingTable.Cols.TITLE));
        String description=getString(getColumnIndex(BuildingTable.Cols.DESCRIPTION));
        long date=getLong(getColumnIndex(BuildingTable.Cols.DATE));
        int isSolved=getInt(getColumnIndex(BuildingTable.Cols.SOLVED));

        Building building=new Building(UUID.fromString(uuidString));
        building.setTitle(title);
        building.setDescription(description);
        building.setDate(new Date(date));
        building.setSolved(isSolved != 0);
        return building;


    }
}
