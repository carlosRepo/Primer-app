package com.example.carlos.myapplication.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb  = new DatabaseHelper(this);

        //Obtener todos los elementos de la vista
        Button botonLoguear=(Button) findViewById(R.id.buttonEnviar);
        final EditText textNick=(EditText) findViewById(R.id.editTextCorreo);
        final EditText textPassword=(EditText) findViewById(R.id.editTextPassword);

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
                Toast.makeText(MainActivity.this,("Logueado Exitosamente."),Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

}
