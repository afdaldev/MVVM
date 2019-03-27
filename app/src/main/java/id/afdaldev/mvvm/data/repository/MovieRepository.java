package id.afdaldev.mvvm.data.repository;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import id.afdaldev.mvvm.data.model.Movie;
import id.afdaldev.mvvm.data.model.MovieResponse;
import id.afdaldev.mvvm.data.remote.ApiEndPoint;
import id.afdaldev.mvvm.data.remote.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static final String TAG = MovieRepository.class.getSimpleName();
    private static final String API_KEY = "678ef42a1b584848591cbd02ac3899c3";

    private MovieService movieService;
    private static MovieRepository sInstance;

    public MovieRepository(){

    }

    public ApiEndPoint getMovieService(){
        return MovieService.getInstance();
    }

    public synchronized static MovieRepository getInstance(){
        Log.d(TAG, "Getting the repository");
        if (sInstance == null){
            sInstance = new MovieRepository();
            Log.d(TAG, "Made New Repository");
        }

        return sInstance;
    }

    public LiveData<List<Movie>> getNowPlayingMovies(){
        final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();

        getMovieService().getNowPlayingMovies(API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        moviesLiveData.postValue(response.body().getResults());
                        Log.d(TAG, "Getting now playing movies");
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

        return moviesLiveData;
    }

}
