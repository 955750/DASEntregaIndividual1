package com.example.dasentregaindividual1.menu_principal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.dasentregaindividual1.R;

public class SalirDialogFragment extends DialogFragment {

    private ListenerSalirDialogFragment listenerSalirDialogFragment;

    public interface ListenerSalirDialogFragment {
        void alPulsarPositivo();
        void alPulsarNeutral();
        void alPulsarNegativo();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        listenerSalirDialogFragment = (ListenerSalirDialogFragment) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.dialogo_salir_titulo);
        builder.setMessage(R.string.dialogo_salir_mensaje);

        builder.setPositiveButton(R.string.salir_y_cerrar_sesion_mensaje,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                seleccionarOpcion(1);
                // listenerSalirDialogFragment.alPulsarPositivo();
            }
        });

        builder.setNeutralButton(R.string.no_salir_mensaje, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                seleccionarOpcion(2);
                //listenerSalirDialogFragment.alPulsarNeutral();
            }
        });

        builder.setNegativeButton(R.string.cerrar_sesion_mensaje, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                seleccionarOpcion(3);
                // listenerSalirDialogFragment.alPulsarNegativo();
            }
        });

        return builder.create();
    }

    private void seleccionarOpcion(int opcionSalirId) {
        Bundle opcionSalir = new Bundle();
        opcionSalir.putInt("opcionSalirId", opcionSalirId);
        getParentFragmentManager().setFragmentResult("opcionSalir", opcionSalir);
    }
}