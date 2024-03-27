package com.example.muebleria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Eliminar extends AppCompatActivity {

    private TextView idDele;
    private TextView tipDele;
    private TextView matDele;
    private TextView alDele;
    private TextView anDele;
    private TextView proDele;
    private TextView colDele;
    private TextView canDele;
    private TextView preDele;

    private TextView men2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        idDele = findViewById(R.id.idDele);
        tipDele = findViewById(R.id.tipDele);
        matDele = findViewById(R.id.matDele);
        alDele = findViewById(R.id.alDele);
        anDele = findViewById(R.id.anDele);
        proDele = findViewById(R.id.proDele);
        colDele = findViewById(R.id.colDele);
        canDele = findViewById(R.id.canDele);
        preDele = findViewById(R.id.preDele);

        men2 = findViewById(R.id.men2);

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

        idDele.setText(id);
        tipDele.setText(tipoM);
        matDele.setText(material);
        alDele.setText(alto);
        anDele.setText(ancho);
        proDele.setText(prof);
        colDele.setText(color);
        canDele.setText(cant);
        preDele.setText(precio);

    }


    public void Elimina(View view) {

        RequestParams params = new RequestParams();
        params.put("id", idDele.getText().toString());

        String url = "http://192.168.3.204/WSBodega/EliminarBodega.php";
        new AsyncHttpClient().post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("Elimina", "onSuccess");
                if (statusCode == 200) {
                    Log.d("Elimina", "if 200");
                    men2.setText("Eliminación OK");
                    volver(view);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Elimina", "onFailure");
                men2.setText("Error al Eliminar...");
            }
        });
    }



    public void volver(View view){
        Intent x = new Intent(Eliminar.this, Listar.class);
        startActivity(x);
    }

}