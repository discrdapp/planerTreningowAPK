package com.example.zadanie1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int aktualneZdjId = 0;
    int lacznaLiczbaSerii = 0;
    int aktualnaSeria = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void LosujCwiczeniePrzycisk(View v) {
        Random Losowa = new Random();

        int Wylosowana = Losowa.nextInt(6);

        String[] cwiczenia = {"Przysiad", "Pompki", "Plank", "Wykroki", "Burpees", "Podciąganie"};
        int[] zdjecia = {R.drawable.cwiczenie1, R.drawable.cwiczenie2, R.drawable.cwiczenie3, R.drawable.cwiczenie4, R.drawable.cwiczenie5, R.drawable.cwiczenie6};

        aktualneZdjId = Wylosowana;

        ImageView img = findViewById(R.id.cwiczenieImg);
        img.setImageResource(zdjecia[Wylosowana]);

        TextView cwiczenieTekst = findViewById(R.id.tekstCwiczenie);
        cwiczenieTekst.setText(cwiczenia[Wylosowana]);
    }

    public void przyciskPlus(View v) {
        aktualnaSeria++;
        updateAktualnaTekst();
    }

    public void przyciskMinus(View v) {
        if (aktualnaSeria - 1 >= 0) {
            aktualnaSeria--;
        }
        updateAktualnaTekst();
    }

    public void updateAktualnaTekst() {
        TextView aktualnaTekst = findViewById(R.id.aktualnaTekst);

        aktualnaTekst.setText("" + aktualnaSeria);
    }

    public void nastepneCwiczenie(View v) {
        if (aktualneZdjId != 0) {
            lacznaLiczbaSerii += aktualnaSeria;
            aktualnaSeria = 0;
            LosujCwiczeniePrzycisk(null);
            updateAktualnaTekst();
            updateLacznaTekst();
        }
    }

    public void updateLacznaTekst() {
        TextView lacznaTekst = findViewById(R.id.lacznaTekst);

        lacznaTekst.setText("Łączna liczba serii: " + lacznaLiczbaSerii);
    }

    public void resetuj(View v) {
        lacznaLiczbaSerii = 0;
        aktualnaSeria = 0;
        aktualneZdjId = 0;

        TextView cwiczenieTekst = findViewById(R.id.tekstCwiczenie);
        cwiczenieTekst.setText("Losuj ćwiczenie");

        ImageView img = findViewById(R.id.cwiczenieImg);
        img.setImageResource(R.drawable.cwiczenie0);
        updateAktualnaTekst();
        updateLacznaTekst();
    }
}