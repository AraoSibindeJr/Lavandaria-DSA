package src.AppExecutavel;

import src.UI_Telas.Tela_Cadastro;
import src.UI_Telas.Tela_Principal;

public class AppExecutavel {
    public static void main(String[] args) {
        Tela_Principal telaPrincipal = new Tela_Principal();
        telaPrincipal.getFrame().setVisible(false);
        new Tela_Cadastro(telaPrincipal);
    }
}
