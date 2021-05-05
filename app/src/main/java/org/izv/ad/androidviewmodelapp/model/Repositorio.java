package org.izv.ad.androidviewmodelapp.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repositorio {

    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService threadExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private Context context;
    private List<String> contactos = new ArrayList<>();
    private MutableLiveData<List<String>> liveContactos;

    public Repositorio(Context context) {
        this.context = context;
    }

    public LiveData<List<String>> getContactos() {
        if (liveContactos == null) {
            liveContactos = new MutableLiveData<List<String>>();
            loadContactos();
        }
        return liveContactos;
    }

    private Cursor getCursorContactos() {
        //select * from ContactsContract.Contacts.CONTENT_URI
        return context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, // tabla
                null, // *
                null, // where
                null, // parámetros
                null); // order by
    }

    public List<String> getMetaDatos() {
        //opción 1
        /*List<String> columnas = new ArrayList<>();
        int numeroColumnas = cursor.getColumnCount();
        for (int i = 0; i < numeroColumnas; i++) {
            columnas.add(cursor.getColumnName(i));
        }
        return columnas;*/
        //opción 2
        return Arrays.asList(getCursorContactos().getColumnNames());
    }

    private void loadContactos() {
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String content, value;
                List<String> data = new ArrayList<>();
                Cursor cursor = getCursorContactos();
                while(cursor.moveToNext()) {
                    content = "";
                    //opción 1
                    String[] columnas = cursor.getColumnNames();
                    for (String s: columnas) {
                        if(s.equals("display_name") || s.equals("display_name_alt") ) {
                            value = cursor.getString(cursor.getColumnIndex(s));
                            if(value != null) {
                                content += s + ": " + value;
                            }
                        }
                    }
                    //opción 2
                    /*int numeroColumnas = cursor.getColumnCount();
                    for (int i = 0; i < numeroColumnas; i++) {
                        if(cursor.getColumnName(i).equals("display_name") || cursor.getColumnName(i).equals("display_name_alt")) {
                            value = cursor.getString(i);
                            if(value != null) {
                                content += cursor.getColumnName(i) + ": " + value;
                            }
                        }
                    }*/
                    data.add(content);
                    liveContactos.postValue(data);
                }
            }
        });
    }
}