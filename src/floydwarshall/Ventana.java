package floydwarshall;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ventana extends JFrame implements ActionListener {

    int a = ingreso("Ingrese el numero de nodos");
    Container contenedor;
    JLabel[] etiqueta1;
    JLabel[] etiqueta2;
    JTextField[][] matriz;
    JTextArea mensaje;
    JTextArea mayor;
    JScrollPane scrollpane;
    JScrollPane scrollpanemayor;
    JButton aceptar;

    public Ventana() {
        matriz = new JTextField[a][a];
        etiqueta1 = new JLabel[a];
        etiqueta2 = new JLabel[a];
        contenedor = getContentPane();
        contenedor.setLayout(null);
        mayor = new JTextArea();
        scrollpanemayor = new JScrollPane(mayor);
        scrollpanemayor.setBounds(0, 0, 600, 474);
        scrollpanemayor.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpanemayor.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        for (int i = 0; i < a; i++) {
            etiqueta1[i] = new JLabel("" + (i + 1));
            etiqueta2[i] = new JLabel("" + (i + 1));
            etiqueta1[i].setBounds((int) ((i + 2.4) * 25), 25, 25, 25);
            etiqueta2[i].setBounds(21, (i + 2) * 25, 25, 25);
            mayor.add(etiqueta1[i]);
            mayor.add(etiqueta2[i]);
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = new JTextField();
                matriz[i][j].setBounds((j + 2) * 25, (i + 2) * 25, 25, 25);
                mayor.add(matriz[i][j]);
                matriz[i][j].addActionListener(this);
            }
        }
        int p = matriz[a - 1][a - 1].getX();
        int w = matriz[a - 1][a - 1].getY();
        aceptar = new JButton("Aceptar");
        aceptar.setBounds(p + 80, 40, 100, 30);
        aceptar.addActionListener(this);
        mensaje = new JTextArea();
        scrollpane = new JScrollPane(mensaje);
        scrollpane.setBounds(p + 80, 80, 300, 300);
        
        String cadena = new String("");
        for (int c = 0; c < (p / 2.9); c++) {
            cadena += " ";
        }
        mayor.setText(cadena);
        String cadena1 = new String("");
        for (int d = 0; d < (w / 14); d++) {
            cadena1 += "\n";
        }
        mayor.setText(cadena + cadena1);
        mensaje.setEditable(false);
        mayor.add(aceptar);
        mayor.add(scrollpane);
        mayor.setEditable(false);
        contenedor.add(scrollpanemayor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("INGRESO DE MATRIZ DE ADYACENCIA");
        setSize(607, 507);
        setLocationRelativeTo(null);
        show();
        matriz[0][0].grabFocus();
    }

    public int ingreso(String men) {
        boolean ban = true;
        int a = 0;
        int contador = 0;
        do {
            try {
                a = Integer.parseInt(JOptionPane.showInputDialog(null, men, "Entrada de  datos", JOptionPane.QUESTION_MESSAGE));
                ban = true;
                if (a <= 1 || a > 148) {
                    throw new Exception("Tamaño inválido");
                }
            } catch (NumberFormatException e) {
                contador++;
                JOptionPane.showMessageDialog(null, "Solo ingrese números enteros", "Error", JOptionPane.ERROR_MESSAGE);
                ban = false;
            } catch (Exception e) {
                contador++;
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ban = false;
            }
            if (contador == 3) {
                System.exit(0);
            }
        } while (ban == false);
        return a;
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == aceptar) {
            long adyacencia[][] = new long[a][a];

            try {
                for (int i = 0; i < a; i++) {
                    for (int j = 0; j < a; j++) {
                        if ((matriz[i][j].getText()).equals("")) {
                            throw new Exception("Faltaron valores por ingresar");
                        } else if (matriz[i][j].getText().contains("-")) {
                            throw new Exception("Los enlaces no pueden tener distancias negativas\n");
                        } else if (matriz[i][j].getText().equals("i")) {
                            adyacencia[i][j] = 999999999;
                        } else {
                            if (((matriz[j][j].getText()).contains("i"))) {
                                throw new Exception(" Los bucles no pueden ser infinitos\n");
                            } else if ((matriz[i][j].getText()).contains("1") || (matriz[i][j].getText()).contains("2") || (matriz[i][j].getText()).contains("3") || (matriz[i][j].getText()).contains("4") || (matriz[i][j].getText()).contains("5") || (matriz[i][j].getText()).contains("6") || (matriz[i][j].getText()).contains("7") || (matriz[i][j].getText()).contains("8") || (matriz[i][j].getText()).contains("9") || (matriz[i][j].getText()).contains("i")) {
                                adyacencia[i][j] = Long.parseLong(matriz[i][j].getText());
                            } else if (i == j && ((matriz[i][j].getText()).contains("0"))) {
                                adyacencia[i][j] = Long.parseLong(matriz[i][j].getText());
                            } else {
                                throw new Exception("Si va a ingresar letras, ingrese sólo la i\nLa i representa Infinito\nCeros solo en la diagonal principal");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                for (int k = 0; k < a; k++) {
                    for (int l = 0; l < a; l++) {
                        adyacencia[k][l] = 0;
                    }
                }
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                matriz[0][0].grabFocus();
            }

            Logica x = new Logica();
            String resultado = x.floyd(adyacencia);
            mensaje.setText(resultado);
        }
    }
}
