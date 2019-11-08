package pe.ecouni.ecouniapp.presentation.model;

import pe.ecouni.ecouniapp.R;

public class PremiosItem {
    private String descr;
    private String puntos;
    private int idFoto;

    public PremiosItem() {
    }

    public PremiosItem(String descr, String puntos, int idFoto) {
        this.descr = descr;
        this.puntos = puntos;
        this.idFoto = idFoto;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getId(){
        return descr.hashCode()+puntos.hashCode();
    }

    public static PremiosItem[] ITEMS= {
        new PremiosItem("Llaveros x1","50", R.drawable.premio_1),
        new PremiosItem("USB","120",R.drawable.premio_2),
        new PremiosItem("Polera","300",R.drawable.premio_3),
        new PremiosItem("Semi beca","1000",R.drawable.premio_4)
    };

    public static PremiosItem getItem(int id){
        for (PremiosItem item:ITEMS){
            if (item.getId() == id)
                return item;
        }
        return null;
    }

}
