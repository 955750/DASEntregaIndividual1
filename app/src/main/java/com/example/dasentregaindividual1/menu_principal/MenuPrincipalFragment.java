package com.example.dasentregaindividual1.menu_principal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dasentregaindividual1.R;

public class MenuPrincipalFragment extends Fragment {

    private Button botonJornadas;
    private Button botonClasificacion;
    private Button botonAjustes;
    private Button botonCerrarSesion;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        Log.d("MenuPrincipalFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_menu_principal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("MenuPrincipalFragment", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        botonJornadas = view.findViewById(R.id.boton_jornadas);
        botonJornadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegarHaciaJornadas(view);
            }
        });

        botonClasificacion = view.findViewById(R.id.boton_clasificacion);
        botonClasificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegarHaciaClasificacion(view);
            }
        });

        botonAjustes = view.findViewById(R.id.boton_ajustes);
        botonAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegarHaciaAjustes(view);
            }
        });

        botonCerrarSesion = view.findViewById(R.id.cerrar_sesion);
        botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DIALOGO CERRAR SESIÓN
            }
        });
    }

    private void navegarHaciaJornadas(View view) {
        NavDirections accion = MenuPrincipalFragmentDirections
            .actionMenuPrincipalFragmentToListaPartidosFragment();
        Navigation.findNavController(view).navigate(accion);
    }

    private void navegarHaciaClasificacion(View view) {
        NavDirections accion = MenuPrincipalFragmentDirections
            .actionMenuPrincipalFragmentToClasificacionFragment();
        Navigation.findNavController(view).navigate(accion);
    }

    private void navegarHaciaAjustes(View view) {
        NavDirections accion = MenuPrincipalFragmentDirections
            .actionMenuPrincipalFragmentToAjustesFragment();
        Navigation.findNavController(view).navigate(accion);
    }
}