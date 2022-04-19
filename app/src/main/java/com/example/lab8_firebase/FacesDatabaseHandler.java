package com.example.lab8_firebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FacesDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "facesManager";
    private static final String TABLE_FACES = "faces";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_HAPPY = "happy";
    private static final String KEY_UNHAPPY = "unhappy";
    private static final String KEY_NORMAL = "normal";

    // Constructor
    public FacesDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_FACES + "("
                   + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_EMAIL + " TEXT,"
                   + KEY_HAPPY + " INTEGER, " + KEY_UNHAPPY + " INTEGER, " + KEY_NORMAL + " INTEGER" + ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FACES);

        onCreate(sqLiteDatabase);
    }

    // save faces
    void saveFaces(Faces faces) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, faces.getEmail());
        values.put(KEY_HAPPY, faces.getHappy());
        values.put(KEY_UNHAPPY, faces.getUnhappy());
        values.put(KEY_NORMAL, faces.getNormal());

        db.insert(TABLE_FACES, null, values);
        db.close();
    }

    // load
    public List<Faces> getFaces() {
        List<Faces> list = new ArrayList<Faces>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FACES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Faces faces = new Faces();
                faces.setId(Integer.parseInt(cursor.getString(0)));
                faces.setEmail(cursor.getString(1));
                faces.setHappy(Integer.parseInt(cursor.getString(2)));
                faces.setUnhappy(Integer.parseInt(cursor.getString(3)));
                faces.setNormal(Integer.parseInt(cursor.getString(4)));

                // Adding people to list
                list.add(faces);
            } while (cursor.moveToNext());
        }

        return list;
    }

    // find by id
//    Faces getFace(String email) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_FACES, new String[] {
//                KEY_ID, KEY_EMAIL, KEY_HAPPY, KEY_UNHAPPY, KEY_NORMAL
//        }, KEY_EMAIL + " =?", new String[] {String.valueOf(email)}, null, null, null, null);
//
//        if(cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        Faces faces = new Faces(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
//                                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
//                                Integer.parseInt(cursor.getString(4)));
//        return faces;
//    }
}
