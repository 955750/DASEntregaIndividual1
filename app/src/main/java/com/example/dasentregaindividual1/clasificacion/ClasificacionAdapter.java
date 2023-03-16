package com.example.dasentregaindividual1.clasificacion;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.data.base_de_datos.modelos.EquipoClasificacion;

public class ClasificacionAdapter extends RecyclerView.Adapter<ClasificacionViewHolder> {

    private EquipoClasificacion[] clasificacion;
    private boolean[] seleccionados;

    public ClasificacionAdapter(EquipoClasificacion[] pClasificacion) {
        clasificacion = pClasificacion;
        seleccionados = new boolean[pClasificacion.length];
    }

    @NonNull
    @Override
    public ClasificacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View clasificacionItem = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.clasificacion_item, parent, false);
        ClasificacionViewHolder clasificacionViewHolder = new ClasificacionViewHolder(
            clasificacionItem);
        clasificacionViewHolder.seleccion = seleccionados;
        return clasificacionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClasificacionViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
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
        if (equipoActual.getPosicion() > 8) {
            Log.d("ClasificacionAdapter", String.valueOf(equipoActual.getPosicion()));
            holder.cardClasificacion.setStrokeColor(context.getColor(R.color.naranja_menos_vivo));
            holder.cardClasificacion.setCardBackgroundColor(context.getColor(R.color.naranja_claro));
        } else {
            holder.cardClasificacion.setStrokeColor(context.getColor(R.color.naranja_vivo));
            holder.cardClasificacion.setCardBackgroundColor(context.getColor(R.color.naranja_oscuro));
        }
        if (equipoActual.getEsFavorito()) {
            holder.añadirEliminarFavoritosIV.setImageResource(R.drawable.ic_favorito_true_32dp);
        } else {
            holder.añadirEliminarFavoritosIV.setImageResource(R.drawable.ic_favorito_false_32dp);
        }
    }

    @Override
    public int getItemCount() {
        return clasificacion.length;
    }
}
