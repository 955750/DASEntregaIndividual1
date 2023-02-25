package com.example.dasentregaindividual1.lista_partidos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.lista_partidos.data_classes.EquipoPartido;
import com.example.dasentregaindividual1.lista_partidos.data_classes.Partido;

public class ListaPartidosAdapter extends RecyclerView.Adapter<ListaPartidosViewHolder> {

    private Partido[] partidosJornada;

    public ListaPartidosAdapter(Partido[] pPartidosJornada) {
        partidosJornada = pPartidosJornada;
    }

    @NonNull
    @Override
    public ListaPartidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listaJornadasItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.lista_partidos_item, parent, false);
        ListaPartidosViewHolder listaPartidosViewHolder = new ListaPartidosViewHolder(
                listaJornadasItem);
        return listaPartidosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPartidosViewHolder holder, int position) {
        EquipoPartido equipoLocal = partidosJornada[position].getEquipoLocal();
        holder.escudoEquipoLocalIV.setImageResource(equipoLocal.getEscudo());
        holder.nombreEquipoLocalTV.setText(equipoLocal.getNombre());
        holder.ultimosPartidosEquipoLocalTV.setText(equipoLocal.getRachaUltimosPartidos());
        holder.puntosEquipoLocalTV.setText(String.valueOf(equipoLocal.getPuntos()));

        EquipoPartido equipoVisitante = partidosJornada[position].getEquipoVisitante();
        holder.escudoEquipoVisitanteIV.setImageResource(equipoVisitante.getEscudo());
        holder.nombreEquipoVisitanteTV.setText(equipoVisitante.getNombre());
        holder.ultimosPartidosEquipoVisitanteTV.setText(equipoVisitante.getRachaUltimosPartidos());
        holder.puntosEquipoVisitanteTV.setText(String.valueOf(equipoVisitante.getPuntos()));
    }

    @Override
    public int getItemCount() {
        return partidosJornada.length;
    }
}
