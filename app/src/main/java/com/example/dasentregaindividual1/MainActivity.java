package com.example.dasentregaindividual1;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.example.dasentregaindividual1.data.base_de_datos.BaseDeDatos;
import com.example.dasentregaindividual1.lista_partidos.ListaPartidosFragment;
import com.example.dasentregaindividual1.data.base_de_datos.modelos.Partido;
import com.example.dasentregaindividual1.login.LoginFragmentDirections;
import com.example.dasentregaindividual1.menu_principal.SalirDialogFragment;

public class MainActivity extends AppCompatActivity
        implements SalirDialogFragment.ListenerSalirDialogFragment {

    private SQLiteDatabase baseDeDatos;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recuperar instancia de la base de datos
        BaseDeDatos gestorBD = new BaseDeDatos (this, "Euroliga",
                null, 1);
        baseDeDatos = gestorBD.getWritableDatabase();

        // Para que el nombre de la barra cambie en función del fragmento en el que estamos

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
            NavigationUI.setupActionBarWithNavController(this, navController,
                appBarConfiguration);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void alPulsarPositivo() {
        cerrarSesion();
        finish();
    }

    @Override
    public void alPulsarNeutral() {
        cerrarSesion();
        NavDirections accion = com.example.dasentregaindividual1.login.LoginFragmentDirections
            .actionLoginFragmentToMenuPrincipalFragment();
        // Navigation.findNavController(view).navigate(accion);
    }

    @Override
    public void alPulsarNegativo() {
        // Nothing TO-DO
    }

    private void cerrarSesion() {
        /*
        UPDATE Usuario SET sesion_iniciada = 0
        WHERE nombre_usuario = ?
        */
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        String usuario = preferencias.getString("Usuario", null);
        ContentValues iniciarSesion = new ContentValues();
        iniciarSesion.put("sesion_iniciada", 0);
        String[] argumentos = new String[] {usuario};
        baseDeDatos.update("Usuario", iniciarSesion,
                "nombre_usuario = ?", argumentos);
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