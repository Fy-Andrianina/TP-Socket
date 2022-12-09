package view;

import javax.swing.JButton;

public class Bouton extends JButton {
    String fichier;

    public Bouton(String text) {
        super(text);
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
}
