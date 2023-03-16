package com.example.dasentregaindividual1.clasificacion;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.data.base_de_datos.modelos.EquipoClasificacion;
import com.example.dasentregaindividual1.data.base_de_datos.BaseDeDatos;

public class ClasificacionFragment extends Fragment {

    private RecyclerView clasificacionRecyclerView;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        Log.d("ClasificacionFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_clasificacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("ClasificacionFragment", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        clasificacionRecyclerView = view.findViewById(R.id.clasifiacion_recycler_view);
        // RECUPERAR DATOS DE LA BBDD
        BaseDeDatos gestorBD = new BaseDeDatos(requireContext(), "Euroliga", null, 1);
        SQLiteDatabase bd = gestorBD.getReadableDatabase();
        SharedPreferences preferencias = PreferenceManager
            .getDefaultSharedPreferences(requireContext());
        String usuario = preferencias.getString("Usuario", null);
        /*
        SELECT e.*
        FROM Equipo AS e INNER JOIN Favorito AS f ON e.nombre = f.nombre_equipo
         */

        Cursor cEquipo = bd.rawQuery(
        "SELECT * FROM Equipo " +
            "ORDER BY part_perdidos_tot ASC", null
        );
        EquipoClasificacion[] listaEquipos = new EquipoClasificacion[18];
        int ind = 0;
        while (cEquipo.moveToNext()) {
            int posicion = ind + 1;
            String nombre = cEquipo.getString(0);
            int escudoId = cEquipo.getInt(1);
            int partGanTot = cEquipo.getInt(2);
            int partPerdTot = cEquipo.getInt(3);
            int puntFavor = cEquipo.getInt(4);
            int puntContra = cEquipo.getInt(5);
            int partGanUlt10 = cEquipo.getInt(6);
            int partPerUlt10 = cEquipo.getInt(7);
            boolean esFavorito;

            /*
            SELECT COUNT(*) FROM Favorito
            WHERE nombre_usuario = ?
            AND nombre_equipo = ?
            */
            String[] campos = new String[] {"COUNT(*)"};
            String[] argumentos = new String[] {usuario, nombre};
            Cursor cFavorito = bd.query("Favorito", campos,
                "nombre_usuario = ? AND nombre_equipo = ?", argumentos, null,
                null, null);
            cFavorito.moveToFirst();
            int equipoFavorito = cFavorito.getInt(0);
            esFavorito = equipoFavorito == 1;
            listaEquipos[ind] = new EquipoClasificacion(
                posicion, escudoId, nombre, partGanTot, partPerdTot, puntFavor, puntContra,
                partGanUlt10, partPerUlt10, esFavorito
            );
            ind++;
            cFavorito.close();
        }
        cEquipo.close();
        clasificacionRecyclerView.setAdapter(new ClasificacionAdapter(listaEquipos));
    }
}
