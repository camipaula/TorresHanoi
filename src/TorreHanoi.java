import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class TorreHanoi extends JFrame {
    private JTextArea torreA;
    private JPanel panel1;
    private JTextArea torreB;
    private JTextArea torreC;
    private JButton bButtonAB;
    private JButton aButtonBA;
    private JButton bButton1CB;
    private JButton cButtonAC;
    private JButton cButton1BC;
    private JButton aButton1CA;
    private JComboBox comboBox1;
    private JButton iniciarButton;
    private JButton reiniciarButton;
    private JButton resolverButton;
    private JTextField numMinimodeMovimientos;
    private JTextField numMovimientos;

    private Stack<String> torreA1 = new Stack();
    private Stack<String> torreB1 = new Stack();
    private Stack<String> torreC1 = new Stack();
    private int numMov = 0;
    private Funciones f = new Funciones();

    public TorreHanoi(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                torreA.setText("");
                torreB.setText("");
                torreC.setText("");

                //LLENAR TORRE A
                int numDiscos = Integer.parseInt(comboBox1.getSelectedItem().toString());
                f.TorreA(numDiscos, torreA1);
                for (int i = numDiscos - 1; i >= 0; i--) {
                    torreA.setText(torreA.getText() + "\n" + torreA1.get(i));
                }

                //Numero minimo de movimientos
                numMinimodeMovimientos.setText(String.valueOf(f.numMinMov(numDiscos)));
            }
        });
        bButtonAB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!torreA1.isEmpty()) {
                    if (torreB1.isEmpty() || torreA1.peek().compareTo(torreB1.peek()) <= -1) {
                        numMov = numMov + 1;
                        numMovimientos.setText(String.valueOf(numMov));


                        //cambiar de torre
                        f.cambiarDeTorre(torreA1, torreB1);
                        cambiartextos(torreA, torreB, torreA1, torreB1);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }

            }
        });

        //boton de torre A a torre C
        cButtonAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!torreA1.isEmpty()) {
                    if (torreC1.isEmpty() || torreA1.peek().compareTo(torreC1.peek()) <= -1) {
                        numMov = numMov + 1;
                        numMovimientos.setText(String.valueOf(numMov));


                        f.cambiarDeTorre(torreA1, torreC1);
                        cambiartextos(torreA, torreC, torreA1, torreC1);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }

        });
        aButtonBA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!torreB1.isEmpty()) {
                    numMov = numMov + 1;
                    numMovimientos.setText(String.valueOf(numMov));
                    if (torreA1.isEmpty() || torreB1.peek().compareTo(torreA1.peek()) <= -1) {


                        //cambiar de torre b a torre a
                        f.cambiarDeTorre(torreB1, torreA1);
                        cambiartextos(torreB, torreA, torreB1, torreA1);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });


        aButton1CA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!torreC1.isEmpty()) {
                    if (torreA1.isEmpty() || torreC1.peek().compareTo(torreA1.peek()) <= -1) {
                        numMov = numMov + 1;
                        numMovimientos.setText(String.valueOf(numMov));

                        //cambiar de torre c a torre a
                        f.cambiarDeTorre(torreC1, torreA1);
                        cambiartextos(torreC, torreA, torreC1, torreA1);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });

        cButton1BC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!torreB1.isEmpty()) {
                    if (torreC1.isEmpty() || torreB1.peek().compareTo(torreC1.peek()) <= -1) {
                        numMov = numMov + 1;
                        numMovimientos.setText(String.valueOf(numMov));

                        f.cambiarDeTorre(torreB1, torreC1);
                        cambiartextos(torreB, torreC, torreB1, torreC1);


                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });


        bButton1CB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!torreC1.isEmpty()) {
                    if (torreB1.isEmpty() || torreC1.peek().compareTo(torreB1.peek()) <= -1) {
                        numMov = numMov + 1;
                        numMovimientos.setText(String.valueOf(numMov));

                        f.cambiarDeTorre(torreC1, torreB1);
                        cambiartextos(torreC, torreB, torreC1, torreB1);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.limpiar(torreA1, torreB1, torreC1);
                numMov = 0;
                torreA.setText("");
                torreB.setText("");
                torreC.setText("");
                numMinimodeMovimientos.setText("");
                numMovimientos.setText("");
            }
        });


        resolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numDiscos = Integer.parseInt(comboBox1.getSelectedItem().toString());
                if (torreA1.size() == numDiscos) {
                    f.resolver(Integer.parseInt(comboBox1.getSelectedItem().toString()), torreA1, torreB1, torreC1);
                    numMovimientos.setText(String.valueOf(String.format("%.0f", f.numMinMov(Integer.parseInt(comboBox1.getSelectedItem().toString())))));
                    cambiartextos(torreA, torreC, torreA1, torreC1);
                } else {

                    f.limpiar(torreA1, torreB1, torreC1);
                    numMov = 0;
                    torreA.setText("");
                    torreB.setText("");
                    torreC.setText("");
                    numMinimodeMovimientos.setText("");
                    numMovimientos.setText("");

                    f.TorreA(numDiscos, torreA1);

                    for (int i = numDiscos - 1; i >= 0; i--) {
                        torreA.setText(torreA.getText() + "\n" + torreA1.get(i));
                    }
                    f.resolver(Integer.parseInt(comboBox1.getSelectedItem().toString()), torreA1, torreB1, torreC1);
                    numMovimientos.setText(String.valueOf(String.format("%.0f", f.numMinMov(Integer.parseInt(comboBox1.getSelectedItem().toString())))));
                    cambiartextos(torreA, torreC, torreA1, torreC1);

                }

            }
        });
    }

    private void cambiartextos(JTextArea textArea1, JTextArea textArea2, Stack<String> primeraTorre, Stack<String> segundaTorre) {
        textArea1.setText("");
        textArea2.setText("");
        int numDiscos = Integer.parseInt(comboBox1.getSelectedItem().toString());

        if (segundaTorre.size() == numDiscos && numMov == f.numMinMov(numDiscos)) {
            JOptionPane.showMessageDialog(null, "Felicidades has alcanzado el obejtivo de minimo de movimientos");
        } else if (segundaTorre.size() == numDiscos && numMov != f.numMinMov(numDiscos)) {
            JOptionPane.showMessageDialog(null, "Felicidades lo has resuelto\n Intenta superar el objetivo con el minimo de mvimientos");
        }

        for (int i = primeraTorre.size() - 1; i >= 0; i--) {
            textArea1.setText(textArea1.getText() + "\n" + primeraTorre.get(i));
        }
        for (int i = segundaTorre.size() - 1; i >= 0; i--) {
            textArea2.setText(textArea2.getText() + "\n" + segundaTorre.get(i));
        }
    }




}
