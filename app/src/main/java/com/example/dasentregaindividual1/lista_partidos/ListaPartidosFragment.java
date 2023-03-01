package com.example.dasentregaindividual1.lista_partidos;

import android.content.Context;
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

        jornadasRecyclerView = view.findViewById(R.id.jornadas_recycler_view);
        Partido[] listaPartidos = elListener.cargarPartidosJornada();
        jornadasRecyclerView.setAdapter(new ListaPartidosAdapter(listaPartidos));
    }
}