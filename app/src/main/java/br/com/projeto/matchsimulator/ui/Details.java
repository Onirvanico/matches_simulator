package br.com.projeto.matchsimulator.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import br.com.projeto.matchsimulator.databinding.ActivityDetailsBinding;
import br.com.projeto.matchsimulator.domain.Match;

public class Details extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra(Extra.MATCH_KEY)) {
            Match match = getIntent().getParcelableExtra(Extra.MATCH_KEY);
            fillViews(match);

        }
    }

    private void fillViews(Match match) {

        getSupportActionBar().setTitle(match.getPlace().getName());
        binding.matchDescription.setText(match.getDescription());
        fillImage(binding.imagePlace, match.getPlace().getImage());
        binding.leftTeamName.setText(match.getLeftTeam().getName());
        binding.leftTeamScore.setText(String.valueOf(match.getLeftTeam().getScore()));
        binding.leftTeamImageRating.setRating(match.getLeftTeam().getStars());
        fillImage(binding.leftTeamImage, match.getLeftTeam().getImage());

        binding.rightTeamName.setText(match.getRightTeam().getName());
        binding.rightTeamScore.setText(String.valueOf(match.getRightTeam().getScore()));
        binding.rightTeamImageRating.setRating(match.getRightTeam().getStars());
        fillImage(binding.rightTeamImage, match.getRightTeam().getImage());
    }

    private void fillImage(AppCompatImageView image, String url) {
        Glide.with(this)
                .load(url)
                .into(image);
    }
}