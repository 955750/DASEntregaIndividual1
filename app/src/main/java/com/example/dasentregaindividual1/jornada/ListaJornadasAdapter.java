package com.example.dasentregaindividual1.jornada;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;

public class ListaJornadasAdapter extends RecyclerView.Adapter<ListaJornadasViewHolder> {

    private int[] escudosEquiposLocales;
    private String[] nombresEquiposLocales;
    private String[] ultimosPartidosEquiposLocales;

    private int[] escudosEquiposVisitantes;
    private String[] nombresEquiposVisitantes;
    private String[] ultimosPartidosEquiposVisitantes;

    public ListaJornadasAdapter(
        int[] pEscudosEquiposLocales,
        String[] pNombresEquiposLocales,
        String[] pUltimosPartidosEquiposLocales,
        int[] pEscudosEquiposVisitantes,
        String[] pNombresEquiposVisitantes,
        String[] pUltimosPartidosEquiposVisitantes
    ) {
        escudosEquiposLocales = pEscudosEquiposLocales;
        nombresEquiposLocales = pNombresEquiposLocales;
        ultimosPartidosEquiposLocales = pUltimosPartidosEquiposLocales;
        escudosEquiposVisitantes = pEscudosEquiposVisitantes;
        nombresEquiposVisitantes = pNombresEquiposVisitantes;
        ultimosPartidosEquiposVisitantes = pUltimosPartidosEquiposVisitantes;
    }

    @NonNull
    @Override
    public ListaJornadasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listaJornadasItem= LayoutInflater
                .from(parent.getContext()).inflate(R.layout.lista_jornadas_item,null);
        ListaJornadasViewHolder listaJornadasViewHolder = new ListaJornadasViewHolder(
                listaJornadasItem);
        return listaJornadasViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaJornadasViewHolder holder, int position) {
        holder.escudoEquipoLocalIV.setImageResource(escudosEquiposLocales[position]);
        holder.nombreEquipoLocalTV.setText(nombresEquiposLocales[position]);
        holder.ultimosPartidosEquipoLocalTV.setText(ultimosPartidosEquiposLocales[position]);

        holder.escudoEquipoVisitanteIV.setImageResource(escudosEquiposVisitantes[position]);
        holder.nombreEquipoVisitanteTV.setText(nombresEquiposVisitantes[position]);
        holder.ultimosPartidosEquipoVisitanteTV.setText(ultimosPartidosEquiposVisitantes[position]);
    }

    @Override
    public int getItemCount() {
        return escudosEquiposLocales.length;
    }
}
