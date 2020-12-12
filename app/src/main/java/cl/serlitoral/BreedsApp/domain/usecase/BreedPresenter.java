package cl.serlitoral.BreedsApp.domain.usecase;

import android.util.Log;

import cl.serlitoral.BreedsApp.data.repository.iBreddRepositoryPresenter;
import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.presenter.iBreedPresenterView;

import java.util.List;

public class BreedPresenter implements iBreddRepositoryPresenter {
    private iBreedPresenterView presenter;
    private BreedRepository repository;

    public BreedPresenter(iBreedPresenterView viewBreed, BreedRepository repository) {
        this.presenter = viewBreed;
        this.repository = repository;
        repository.setBreedPresenter(this);
        repository.lodadBreedList();
    }

    @Override
    public void showBreed(List<String> breeds) {
        presenter.showBreed(breeds);
    }


}
