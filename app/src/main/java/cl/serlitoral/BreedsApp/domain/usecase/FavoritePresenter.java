package cl.serlitoral.BreedsApp.domain.usecase;

import cl.serlitoral.BreedsApp.data.model.Favorite;

import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.presenter.iFavoritePresenterView;

import java.util.List;

public class FavoritePresenter {
    private iFavoritePresenterView presenter;
    private BreedRepository repository;

    public FavoritePresenter(iFavoritePresenterView favoriteView, BreedRepository repository) {
        this.presenter = favoriteView;
        this.repository = repository;
        repository.setFavPresenter(this);
        repository.downloadAllFavorites();
    }

    public void showFavorites(List<Favorite> listFavorites) {
        presenter.showFavorites(listFavorites);
    }
}
