package cl.serlitoral.BreedsApp.domain.usecase;

import cl.serlitoral.BreedsApp.data.repository.iBreddRepositoryPresenter;
import cl.serlitoral.BreedsApp.data.repository.BreedRepository;
import cl.serlitoral.BreedsApp.domain.presenter.iBreedPresenterView;

import java.util.List;

public class ImagePresenter implements iBreddRepositoryPresenter {

        private iBreedPresenterView presenter;
        private BreedRepository repository;
        private String breed;

        public ImagePresenter(iBreedPresenterView viewBreed, BreedRepository repository, String pBreed) {
            this.presenter = viewBreed;
            this.repository = repository;
            this.breed=pBreed;
            repository.setImagePresenter(this);
            repository.loadBreedPictures(breed);
        }

        @Override
        public void showBreed(List<String> breeds) {
            presenter.showBreed(breeds);
        }

        public void addFavorite(String image, String pBreed){
            repository.loadNewFavorite(image,pBreed);
        }
    }

