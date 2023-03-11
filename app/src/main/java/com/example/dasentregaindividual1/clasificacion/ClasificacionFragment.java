package com.example.dasentregaindividual1.clasificacion;

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
        Cursor c = bd.rawQuery(
        "SELECT * FROM Equipo " +
            "ORDER BY part_perdidos_tot ASC", null
        );
        EquipoClasificacion[] listaEquipos = new EquipoClasificacion[18];
        int ind = 0;
        while (c.moveToNext()) {
            int posicion = ind + 1;
            String nombre = c.getString(0);
            int escudoId = c.getInt(1);
            int partGanTot = c.getInt(2);
            int partPerdTot = c.getInt(3);
            int puntFavor = c.getInt(4);
            int puntContra = c.getInt(5);
            int partGanUlt10 = c.getInt(6);
            int partPerUlt10 = c.getInt(7);
            listaEquipos[ind] = new EquipoClasificacion(
                posicion, escudoId, nombre, partGanTot, partPerdTot, puntFavor, puntContra,
                partGanUlt10, partPerUlt10
            );
            ind++;
        }
        c.close();
        clasificacionRecyclerView.setAdapter(new ClasificacionAdapter(listaEquipos));
    }
}
