package com.example.lab8_firebase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Faces.class}, version = 1)
public abstract class AppFacesDatabase extends RoomDatabase {

    public abstract FacesDAO facesDAO();

}
