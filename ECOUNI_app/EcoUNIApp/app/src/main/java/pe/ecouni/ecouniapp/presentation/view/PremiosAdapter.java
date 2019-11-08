package pe.ecouni.ecouniapp.presentation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.MenuItem;
import pe.ecouni.ecouniapp.presentation.model.PremiosItem;

public class PremiosAdapter extends BaseAdapter {
    private Context context;

    public PremiosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return PremiosItem.ITEMS.length;
    }

    @Override
    public PremiosItem getItem(int position) {
        return PremiosItem.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_premio_item, parent, false);
        }

        ImageView fotoPremio = view.findViewById(R.id.foto);
        TextView descrPremio = view.findViewById(R.id.descr);
        TextView puntosPremio = view.findViewById(R.id.puntos);

        final PremiosItem item = getItem(position);

        descrPremio.setText(item.getDescr());
        puntosPremio.setText(item.getPuntos());
        Glide.with(fotoPremio.getContext())
                .load(item.getIdFoto())
                .into(fotoPremio);
        return view;
    }
}
