/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boule;

import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame implements MouseMotionListener {

    private JCheckBox check1 = new JCheckBox("Morphing?");
    public static int temp = 0,  valeurx = 0;
    private Panneau pan = new Panneau();
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("Choix de la forme :");
    private JLabel label1 = new JLabel("Choix du niveau :");
    private int i = 100;
    private boolean j = false;
    public boolean morphing = false;
    private static int increment = 0;
    boolean monte = false;
    public boolean reculX = false,  reculY = false;
    private JComboBox combo = new JComboBox();
    private JComboBox combo2 = new JComboBox();
    private int choix = 0;
    int tempX = 0, tempY = 0;

    public Fenetre() {
        check1.addActionListener(new StateListener());
        this.addMouseMotionListener(this);
        this.setTitle("CASSE BRIQUE V1.3");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        container.setLayout(new BorderLayout());


        String[] tab = {"Ronde", "Carré", "Triangulaire", "Surprise"};
        combo = new JComboBox(tab);
        combo.addActionListener(new ItemAction());
        combo.setPreferredSize(new Dimension(100, 20));
        combo.setForeground(Color.blue);
        String[] tab2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        combo2 = new JComboBox(tab2);
        combo2.addActionListener(new ItemAction2());
        combo2.setPreferredSize(new Dimension(100, 20));
        combo2.setForeground(Color.blue);

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 5));
        top.add(label);
        top.add(combo);
        top.add(label1);
        top.add(combo2);
        top.add(check1);
        top.setBackground(Color.yellow);

        container.add(top, BorderLayout.NORTH);
        container.setBackground(Color.white);
        container.add(pan, BorderLayout.CENTER);
        this.setContentPane(container);
        this.setVisible(true);
        go();
    }

    public void go() {


        for (;;) {
            int x = pan.getPosX(), y = pan.getPosY(), x1 = pan.getRectX(), y1 = pan.getRectY();
            int xlargeur = pan.getRectXLength();
            int yprof = pan.getRectYLength();
            int r = pan.getRayon();
            if (pan.isOnTouche()) {
                if (reculX)
                    reculX = false;
                else
                    reculX = true;
                if (reculY)
                    reculY = false;
                else
                    reculY = true;
            }
            if (x < 1 || (x == ((x1 + xlargeur)) && ((y >= (y1 - r)) && (y <= (y1 + yprof))))) {
                reculX = false;
            }
            if (x > (pan.getWidth() - r) || (x == (x1 - r) && ((y >= (y1 - r)) && (y <= (y1 + yprof))))) {
                reculX = true;
            }
            if (y < 1 || (y == (yprof + y1) && (x >= (x1 - r) && x <= x1 + xlargeur))) {
                reculY = false;
            }
            if (y > (pan.getHeight() - r) || (y == (y1 - r) && (x >= (x1 - r) && x <= x1 + xlargeur))) {
                reculY = true;
            }
            if (!reculX) {
                x++;
            } else {
                x--;
            }
            if (!reculY) {
                y++;
            } else {
                y--;
            }


            pan.setPosX(x);
            pan.setPosY(y);
            pan.repaint();

            //i = Math.random();
            //j = (int) (Math.pow(i * 2, (i * 2)));
            try {
                // Thread.sleep(j);
                Thread.sleep(i);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (morphing) {
                while (pan.getCompteur() >= 10) {
                    pan.setCompteur(0);
                    if (increment >= 15) {
                        monte = false;
                    } else if (increment < 1) {
                        monte = true;
                    }
                    if (monte) {

                        increment++;
                        if (choix == 0 || choix == 1) {
                            pan.setRayon(pan.getRayon() + increment);
                        }
                        if (choix == 2) {
                            pan.setPosX(pan.getPosX() + increment);
                            pan.setPosY(pan.getPosY() + increment);
                        }
                    } else if (!monte) {
                        increment--;
                        if (choix == 0 || choix == 1) {
                            pan.setRayon(pan.getRayon() - (15 - increment));
                        }
                        if (choix == 2) {
                            pan.setPosX(pan.getPosX() - (15 - increment));
                            pan.setPosY(pan.getPosY() - (15 - increment));
                        }
                    }
                    pan.repaint();

                }
            }
        }

    }

    class ItemAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            choix = combo.getSelectedIndex();
            pan.setChoix(choix);
        }
        }

    class ItemAction2 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            i = (combo2.getSelectedIndex() + 1) * 2;
        }
        }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        pan.setRectX(e.getX() - (pan.getRectXLength() / 2));
        pan.repaint();
    }

    class StateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (((JCheckBox) e.getSource()).isSelected()) {
                morphing = true;
                tempX = pan.getPosX();
                tempY = pan.getPosY();

            }
            if (!((JCheckBox) e.getSource()).isSelected()) {
                pan.setRayon(20);
                morphing = false;
                increment = 0;
                pan.setPosX(tempX);
                pan.setPosY(tempY);
            }

        }
        }
}




