package com.example.lab8_firebase;

import androidx.appcompat.app.AppCompatActivity;

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
    private int id;

    private FirebaseAuth auth;

    private FacesDatabaseHandler db;

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
        db = new FacesDatabaseHandler(this);

        // Nhận email from page "Sign in"
        Bundle bundle = getIntent().getExtras();
        final String email = (String) bundle.get("email");

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

                // save email + faces
                db.saveFaces(new Faces(email, happy+=1));

                // check database
//                Faces faces = db.getFace(email);
//                if (faces.getEmail()) {
//                    db.getFaces();
//                    Toast.makeText(MainActivity4.this, "++: " + (happy+=1), Toast.LENGTH_SHORT).show();
//                }

                Toast.makeText(MainActivity4.this, "Email: " + email + " - Happy: " + happy, Toast.LENGTH_SHORT).show();
            }
        });

        // normal
        imgBtnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save email + faces
                db.saveFaces(new Faces(email, normal+=1));

                Toast.makeText(MainActivity4.this, "Email: " + email + " - Normal: " + normal, Toast.LENGTH_SHORT).show();
            }
        });

        // unhappy
        imgBtnUnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save email + faces
                db.saveFaces(new Faces(email, unHappy+=1));

                Toast.makeText(MainActivity4.this, "Email: " + email + " - Unhappy: " + unHappy, Toast.LENGTH_SHORT).show();
            }
        });
    }
}