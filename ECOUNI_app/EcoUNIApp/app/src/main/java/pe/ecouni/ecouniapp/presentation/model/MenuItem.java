package pe.ecouni.ecouniapp.presentation.model;

import pe.ecouni.ecouniapp.R;

public class MenuItem {
    private String title;
    private int idIcon;
    private int color;

    public MenuItem() {
    }

    public MenuItem(String title,int idIcon,int color) {
        this.title = title;
        this.idIcon = idIcon;
        this.color = color;
    }

    public int getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(int idIcon) {
        this.idIcon = idIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId(){
        return title.hashCode();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static MenuItem[] ITEMS= {
        new MenuItem("Perfil", R.drawable.ic_person_black_24dp,R.color.menuPerfil),
        new MenuItem("Reciclar",R.drawable.recicle_icon,R.color.menuReciclar),
        new MenuItem("Premios",R.drawable.gift_icon,R.color.menuPremios),
        new MenuItem("Puntos",R.drawable.ic_stars_black_24dp,R.color.menuPuntos),
        new MenuItem("Feedback",R.drawable.ic_chat_bubble_outline_black_24dp,R.color.menuFeedback),
        new MenuItem("Info",R.drawable.ic_info_outline_black_24dp,R.color.menuInfo)
    };

    public static MenuItem getItem(int id){
        for (MenuItem item:ITEMS){
            if (item.getId() == id)
                return item;
        }
        return null;
    }

}
