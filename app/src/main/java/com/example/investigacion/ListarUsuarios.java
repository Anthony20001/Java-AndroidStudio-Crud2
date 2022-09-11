package com.example.investigacion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.investigacion.adaptadores.AdaptadorUsuario;
import com.example.investigacion.database.Util;
import com.example.investigacion.database.tables.User;

import java.util.concurrent.atomic.AtomicBoolean;

public class ListarUsuarios extends AppCompatActivity {

    Button btn_goListMusic, btn_update_user, btn_delete_user, btn_create_user;
    ImageButton btn_delete_user_cancel, btn_delete_user_success, btn_update_user_cancel, btn_update_user_success;
    LinearLayout layout_delete_user, layout_update_user;
    EditText input_update_id, input_delete_id;
    RecyclerView recyclerView;
    ImageButton btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        recyclerView = findViewById(R.id.read_recycler_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        try{
            refresh();
        }catch (Exception e){
            Toast.makeText(this, "ERR: "+e, Toast.LENGTH_SHORT).show();
        }


        input_update_id = findViewById(R.id.read_input_update_id);
        input_delete_id = findViewById(R.id.read_input_delete_id);
        btn_goListMusic = findViewById(R.id.read_btn_listMusic);
        btn_create_user = findViewById(R.id.read_btn_createUser);
        btn_update_user = findViewById(R.id.read_btn_updateUser);
        btn_delete_user = findViewById(R.id.update_btn);
        btn_delete_user_success = findViewById(R.id.read_btn_delete_true);
        btn_delete_user_cancel = findViewById(R.id.read_btn_delete_false);
        btn_update_user_success = findViewById(R.id.read_btn_update_true);
        btn_update_user_cancel = findViewById(R.id.read_btn_update_false);
        layout_delete_user = findViewById(R.id.read_layout_delete_user);
        layout_update_user = findViewById(R.id.read_layout_update_user);
        btn_refresh = findViewById(R.id.read_btn_refresh);


        btn_goListMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarUsuarios.this, MusicOnline.class);
                startActivity(intent);
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });




        btn_delete_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layout_delete_user.getVisibility() == View.GONE){
                    layout_delete_user.setVisibility(View.VISIBLE);
                }else{
                    layout_delete_user.setVisibility(View.GONE);
                }

                if(layout_update_user.getVisibility() == View.VISIBLE){
                    layout_update_user.setVisibility(View.GONE);
                }
            }
        });

        btn_delete_user_success.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                int id = Integer.parseInt(input_delete_id.getText().toString().trim());
                User user = new User(ListarUsuarios.this);
                user.setId(id);

                if(verifyId(id)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListarUsuarios.this);
                    builder
                            .setTitle("Aviso")
                            .setMessage("¿Desea eliminar la cuenta con id = "+ id)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(user.delete()){
                                        refresh();
                                        Toast.makeText(ListarUsuarios.this, "La cuenta ha sido eliminada", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(ListarUsuarios.this, "No existe el id: "+id, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {}
                            }).create().show();
                }else{
                    Toast.makeText(ListarUsuarios.this, "El id "+id+" no existe", Toast.LENGTH_SHORT).show();
                }
                input_delete_id.setText("");

            }
        });

        btn_delete_user_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_delete_user.setVisibility(View.GONE);
            }
        });





        btn_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layout_update_user.getVisibility() == View.GONE){
                    layout_update_user.setVisibility(View.VISIBLE);
                }else{
                    layout_update_user.setVisibility(View.GONE);
                };

                if(layout_delete_user.getVisibility() == View.VISIBLE){
                    layout_delete_user.setVisibility(View.GONE);
                }
            }
        });

        btn_update_user_success.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                String id = input_update_id.getText().toString().trim();
                User user = new User(ListarUsuarios.this);

                if(verifyId(Integer.parseInt(id))){
                    user.read().forEach(value -> {
                        if (value.getId() == Integer.parseInt(id)) {
                            Intent intent = new Intent(ListarUsuarios.this, ActualizarUsuario.class);
                            intent.putExtra("id", value.getId());
                            intent.putExtra("username", value.getUsername());
                            intent.putExtra("email", value.getEmail());
                            intent.putExtra("password", value.getPassword());
                            startActivity(intent);
                        }
                    });
                }else{
                    Toast.makeText(ListarUsuarios.this, "No existe el id: "+id, Toast.LENGTH_SHORT).show();
                }
                input_update_id.setText("");
            }
        });

        btn_update_user_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_update_user.setVisibility(View.GONE);
            }
        });




        btn_create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarUsuarios.this, Registrarse.class);
                startActivity(intent);

            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean verifyId(int id){
        User user = new User(ListarUsuarios.this);
        AtomicBoolean exist = new AtomicBoolean(false);
        user.read().forEach(value -> {
            if(value.getId() == id){
                exist.set(true);
            }
        });
        return exist.get();
    }

    public void refresh(){
        User user = new User(ListarUsuarios.this);
        AdaptadorUsuario adaptadorUsuario = new AdaptadorUsuario(ListarUsuarios.this, user.read());
        recyclerView.setAdapter(adaptadorUsuario);
    }
}