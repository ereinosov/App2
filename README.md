# App2 — Consumo de API RESTful con Supabase en Android

![Android](https://img.shields.io/badge/Android-API%2024%2B-3DDC84?style=flat&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-11-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Supabase](https://img.shields.io/badge/Supabase-REST%20API-3ECF8E?style=flat&logo=supabase&logoColor=white)
![Volley](https://img.shields.io/badge/Volley-1.2.1-4285F4?style=flat&logo=google&logoColor=white)

Aplicación nativa Android desarrollada en Java que consume datos de una tabla `alumnos` desde una base de datos Supabase mediante su API RESTful. Los registros se obtienen en formato JSON usando Volley y se muestran en un `TextView`.

---

## Descripción

La app realiza una petición GET al endpoint REST de Supabase, recorre el arreglo JSON recibido y presenta la lista de alumnos del curso directamente en pantalla.

---

## Tecnologías

| Tecnología     | Versión / Rol                                   |
|----------------|-------------------------------------------------|
| Android (Java) | SDK mínimo API 24                               |
| Supabase       | Base de datos PostgreSQL + API REST             |
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

Tabla `alumnos` en Supabase:

```sql
CREATE TABLE alumnos (
  no                          SERIAL PRIMARY KEY,
  cedula                      INT8,
  apellidos_y_nombres         TEXT,
  correo_institucional        TEXT,
  correo_institucional_microsoft TEXT
);
```

Datos del curso (19 registros):

```sql
INSERT INTO alumnos (cedula, apellidos_y_nombres, correo_institucional, correo_institucional_microsoft) VALUES
  (1250143656, 'BENITES PEREZ DARIEM ALBERTO',         'dbenitesp@uteq.edu.ec',   'dbenitesp@msuteq.edu.ec'),
  (1250208392, 'BUENO TROYA ERWIN DANIEL',             'ebuenot@uteq.edu.ec',     'ebuenot@msuteq.edu.ec'),
  (1250931878, 'CALDERON SALTOS JOSEPH ALEXANDER',     'jcalerons3@uteq.edu.ec',  'jcalerons3@msuteq.edu.ec'),
  (1250520978, 'CASTRO COELLO MARIO SEBASTIAN',        'mcastroc6@uteq.edu.ec',   'mcastroc6@msuteq.edu.ec'),
  (1206883967, 'CEDEÑO INTRIAGO JILMAR JESUS',         'jcedenoi4@uteq.edu.ec',   'jcedenoi4@msuteq.edu.ec'),
  (940514714,  'GARCIA BAZURTO KEVIN LEONEL',          'kgarciab6@uteq.edu.ec',   'kgarciab6@msuteq.edu.ec'),
  (1206748210, 'GIRALDO CAGUA DIEGO JOSE',             'dgiraldoc@uteq.edu.ec',   'dgiraldoc@msuteq.edu.ec'),
  (1250598925, 'HERRERA BARCO HUMBERTO ALDAIR',        'hherrerab@uteq.edu.ec',   'hherrerab@msuteq.edu.ec'),
  (959540774,  'LOOR MENDOZA BYRON DANIEL',            'bloorm2@uteq.edu.ec',     'bloorm2@msuteq.edu.ec'),
  (503360398,  'MENDOZA BERMELLO ANGELLO AGUSTIN',     'amendozab5@uteq.edu.ec',  'amendozab5@msuteq.edu.ec'),
  (941301319,  'MENDOZA VILLARROEL ELIAN JOSSUE',      'emendozav3@uteq.edu.ec',  'emendozav3@msuteq.edu.ec'),
  (941038481,  'MERA JUMBO ALLISON MICHELLE',          'ameraj2@uteq.edu.ec',     'ameraj2@msuteq.edu.ec'),
  (1750901124, 'PIGUAVE MONTAÑO NIXON ANTONIO',        'npiguavem@uteq.edu.ec',   'npiguavem@msuteq.edu.ec'),
  (2350898850, 'POZO CASTILLO STEVEN FERNANDO',        'spozoc@uteq.edu.ec',      'spozoc@msuteq.edu.ec'),
  (926972829,  'REINOSO VELEZ EDUARDO DAVID',          'ereinosov@uteq.edu.ec',   'ereinosov@msuteq.edu.ec'),
  (1206971937, 'RIVAS PILOSO DAYVER YAHIR',            'drivasp@uteq.edu.ec',     'drivasp@msuteq.edu.ec'),
  (942667338,  'SILVA TRIVIÑO JOHN JAIRO',             'jsilvat2@uteq.edu.ec',    'jsilvat2@msuteq.edu.ec'),
  (1250958004, 'TORRALES AVILES MARK YAIR',            'mtorralesa@uteq.edu.ec',  'mtorralesa@msuteq.edu.ec'),
  (959420142,  'ZAMBRANO TRIVIÑO LEONEL LEONIDA',      'lzambranot6@uteq.edu.ec', 'lzambranot6@msuteq.edu.ec');
```

---

## Autor

Eduardo Reinoso — UTEQ
