package com.example.dasentregaindividual1.crear_cuenta;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.data.base_de_datos.BaseDeDatos;
import com.example.dasentregaindividual1.login.LoginFragmentDirections;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearCuentaFragment extends Fragment {

    /* Atributos de la interfaz gráfica */
    private TextInputEditText usuarioTV;
    private TextInputEditText contraseñaTV;
    private TextInputEditText repetirContraseñaTV;
    private Button crearCuenta;

    /* Otros atributos */
    private SQLiteDatabase bd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CrearCuentaFragment", "onCreate");
        BaseDeDatos gestorBD = new BaseDeDatos(requireContext(), "Euroliga",
                null, 1);
        bd = gestorBD.getWritableDatabase();
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        Log.d("CrearCuentaFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_crear_cuenta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("CrearCuentaFragment", "onViewCreated");

        usuarioTV = view.findViewById(R.id.campo_usuario_crear_cuenta);
        contraseñaTV = view.findViewById(R.id.campo_contraseña_crear_cuenta);
        repetirContraseñaTV = view.findViewById(R.id.campo_repetir_contraseña_crear_cuenta);
        crearCuenta = view.findViewById(R.id.boton_crear_cuenta);
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario(view);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("CrearCuentaFragment", "onDestroy");
        bd.close();
    }

    private void crearUsuario(View view) {
        if (camposValidos()) {
            crearCuenta();
            NavDirections accion = CrearCuentaFragmentDirections
                    .actionCrearCuentaFragmentToMenuPrincipalFragment();
            Navigation.findNavController(view).navigate(accion);
        }
    }

    private boolean camposValidos() {
        if (usuarioTV.getText().toString().equals("")) {
            Toast.makeText(
                requireContext(),
            getString(R.string.toast_campo_vacio, "usuario"),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else if (contraseñaTV.getText().toString().equals("")) {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_campo_vacio, "contraseña"),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else if (repetirContraseñaTV.getText().toString().equals("")) {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_campo_vacio, "repetir contraseña"),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else if (!usuarioValido(usuarioTV.getText().toString())) {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_nombre_usuario_existente),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else if (!contraseñaTV.getText().toString().equals(
            repetirContraseñaTV.getText().toString())
        ) {
            Log.d("CrearCuentaFragment", "Contraseñas iguales = true");
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_contraseñas_no_coinciden),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else if (!contraseñaCumpleFormato()) {
            return false;
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_cuenta_creada_con_exito),
                Toast.LENGTH_SHORT
            ).show();
            return true;
        }
    }

    private boolean usuarioValido(String pUsuario) {
        /*
        SELECT COUNT(*) FROM Usuario
        WHERE nombre_usuario = 'julen_fuentes'
         */
        String[] campos = new String[] {"COUNT(*)"};
        String[] argumentos = new String[] {pUsuario};
        Cursor cUsuario = bd.query("Usuario", campos, "nombre_usuario = ?",
                argumentos, null, null, null);

        cUsuario.moveToFirst();
        int cantidadUsuarios = cUsuario.getInt(0);
        cUsuario.close();
        return cantidadUsuarios != 1;
    }

    private boolean contraseñaCumpleFormato() {
        /*
            ^ represents starting character of the string.
            (?=.*[0-9]) represents a digit must occur at least once.
            (?=.*[a-z]) represents a lower case alphabet must occur at least once.
            (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
            (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
            (?=\\S+$) white spaces don’t allowed in the entire string.
            .{8, 20} represents at least 8 characters and at most 20 characters.
            $ represents the end of the string.
         */


        String contraseña = contraseñaTV.getText().toString();
        String regex = "^(?=.*[0-9])" +
                       "(?=.*[a-z])(?=.*[A-Z])" +
                       "(?=.*[@#$%^&+=])" +
                       "(?=\\S+$).{8,20}$";
        Pattern patronContraseña = Pattern.compile(regex);
        boolean cumplePatron = patronContraseña.matcher(contraseña).matches();
        if (contraseña.length() < 8) {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_minimo_8_caracteres),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else if (!cumplePatron) {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_formato_contraseña_invalido),
                Toast.LENGTH_SHORT)
            .show();
            return false;
        } else {
            return true;
        }
    }

    private void crearCuenta() {
        BaseDeDatos gestorBD = new BaseDeDatos(requireContext(), "Euroliga",
                null, 1);
        SQLiteDatabase bd = gestorBD.getWritableDatabase();
        ContentValues nuevoUsuario = new ContentValues();
        nuevoUsuario.put("nombre_usuario", usuarioTV.getText().toString());
        nuevoUsuario.put("contraseña", contraseñaTV.getText().toString());
        bd.insert("Usuario", null, nuevoUsuario);
        bd.close();
    }
}