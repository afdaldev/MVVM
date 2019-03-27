package id.afdaldev.mvvm.ui;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import id.afdaldev.mvvm.base.BaseViewModel;
import id.afdaldev.mvvm.data.model.Movie;
import id.afdaldev.mvvm.data.repository.MovieRepository;

public class MovieViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> showLoading;


    public MovieViewModel(MovieRepository repository) {
        super(repository);
        showLoading = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getNowPlayingMovies(){
        showLoading.setValue(true);

        final MediatorLiveData<List<Movie>> mediatorLiveData = new MediatorLiveData<>();
        mediatorLiveData.addSource(getRepository().getNowPlayingMovies(), movies -> {
            mediatorLiveData.postValue(movies);
            showLoading.setValue(false);
        });
        return mediatorLiveData;
    }

    public LiveData<Boolean> getShowLoading() {
        return showLoading;
    }

}
