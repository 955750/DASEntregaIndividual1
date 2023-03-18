package com.example.dasentregaindividual1.lista_partidos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;
import com.google.android.material.card.MaterialCardView;

public class ListaPartidosViewHolder extends RecyclerView.ViewHolder {

    /* Atributos de la interfaz gráfica */
    public ImageView escudoEquipoLocalIV;
    public TextView nombreEquipoLocalTV;
    public TextView ultimosPartidosEquipoLocalTV;
    public TextView puntosEquipoLocalTV;
    public ImageView escudoEquipoVisitanteIV;
    public TextView nombreEquipoVisitanteTV;
    public TextView ultimosPartidosEquipoVisitanteTV;
    public TextView puntosEquipoVisitanteTV;
    public TextView fechaPartidoTV;
    public TextView horaPartidoTV;
    public MaterialCardView cardPartido;

    /* Otros atributos */
    public boolean[] seleccion;


    public ListaPartidosViewHolder(@NonNull View itemView) {
        super(itemView);

        /* Instanciar elementos visuales del equipo local */
        escudoEquipoLocalIV = itemView.findViewById(R.id.escudo_equipo_local);
        nombreEquipoLocalTV = itemView.findViewById(R.id.nombre_equipo_local);
        ultimosPartidosEquipoLocalTV = itemView.findViewById(R.id.ultimos_partidos_equipo_local);
        puntosEquipoLocalTV = itemView.findViewById(R.id.puntos_equipo_local);

        /* Instanciar elementos visuales del equipo visitante */
        escudoEquipoVisitanteIV = itemView.findViewById(R.id.escudo_equipo_visitante);
        nombreEquipoVisitanteTV = itemView.findViewById(R.id.nombre_equipo_visitante);
        ultimosPartidosEquipoVisitanteTV = itemView
            .findViewById(R.id.ultimos_partidos_equipo_visitante);
        puntosEquipoVisitanteTV = itemView.findViewById(R.id.puntos_equipo_visitante);

        /* Instanciar elementos visuales del resto de detalles del partido */
        fechaPartidoTV = itemView.findViewById(R.id.fecha_partido);
        horaPartidoTV = itemView.findViewById(R.id.hora_partido);
        cardPartido = itemView.findViewById(R.id.card_partido);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegarHaciaDetallePartidoTrasClick(view);
            }
        });
    }

    /*
     * Al hacer click en un partido de la lista, navegamos a otro fragmento en el que se nos dan
     * detalles adicionales sobre el partido.
     */
    private void navegarHaciaDetallePartidoTrasClick(View view) {
        if (!seleccion[getAdapterPosition()]) {
            int puntosLocal = Integer.parseInt(puntosEquipoLocalTV.getText().toString());
            int puntosVisitante = Integer.parseInt(puntosEquipoVisitanteTV.getText()
                .toString());
            NavDirections accion = ListaPartidosFragmentDirections
                .actionListaPartidosFragmentToDetallePartidoFragment(
                    puntosLocal,
                    puntosVisitante
                );
            Navigation.findNavController(view).navigate(accion);
        }
        else {
            // Nothing TO-DO
        }
        seleccion[getAdapterPosition()] = !seleccion[getAdapterPosition()];
    }
}
