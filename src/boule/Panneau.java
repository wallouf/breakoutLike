/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boule;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author a807087
 */
public class Panneau extends JPanel {

    private int premier = 0;
    private int posX = -50;
    private int posY = -50;
    private int rectX = 0;
    private int rectY = 0;
    private int nombreBrique = 12;
    private int rectXLength = 0;
    private int rectYLength = 0;
    private int compteur = 0;
    private int rayon = 5;
    public int largeurBrique = 10,  hauteurBrique = 10;
    private int largeur = 0,  hauteur = 0;
    public int briqueLarg[][] = new int[50][50];
    public int briqueHaut[][] = new int[50][50];
    public boolean briqueValid[][] = new boolean[50][50];
    private int choix = 0;
    private int i = 0;
    private int numeroBriquex = 0;
    private int numeroBriquey = 0;
    private boolean onTouche = false;

    public boolean isOnTouche() {
        return onTouche;
    }

    public void setOnTouche(boolean onTouche) {
        this.onTouche = onTouche;
    }

    public int getRayon() {
        return rayon;
    }

    public int[][] getBriqueHaut() {
        return briqueHaut;
    }

    public void setBriqueHaut(int[][] briqueHaut) {
        this.briqueHaut = briqueHaut;
    }

    public int[][] getBriqueLarg() {
        return briqueLarg;
    }

    public void setBriqueLarg(int[][] briqueLarg) {
        this.briqueLarg = briqueLarg;
    }

    public int getHauteurBrique() {
        return hauteurBrique;
    }

    public void setHauteurBrique(int hauteurBrique) {
        this.hauteurBrique = hauteurBrique;
    }

    public int getLargeurBrique() {
        return largeurBrique;
    }

    public void setLargeurBrique(int largeurBrique) {
        this.largeurBrique = largeurBrique;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public void paintComponent(Graphics g) {

        compteur++;
        largeurBrique = (int) ((this.getWidth() - 40) / nombreBrique);
        largeur = largeurBrique + 2;
        hauteurBrique = (int) (0.02 * this.getHeight());
        hauteur = hauteurBrique + 2;
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (premier == 0) {
            premier++;
            rectX = (int) (0.43 * this.getWidth());
        }


        rectY = (int) (0.97 * this.getHeight());
        rectXLength = (int) (0.12 * this.getWidth());
        rectYLength = (int) (0.02 * this.getHeight());

        g.setColor(Color.black);
        g.fillRect(rectX, rectY, rectXLength, rectYLength);
        g.setColor(Color.red);
        switch (choix) {
            case 0:
                g.fillOval(posX, posY, rayon, rayon);
                break;
            case 1:
                g.fillRect(posX, posY, rayon, rayon);
                break;
            case 2:
                 {
                    g.drawLine(posX + 10, posY, posX, posY + 20);
                    g.drawLine(posX, posY + 20, posX + 20, posY + 20);
                    g.drawLine(posX + 20, posY + 20, posX + 10, posY);
                }
                break;
            default:
                g.fillOval(posX, posY, rayon, rayon);
                break;
        }
        numeroBriquex = (int) ((posX + 10) / largeur) - 1;
        numeroBriquey = (int) ((posY - hauteur * 2.2) / hauteur);
        if (numeroBriquex <= 10 && numeroBriquex >= 0 && numeroBriquey <= 9 && numeroBriquey >= 0) {
            if (briqueLarg[numeroBriquex][numeroBriquey] != 0) {
                //on rebondi sur la brique
                onTouche = true;
                //on efface la brique
                briqueLarg[numeroBriquex][numeroBriquey] = 0;
                briqueHaut[numeroBriquex][numeroBriquey] = 0;
                briqueValid[numeroBriquex][numeroBriquey] = true;
            }
        }
       onTouche = false;
        
// CONSTRUCTION DES BRIQUES
        for (int z = 0; z <= 11; z++) {
            for (int w = 1; w <= 9; w++) {
                if (!briqueValid[z][w]) {
                    briqueLarg[z][w] = (int) ((largeur * 0.80) + largeur * z);
                    briqueHaut[z][w] = (int) (hauteur + 4 + (hauteur * w));
                    g.setColor(Color.green);

                    if (briqueLarg[z][w] < (this.getWidth() - largeur)) {

                        g.fillRect(briqueLarg[z][w], briqueHaut[z][w], largeurBrique, hauteurBrique);
                    }
                } else {
                    briqueLarg[z][w] = 0;
                    briqueHaut[z][w] = 0;
                    g.setColor(Color.green);

                    if (briqueLarg[z][w] < (this.getWidth() - largeur)) {

                        g.fillRect(briqueLarg[z][w], briqueHaut[z][w], 0, 0);
                    }
                }



            }

        }
    }

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getRectYLength() {
        return rectYLength;
    }

    public void setRectYLength(int rectYLength) {
        this.rectYLength = rectYLength;
    }

    public void setChoix(int i) {
        this.choix = i;
    }

    public int getRectX() {
        return rectX;
    }

    public void setRectX(int rectX) {
        this.rectX = rectX;
    }

    public int getRectXLength() {
        return rectXLength;
    }

    public void setRectXLength(int rectXLength) {
        this.rectXLength = rectXLength;
    }

    public int getRectY() {
        return rectY;
    }

    public void setRectY(int rectY) {
        this.rectY = rectY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}

