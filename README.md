# App2 — Consumo de API RESTful con Supabase en Android

Aplicación nativa Android desarrollada en Java que consume datos de una tabla `alumnos` desde una base de datos Supabase mediante su API RESTful. Los registros se obtienen en formato JSON usando Volley y se muestran en un `TextView`.

---

## Descripción

La app realiza una petición GET al endpoint REST de Supabase, recorre el arreglo JSON recibido y presenta la lista de alumnos del curso directamente en pantalla.

---

## Tecnologías

| Tecnología     | Versión / Rol                        |
|----------------|--------------------------------------|
| Android (Java) | SDK mínimo API 24                    |
| Supabase       | Base de datos PostgreSQL + API REST  |
| Volley         | Librería HTTP (com.android.volley:volley:1.2.1) |

---

## Estructura del proyecto

```
App2/
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml
│   ├── java/
│   │   └── .../MainActivity.java
│   └── res/
│       └── layout/
│           └── activity_main.xml
├── build.gradle
├── gradle.properties
├── settings.gradle
└── gradlew
```

---

## Configuración

En `MainActivity.java`, reemplaza las constantes con los valores de tu propio proyecto Supabase (**Settings → API**):

```java
private static final String SUPABASE_URL = "https://<tu-proyecto>.supabase.co";
private static final String SUPABASE_KEY = "<tu-api-key>";
```

> Cada alumno debe usar su propio proyecto Supabase. No compartas tu API Key ni la subas al repositorio.

---

## Base de datos

Tabla requerida en Supabase:

```sql
CREATE TABLE alumnos (
  id       SERIAL PRIMARY KEY,
  nombre   TEXT NOT NULL,
  apellido TEXT NOT NULL,
  cedula   TEXT
);
```

---

## Autor

Eduardo Reinoso — UTEQ
