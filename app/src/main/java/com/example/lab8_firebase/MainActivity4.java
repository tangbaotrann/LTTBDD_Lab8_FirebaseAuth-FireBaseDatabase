package com.example.lab8_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity4 extends AppCompatActivity {

    private Button btnFinish;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // find id
        btnFinish = findViewById(R.id.btnFinish);

        auth = FirebaseAuth.getInstance();

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
    }
}