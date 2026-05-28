# App2 — Consumo de API RESTful con Supabase en Android

**Asignatura:** Desarrollo de Aplicaciones Móviles  
**Actividad:** Consumo de base de datos Supabase desde una app nativa Android usando Volley

---

## Objetivo

Implementar una aplicación nativa Android que consulte una tabla de alumnos almacenada en Supabase mediante su API RESTful (JSON) y muestre los registros obtenidos en un `TextView`.

---

## Tecnologías

| Tecnología | Rol |
|---|---|
| Android (Java) | Plataforma de desarrollo nativa |
| Supabase | Base de datos PostgreSQL + API REST |
| Volley | Librería HTTP para Android |
| JSON | Formato de intercambio de datos |

---

## Paso 1 — Crear la tabla `alumnos` en Supabase

Proyecto Supabase → **Table Editor** → **New Table**, o directamente con SQL:

```sql
CREATE TABLE alumnos (
  id       SERIAL PRIMARY KEY,
  nombre   TEXT NOT NULL,
  apellido TEXT NOT NULL,
  cedula   TEXT
);
```

> Cada alumno trabaja con su **propio proyecto Supabase**. No compartas tu URL ni tu API Key.

---

## Paso 2 — Insertar los datos del curso

```sql
INSERT INTO alumnos (nombre, apellido, cedula) VALUES
  ('Juan',   'Pérez',  '1234567890'),
  ('María',  'Gómez',  '0987654321'),
  ('Carlos', 'Torres', '1122334455');
  -- continuar con todos los compañeros
```

---

## Paso 3 — Crear el proyecto Android

1. Android Studio → **New Project** → **Empty Activity**
2. Nombre del paquete y SDK mínimo recomendado: **API 24**

---

## Paso 4 — Agregar Volley

En `build.gradle (Module: app)`:

```groovy
dependencies {
    implementation 'com.android.volley:volley:1.2.1'
}
```

Sincronizar: **Sync Now**.

---

## Paso 5 — Permiso de Internet

En `AndroidManifest.xml`, antes de `<application>`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

## Paso 6 — Consumir la API RESTful de Supabase

Los datos de conexión están en tu proyecto Supabase → **Settings → API**.

`MainActivity.java`:

```java
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String SUPABASE_URL = "https://<tu-proyecto>.supabase.co";
    private static final String SUPABASE_KEY = "<tu-api-key>";

    private TextView textViewAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAlumnos = findViewById(R.id.tvAlumnos);
        cargarAlumnos();
    }

    private void cargarAlumnos() {
        String url = SUPABASE_URL + "/rest/v1/alumnos?select=*";

        JsonArrayRequest request = new JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            response -> {
                // Paso 7: Recorrer el arreglo JSON
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject alumno = response.getJSONObject(i);
                        String nombre   = alumno.getString("nombre");
                        String apellido = alumno.getString("apellido");
                        String cedula   = alumno.getString("cedula");
                        sb.append((i + 1))
                          .append(". ")
                          .append(nombre).append(" ").append(apellido)
                          .append(" — ").append(cedula)
                          .append("\n");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // Paso 8: Mostrar los registros
                textViewAlumnos.setText(sb.toString());
            },
            error -> textViewAlumnos.setText("Error: " + error.getMessage())
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("apikey", SUPABASE_KEY);
                headers.put("Authorization", "Bearer " + SUPABASE_KEY);
                return headers;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
```

> Reemplaza `<tu-proyecto>` y `<tu-api-key>` con los valores reales de tu proyecto.

---

## Paso 7 — Layout `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvAlumnos"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Cargando alumnos..."
        android:textSize="16sp"
        android:lineSpacingMultiplier="1.4" />

</ScrollView>
```

---

## Estructura del proyecto

```
App2/
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml
│   ├── java/.../
│   │   └── MainActivity.java
│   └── res/
│       └── layout/
│           └── activity_main.xml
├── build.gradle
└── README.md
```

---

## Criterios de evaluación

- [ ] Tabla `alumnos` creada en proyecto Supabase propio
- [ ] Datos de todos los compañeros insertados
- [ ] App Android nativa funcional
- [ ] Librería Volley integrada correctamente
- [ ] Petición GET con headers `apikey` y `Authorization`
- [ ] Recorrido del arreglo JSON con bucle
- [ ] Registros mostrados en `TextView`
- [ ] Proyecto Supabase individual (no compartido)

---

## Notas

- No subas tu `API Key` al repositorio. Usa `local.properties` o `BuildConfig` para almacenarla.
- Para esta actividad la tabla puede quedar con permisos públicos de lectura (`anon` key). En producción, habilita **Row Level Security (RLS)**.
- Documentación oficial de la API REST de Supabase: https://supabase.com/docs/guides/api

---

## Repositorio

https://github.com/ereinosov/App2
