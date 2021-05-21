package com.example.myhomiee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    EditText userEmail, userPassword;
    Button btnLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        btnLogin =  findViewById(R.id.btnLogin);
        userEmail = findViewById(R.id.editTextEmail);
        userPassword = findViewById(R.id.editTextPassword);
        MQTTService mqttService;
        mqttService = new MQTTService(this);
        mqttService.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                Log.d("code", "200");
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.d("code", "404");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

                Log.d(topic, message.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Intent intent = new Intent(MainActivity.this, MainHomeRoom.class);
//                            startActivity(intent);
                        } else {
                            Log.e("code", "404");
                        }
                    }
                });

            }
        });
    }

    private void sendDataMQTT(String data, String topic){
        MqttMessage msg = new MqttMessage();
        msg.setId(1234);
        msg.setQos(0);
        msg.setRetained(true);
        byte[] b = data.getBytes(Charset.forName(getString(R.string.utf)));
        msg.setPayload(b);

        Log.d("ABC","Publish:"+ msg);
        try {
            MQTTService.mqttAndroidClient.publish(topic,msg);
        } catch (MqttException e){

        }
    }

}
