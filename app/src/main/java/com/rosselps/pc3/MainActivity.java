package com.rosselps.pc3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtDNI, txtNombre, txtCargo;
    AdminSQLiteOpenHelper admin;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDNI = findViewById(R.id.txtDNI);
        txtNombre = findViewById(R.id.txtNombre);
        txtCargo = findViewById(R.id.txtCargo);

        admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
    }

    public void registrarEmpleado(View view){
        db = admin.getWritableDatabase();

        String dni = txtDNI.getText().toString();
        String nombre = txtNombre.getText().toString();
        String cargo = txtCargo.getText().toString();

        if(!dni.isEmpty() && !nombre.isEmpty() && !cargo.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("dni", dni);
            registro.put("nombre", nombre);
            registro.put("cargo", cargo);

            db.insert("empleados", null, registro);

            //db.close();

            txtDNI.setText("");
            txtNombre.setText("");
            txtCargo.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscarEmpleado(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
