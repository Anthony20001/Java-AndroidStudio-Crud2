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

public class ActualizarUsuario extends AppCompatActivity {

    Button btn_update;
    EditText input_username, input_email, input_password1, input_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_usuario);

        btn_update = findViewById(R.id.update_btn);
        input_username = findViewById(R.id.update_input_name);
        input_email = findViewById(R.id.update_input_email);
        input_password1 = findViewById(R.id.update_input_password1);
        input_password2 = findViewById(R.id.update_input_password2);


        //====== getIntent ======

        String
                username = getIntent().getExtras().getString("username"),
                email = getIntent().getExtras().getString("email"),
                password = getIntent().getExtras().getString("password");

        input_username.setText(username);
        input_email.setText(email);
        input_password1.setText(password);
        input_password2.setText(password);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String
                        username = input_username.getText().toString().trim(),
                        email = input_email.getText().toString().trim(),
                        password1 = input_password1.getText().toString().trim(),
                        password2 = input_password2.getText().toString().trim();

                int id = getIntent().getExtras().getInt("id");

                if(!empty_input(username) && !empty_input(email) && !empty_input(password1) && !empty_input(password2)){
                    if(password1.equals(password2)){
                        User user = new User(ActualizarUsuario.this);
                        user.setId(id);
                        user.setUsername(input_username.getText().toString().trim());
                        user.setEmail(input_email.getText().toString().trim());
                        user.setPassword(input_password1.getText().toString().trim());
                        if(user.update()){
                            Toast.makeText(ActualizarUsuario.this, "Se actualizó la información del usuario", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActualizarUsuario.this, ListarUsuarios.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(ActualizarUsuario.this, "Lo sentimos, no se logró actualizar el registro", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ActualizarUsuario.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(ActualizarUsuario.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean empty_input(String value){
        return value.equals("");
    }
}