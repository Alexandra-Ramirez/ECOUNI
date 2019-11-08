package pe.ecouni.ecouniapp.presentation.model;

public class CalendItem {
    private int dia;
    private int cantidad;
    private boolean selected;

    public CalendItem(int dia, int cantidad,boolean selected) {
        this.dia = dia;
        this.cantidad = cantidad;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public CalendItem() {
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
