package com.example.lab8_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity3 extends AppCompatActivity {

    private EditText edtYourName, edtEmailRegister, edtTypePasswordRegister, edtReTypePasswordRegister;
    private Button btnRegisterMain3;

    private FirebaseAuth auth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // find id
        edtYourName = findViewById(R.id.edtYourName);
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtTypePasswordRegister = findViewById(R.id.edtTypePasswordRegister);
        edtReTypePasswordRegister = findViewById(R.id.edtReTypePasswordRegister);
        btnRegisterMain3 = findViewById(R.id.btnRegisterMain3);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        // handle button register
        btnRegisterMain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtYourName= edtYourName.getText().toString().trim();
                String txtEmailRegister = edtEmailRegister.getText().toString().trim();
                String txtTypePasswordRegister = edtTypePasswordRegister.getText().toString().trim();
                String txtReTypePasswordRegister = edtReTypePasswordRegister.getText().toString().trim();

                // check
                if(txtYourName.equals("") || txtEmailRegister.equals("") || txtTypePasswordRegister.equals("") || txtReTypePasswordRegister.equals("")) {
                    Toast.makeText(MainActivity3.this, "B???n c???n nh???p ?????y ????? th??ng tin ????? ti???p t???c!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtTypePasswordRegister.length() < 4) {
                    Toast.makeText(MainActivity3.this, "M???t kh???u c???n ph???i l???n h??n 4 k?? t???!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!txtTypePasswordRegister.equals(txtReTypePasswordRegister)) {
                    Toast.makeText(MainActivity3.this, "M???t kh???u nh???p l???i kh??ng kh???p!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // action
                onClick_btnRegister(txtEmailRegister, txtTypePasswordRegister);
            }
        });
    }

    // Handle action
    private void onClick_btnRegister(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity3.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // save to database
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", edtYourName.getText().toString().trim());
                    map.put("email", edtEmailRegister.getText().toString().trim());
                    db.getReference()
                            .child("ManagerUsers")
                            .push()
                            .child("User")
                            .setValue(map);

                    Toast.makeText(MainActivity3.this, "B???n ???? ????ng k?? th??nh c??ng t??i kho???n.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity3.this, "????ng k?? th???t b???i. Vui l??ng th??? l???i!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}