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

public class IniciarSesion extends AppCompatActivity {

    Button btn_login, btn_signin;
    EditText input_email, input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        btn_login = findViewById(R.id.login_btn_in);
        btn_signin = findViewById(R.id.login_btn_signin);
        input_email = findViewById(R.id.login_input_email);
        input_password = findViewById(R.id.login_input_password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String
                        email = input_email.getText().toString().trim(),
                        password = input_password.getText().toString().trim();

                User user = new User(IniciarSesion.this);

                if(verifyUser(email)){
                    user.read().forEach(item -> {
                        if(item.getEmail().equalsIgnoreCase(email)){
                            if(item.getPassword().equals(password)){
                                Toast.makeText(IniciarSesion.this, "Bienvenido, "+item.getUsername(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(IniciarSesion.this, ListarUsuarios.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(IniciarSesion.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(IniciarSesion.this, "Correo electrónico no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarSesion.this, Registrarse.class);
                startActivity(intent);
            }
        });


    }


    public boolean empty_input(String value){
        return value.equals("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean verifyUser(String email){
        User user = new User(IniciarSesion.this);
        AtomicBoolean exist = new AtomicBoolean(false);
        user.read().forEach(value -> {
            if(value.getEmail().equalsIgnoreCase(email)){
                exist.set(true);
            }
        });
        return exist.get();
    }
}