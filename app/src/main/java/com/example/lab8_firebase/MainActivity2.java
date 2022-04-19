package com.example.lab8_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private EditText edtEmail, edtTypePassword;
    private Button btnSignInMain2;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // find id
        edtEmail = findViewById(R.id.edtEmail);
        edtTypePassword = findViewById(R.id.edtTypePassword);
        btnSignInMain2 = findViewById(R.id.btnSignInMain2);

        auth = FirebaseAuth.getInstance();

        // action
        btnSignInMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = edtEmail.getText().toString().trim();
                String txtTypePassword= edtTypePassword.getText().toString().trim();

                // check
                if(txtEmail.equals("") || txtTypePassword.equals("")) {
                    Toast.makeText(MainActivity2.this, "Bạn cần phải nhập đầy đủ thông tin để tiếp tục!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // action
                onClick_btnSignIn(txtEmail, txtTypePassword);
            }
        });
    }

    // Handle action
    private void onClick_btnSignIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(MainActivity2.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity2.this, "Đăng nhập thành công. Xin chúc mừng bạn.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);

                // Get email to "MainActivity4.class"
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}