package id.afdaldev.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.afdaldev.mvvm.adapter.MovieRecyclerViewAdapter;
import id.afdaldev.mvvm.base.ViewModelProviderFactory;
import id.afdaldev.mvvm.data.model.Movie;
import id.afdaldev.mvvm.ui.MovieViewModel;
import id.afdaldev.mvvm.utils.InjectorUtils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_movie);
        progressBar = findViewById(R.id.pb_movie);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);

        ViewModelProviderFactory factory = InjectorUtils.viewModelProviderFactory();
        MovieViewModel viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel.class);


        viewModel.getNowPlayingMovies().observe(this, movies -> {
            adapter = new MovieRecyclerViewAdapter(this, movies);
            recyclerView.setAdapter(adapter);
            for (Movie movie : movies){
                Log.d(TAG, movie.getTitle());
            }
        });

        viewModel.getShowLoading().observe(this, aBoolean -> {
            if (aBoolean){
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }
}