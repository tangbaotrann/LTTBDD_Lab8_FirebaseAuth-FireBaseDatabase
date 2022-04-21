package com.example.lab8_firebase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FacesDAO {

    // load
    @Query("select * from Faces")
    List<Faces> getFaces();

    // find by email
    @Query("select * from FACES where email like :e_mail")
    Faces findByEmail(String e_mail);

    // save
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveFaces(Faces faces);

    // update
    @Update
    void updateFaces(Faces faces);

}
