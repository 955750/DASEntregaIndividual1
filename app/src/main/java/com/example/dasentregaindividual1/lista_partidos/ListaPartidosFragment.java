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
import com.example.dasentregaindividual1.data.database.BaseDeDatos;
import com.example.dasentregaindividual1.lista_partidos.data_classes.Partido;

public class ListaPartidosFragment extends Fragment {

    private RecyclerView jornadasRecyclerView;

    private ListenerDelFragment elListener;
    public interface ListenerDelFragment {
        Partido[] cargarPartidosJornada();
        // void seleccionarElemento(int pos, View v);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            elListener = (ListenerDelFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("La clase " + context
                    + "debe implementar ListenerDelFragment");
        }
    }

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
        Cursor c = bd.rawQuery(
        "SELECT * FROM Partido ", null
        );
        // Crear otro objeto modelo (data class para recoger datos de la BBDD)
        /*
        EquipoClasificacion[] listaPartidos = new EquipoClasificacion[18];
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
        */
        //  Partido[] listaPartidos = elListener.cargarPartidosJornada();
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