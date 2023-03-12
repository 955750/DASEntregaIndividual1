package com.example.dasentregaindividual1.lista_partidos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.data.base_de_datos.BaseDeDatos;
import com.example.dasentregaindividual1.data.base_de_datos.modelos.EquipoPartido;
import com.example.dasentregaindividual1.data.base_de_datos.modelos.Partido;

public class ListaPartidosFragment extends Fragment {

    private RecyclerView jornadasRecyclerView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        Log.d("ListaPartidosFragment", "onCreateView");

        return inflater.inflate(R.layout.fragment_lista_partidos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BaseDeDatos GestorDB = new BaseDeDatos (requireContext(), "Euroliga", null, 1);
        SQLiteDatabase bd = GestorDB.getReadableDatabase();

        jornadasRecyclerView = view.findViewById(R.id.jornadas_recycler_view);

        Cursor cPartido = bd.rawQuery(
        "SELECT * FROM Partido", null
        );
        Partido[] listaPartidos = new Partido[9];
        int j = 0;
        while (cPartido.moveToNext()) {
            // Extraer datos de tabla 'Partido'
            Integer partidoId = cPartido.getInt(0);
            Integer numJornada = cPartido.getInt(1); // De momento no se usa
            String fecha = cPartido.getString(2);
            String hora = cPartido.getString(3);

            /*
            SELECT j.puntos, j.partido_id, j.local, e.nombre, e.escudo_id, e.part_ganados_ult_10, e.part_perdidos_ult_10
            FROM Juega AS j INNER JOIN Equipo AS e ON j.nombre_equipo = e.nombre
            WHERE j.partido_id = 2
            */

            // Extraer datos equipos partidos
            String[] campos = new String[] {"j.puntos", "j.partido_id", "j.local", "e.nombre",
                    "e.escudo_id", "e.part_ganados_ult_10", "e.part_perdidos_ult_10"};
            String[] argumentos = new String[] {partidoId.toString()};
            String table = "Juega AS j INNER JOIN Equipo AS e ON j.nombre_equipo = e.nombre";
            Cursor cEquipoPartido = bd.query(table, campos, "partido_id = ?",
                    argumentos, null, null, null);

            EquipoPartido[] equiposPartido = new EquipoPartido[2];
            // int i = 0;
            while (cEquipoPartido.moveToNext()) {
                int puntos = cEquipoPartido.getInt(0);
                int local = cEquipoPartido.getInt(2);
                String nombre = cEquipoPartido.getString(3);
                int escudoId = cEquipoPartido.getInt(4);
                int partGanUlt10 = cEquipoPartido.getInt(5);
                int partPerUlt10 = cEquipoPartido.getInt(6);
                EquipoPartido eq = new EquipoPartido(
                    escudoId,
                    nombre,
                    getString(R.string.racha_ultimos_partidos, partGanUlt10, partPerUlt10),
                    puntos,
                    local
                );
                if (local == 1) {
                    equiposPartido[0] = eq;
                } else {
                    equiposPartido[1] = eq;
                }
                // i++;
            }
            cEquipoPartido.close();

            listaPartidos[j] = new Partido(equiposPartido, fecha, hora);
            j++;
        }
        cPartido.close();

        jornadasRecyclerView.setAdapter(new ListaPartidosAdapter(listaPartidos));
    }

    /*private Partido[] getPartidosJornada(int idJornada) {
        BaseDeDatos GestorDB = new BaseDeDatos (requireContext(), "Euroliga", null, 1);
        SQLiteDatabase bd = GestorDB.getReadableDatabase();

        Cursor c = bd.rawQuery("SELECT Codigo,Nombre FROM Estudiantes WHERE Codigo > 2", null);
        while (c.moveToNext()) {
            // String
        }
    }*/
}