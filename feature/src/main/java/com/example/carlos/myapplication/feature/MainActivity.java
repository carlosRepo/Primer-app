package com.example.carlos.myapplication.feature;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button botonLoguear;
    Button botonRegistro;
    Button botonShowAll;
    Button botonUpdate;
    Button botonDelete;
    EditText textId;
    EditText textNick;
    EditText textPassword;
    EditText textNickRegistro;
    EditText textPasswordRegistro;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //crear un objeto para manipular la bd
        myDb  = new DatabaseHelper(this);
        //Obtener todos los elementos de la vista
        botonLoguear=(Button) findViewById(R.id.buttonEnviar);
        botonRegistro=(Button) findViewById(R.id.buttonRegistro);
        botonShowAll=(Button) findViewById(R.id.buttonShowAll);
        botonUpdate=(Button) findViewById(R.id.buttonUpdate);
        botonDelete=(Button) findViewById(R.id.buttonDelete);
        textId=(EditText) findViewById(R.id.editTextId);
        textNick=(EditText) findViewById(R.id.editTextCorreo);
        textPassword=(EditText) findViewById(R.id.editTextPassword);
        textNickRegistro=(EditText) findViewById(R.id.editTextNickRegistro);
        textPasswordRegistro=(EditText) findViewById(R.id.editTextPasswordRegistro);
        botonLoguear();
        botonRegistrar();
        botonMostrar();
        botonUpdate();
        botonDelete();
    }

    public void botonLoguear(){
        //Evento boton Loguear
        botonLoguear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textNick.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this,("Ingrese un correo."),Toast.LENGTH_LONG).show();
                    return;
                }
                if (textPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this,("Ingrese su contraseña."),Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(MainActivity.this,("Logueado."),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void botonRegistrar(){
        //Evento boton Registro
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textNickRegistro.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this,("Ingrese un correo."),Toast.LENGTH_LONG).show();
                    return;
                }
                if (textPasswordRegistro.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this,("Ingrese su contraseña."),Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isInserted=myDb.insertData(textNickRegistro.getText().toString(),textPasswordRegistro.getText().toString());
                if (isInserted==true){
                    Toast.makeText(MainActivity.this,("Registrado Exitosamente."),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,("Error en la bd."),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void botonMostrar(){
        //Evento boton Ver Todos
        botonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount()==0){
                    showMessage("Error","No Accounts Founded");
                    return;
                }else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Id :"+res.getString(0)+"\n");
                        buffer.append("Nick :"+res.getString(1)+"\n");
                        buffer.append("Pass :"+res.getString(2)+"\n\n");
                    }
                    showMessage("Cuentas",buffer.toString());
                }

            }
        });
    }

    public void botonUpdate(){
        //Evento boton Ver Todos
        botonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated=myDb.updateData(textId.getText().toString(),textNickRegistro.getText().toString(),textPasswordRegistro.getText().toString());
                if (isUpdated==true){
                    Toast.makeText(MainActivity.this,("Profile Updated."),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,("Not Found."),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void botonDelete(){
        //Evento boton Ver Todos
        botonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(textId.getText().toString());
                //pregunta si borro alguna fila
                if (deletedRows > 0){
                    Toast.makeText(MainActivity.this,("Profile Deleted."),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,("Profile not found."),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

}
