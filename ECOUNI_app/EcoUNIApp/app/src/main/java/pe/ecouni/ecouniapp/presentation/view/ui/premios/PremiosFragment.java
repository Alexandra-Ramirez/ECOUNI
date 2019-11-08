package pe.ecouni.ecouniapp.presentation.view.ui.premios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.view.PremiosAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class PremiosFragment extends Fragment {

    public static PremiosFragment newInstance() {
        return new PremiosFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_premios, container, false);

        GridView gridView = root.findViewById(R.id.gridview);
        PremiosAdapter adapter = new PremiosAdapter(getContext());
        gridView .setAdapter(adapter);

        return root;
    }
}