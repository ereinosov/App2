package com.uteq.software.app2;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SupabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_supabase);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText txtAlumnos = findViewById(R.id.txtAlumnos);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://juvmjeuqraunblfhkipn.supabase.co/rest/v1/alumnos",
                null,
                response -> {
                    try {
                        StringBuilder texto = new StringBuilder();
                       // JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonAlumno = response.getJSONObject(i);
                            texto.append((i+1)+ " " + jsonAlumno.optString("No.", "") + "\n");
                            texto.append((i+1)+ " " + jsonAlumno.optString("CÉDULA", "") + "\n");
                            texto.append((i+1)+ " " + jsonAlumno.optString("APELLIDOS Y NOMBRES", "") + "\n");
                            texto.append((i+1)+ " " + jsonAlumno.optString("CORREO INSTITUCIONAL", "") + "\n");
                            texto.append((i+1)+ " " + jsonAlumno.optString("CORREO INSTITUCIONAL MICROSOFT", "") + "\n\n");
                        }
                        //  texto.append(agregarAlumnosALista(data.getJSONObject(i), i+1));

                        txtAlumnos.setText(texto.toString());

                    } catch (Exception e) {
                        txtAlumnos.setText("Error procesando datos:\n" + e.getMessage());
                    }
                },
                //new ApiErrorListener(this)
                error -> txtAlumnos.setText("Error API:\n" + error.getMessage())
        ){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("apikey", "");
                return headers;
            }
        };

        queue.add(request);

    }
}