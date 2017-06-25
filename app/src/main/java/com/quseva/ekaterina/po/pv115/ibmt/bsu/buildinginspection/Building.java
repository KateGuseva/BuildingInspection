package com.quseva.ekaterina.po.pv115.ibmt.bsu.buildinginspection;

import java.util.Date;
import java.util.UUID;

/**Данный класс является моделью
 * Created by Job on 08.12.2016.
 */

public class Building {
    private UUID mId;//уникальный идентификатор
    private String mTitle;//название объекта
    private String mDescription;// описание объекта
    private Date mDate;
    private boolean mSolved;//признак срочности




    public Building(){
       this(UUID.randomUUID());
    }
    public Building(UUID id){
        mId=id;
        mDate=new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getPhotoFilename(){
        return "IMG_" + getId().toString() + ".jpg";
    }
}

