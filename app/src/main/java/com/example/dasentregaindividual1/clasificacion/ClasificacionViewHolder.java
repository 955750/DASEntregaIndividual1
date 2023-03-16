package com.example.dasentregaindividual1.clasificacion;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.data.base_de_datos.BaseDeDatos;
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
    public ImageView añadirEliminarFavoritosIV;
    public boolean[] seleccion;

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
        añadirEliminarFavoritosIV = itemView.findViewById(R.id.añadir_eliminar_favoritos);
        añadirEliminarFavoritosIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = itemView.getContext();
                if (!seleccion[getAdapterPosition()]) {
                    añadirEquipoFavorito(context);
                    añadirEliminarFavoritosIV.setImageResource(R.drawable.ic_favorito_true_32dp);
                } else {
                    eliminarEquipoFavorito(context);
                    añadirEliminarFavoritosIV.setImageResource(R.drawable.ic_favorito_false_32dp);
                }
                seleccion[getAdapterPosition()] = !seleccion[getAdapterPosition()];
            }
        });
    }

    private void añadirEquipoFavorito(Context context) {
        // Recuperar instancia de la base de datos
        BaseDeDatos gestorBD = new BaseDeDatos (context, "Euroliga",
                null, 1);
        SQLiteDatabase baseDeDatos = gestorBD.getWritableDatabase();

        // Añadir equipo a favoritos
        ContentValues nuevoEqFavorito = new ContentValues();
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(context);
        String usuario = preferencias.getString("Usuario", null);
        nuevoEqFavorito.put("nombre_usuario", usuario);
        nuevoEqFavorito.put("nombre_equipo", nombreEquipoTV.getText().toString());
        baseDeDatos.insert("Favorito", null, nuevoEqFavorito);
        Toast.makeText(context, "AÑADIR FAV.", Toast.LENGTH_SHORT).show();

        gestorBD.close();
    }

    private void eliminarEquipoFavorito(Context context) {
        // Recuperar instancia de la base de datos
        BaseDeDatos gestorBD = new BaseDeDatos (context, "Euroliga",
                null, 1);
        SQLiteDatabase baseDeDatos = gestorBD.getWritableDatabase();
        Toast.makeText(context, "ELIMINAR FAV.", Toast.LENGTH_SHORT).show();

        // Eliminar equipo de los favoritos
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(context);
        String usuario = preferencias.getString("Usuario", null);
        String[] argumentos = {usuario, nombreEquipoTV.getText().toString()};
        baseDeDatos.delete("Favorito", "nombre_usuario = ? AND nombre_equipo = ?",
            argumentos);

        gestorBD.close();
    }
}
