package com.example.dasentregaindividual1.login;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.data.base_de_datos.BaseDeDatos;
import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends Fragment {

    /* Atributos de la interfaz gráfica */
    private TextInputEditText usuarioTV;
    private TextInputEditText contraseñaTV;
    private Button botonIniciarSesion;
    private Button botonCrearCuenta;

    /* Otros atributos */
    private SQLiteDatabase baseDeDatos;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LoginFragment", "onCreate");
        
        // Recuperar instancia de la base de datos
        BaseDeDatos gestorBD = new BaseDeDatos (requireContext(), "Euroliga", 
                null, 1);
        baseDeDatos = gestorBD.getWritableDatabase();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        Log.d("LoginFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("LoginFragment", "onViewCreated");
        
        usuarioTV = view.findViewById(R.id.campo_usuario);
        contraseñaTV = view.findViewById(R.id.campo_contraseña);
        botonIniciarSesion = view.findViewById(R.id.boton_iniciar_sesion);
        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hacerLogin(view);
            }
        });

        botonCrearCuenta = view.findViewById(R.id.boton_crear_cuenta);
        botonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegarHaciaCrearCuenta(view);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LoginFragment", "onDestroy");
        baseDeDatos.close();
    }
    
    private void hacerLogin(View view) {
        if (camposValidos()) {
            iniciarSesion(usuarioTV.getText().toString(), contraseñaTV.getText().toString());
            navegarHaciaMenuPrincipal(view);
        }
    }
    
    private boolean camposValidos() {
        if (usuarioTV.getText().toString().equals("")) {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_campo_vacio, "Usuario"),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else if (contraseñaTV.getText().toString().equals("")) {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_campo_vacio, "Contraseña"),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else if (!usuarioCorrecto(
            usuarioTV.getText().toString(),
            contraseñaTV.getText().toString()
        )) {
            Toast.makeText(
                    requireContext(),
                    getString(R.string.toast_usuario_contraseña_incorrectos),
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        } else {
            Toast.makeText(
                    requireContext(),
                    getString(R.string.toast_usuario_contraseña_correctos),
                    Toast.LENGTH_SHORT
            ).show();
            return true;
        }
    }

    private boolean usuarioCorrecto(String pUsuario, String pContraseña) {
        /*
        SELECT COUNT(*) FROM Usuario
        WHERE nombre_usuario = ? AND
        contraseña = ?
        */
        String[] campos = new String[]{"COUNT(*)"};
        String[] argumentos = new String[]{pUsuario, pContraseña};
        Cursor cUsuario = baseDeDatos.query("Usuario", campos,
                "nombre_usuario = ? AND contraseña = ?",
                argumentos, null, null, null);

        cUsuario.moveToFirst();
        int cantidadUsuarios = cUsuario.getInt(0);
        cUsuario.close();
        return cantidadUsuarios == 1;
    }

    private void iniciarSesion(String pUsuario, String pContraseña) {
        /*
        UPDATE Usuario SET sesion_iniciada = 1
        WHERE nombre_usuario = ? AND
        contraseña = ?
        */
        ContentValues iniciarSesion = new ContentValues();
        iniciarSesion.put("sesion_iniciada", 1);
        String[] argumentos = new String[] {pUsuario, pContraseña};
        baseDeDatos.update("Usuario", iniciarSesion,
                "nombre_usuario = ? AND contraseña = ?", argumentos);

        SharedPreferences preferencias = PreferenceManager
            .getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("Usuario", pUsuario);
        editor.apply();
    }

    private void navegarHaciaMenuPrincipal(View view) {
        NavDirections accion = LoginFragmentDirections
            .actionLoginFragmentToMenuPrincipalFragment();
        Navigation.findNavController(view).navigate(accion);
    }

    private void navegarHaciaCrearCuenta(View view) {
        NavDirections accion = LoginFragmentDirections
            .actionLoginFragmentToCrearCuentaFragment();
        Navigation.findNavController(view).navigate(accion);
    }
}