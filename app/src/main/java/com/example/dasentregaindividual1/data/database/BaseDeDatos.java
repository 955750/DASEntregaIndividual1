package com.example.dasentregaindividual1.data.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dasentregaindividual1.R;

public class BaseDeDatos extends SQLiteOpenHelper {

    public BaseDeDatos(
        @Nullable Context context,
        @Nullable String name,
        @Nullable SQLiteDatabase.CursorFactory factory,
        int version
    ) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        crearTablas(sqLiteDatabase);
        añadirUsuarios(sqLiteDatabase);
        añadirEquipos(sqLiteDatabase);
        /*

        PRIMARY KEY (id)"

        sqLiteDatabase.execSQL(
            "CREATE TABLE Jugador (" +
                " 'numero' INTEGER PRIMARY KEY NOT NULL, " +
                " 'equipo_id' INTEGER NOT NULL, " +
                " 'nacionalidad' TEXT, " +
                " 'altura' FLOAT, " +
                " 'fecha_nacimiento' DATETIME" +
            ")"
        );

        sqLiteDatabase.execSQL(
            "CREATE TABLE Partido (" +
                " 'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                " 'numero_jornada' INTEGER NOT NULL, " +
                " 'temporada_regular_si_no' BOOLEAN NOT NULL, " +
                " 'fecha' DATETIME NOT NULL, " +
                " 'hora' TIMESTAMP NOT NULL" +
            ")"
        );

        sqLiteDatabase.execSQL(
            "CREATE TABLE Favorito (" +
                " 'usuario_id' INTEGER PRIMARY KEY NOT NULL, " +
                " 'equipo_id' INTEGER PRIMARY KEY NOT NULL" +
            ")"
        );

        sqLiteDatabase.execSQL(
            "CREATE TABLE Estadisticas (" +
                " 'numero_jugador' INTEGER PRIMARY KEY NOT NULL, " +
                " 'equipo_id' INTEGER PRIMARY KEY NOT NULL, " +
                " 'partido_id' INTEGER PRIMARY KEY NOT NULL, " +
                " 'puntos' INTEGER NOT NULL, " +
                " 'asistencias' INTEGER NOT NULL, " +
                " 'rebotes' INTEGER NOT NULL" +
            ")"
        );

        sqLiteDatabase.execSQL(
            "CREATE TABLE Juega (" +
                " 'numero_jugador' INTEGER NOT NULL, " +
                " 'equipo_id' INTEGER NOT NULL, " +
                " 'puntos_favor' INTEGER NOT NULL, " +
                " 'puntos_contra' INTEGER NOT NULL" +
            ")"
        );*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Usuario");
        /*sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Equipo");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Jugador");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Partido");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Favorito");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Estadisticas");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Juega");*/
        onCreate(sqLiteDatabase);
    }

    private void crearTablas(SQLiteDatabase sqLiteDatabase) {
        // FALTA AÑADIR PRIMARY / FOREIGN KEY + "ON UPDATE, ON DELETE" (en algunos, revisar todos)
        sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS Usuario (" +
                " 'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                " 'nombre_usuario' TEXT NOT NULL, " +
                " 'contraseña' TEXT NOT NULL" +
            ")"
        );

        sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS Equipo (" +
                " 'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                " 'nombre' TEXT NOT NULL," +
                " 'escudo_id' INTEGER NOT NULL, " +
                " 'part_ganados_tot' INTEGER NOT NULL, " +
                " 'part_perdidos_tot' INTEGER NOT NULL, " +
                " 'puntos_favor_tot' INTEGER NOT NULL, " +
                " 'puntos_contra_tot' INTEGER NOT NULL, " +
                " 'part_ganados_ult_10' INTEGER NOT NULL, " +
                " 'part_perdidos_ult_10' INTEGER NOT NULL" +
            ")"
        );
    }

    private void añadirUsuarios(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "INSERT INTO Usuario (" +
                "'nombre_usuario', 'contraseña'" +
            ")" +
            "VALUES ('julen_fuentes', 'patata123')"
        );
        sqLiteDatabase.execSQL(
            "INSERT INTO Usuario (" +
                "'nombre_usuario', 'contraseña'" +
            ")" +
            "VALUES ('iker_sobron', 'patata456')"
        );
    }

    private void añadirEquipos(SQLiteDatabase sqLiteDatabase) {
        String[] nombres = {"Alba Berlin", "Anadolu Efes", "AS Monaco", "Baskonia", "Bayern Munich",
                            "Crvena Zvezda", "Emporio Armani Milan", "FC Barcelona", "Fenerbahce",
                            "LDLC Asvel Villeurbane", "Maccabi Tel Aviv", "Olympiacos",
                            "Panathinaikos", "Partizan Belgrade", "Real Madrid", "Valencia Basket",
                            "Virtus Bologna", "Zalgiris Kaunas"};
        Integer[] idsEscudos = {R.drawable.escudo_alba_berlin, R.drawable.escudo_anadolu_efes,
            R.drawable.escudo_as_monaco, R.drawable.escudo_baskonia, R.drawable.escudo_bayern_munich,
            R.drawable.escudo_crvena_zvezda, R.drawable.escudo_emporio_armani_milan,
            R.drawable.escudo_fc_barcelona, R.drawable.escudo_fenerbahce,
            R.drawable.escudo_ldlc_asvel_villeurbanne, R.drawable.escudo_maccabi_tel_aviv,
            R.drawable.escudo_olympiacos, R.drawable.escudo_panathinaikos,
            R.drawable.escudo_partizan_belgrade, R.drawable.escudo_real_madrid,
            R.drawable.escudo_valencia_basket, R.drawable.escudo_virtus_bologna,
            R.drawable.escudo_zalgiris_kaunas
        };
        Integer[] partGanadosTot = {7, 13, 16, 14, 10, 11, 10, 18, 16, 8, 13, 18, 8, 14, 18, 13,
                                    12, 13};
        Integer[] partPerdidosTot = {19, 12, 10, 12, 16, 15, 15, 8, 9, 18, 13, 8, 18, 12, 7, 13,
                                     14, 13};
        Integer[] puntosFavorTot = {2077, 2046, 2135, 2216, 2002, 1944, 1799, 2085, 2100, 1953,
                                    2136, 2202, 2020, 2185, 2080, 2123, 2018, 1964};
        Integer[] puntosContraTot = {2201, 1964, 2104, 2144, 2058, 2024, 1886, 1978, 2004, 2090,
                                     2159, 1979, 2125, 2123, 1902, 2198, 2102, 2044};
        Integer[] partGanadosUlt10 = {3, 5, 5, 3, 5, 3, 5, 7, 6, 2, 4, 8, 2, 7, 8, 6, 5, 5};
        Integer[] partPerdidosUlt10 = {7, 5, 5, 7, 5, 7, 5, 3, 4, 8, 6, 2, 8, 3, 2, 4, 5, 5};

        for (int i = 0; i < 18; i++) {
            @SuppressLint("DefaultLocale")
            String consulta = String.format(
                "INSERT INTO Equipo (" +
                    "'nombre', 'escudo_id', 'part_ganados_tot', 'part_perdidos_tot'," +
                    "'puntos_favor_tot', 'puntos_contra_tot', 'part_ganados_ult_10'," +
                    "'part_perdidos_ult_10'" +
                ")" +
                "VALUES (%1$s, %2$d, %3$d, %4$d, %5$d, %6$d, %7$d, %8$d)",
                nombres[i], idsEscudos[i], partGanadosTot[i], partPerdidosTot[i], puntosFavorTot[i],
                puntosContraTot[i], partGanadosUlt10[i], partPerdidosUlt10[i]
            );
            sqLiteDatabase.execSQL(consulta);
        }
    }
}
