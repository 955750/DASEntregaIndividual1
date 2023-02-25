package com.example.dasentregaindividual1.lista_partidos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;

public class ListaPartidosAdapter extends RecyclerView.Adapter<ListaPartidosViewHolder> {

    private int[] escudosEquiposLocales;
    private String[] nombresEquiposLocales;
    private String[] ultimosPartidosEquiposLocales;
    private int[] puntosEquiposLocales;

    private int[] escudosEquiposVisitantes;
    private String[] nombresEquiposVisitantes;
    private String[] ultimosPartidosEquiposVisitantes;
    private int[] puntosEquiposVisitantes;

    public ListaPartidosAdapter(
        int[] pEscudosEquiposLocales,
        String[] pNombresEquiposLocales,
        String[] pUltimosPartidosEquiposLocales,
        int[] pPuntosEquiposLocales,
        int[] pEscudosEquiposVisitantes,
        String[] pNombresEquiposVisitantes,
        String[] pUltimosPartidosEquiposVisitantes,
        int[] pPuntosEquiposVisitantes
    ) {
        escudosEquiposLocales = pEscudosEquiposLocales;
        nombresEquiposLocales = pNombresEquiposLocales;
        ultimosPartidosEquiposLocales = pUltimosPartidosEquiposLocales;
        puntosEquiposLocales = pPuntosEquiposLocales;

        escudosEquiposVisitantes = pEscudosEquiposVisitantes;
        nombresEquiposVisitantes = pNombresEquiposVisitantes;
        ultimosPartidosEquiposVisitantes = pUltimosPartidosEquiposVisitantes;
        puntosEquiposVisitantes = pPuntosEquiposVisitantes;
    }

    @NonNull
    @Override
    public ListaPartidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listaJornadasItem= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.lista_partidos_item, parent, false);
        ListaPartidosViewHolder listaPartidosViewHolder = new ListaPartidosViewHolder(
                listaJornadasItem);
        return listaPartidosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPartidosViewHolder holder, int position) {
        holder.escudoEquipoLocalIV.setImageResource(escudosEquiposLocales[position]);
        holder.nombreEquipoLocalTV.setText(nombresEquiposLocales[position]);
        holder.ultimosPartidosEquipoLocalTV.setText(ultimosPartidosEquiposLocales[position]);
        holder.puntosEquipoLocalTV.setText(String.valueOf(puntosEquiposLocales[position]));

        holder.escudoEquipoVisitanteIV.setImageResource(escudosEquiposVisitantes[position]);
        holder.nombreEquipoVisitanteTV.setText(nombresEquiposVisitantes[position]);
        holder.ultimosPartidosEquipoVisitanteTV.setText(ultimosPartidosEquiposVisitantes[position]);
        holder.puntosEquipoVisitanteTV.setText(String.valueOf(puntosEquiposVisitantes[position]));
    }

    @Override
    public int getItemCount() {
        return escudosEquiposLocales.length;
    }
}
