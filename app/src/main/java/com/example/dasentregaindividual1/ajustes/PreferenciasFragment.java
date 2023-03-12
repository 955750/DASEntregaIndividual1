package com.example.dasentregaindividual1.ajustes;

import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.dasentregaindividual1.R;

import java.util.Locale;

public class PreferenciasFragment extends PreferenceFragmentCompat
    implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.pref_config);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        SharedPreferences preferencias = PreferenceManager
                .getDefaultSharedPreferences(requireContext());
        switch (s) {
            case "idioma":
                String idioma = preferencias.getString("idioma", null);
                switch (idioma) {
                    case "Euskara":
                        cambiarIdioma("eu");
                        break;
                    case "Castellano":
                        cambiarIdioma("es");
                        break;
                    case "English":
                        cambiarIdioma("en");
                        break;
                }
                break;
            case "modo_oscuro":
                // ACTIVAR / DESACTIVAR A MODO OSCURO
                Boolean modoOscuro = preferencias.getBoolean("modo_oscuro", false);
                cambiarModo(modoOscuro);
                break;
            default:
                break;
        }
    }

    private void cambiarModo(boolean pModoOscuro) {
        if (pModoOscuro) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void cambiarIdioma(String pIdioma) {
        // Forzar la localización
        Locale nuevaloc = new Locale(pIdioma);
        Locale.setDefault(nuevaloc);
        Configuration configuration = requireContext().getResources().getConfiguration();
        configuration.setLocale(nuevaloc);
        configuration.setLayoutDirection(nuevaloc);

        // Actualizar configuración de los recursos (res)
        Context context = requireContext().createConfigurationContext(configuration);
        requireContext().getResources().updateConfiguration(
                configuration, context.getResources().getDisplayMetrics());

        // Finalizar y reiniciar actividad (para que se aplique la nueva configuración)
        requireActivity().finish();
        requireActivity().startActivity(requireActivity().getIntent());
    }
}