package com.example.dasentregaindividual1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
    }

    /* Función para que la flecha de la 'app bar' nos lleve al fragmento anterior */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation
            .findNavController(this, R.id.nav_host_fragment);
        return NavigationUI
            .navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}