package com.rosselps.pc3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText txtBuscarDNI;
    TextView tvNombre, tvCargo;
    AdminSQLiteOpenHelper admin;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtBuscarDNI = findViewById(R.id.txtBuscarDNI);
        tvNombre = findViewById(R.id.tvNombre);
        tvCargo = findViewById(R.id.tvCargo);
        admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
    }

    public void buscarEmpleado(View view){
        db = admin.getWritableDatabase();
        String dni = txtBuscarDNI.getText().toString();

        if(!dni.isEmpty()){
            Cursor fila = db.rawQuery("select nombre, cargo from empleados where dni=" + dni, null);

            if(fila.moveToFirst()){
                tvNombre.setText("Nombre: " + fila.getString(0));
                tvCargo.setText("Cargo: " + fila.getString(1));
                fila.close();
            } else {
                Toast.makeText(this, "No existe un empleado con ese DNI", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debes introducir el DNI", Toast.LENGTH_SHORT).show();
        }

        //db.close();
    }

    public void regresar(View view){
        finish();
    }
}
