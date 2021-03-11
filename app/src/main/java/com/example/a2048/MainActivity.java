package com.example.a2048;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a2048.Game.GameActivity;
import com.example.a2048.actividades.AcercaDeActivity;
import com.example.a2048.actividades.ContenedorInstruccionesActivity;
import com.example.a2048.fragments.InicioFragment;
import com.example.a2048.fragments.RankingFragment;
import com.example.a2048.interfaces.IComunicaFragments;
import com.example.a2048.scores.ScoreDisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener, IComunicaFragments {
    public Button startGame;

    Fragment fragmentInicio,registroJugadorFragment,gestionJugadorFragment,rankingFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentInicio = new InicioFragment();
        rankingFragment = new RankingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
        startGame = findViewById(R.id.cardJugar);
    }

    public void onFragmentInteraction(Uri uri) {
    }

    /*@Override
    public void mostrarMenu() {
        Utilidades.obtenerListaAvatars();//llena la lista de avatars para ser utilizada
        Utilidades.consultarListaJugadores(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
    }*/

    @Override
    public void iniciarJuego(String username) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }


    @Override
    public void llamarAjustes() {
          Toast.makeText(getApplicationContext(),"IN PROGRESS",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void consultarRanking() {
       Intent intent = new Intent(this, ScoreDisplay.class);
       startActivity(intent);
    }

    @Override
    public void consultarInstrucciones() {
        Intent intent=new Intent(this, ContenedorInstruccionesActivity.class);
        startActivity(intent);
    }

    @Override
    public void gestionarUsuario() {
        Toast.makeText(getApplicationContext(),"IN PROGRESS",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void consultarInformacion() {
        Intent intent=new Intent(this, AcercaDeActivity.class);
        startActivity(intent);
    }
}