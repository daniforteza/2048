package com.example.a2048.actividades;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.a2048.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class AcercaDeActivity extends AppCompatActivity {

    FloatingActionButton btnInicio;
    RelativeLayout layoutFondo;
    ImageButton icoYoutube,icoFace,icoInstagram,icoTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        layoutFondo=findViewById(R.id.idLayoutFondo);
        icoYoutube=findViewById(R.id.icoYoutube);
        icoFace=findViewById(R.id.icoFace);
        icoInstagram=findViewById(R.id.icoInstagram);
        icoTwitter=findViewById(R.id.icoTwitter);



        Drawable shape = (Drawable) layoutFondo.getBackground();

    }

    public void onClick(View view) {
        Intent miIntent=null;
        boolean error=false;
        switch (view.getId()){
            case R.id.icoYoutube: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCwFI7epHgTBY477PnA_2AqA"));
                break;
            case R.id.icoFace: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://ca-es.facebook.com/"));
                break;
            case R.id.icoTwitter: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/twitter"));
                break;
            case R.id.icoInstagram: miIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/daniforteza25/"));
                break;

        }
        if (error==false){
            startActivity(miIntent);
        }

    }
}