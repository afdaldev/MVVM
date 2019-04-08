package id.afdaldev.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import id.afdaldev.mvvm.data.model.Movie;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mDetailTitle, mReleaseDate, mPopularity, mVoteCount, mVoteAverage, mOverview;
    private ImageView mImgDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mImgDetail = findViewById(R.id.img_detail_movie);
        mDetailTitle = findViewById(R.id.tv_detail_title);
        mReleaseDate = findViewById(R.id.tv_release_date);
        mPopularity = findViewById(R.id.tv_popularity);
        mVoteCount = findViewById(R.id.tv_vote_count);
        mVoteAverage = findViewById(R.id.tv_vote_average);
        mOverview = findViewById(R.id.tv_overview);

        Movie movie = getIntent().getParcelableExtra("movie");

        String base = "https://image.tmdb.org/t/p/w185";
        Picasso.get()
                .load(base + movie.getPosterPath()).into(mImgDetail);
        mDetailTitle.setText(movie.getTitle());
        mReleaseDate.setText(movie.getReleaseDate());
        mPopularity.setText(movie.getPopularity());
        mVoteCount.setText(Integer.parseInt(String.format(String.valueOf(movie.getVoteCount()))));

        mOverview.setText(movie.getOverview());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
