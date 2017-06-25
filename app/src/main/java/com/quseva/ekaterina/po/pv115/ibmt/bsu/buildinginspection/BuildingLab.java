package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database.BuildingBaseHelper;
import com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database.BuildingCursorWrapper;
import com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database.BuildingDbSchema;
import com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection.database.BuildingDbSchema.BuildingTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**Класс для хранения массива-списка объектов
 * Created by Job on 09.12.2016.
 */

public class BuildingLab {
    private static BuildingLab sBuildingLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static BuildingLab get(Context context) {
        if (sBuildingLab == null) {
            sBuildingLab = new BuildingLab(context);
        }
        return sBuildingLab;
    }

    private BuildingLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new BuildingBaseHelper(mContext).getWritableDatabase();


    }

    public void addBuilding(Building b) {
        ContentValues values = getContentValues(b);
        mDatabase.insert(BuildingTable.NAME, null, values);


    }


    public List<Building> getBuilding() {
        List<Building> buildings=new ArrayList<>();
        BuildingCursorWrapper cursor = queryBuildings(null, null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                buildings.add(cursor.getBuilding());
                cursor.moveToNext();
            }

            }finally {
            cursor.close();
        }
        return buildings;
    }

    public File getPhotoFile(Building building){
        File externalFilesDir=mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir==null){
            return null;
        }
        return new File(externalFilesDir, building.getPhotoFilename());
    }



    public Building getBuilding(UUID id) {//метод возвращает объект Building с заданным идентификатором

        BuildingCursorWrapper cursor = queryBuildings(
                BuildingTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getBuilding();
        }finally{
            cursor.close();
        }
    }


    public void updateBuilding(Building building) {
        String uuidString = building.getId().toString();
        ContentValues values = getContentValues(building);

        mDatabase.update(BuildingTable.NAME, values,
                BuildingTable.Cols.UUID + " =?",
                new String[]{uuidString});
    }

    public void deleteCrime(UUID crimeId)
    {
        String uuidString = crimeId.toString();
        mDatabase.delete(BuildingTable.NAME, BuildingTable.Cols.UUID + " = ?", new String[] {uuidString});
    }
    private static ContentValues getContentValues(Building building) {
        ContentValues values = new ContentValues();
        values.put(BuildingTable.Cols.UUID, building.getId().toString());
        values.put(BuildingTable.Cols.TITLE, building.getTitle());
        values.put(BuildingTable.Cols.DESCRIPTION, building.getDescription());
        values.put(BuildingTable.Cols.DATE, building.getDate().getTime());
        values.put(BuildingTable.Cols.SOLVED, building.isSolved() ? 1 : 0);
        return values;

    }

    private BuildingCursorWrapper queryBuildings(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                BuildingTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null

        );
        return new BuildingCursorWrapper(cursor);
    }
}
