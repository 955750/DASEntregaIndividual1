package com.example.dasentregaindividual1.jornada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dasentregaindividual1.R;

public class ListaJornadasActivity extends AppCompatActivity {
    
    private RecyclerView jornadasRecyclerView;
    private ListaJornadasAdapter listaJornadasAdapter;

    // De momento los 9 primeros equipos
    private int[] escudosEquiposLocales;
    private String[] nombresEquiposLocales;
    private String[] ultimosPartidosEquiposLocales;

    // De momento los 9 últimos equipos
    private int[] escudosEquiposVisitantes;
    private String[] nombresEquiposVisitantes;
    private String[] ultimosPartidosEquiposVisitantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jornadas);
        
        jornadasRecyclerView = (RecyclerView) findViewById(R.id.jornadas_recycler_view);

        escudosEquiposLocales = new int[] {
                R.drawable.escudo_alba_berlin,
                R.drawable.escudo_anadolu_efes,
                R.drawable.escudo_as_monaco,
                R.drawable.escudo_baskonia,
                R.drawable.escudo_bayern_munich,
                R.drawable.escudo_crvena_zvezda,
                R.drawable.escudo_emporio_armani_milan,
                R.drawable.escudo_fc_barcelona,
                R.drawable.escudo_fenerbahce
        };
        nombresEquiposLocales = new String[] {
                "Alba Berlin",
                "Anadolu Efes",
                "AS Monaco",
                "Baskonia",
                "Bayern Munich",
                "Crvena Zvezda",
                "Emporio Armani Milan",
                "FC Barcelona",
                "Fenerbahce"
        };
        ultimosPartidosEquiposLocales = new String[] {
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0"
        };

        escudosEquiposVisitantes = new int[] {
                R.drawable.escudo_ldlc_asvel_villeurbanne,
                R.drawable.escudo_maccabi_tel_aviv,
                R.drawable.escudo_olympiacos,
                R.drawable.escudo_panathinaikos,
                R.drawable.escudo_partizan_belgrade,
                R.drawable.escudo_real_madrid,
                R.drawable.escudo_valencia_basket,
                R.drawable.escudo_virtus_bologna,
                R.drawable.escudo_zalgiris_kaunas
        };
        nombresEquiposVisitantes = new String[] {
                "LDLC Asvel Villeurbanne",
                "Maccabi Tel Aviv",
                "Olympiacos",
                "Panathinaikos",
                "Partizan Belgrade",
                "Real Madrid",
                "Valencia Basket",
                "Virtus Bologna",
                "Zalgiris Kaunas"
        };
        ultimosPartidosEquiposVisitantes = new String[] {
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0",
                "10 - 0"
        };

        listaJornadasAdapter = new ListaJornadasAdapter(
                escudosEquiposLocales,
                nombresEquiposLocales,
                ultimosPartidosEquiposLocales,
                escudosEquiposVisitantes,
                nombresEquiposVisitantes,
                ultimosPartidosEquiposVisitantes
        );
        jornadasRecyclerView.setAdapter(listaJornadasAdapter);
    }
}