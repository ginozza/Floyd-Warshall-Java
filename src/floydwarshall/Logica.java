package floydwarshall;

import javax.swing.JOptionPane;

public class Logica {
    public void setVentana(Ventana miVentana) {
    }

    public String floyd(long[][] adyacencia) {
        int n = adyacencia.length;
        long D[][] = adyacencia;

        String enlaces[][] = new String[n][n];
        String[][] aux_enlaces = new String[adyacencia.length][adyacencia.length];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                enlaces[i][j] = "";
                aux_enlaces[i][j] = "";
            }
        }
        
        String enl_rec = "";
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    float aux = D[i][j];
                    float aux2 = D[i][k];
                    float aux3 = D[k][j];
                    float aux4 = aux2 + aux3;
                    float res = Math.min(aux, aux4);
                    if (aux != aux4) {
                        if (res == aux4) {
                            enl_rec = "";
                            aux_enlaces[i][j] = k + "";
                            enlaces[i][j] = enlaces(i, k, aux_enlaces, enl_rec) + (k + 1);
                        }
                    }
                    D[i][j] = (long) res;
                }
            }
        }

        String cadena = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cadena += D[i][j] + " ";
            }
            cadena += "\n";
        }

        String enlacesres = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (enlaces[i][j].equals("") == true) {
                        enlacesres += " De ( " + (i + 1) + " a " + (j + 1) + " ) = " + "( " + (i + 1) + " , " + (j + 1) + " )" + "\n";
                    } else {
                        enlacesres += " De ( " + (i + 1) + " a " + (j + 1) + " ) = ( " + (i + 1) + " , " + enlaces[i][j] + " , " + (j + 1) + ")\n";
                    }
                }
            }
        }

        return "Las distancias minimas entre nodos son: \n" + cadena + "\nLos caminos minimos entre nodos son:\n" + enlacesres;
    }

    public String enlaces(int i, int k, String[][] aux_enlaces, String enl_rec) {
        if (aux_enlaces[i][k].equals("") == true) {
            return "";
        } else {
            enl_rec += enlaces(i, Integer.parseInt(aux_enlaces[i][k].toString()), aux_enlaces, enl_rec) + (Integer.parseInt(aux_enlaces[i][k].toString()) + 1) + " , ";
            return enl_rec;
        }
    }
}
