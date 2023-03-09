package com.example.dasentregaindividual1.clasificacion;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;
import com.google.android.material.card.MaterialCardView;

public class ClasificacionViewHolder extends RecyclerView.ViewHolder {

    public TextView posicionClasifacionTV;
    public ImageView escudoTV;
    public TextView nombreEquipoTV;
    public TextView partidosGanadosTotalesTV;
    public TextView partidosPerdidosTotalesTV;
    public TextView puntosFavorTotalesTV;
    public TextView puntosContraTotalesTV;
    public TextView rachaUltimos10TV;
    public MaterialCardView cardClasificacion;

    public ClasificacionViewHolder(@NonNull View itemView) {
        super(itemView);

        posicionClasifacionTV = itemView.findViewById(R.id.posicion_clasificacion);
        escudoTV = itemView.findViewById(R.id.escudo_clasificacion);
        nombreEquipoTV = itemView.findViewById(R.id.nombre_equipo_clasificacion);
        partidosGanadosTotalesTV = itemView.findViewById(R.id.partidos_ganados_totales);
        partidosPerdidosTotalesTV = itemView.findViewById(R.id.partidos_perdidos_totales);
        puntosFavorTotalesTV = itemView.findViewById(R.id.puntos_favor_totales);
        puntosContraTotalesTV = itemView.findViewById(R.id.puntos_contra_totales);
        rachaUltimos10TV = itemView.findViewById(R.id.racha_ultimos_10);
        cardClasificacion = itemView.findViewById(R.id.card_clasificacion);
    }
}
