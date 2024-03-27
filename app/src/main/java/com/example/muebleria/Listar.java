package com.example.muebleria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Listar extends AppCompatActivity {

    private TableLayout tablelistar;
    private EditText ibuscar;
    private Button btnBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        tablelistar = findViewById(R.id.tablelistar);
        ibuscar = findViewById(R.id.ibuscar);
        btnBuscar = findViewById(R.id.btnBuscar);

        mostrar("");

    }

    private void mostrar(String buscar){

        String url = "http://192.168.3.204/WSBodega/ListarBodega.php";

        if(!buscar.equals("")){
            url = "http://192.168.3.204/WSBodega/BuscarBodega.php?buscar="+buscar;
        }

        Log.d("mostar", url);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    tablelistar.removeAllViews();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("muebles");

                    if(jsonArray.length() == 0 ) {
                        Toast.makeText(Listar.this, "No se encuentran coincidencias", Toast.LENGTH_SHORT).show();

                    } else {
                        TableRow tableRowTh = new TableRow(Listar.this);

                        TextView idTh = new TextView(Listar.this);
                        idTh.setText("ID ");
                        TextView muebleTh = new TextView(Listar.this);
                        muebleTh.setText("T.Mueble");
                        TextView materialTh = new TextView(Listar.this);
                        materialTh.setText("Material");
                        TextView altoTh = new TextView(Listar.this);
                        altoTh.setText("Alto ");
                        TextView anchoTh = new TextView(Listar.this);
                        anchoTh.setText("Anch ");
                        TextView profTh = new TextView(Listar.this);
                        profTh.setText("Prof ");
                        TextView colorTh = new TextView(Listar.this);
                        colorTh.setText("Color");
                        TextView cantTh = new TextView(Listar.this);
                        cantTh.setText("Cant ");
                        TextView precioTh = new TextView(Listar.this);
                        precioTh.setText("Valor");

                        tableRowTh.addView(idTh);
                        tableRowTh.addView(muebleTh);
                        tableRowTh.addView(materialTh);
                        tableRowTh.addView(altoTh);
                        tableRowTh.addView(anchoTh);
                        tableRowTh.addView(profTh);
                        tableRowTh.addView(colorTh);
                        tableRowTh.addView(cantTh);
                        tableRowTh.addView(precioTh);

                        tablelistar.addView(tableRowTh);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Context context = Listar.this;

                            TableRow tableRow = new TableRow(Listar.this);

                            TextView id = new TextView(context);
                            TextView tipo = new TextView(context);
                            TextView mater = new TextView(context);
                            TextView alt = new TextView(context);
                            TextView anch = new TextView(context);
                            TextView prof = new TextView(context);
                            TextView col = new TextView(context);
                            TextView can = new TextView(context);
                            TextView pre = new TextView(context);

                            Button btnEdit = new Button(context);
                            Button btnDelete = new Button(context);

                            id.setText(object.getString("ID_MUEBLE"));
                            tipo.setText(object.getString("TIPO_MUEBLE"));
                            mater.setText(object.getString("MATERIAL_MUEBLE"));
                            alt.setText(object.getString("ALT_MUEBLE"));
                            anch.setText(object.getString("ANCH_MUEBLE"));
                            prof.setText(object.getString("PROF_MUEBLE"));
                            col.setText(object.getString("COLOR_MUEBLE"));
                            can.setText(object.getString("CANT_MUEBLE"));
                            pre.setText(object.getString("PRECIO_MUEBLE"));

                            btnEdit.setText("Editar");
                            btnEdit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Button button = (Button) view;
                                    Intent x = new Intent(Listar.this, Editar.class);
                                    x.putExtra("id", id.getText().toString());
                                    x.putExtra("tipoM", tipo.getText().toString());
                                    x.putExtra("material", mater.getText().toString());
                                    x.putExtra("alto", alt.getText().toString());
                                    x.putExtra("ancho", anch.getText().toString());
                                    x.putExtra("prof", prof.getText().toString());
                                    x.putExtra("color", col.getText().toString());
                                    x.putExtra("cant", can.getText().toString());
                                    x.putExtra("precio", pre.getText().toString());
                                    startActivity(x);
                                    Log.d("mostrar", "onClick edit");
                                }
                            });

                            btnDelete.setText("Eliminar");
                            btnDelete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Button button = (Button) view;
                                    Intent x = new Intent(Listar.this, Eliminar.class);
                                    x.putExtra("id", id.getText().toString());
                                    x.putExtra("tipoM", tipo.getText().toString());
                                    x.putExtra("material", mater.getText().toString());
                                    x.putExtra("alto", alt.getText().toString());
                                    x.putExtra("ancho", anch.getText().toString());
                                    x.putExtra("prof", prof.getText().toString());
                                    x.putExtra("color", col.getText().toString());
                                    x.putExtra("cant", can.getText().toString());
                                    x.putExtra("precio", pre.getText().toString());
                                    startActivity(x);
                                    Log.d("mostrar", "onClick delete");
                                }
                            });

                            Log.d("mostrar", "onResponse aqui");

                            tableRow.addView(id);
                            tableRow.addView(tipo);
                            tableRow.addView(mater);
                            tableRow.addView(alt);
                            tableRow.addView(anch);
                            tableRow.addView(prof);
                            tableRow.addView(col);
                            tableRow.addView(can);
                            tableRow.addView(pre);

                            tablelistar.addView(tableRow);

                            TableRow tableRowb = new TableRow(Listar.this);
                            TextView espa = new TextView(Listar.this);
                            espa.setText("      ");
                            tableRowb.addView(espa);
                            tableRowb.addView(btnEdit);
                            tableRowb.addView(btnDelete);
                            tablelistar.addView(tableRowb);

                            TextView line = new TextView(Listar.this);
                            line.setText("      ");
                            TableRow tableRowl = new TableRow(Listar.this);
                            tableRowl.addView(line);
                            tablelistar.addView(tableRowl);


                        }
                    }
                } catch (JSONException ex) {
                    Toast.makeText(Listar.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mostrar", "onErrorResponse");
                System.out.println(error.getMessage());
                Toast.makeText(Listar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        Volley.newRequestQueue(this).add(request);
    }


    public void buscar(View view){
        String textobuscar = ibuscar.getText().toString();
        mostrar(textobuscar);
    }

    public void volver(View view){
        Intent x = new Intent(Listar.this, MainActivity.class);
        startActivity(x);
    }

}