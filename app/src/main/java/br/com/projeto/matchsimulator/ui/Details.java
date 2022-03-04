package br.com.projeto.matchsimulator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.projeto.matchsimulator.databinding.ActivityDetailsBinding;

public class Details extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}