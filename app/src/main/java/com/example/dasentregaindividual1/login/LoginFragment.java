package com.example.dasentregaindividual1.login;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.dasentregaindividual1.R;
import com.example.dasentregaindividual1.data.database.BaseDeDatos;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginFragment extends Fragment {

    /* Atributos de la interfaz gráfica */
    private TextInputLayout campoUsuarioLayout;
    private TextInputEditText campoUsuarioEditText;
    private TextInputLayout campoContraseñaLayout;
    private TextInputEditText campoContraseñaEditText;
    private Button botonIniciarSesion;

    /* Otros atributos */
    private String usuario;
    private String contraseña;
    private SQLiteDatabase baseDeDatos;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recuperar instancia de la base de datos
        BaseDeDatos GestorDB = new BaseDeDatos (requireContext(), "Euroliga", null, 1);
        baseDeDatos = GestorDB.getReadableDatabase();
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

        campoUsuarioLayout = view.findViewById(R.id.campo_usuario_layout);
        campoUsuarioEditText = view.findViewById(R.id.campo_usuario);

        campoContraseñaLayout = view.findViewById(R.id.campo_contraseña_layout);
        campoContraseñaEditText = view.findViewById(R.id.campo_contraseña);

        botonIniciarSesion = view.findViewById(R.id.boton_iniciar_sesion);
        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (campoUsuarioEditText.getText() != null)
                    usuario = campoUsuarioEditText.getText().toString();
                if (campoContraseñaEditText.getText() != null)
                    contraseña = campoContraseñaEditText.getText().toString();
                Log.d("LoginFragment", "Usuario: " + usuario);
                Log.d("LoginFragment", "Contraseña: " + contraseña);
                String consulta = String.format(
                        "SELECT COUNT(*) FROM Usuario " +
                        "WHERE nombre_usuario = '%1$s' AND " +
                        "contraseña = '%2$s'",
                        usuario, contraseña
                );
                Cursor c = baseDeDatos.rawQuery(consulta, null);
                while (c.moveToNext()) {
                    int cantidad = c.getInt(0);
                    if (cantidad == 1) {
                        Toast.makeText(
                                requireContext(),
                                "Usuario y contraseñas CORRECTOS",
                                Toast.LENGTH_SHORT
                        ).show();
                        NavDirections accion = LoginFragmentDirections
                                .actionLoginFragmentToMenuPrincipalFragment();
                        Navigation.findNavController(view).navigate(accion);
                    } else {
                        Toast.makeText(
                                requireContext(),
                                "Usuario y contraseñas INCORRECTOS",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
                c.close();
                // baseDeDatos.close();
            }
        });
    }
}