package org.izv.ad.androidviewmodelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import org.izv.ad.androidviewmodelapp.view.ModeloVistaAndroid;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        TextView tvText = findViewById(R.id.tvText);
        tvText.setText("");
        //ModeloVistaAndroid viewModel = new ViewModelProvider(this).get(ModeloVistaAndroid.class);
        ModeloVistaAndroid viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ModeloVistaAndroid.class);
        viewModel.getContactos().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> contactos) {
                for(String s: contactos) {
                    tvText.append(s + "\n");
                }
                tvText.append("last: " + contactos.size() + ": " + contactos.get(contactos.size() - 1) + "\n");
            }
        });
    }
}