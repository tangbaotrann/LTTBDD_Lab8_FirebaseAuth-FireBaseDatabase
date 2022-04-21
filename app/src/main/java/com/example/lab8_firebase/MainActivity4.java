package com.example.lab8_firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    private Button btnFinish;
    private ImageButton imgBtnHappy, imgBtnNormal, imgBtnUnHappy;
    private int happy, normal, unHappy;

    private FirebaseAuth auth;

    //private FacesDatabaseHandler db;
    private AppFacesDatabase db;
    private FacesDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // find id
        btnFinish = findViewById(R.id.btnFinish);
        imgBtnHappy = findViewById(R.id.imgBtnHappy);
        imgBtnNormal = findViewById(R.id.imgBtnNormal);
        imgBtnUnHappy = findViewById(R.id.imgBtnUnHappy);

        // Firebase
        auth = FirebaseAuth.getInstance();

        // SQLite
        //db = new FacesDatabaseHandler(this);
        db = Room.databaseBuilder(getApplicationContext(), AppFacesDatabase.class, "faces-manager").allowMainThreadQueries().build();
        // interface
        dao = db.facesDAO();

        // Nhận email from page "Sign in"
        Bundle bundle = getIntent().getExtras();
        String email = (String) bundle.get("email");
        // check mail
        Faces faces = dao.findByEmail(email);
        if(faces == null) {
            Toast.makeText(this, "New face.", Toast.LENGTH_SHORT).show();
        }

        // action
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity4.this, "Bạn đã đăng xuất thành công.", Toast.LENGTH_SHORT).show();
            }
        });

        // happy
        imgBtnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Faces faces = dao.findByEmail(email);
                // check email
                if(faces != null) {
                    faces.setHappy(faces.getHappy() + 1);

                    try {
                        dao.updateFaces(faces);
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Toast.makeText(MainActivity4.this, "Email: " + email + " - Happy: " + faces.getHappy(), Toast.LENGTH_SHORT).show();
                } else {
                    dao.saveFaces(new Faces(email, 1, 0, 0));
                    Toast.makeText(MainActivity4.this, email + " - Happy: 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // normal
        imgBtnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Faces faces = dao.findByEmail(email);

               if(faces != null) {
                   faces.setNormal(faces.getNormal() + 1);

                   try {
                       dao.updateFaces(faces);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

                   Toast.makeText(MainActivity4.this, "Email: " + email + " - Normal: " + faces.getNormal(), Toast.LENGTH_SHORT).show();
               } else {
                   dao.saveFaces(new Faces(email, 0, 0, 1));
                   Toast.makeText(MainActivity4.this, "Email: " + email + " - Normal: 1", Toast.LENGTH_SHORT).show();
               }
            }
        });

        // unhappy
        imgBtnUnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Faces faces = dao.findByEmail(email);

                if(faces != null) {
                    faces.setUnhappy(faces.getUnhappy() + 1);

                    try {
                        dao.updateFaces(faces);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity4.this, "Email: " + email + " - Unhappy: " + faces.getUnhappy(), Toast.LENGTH_SHORT).show();
                } else {
                    dao.saveFaces(new Faces(email, 0, 1, 0));
                    Toast.makeText(MainActivity4.this, "Email: " + email + " - Unhappy: 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}