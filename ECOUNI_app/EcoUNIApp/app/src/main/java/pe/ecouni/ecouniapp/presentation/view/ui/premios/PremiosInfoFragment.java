package pe.ecouni.ecouniapp.presentation.view.ui.premios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import pe.ecouni.ecouniapp.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class PremiosInfoFragment extends Fragment {

    public static PremiosInfoFragment newInstance() {
        return new PremiosInfoFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_premios_info, container, false);
    }
}