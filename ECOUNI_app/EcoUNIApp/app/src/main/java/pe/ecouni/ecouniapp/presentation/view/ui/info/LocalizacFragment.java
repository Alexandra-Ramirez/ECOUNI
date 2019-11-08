package pe.ecouni.ecouniapp.presentation.view.ui.info;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.ecouni.ecouniapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocalizacFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocalizacFragment extends Fragment {

    public LocalizacFragment() {
        // Required empty public constructor
    }

    public static LocalizacFragment newInstance() {
        return new LocalizacFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_localizac, container, false);
    }

}
