package id.afdaldev.mvvm.base;

import androidx.lifecycle.ViewModel;
import id.afdaldev.mvvm.data.repository.MovieRepository;

public class BaseViewModel extends ViewModel {

    private MovieRepository repository;

    public BaseViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    protected MovieRepository getRepository(){
        return repository;
    }


}
