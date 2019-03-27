package id.afdaldev.mvvm.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import id.afdaldev.mvvm.data.repository.MovieRepository;
import id.afdaldev.mvvm.ui.MovieViewModel;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieRepository repository;

    public ViewModelProviderFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)){
            return (T) new MovieViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class : " + modelClass);
    }

}
