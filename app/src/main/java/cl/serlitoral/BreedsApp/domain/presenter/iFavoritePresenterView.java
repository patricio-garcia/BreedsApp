package cl.serlitoral.BreedsApp.domain.presenter;

import cl.serlitoral.BreedsApp.data.model.Favorite;

import java.util.List;

public interface iFavoritePresenterView {



    void showFavorites(List<Favorite> listFavorites);
}
