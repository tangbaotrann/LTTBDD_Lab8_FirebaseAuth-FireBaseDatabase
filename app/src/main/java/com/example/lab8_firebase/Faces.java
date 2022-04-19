package com.example.lab8_firebase;

public class Faces {

    int id;
    String email;
    int happy;
    int unhappy;
    int normal;

    public Faces() {
    }

    public Faces(String email) {
        this.email = email;
    }

    // email + face: happy
    public Faces(String email, int happy) {
        this.email = email;
        this.happy = happy;
    }

    public Faces(int id, String email, int happy, int unhappy, int normal) {
        this.id = id;
        this.email = email;
        this.happy = happy;
        this.unhappy = unhappy;
        this.normal = normal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
