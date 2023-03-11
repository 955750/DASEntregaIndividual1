package com.example.dasentregaindividual1;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dasentregaindividual1.data.database.BaseDeDatos;
import com.example.dasentregaindividual1.lista_partidos.ListaPartidosFragment;
import com.example.dasentregaindividual1.data.database.modelos.EquipoPartido;
import com.example.dasentregaindividual1.data.database.modelos.Partido;

public class MainActivity extends AppCompatActivity
        implements ListaPartidosFragment.ListenerDelFragment {

    private Partido[] partidosJornada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Más adelante se hará carga de base de datos */
        BaseDeDatos gestorDB = new BaseDeDatos(this, "NombreBD", null, 1);
        SQLiteDatabase bd = gestorDB.getReadableDatabase();
    }

    @Override
    public Partido[] cargarPartidosJornada() {
        return partidosJornada;
    }

    /*@Override
    public void seleccionarElemento(int pos, View v) {
        // DE MOMENTO PASAMOS SÓLO ESCUDOS Y MARCADOR
        Partido partidoSeleccionado = partidosJornada[pos];

        int puntosEquipoLocal = partidoSeleccionado.getEquipoLocal().getPuntos();
        int escudoIdEquipoLocal = partidoSeleccionado.getEquipoLocal().getEscudoId();

        int puntosEquipoVisitante = partidoSeleccionado.getEquipoVisitante().getPuntos();
        int escudoIdEquipoVisitante = partidoSeleccionado.getEquipoVisitante().getEscudoId();

        // ACTUAR EN FUNCIÓN DE LA ORIENTACIÓN (De momento se deja y luego se cambia)
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // "DetallePartidoFragment" EXISTE
            Navigation.findNavController(v)
                    .navigate(R.id.action_listaPartidosFragment_to_detallePartidoFragment);
            DetallePartidoFragment detallePartidoFragment = (DetallePartidoFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment_2);
            detallePartidoFragment.actualizarDatos(
                puntosEquipoLocal,
                escudoIdEquipoLocal,
                puntosEquipoVisitante,
                escudoIdEquipoVisitante
            );

        } else {
            // "DetallePartidoFragment" NO EXISTE; HAY QUE LANZAR LA ACTIVIDAD QUE LO CONTIENE
            // Enviar datos para crear el fragmento por argumento (safeArgs)
        }
    }*/
}