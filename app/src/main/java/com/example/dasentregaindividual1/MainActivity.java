package com.example.dasentregaindividual1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dasentregaindividual1.lista_partidos.ListaPartidosFragment;
import com.example.dasentregaindividual1.lista_partidos.data_classes.EquipoPartido;
import com.example.dasentregaindividual1.lista_partidos.data_classes.Partido;

public class MainActivity extends AppCompatActivity
        implements ListaPartidosFragment.ListenerDelFragment {

    private Partido[] partidosJornada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Más adelante se hará carga de base de datos */

        // Crear equipos (la racha hay que cambiarla a últimos 10 partidos)
        EquipoPartido olympiacos = new EquipoPartido(
                R.drawable.escudo_olympiacos,
                "Olympiacos",
                getString(R.string.racha_ultimos_partidos, 18, 7),
                92
        );
        EquipoPartido realMadrid = new EquipoPartido(
                R.drawable.escudo_real_madrid,
                "Real Madrid",
                getString(R.string.racha_ultimos_partidos, 17, 7),
                96
        );
        EquipoPartido fcBarcelona = new EquipoPartido(
                R.drawable.escudo_fc_barcelona,
                "FC Barcelona",
                getString(R.string.racha_ultimos_partidos, 17, 8),
                80
        );
        EquipoPartido fenerbahce = new EquipoPartido(
                R.drawable.escudo_fenerbahce,
                "Fenerbahce",
                getString(R.string.racha_ultimos_partidos, 15, 9),
                97
        );
        EquipoPartido asMonaco = new EquipoPartido(
                R.drawable.escudo_as_monaco,
                "AS Monaco",
                getString(R.string.racha_ultimos_partidos, 15, 10),
                70
        );
        EquipoPartido partizanBelgrade = new EquipoPartido(
                R.drawable.escudo_partizan_belgrade,
                "Partizan Belgrade",
                getString(R.string.racha_ultimos_partidos, 13, 12),
                94
        );
        EquipoPartido baskonia = new EquipoPartido(
                R.drawable.escudo_baskonia,
                "Baskonia",
                getString(R.string.racha_ultimos_partidos, 13, 12),
                83
        );
        EquipoPartido maccabiTelAviv = new EquipoPartido(
                R.drawable.escudo_maccabi_tel_aviv,
                "Maccabi Tel Aviv",
                getString(R.string.racha_ultimos_partidos, 13, 12),
                90
        );
        EquipoPartido valenciaBasket = new EquipoPartido(
                R.drawable.escudo_valencia_basket,
                "Valencia Basket",
                getString(R.string.racha_ultimos_partidos,13, 12),
                85
        );
        EquipoPartido zalgirisKaunas = new EquipoPartido(
                R.drawable.escudo_zalgiris_kaunas,
                "Zalgiris Kaunas",
                getString(R.string.racha_ultimos_partidos, 13, 12),
                69
        );
        EquipoPartido anadoluEfes = new EquipoPartido(
                R.drawable.escudo_anadolu_efes,
                "Anadolu Efes",
                getString(R.string.racha_ultimos_partidos, 12, 12),
                90
        );
        EquipoPartido virtusBologna = new EquipoPartido(
                R.drawable.escudo_virtus_bologna,
                "Virtus Bologna",
                getString(R.string.racha_ultimos_partidos, 12, 13),
                88
        );
        EquipoPartido crvenaZvezda = new EquipoPartido(
                R.drawable.escudo_crvena_zvezda,
                "Crvena Zvezda",
                getString(R.string.racha_ultimos_partidos, 11, 14),
                72
        );
        EquipoPartido emporioArmaniMilan = new EquipoPartido(
                R.drawable.escudo_emporio_armani_milan,
                "Emporio Armani Milan",
                getString(R.string.racha_ultimos_partidos, 9, 15),
                78
        );
        EquipoPartido bayernMunich = new EquipoPartido(
                R.drawable.escudo_bayern_munich,
                "Bayern Munich",
                getString(R.string.racha_ultimos_partidos, 9, 16),
                82
        );
        EquipoPartido panathinaikos = new EquipoPartido(
                R.drawable.escudo_panathinaikos,
                "Panathinaikos",
                getString(R.string.racha_ultimos_partidos, 8, 17),
                76
        );
        EquipoPartido ldlcAsvelVilleurbane = new EquipoPartido(
                R.drawable.escudo_ldlc_asvel_villeurbanne,
                "LDLC Asvel Villeurbane",
                getString(R.string.racha_ultimos_partidos, 18, 7),
                89
        );
        EquipoPartido albaBerlin = new EquipoPartido(
                R.drawable.escudo_alba_berlin,
                "Alba Berlin",
                getString(R.string.racha_ultimos_partidos, 7, 18),
                87
        );

        // Crear partidos (son los de la última jornada [JORNADA 25])
        Partido p1 = new Partido(maccabiTelAviv, bayernMunich, "23/02/2023", "20:05");
        Partido p2 = new Partido(emporioArmaniMilan, panathinaikos, "23/02/2023", "20:30");
        Partido p3 = new Partido(realMadrid, zalgirisKaunas, "23/02/2023", "20:45");
        Partido p4 = new Partido(partizanBelgrade, fenerbahce, "23/02/2023", "21:00");
        Partido p5 = new Partido(crvenaZvezda, albaBerlin, "24/02/2023", "19:00");
        Partido p6 = new Partido(ldlcAsvelVilleurbane, anadoluEfes, "24/02/2023", "20:00");
        Partido p7 = new Partido(virtusBologna, baskonia, "24/02/2023", "20:30");
        Partido p8 = new Partido(valenciaBasket, olympiacos, "24/02/2023", "20:30");
        Partido p9 = new Partido(fcBarcelona, asMonaco, "24/02/2023", "20:45");

        // Guardar partidos
        partidosJornada = new Partido[]{p1, p2, p3, p4, p5, p6, p7, p8, p9};
    }

    @Override
    public Partido[] cargarPartidosJornada() {
        return partidosJornada;
    }
}