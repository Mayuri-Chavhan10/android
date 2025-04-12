package com.example.googlemap;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googlemap.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Hide map initially (optional, if layout shows it)
        // Show the button
        binding.showMapButton.setVisibility(View.VISIBLE);

        binding.showMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.showMapButton.setVisibility(View.GONE); // hide button after click

                // Load MapFragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MapFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
