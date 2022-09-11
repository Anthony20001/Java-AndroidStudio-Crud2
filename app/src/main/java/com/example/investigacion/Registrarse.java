package com.example.investigacion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.investigacion.database.tables.User;

import java.util.concurrent.atomic.AtomicBoolean;

public class Registrarse extends AppCompatActivity {

    Button btn_signin;
    EditText input_username, input_email, input_password1, input_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        btn_signin = findViewById(R.id.update_btn);
        input_username = findViewById(R.id.update_input_name);
        input_email = findViewById(R.id.update_input_email);
        input_password1 = findViewById(R.id.update_input_password1);
        input_password2 = findViewById(R.id.update_input_password2);


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String
                        username = input_username.getText().toString().trim(),
                        email = input_email.getText().toString().trim(),
                        password1 = input_password1.getText().toString().trim(),
                        password2 = input_password2.getText().toString().trim();

                if(!empty_input(username) && !empty_input(email) && !empty_input(password1) && !empty_input(password2)){
                    if(!verifyEmail(email)){
                        if(password1.equals(password2)){
                            User user = new User(Registrarse.this);
                            user.setUsername(username);
                            user.setEmail(email);
                            user.setPassword(password1);
                            if(user.create()){
                                Toast.makeText(Registrarse.this, "Nueva cuenta", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(Registrarse.this, "Lo sentimos, no se logró crear el registro", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(Registrarse.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Registrarse.this, "El correo electrónico "+email + " ya está registrado, por favor introduzca otro", Toast.LENGTH_SHORT).show();
                    }

                }else
                    Toast.makeText(Registrarse.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public boolean empty_input(String value){
        return value.equals("");
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean verifyEmail(String email){
        User user = new User(Registrarse.this);
        AtomicBoolean exist = new AtomicBoolean(false);
        user.read().forEach(value -> {
            if(value.getEmail().equalsIgnoreCase(email)){
                exist.set(true);
            }
        });
        return exist.get();
    }
}