package pe.ecouni.ecouniapp.presentation.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.CalendItem;
import pe.ecouni.ecouniapp.presentation.model.ReciclajeDiaMes;

public class CalendarAdapter extends BaseAdapter {
    private static int T_CABEC = 0;
    private static int T_BLANK = 1;
    private static int T_DIA = 2;

    private Context context;

    //private int year,month;
    private int dayOfWeekFirst=0;

    private CalendItem[] cuadros;

    private static String[] cabs1 = {"L", "M", "M", "J", "V", "S", "D"};
    private static String[] cabs2 = {"D", "L", "M", "M", "J", "V", "S"};
    private String[] cabs;
    private int firstDayWeek;   //Sunday or Monday
    private int firstDayofMontDayOfWeek;

    public CalendarAdapter(Context context){
        this.context = context;

        Calendar calendar = Calendar.getInstance();
        firstDayWeek = calendar.getFirstDayOfWeek();
        if(calendar.getFirstDayOfWeek() == Calendar.SUNDAY)
            cabs = cabs2;
        else
            cabs = cabs1;

        setDays(calendar.get(Calendar.MONTH));
    }

    public void setCantidad(int month, List<ReciclajeDiaMes> reciclajeDiaMes){
        setDays(month);
        for (ReciclajeDiaMes reciclajeDia : reciclajeDiaMes){
            cuadros[reciclajeDia.getDia() + firstDayofMontDayOfWeek-1].setCantidad(reciclajeDia.getCant_dia());
        }
        notifyDataSetChanged();
    }

    private void setDays(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,1);

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        firstDayofMontDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - firstDayWeek;

        cuadros = new CalendItem[42];
        for (int i = 0; i < firstDayofMontDayOfWeek; i++){
            cuadros[i] = new CalendItem(0,0,false);
        }
        for(int i = firstDayofMontDayOfWeek, j = 1; i < maxDay + firstDayofMontDayOfWeek;i++,j++){
            cuadros[i] = new CalendItem(j,0,false);
        }
        for(int i = maxDay + firstDayofMontDayOfWeek;i<cuadros.length;i++){
            cuadros[i] = new CalendItem(0,0,false);
        }
    }

    //Retorina cantidad del dia seleccionado
    public int select(int position){
        if (getItemViewType(position)==T_DIA){
            for (int i=0;i<cuadros.length;i++)
                cuadros[i].setSelected(false);
            cuadros[position-7].setSelected(true);
            notifyDataSetChanged();
            return cuadros[position-7].getCantidad();
        }
        return -1;
    }

    @Override
    public int getCount() {
        return 49;  // 7 x 7
    }

    @Override
    public int getItemViewType(int position) {
        if(position<7){
            return T_CABEC;
        }else if(cuadros[position-7].getDia()==0){
            return T_BLANK;
        }else {
            return T_DIA;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;   // cabeceras, blancos y dias
    }

    @Override
    public Object getItem(int position) {
        if(getItemViewType(position)==T_DIA)
            return cuadros[position-7];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        int viewType = getItemViewType(position);

        if(viewType == T_CABEC){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_cal_cab_item, parent, false);
        }else if(viewType == T_BLANK){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_cal_blank_item,parent,false);
        }else{
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_cal_item,parent,false);
        }

        if(viewType == T_CABEC){
            TextView cab = view.findViewById(R.id.cab);
            cab.setText(cabs[position]);
        }
        if(viewType == T_DIA){
            LinearLayout fondo = view.findViewById(R.id.fondo);
            TextView dia = view.findViewById(R.id.dia);
            TextView cant = view.findViewById(R.id.cant);
            if (cuadros[position-7].isSelected())
                fondo.setBackground(context.getDrawable(R.drawable.background_selected));
            String diat_t = cuadros[position-7].getDia()+"";
            dia.setText(diat_t);
            String cant_t = cuadros[position-7].getCantidad()+"";
            cant.setText(cant_t);
        }
        return view;
    }
}
