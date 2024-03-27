package com.example.muebleria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class Registrar extends AppCompatActivity {

    private TextView id;
    private EditText tipoM;
    private EditText material;
    private EditText alto;
    private EditText ancho;
    private EditText prof;
    private EditText color;
    private EditText cant;
    private EditText precio;

    private Button btnGuardar;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        id = findViewById(R.id.id);
        tipoM = findViewById(R.id.tipoM);
        material = findViewById(R.id.material);
        alto = findViewById(R.id.alto);
        ancho = findViewById(R.id.ancho);
        prof = findViewById(R.id.prof);
        color = findViewById(R.id.color);
        cant = findViewById(R.id.cant);
        precio = findViewById(R.id.precio);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancel = findViewById(R.id.btnCancel);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idd, tipo, mater, alt, anch, pro, col, can, pre;
                idd = id.getText().toString();
                tipo = tipoM.getText().toString();
                mater = material.getText().toString();
                alt = alto.getText().toString();
                anch = ancho.getText().toString();
                pro = prof.getText().toString();
                col = color.getText().toString();
                can = cant.getText().toString();
                pre = precio.getText().toString();

                registrar(idd, tipo, mater, alt, anch, pro, col, can, pre);
            }
        }
        );
    }

    private void registrar(String idd, String tipo, String mater, String alt, String anch, String pro, String col, String can, String pre) {
        if(validar()) {
            String url = "http://192.168.3.204/WSBodega/CRUDBodega.php?";
            url = url + "id=" + idd + "&tipoM=" + tipo + "&material=" + mater + "&alto=" + alt + "&ancho=" + anch + "&prof=" + pro + "&color=" + col + "&cant=" + can + "&precio=" + pre;
            Log.d("registrar", url);

            new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("registrar OnSuccess", "");
                    if (statusCode == 200) {
                        Log.d("IF", "");
                        String res = new String(responseBody);
                        Toast.makeText(Registrar.this, res, Toast.LENGTH_LONG).show();
                        Intent x = new Intent(Registrar.this, Listar.class);
                        startActivity(x);

                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("registrar onFailure", "");
                    System.out.println(error);
                    btnGuardar.setText("error");
                    //reg.setText("....Registro Fallido....");
                }
            });
        }
    }



    public boolean validar() {
        boolean respuesta = true;
        String t1 = tipoM.getText().toString();
        if (t1.equals("")) {
            tipoM.setError("Ingrese Tipo de Mueble");
            Toast.makeText(this, "Falta Tipo de Mueble", Toast.LENGTH_SHORT).show();
            respuesta = false;
        }
        String t2 = material.getText().toString();
        if (t2.equals("")) {
            material.setError("Ingrese Material");
            Toast.makeText(this, "Falta Material", Toast.LENGTH_SHORT).show();
            respuesta = false;
        }
        String t3 = alto.getText().toString();
        if (t3.equals("")) {
            alto.setError("Ingrese Altura");
            Toast.makeText(this, "Falta el Alto del Mueble", Toast.LENGTH_SHORT).show();
            respuesta = false;
        }
        String t4 = ancho.getText().toString();
        if (t4.equals("")) {
            ancho.setError("Ingrese Ancho");
            Toast.makeText(this, "Falta el Ancho del Mueble", Toast.LENGTH_SHORT).show();
            respuesta = false;
        }
        String t5 = prof.getText().toString();
        if (t5.equals("")) {
            prof.setError("Ingrese Profundidad");
            Toast.makeText(this, "Falta la Profundidad del Mueble", Toast.LENGTH_SHORT).show();
            respuesta = false;
        }
        String t6 = color.getText().toString();
        if (t6.equals("")) {
            color.setError("Ingrese Color");
            Toast.makeText(this, "Falta el Color del Mueble", Toast.LENGTH_SHORT).show();
            respuesta = false;
        }
        String t7 = cant.getText().toString();
        if (t7.equals("")) {
            cant.setError("Ingrese Cantidad");
            Toast.makeText(this, "Falta la Cantidad de Muebles", Toast.LENGTH_SHORT).show();
            respuesta = false;
        }
        String t8 = precio.getText().toString();
        if (t8.equals("")) {
            precio.setError("Ingrese Precio");
            Toast.makeText(this, "Falta el Precio del Mueble", Toast.LENGTH_SHORT).show();
            respuesta = false;
        }
        return respuesta;
    }

    public void registro(View view) {
        validar();
        String result = tipoM.getText().toString();
        Intent x = new Intent(Registrar.this, Listar.class);
        x.putExtra("Registrado correctamente", result);
        startActivity(x);
    }


    public void volver(View view) {
        Intent x = new Intent(Registrar.this, MainActivity.class);
        startActivity(x);
    }

}