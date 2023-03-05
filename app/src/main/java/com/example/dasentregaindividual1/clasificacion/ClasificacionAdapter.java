package com.example.dasentregaindividual1.clasificacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.clasificacion.modelos.EquipoClasificacion;
import com.example.dasentregaindividual1.lista_partidos.data_classes.EquipoPartido;

public class ClasificacionAdapter extends RecyclerView.Adapter<ClasificacionViewHolder> {

    private EquipoClasificacion[] clasificacion;
    public ClasificacionAdapter(EquipoClasificacion[] pClasificacion) {
        clasificacion = pClasificacion;
    }

    @NonNull
    @Override
    public ClasificacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View clasificacionItem = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.clasificacion_item, parent, false);
        ClasificacionViewHolder clasificacionViewHolder = new ClasificacionViewHolder(
            clasificacionItem);
        return clasificacionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClasificacionViewHolder holder, int position) {
        EquipoClasificacion equipoActual = clasificacion[position];
        holder.posicionClasifacionTV.setText(String.valueOf(equipoActual.getPosicion()));
        holder.escudoTV.setImageResource(equipoActual.getEscudoId());
        holder.nombreEquipoTV.setText(equipoActual.getNombre());
        holder.partidosGanadosTotalesTV.setText(String.valueOf(equipoActual
            .getPartidosGanadosTotales()));
        holder.partidosPerdidosTotalesTV.setText(String.valueOf(equipoActual
            .getPartidosPerdidosTotales()));
        holder.puntosFavorTotalesTV.setText(String.valueOf(equipoActual.getPuntosFavorTotales()));
        holder.puntosContraTotalesTV.setText(String.valueOf(equipoActual.getPuntosContraTotales()));
        String rachaUltimosPartidos = holder.itemView.getContext()
            .getString(R.string.racha_ultimos_partidos,
                equipoActual.getPartidosGanadosUltimos10(),
                equipoActual.getPartidosPerdidosUltimos10()
            );
        holder.rachaUltimos10TV.setText(rachaUltimosPartidos);
    }

    @Override
    public int getItemCount() {
        return clasificacion.length;
    }
}
