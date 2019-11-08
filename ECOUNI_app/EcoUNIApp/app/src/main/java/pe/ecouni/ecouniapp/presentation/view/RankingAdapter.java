package pe.ecouni.ecouniapp.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.RankingItem;

public class RankingAdapter extends ArrayAdapter {
    private Context context;
    private List<RankingItem> items;

    public RankingAdapter(@NonNull Context context) {
        super(context,-1);
        this.context = context;
        //items = Arrays.asList(RankingItem.ITEMS);
        items = new ArrayList<>();
    }

    public RankingAdapter(@NonNull Context context,List<RankingItem> items) {
        super(context,-1);
        this.items = items;
    }

    public void setItems(List<RankingItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public RankingItem getItem(int position) {
        return items.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_ranking_item, parent, false);
        }

        CardView cardView = view.findViewById(R.id.cardview);
        ImageView foto = view.findViewById(R.id.foto);
        TextView nombre = view.findViewById(R.id.nombre);
        TextView facultad = view.findViewById(R.id.facultad);
        TextView cantidad = view.findViewById(R.id.cantidad);
        TextView ranking = view.findViewById(R.id.ranking);

        final RankingItem item = getItem(position);

        cardView.setCardBackgroundColor(context.getResources().getColor(colores[position%10]));
        nombre.setText(item.getNombre());
        facultad.setText(item.getFacultad());
        cantidad.setText(item.getCantidad());
        ranking.setText(item.getRanking());

        if(item.getUrlFoto()!=null && !item.getUrlFoto().isEmpty()) {
            Uri uri = Uri.parse(context.getString(R.string.server_url)+"public/images/"+item.getUrlFoto());
            Glide.with(foto.getContext())
                    .load(uri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(foto);
            return view;
        }else {
            Glide.with(foto.getContext())
                    .load(R.drawable.default_avatar)
                    .apply(RequestOptions.circleCropTransform())
                    .into(foto);
            return view;
        }
    }

    private static int colores[] = {
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6,
            R.color.color7,
            R.color.color8,
            R.color.color9,
            R.color.color10
    };
}
