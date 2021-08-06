package com.example.myhomiee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.jetbrains.annotations.NotNull;

import helpers.MqttHelper;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Button btnLogIn;
    EditText userEmail;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}
        anhXa();
        firebaseAuth = FirebaseAuth.getInstance();
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEmail.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Chưa nhập email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userEmail.getText().toString().trim().length() < 6){
                    Toast.makeText(MainActivity.this, "Email phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Chưa nhập password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().trim().length() < 6){
                    Toast.makeText(MainActivity.this, "Password phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                        password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, MainHomeRoom.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
    void anhXa(){
        userEmail = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.password);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
    }

}