package floydwarshall;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FloydWarshall {
    public static void main(String args[]) {
        try { 
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Ventana miVentana;
        Logica logica;

        miVentana = new Ventana();
        logica = new Logica();
    }
}