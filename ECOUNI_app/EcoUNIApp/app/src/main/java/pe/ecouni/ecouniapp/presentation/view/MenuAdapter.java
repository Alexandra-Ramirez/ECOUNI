package pe.ecouni.ecouniapp.presentation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.MenuItem;

public class MenuAdapter extends BaseAdapter {
    private Context context;

    public MenuAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MenuItem.ITEMS.length;
    }

    @Override
    public MenuItem getItem(int position) {
        return MenuItem.ITEMS[position];
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
            view = inflater.inflate(R.layout.grid_item, parent, false);
        }

        LinearLayout layout = view.findViewById(R.id.layout);
        ImageView iconMenu = view.findViewById(R.id.icon);
        TextView titleMenu = view.findViewById(R.id.title);

        final MenuItem item = getItem(position);

        layout.setBackgroundColor(context.getResources().getColor(item.getColor()));
        titleMenu.setText(item.getTitle());
        Glide.with(iconMenu.getContext())
                .load(item.getIdIcon())
                .into(iconMenu);
        return view;
    }
}
