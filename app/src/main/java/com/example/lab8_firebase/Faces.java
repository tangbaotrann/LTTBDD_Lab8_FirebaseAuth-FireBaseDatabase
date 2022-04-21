package com.example.lab8_firebase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Faces {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="email")
    public String email;

    @ColumnInfo(name="happy")
    public int happy;

    @ColumnInfo(name="unhappy")
    public int unhappy;

    @ColumnInfo(name="normal")
    public int normal;

    public Faces() {
    }

    // email + face: happy
//    public Faces(String email, int happy) {
//        this.email = email;
//        this.happy = happy;
//    }

    public Faces(String email, int happy, int unhappy, int normal) {
        this.email = email;
        this.happy = happy;
        this.unhappy = unhappy;
        this.normal = normal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getUnhappy() {
        return unhappy;
    }

    public void setUnhappy(int unhappy) {
        this.unhappy = unhappy;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }
}
