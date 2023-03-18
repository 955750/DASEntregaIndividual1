package com.example.dasentregaindividual1;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Para que el nombre del 'app bar' cambie en función del fragmento en el que estamos */
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
            .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
            NavigationUI.setupActionBarWithNavController(this, navController,
                appBarConfiguration);
        }

        inicializarPreferencias();
    }

    /* Función para que la flecha de la 'app bar' nos lleve al fragmento anterior */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation
            .findNavController(this, R.id.nav_host_fragment);
        return NavigationUI
            .navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    private void inicializarPreferencias() {
        SharedPreferences preferencias = PreferenceManager
            .getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = preferencias.edit();
        String idiomaSeleccionado = preferencias.getString("idioma", null);
        if (idiomaSeleccionado == null) { // Por defecto poner castellano como idioma
            editor.putString("idioma", "Castellano");
            LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("es");
            AppCompatDelegate.setApplicationLocales(appLocale);
        }
        boolean modoOscuro = preferencias.getBoolean("modo_oscuro", false);
        if (modoOscuro)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        editor.apply();
    }
}