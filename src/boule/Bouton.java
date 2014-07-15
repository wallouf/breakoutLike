package boule;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JButton;
 
 
public class Bouton extends JButton implements MouseListener{
 
         private String name;
     private Image img;
         
     public Bouton(String str){
             super(str);
             this.name = str;
             
             try {
                                 img = ImageIO.read(new File("fondBouton1.png"));
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                        
                        this.addMouseListener(this);
     }
    
     public void paintComponent(Graphics g){
                 
             Graphics2D g2d = (Graphics2D)g;
            
             GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
             g2d.setPaint(gp);
            // g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
             
             g2d.setColor(Color.black);
             g2d.drawString(this.name, (int)(this.getWidth() / 2.1), (this.getHeight() / 2)+5);

            
     }
 
        @Override
        public void mouseClicked(MouseEvent event) {
                 //Pas utile d'utiliser cette méthode ici                      
        }
 
        @Override
        public void mouseEntered(MouseEvent event) {
                
                //Nous changeons le fond en jaune pour notre image lors du survol
                //avec le fichier fondBoutonHover.png
                try {
                         img = ImageIO.read(new File("fondBouton2.png"));
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
        }
 
        @Override
        public void mouseExited(MouseEvent event) {
 
                //Nous changeons le fond en vert pour notre image lorsqu'on quitte le bouton
                //avec le fichier fondBouton.png
                try {
                         img = ImageIO.read(new File("fondBouton1.png"));
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
        }
 
        @Override
        public void mousePressed(MouseEvent event) {
 
                //Nous changeons le fond en orangé pour notre image lors du clic gauche
                //avec le fichier fondBoutonClic.png
                try {
                         img = ImageIO.read(new File("fondBouton4.png"));
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
        }
 
        @Override
        public void mouseReleased(MouseEvent event) {
                
                //Nous changeons le fond en orangé pour notre image 
                //lorsqu'on relâche le clic 
                //avec le fichier fondBoutonHover.png   
                
                //Si on est à l'extérieur de l'objet, on dessine le fond par défaut
                if(event.getY() > 25)
                {
                        try {
                                 img = ImageIO.read(new File("fondBouton1.png"));
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }
                //Sinon on met le fond jaune, la souris est encore dessus...
                else
                {
                        try {
                                 img = ImageIO.read(new File("fondBouton5.png"));
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }               
        }
        
}

