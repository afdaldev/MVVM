package id.afdaldev.mvvm.utils;

import id.afdaldev.mvvm.base.ViewModelProviderFactory;
import id.afdaldev.mvvm.data.repository.MovieRepository;

public class InjectorUtils {

    private static MovieRepository provideRepository(){
        return MovieRepository.getInstance();
    }

    public static ViewModelProviderFactory viewModelProviderFactory(){
        MovieRepository repository = provideRepository();
        return new ViewModelProviderFactory(repository);
    }

}
