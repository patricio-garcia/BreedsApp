package cl.serlitoral.BreedsApp.domain.usecase;

import android.util.Log;

import cl.serlitoral.BreedsApp.data.repository.iBreddRepositoryPresenter;
import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.presenter.iBreedPresenterView;

import java.util.List;

public class ImagePresenter implements iBreddRepositoryPresenter {

        private static final String TAG = "InfoLog";
        private iBreedPresenterView presenter;
        private BreedRepository repository;
        private String breed;

        public ImagePresenter(iBreedPresenterView viewBreed, BreedRepository repository, String pBreed) {
            this.presenter = viewBreed;
            this.repository = repository;
            this.breed=pBreed;
            Log.d(TAG, "PicturePresenter: seteando el presentador del repositorio");
            repository.setImagePresenter(this);
            Log.d(TAG, "PicturePresenter: llamando al método loadBreedList");
            repository.loadBreedPictures(breed);
        }

        @Override
        public void showBreed(List<String> breeds) {
            Log.d(TAG, "showBreed: llamando a ShowBreed en ImagePresenter"+breeds);
            presenter.showBreed(breeds);
        }

        public void addFavorite(String pPicture, String pBreed){
            repository.loadNewFavorite(pPicture,pBreed);
        }
    }

