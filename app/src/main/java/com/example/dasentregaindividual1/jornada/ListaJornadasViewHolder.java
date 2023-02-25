package com.example.dasentregaindividual1.jornada;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;

public class ListaJornadasViewHolder extends RecyclerView.ViewHolder {

    public ImageView escudoEquipoLocalIV;
    public TextView nombreEquipoLocalTV;
    public TextView ultimosPartidosEquipoLocalTV;

    public ImageView escudoEquipoVisitanteIV;
    public TextView nombreEquipoVisitanteTV;
    public TextView ultimosPartidosEquipoVisitanteTV;

    public ListaJornadasViewHolder(@NonNull View itemView) {
        super(itemView);

        escudoEquipoLocalIV = itemView.findViewById(R.id.escudo_equipo_local);
        nombreEquipoLocalTV = itemView.findViewById(R.id.nombre_equipo_local);
        ultimosPartidosEquipoLocalTV = itemView.findViewById(R.id.ultimos_partidos_equipo_local);

        escudoEquipoVisitanteIV = itemView.findViewById(R.id.escudo_equipo_visitante);
        nombreEquipoVisitanteTV = itemView.findViewById(R.id.nombre_equipo_visitante);
        ultimosPartidosEquipoVisitanteTV = itemView
                .findViewById(R.id.ultimos_partidos_equipo_visitante);
    }
}
