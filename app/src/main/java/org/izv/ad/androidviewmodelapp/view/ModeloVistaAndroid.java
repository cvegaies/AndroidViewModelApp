package org.izv.ad.androidviewmodelapp.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.izv.ad.androidviewmodelapp.model.Repositorio;

import java.util.List;

public class ModeloVistaAndroid extends AndroidViewModel {

    private Repositorio repositorio;
    private LiveData<List<String>> liveContactos;

    public ModeloVistaAndroid(@NonNull Application application) {
        super(application);
        repositorio = new Repositorio(application);
        liveContactos = repositorio.getContactos();
    }

    public LiveData<List<String>> getContactos() {
        return liveContactos;
    }

    public List<String> getMetaData() {
        return repositorio.getMetaDatos();
    }
}