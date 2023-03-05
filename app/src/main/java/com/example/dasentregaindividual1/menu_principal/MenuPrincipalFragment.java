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

        botonJornadas = (Button) view.findViewById(R.id.boton_jornadas);
        botonJornadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections accion = MenuPrincipalFragmentDirections
                        .actionMenuPrincipalFragmentToListaPartidosFragment();
                Navigation.findNavController(view).navigate(accion);
            }
        });
    }
}