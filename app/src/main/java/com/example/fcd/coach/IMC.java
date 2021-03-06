package com.example.fcd.coach;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

public class IMC extends ActionBarActivity {

    private Integer minFemme = 15 ; // maigreur si en dessous
    private Integer maxFemme = 30 ; // graisse si au dessus
    private Integer minHomme = 10 ; // maigreur si en dessous
    private Integer maxHomme = 25 ; // graisse si au dessus
    private Integer sexe =0 ; // 0 pour femme, 1 pour homme

    private void ecouteRadio() {
        ((RadioGroup)findViewById(R.id.grpRadioSexe)).setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (((RadioButton)findViewById(R.id.rdHomme)).isChecked()) {
                    Toast.makeText(IMC.this, "Homme", Toast.LENGTH_SHORT).show();
                    sexe=1;
                 } else {
                    Toast.makeText(IMC.this, "Femme", Toast.LENGTH_SHORT).show();
                    sexe=0;
                 }
            }
                                                                                                                        }
        );
    }

    private float calcIMG(String Poid, String  Taille, String Age ){
        float IMG;
        float fPoid = Float.parseFloat(Poid); //((int) txtTaille);
        float fTaille = Float.parseFloat(Taille)/100; //((int) txtTaille);
        float fAge = Float.parseFloat(Age); //((int) txtTaille);
        IMG = (float) (1.2 * fPoid / (fTaille * fTaille) + (0.23 * fAge) - (10.8 * sexe) - 5.4);
        Log.d("calcul img", "" + IMG);
        return IMG;

    }
    public void resultatIMG(float img)
    {
        //resIMG();
        //int cj =0;
        //float cm =0;
        int re =0;
        //cj=sexe;
        //cm=img;
        switch (sexe) {
            case 0:
                if (img < minFemme) re= 0;
                if ((img > minFemme )&& (img < maxFemme)) re= 1;
                if (img > maxFemme) re= 2;
            case 1:
                if (img < minHomme) re= 0;
                if ((img > minHomme )&& (img < maxHomme)) re= 1;
                if (img > maxHomme) re= 2;
                break;
        }
        afficheresultatIMG(re, img);
    }

    public void afficheresultatIMG(int r, float img)
    {
        TextView lblIMG = (TextView) findViewById(R.id.lblIMG);
        ImageView imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        if (r==0) {
            lblIMG.setTextColor(Color.RED);
            lblIMG.setText(String.format("%.01f", img) + "% : IMG trop faible");
            imgSmiley.setImageResource(R.drawable.maigre);
        }
        if (r==1) {
            lblIMG.setTextColor(Color.GREEN);
            lblIMG.setText(String.format("%.01f", img) + "% : IMG normal");
            imgSmiley.setImageResource(R.drawable.normal);
        }

        if (r==2) {
            lblIMG.setTextColor(Color.RED);
            lblIMG.setText(String.format("%.01f", img) + "% : IMG trop ELEVE");
            //imgSmiley.setImageResource(R.drawable.graisse);
        }
    }
    private void ecouteCalcul() {
        ((Button)findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
            private float aFloat;

            public void onClick(View v) {
                String txtPoids = ((EditText)findViewById(R.id.txtPoids)).getText().toString();
                String txtTaille = ((EditText)findViewById(R.id.txtTaille)).getText().toString();
                String txtAge = ((EditText)findViewById(R.id.txtAge)).getText().toString();
                //TextView lblIMG = (TextView) findViewById(R.id.lblIMG);
               //imgSmiley.setImageResource(R.drawable.normal);
                float img;
                if ((!(txtPoids.equals(""))) && (!(txtTaille.equals(""))) && (!(txtAge.equals("")))) {
                // calcul

                    calcIMG(txtPoids,txtTaille,txtAge);
                    img = calcIMG(txtPoids,txtTaille,txtAge);
                    resultatIMG(img);
                        //imgSmiley.setImageResource(R.drawable.normal);
                                                                                                    }
                else{
                    Toast.makeText(IMC.this, "Veuillez saisir tous les champs !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
            // appel ecouter radio
            this.ecouteRadio();
            this.ecouteCalcul();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_imc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
