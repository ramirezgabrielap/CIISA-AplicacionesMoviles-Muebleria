package com.example.muebleria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Editar extends AppCompatActivity {

    private TextView ide;
    private EditText tipoMe;
    private EditText materiale;
    private EditText altoe;
    private EditText anchoe;
    private EditText profe;
    private EditText colore;
    private EditText cante;
    private EditText precioe;

    private TextView mene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        ide = findViewById(R.id.ide);
        tipoMe = findViewById(R.id.tipoMe);
        materiale = findViewById(R.id.materiale);
        altoe = findViewById(R.id.altoe);
        anchoe = findViewById(R.id.anchoe);
        profe = findViewById(R.id.profe);
        colore = findViewById(R.id.colore);
        cante = findViewById(R.id.cante);
        precioe = findViewById(R.id.precioe);

        mene = findViewById(R.id.mene);

        Intent x = getIntent();
        String id = x.getStringExtra("id");
        String tipoM = x.getStringExtra("tipoM");
        String material = x.getStringExtra("material");
        String alto = x.getStringExtra("alto");
        String ancho = x.getStringExtra("ancho");
        String prof = x.getStringExtra("prof");
        String color = x.getStringExtra("color");
        String cant = x.getStringExtra("cant");
        String precio = x.getStringExtra("precio");

        ide.setText(id);
        tipoMe.setText(tipoM);
        materiale.setText(material);
        altoe.setText(alto);
        anchoe.setText(ancho);
        profe.setText(prof);
        colore.setText(color);
        cante.setText(cant);
        precioe.setText(precio);

    }


    public void Edita(View view) {
        RequestParams params = new RequestParams();
        params.put("id", ide.getText().toString());
        params.put("tipoM", tipoMe.getText().toString());
        params.put("material", materiale.getText().toString());
        params.put("alto", altoe.getText().toString());
        params.put("ancho", anchoe.getText().toString());
        params.put("prof", profe.getText().toString());
        params.put("color", colore.getText().toString());
        params.put("cant", cante.getText().toString());
        params.put("precio", precioe.getText().toString());
        Log.d("Edita", "antesURL");
        String url = "http://192.168.3.204/WSBodega/EditarBodega.php";
        Log.d("Edita", "despuesURL");
        new AsyncHttpClient().post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    mene.setText("Editado OK");
                    volver(view);
                    Log.d("Editar", "onSuccess");
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                mene.setText("Error al Editar...");
                Log.d("Editar", "onFailure");
            }
        });
    }


    public void volver(View view) {
        Intent x = new Intent(Editar.this, Listar.class);
        startActivity(x);
    }


}





