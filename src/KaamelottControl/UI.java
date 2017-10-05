package KaamelottControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class UI extends JFrame implements ActionListener, ItemListener, GameInterface {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 675;
    private static final int NB_COLS = 6;
    private static final int NB_ROWS = 2;
    private JPanel panel;
    JTextField  testField1 = null;
    private  JFrame f;
    private int result = 0;


    public void init() {
        this.setTitle("Test radio boutons");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        Container contentPane = this.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        f = new JFrame("Kaamelott");
        f.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        f.getContentPane().add(panel);
        this.display("image_full_accueil");
        this.display("image_half_charac");
    }

    public void display(String message){
        if(message.startsWith("image")){
            if(message.startsWith("image_full")){
                // We set the picture in full screen and wait for a click
                BufferedImage image;
                try {

                    BufferedImage img = ImageIO.read(new File("./pictures/"+message+".jpg"));
                    JLabel pic = new JLabel( new ImageIcon( img));
                    panel.add(pic);
                    pic.addMouseListener(new MouseAdapter()
                    {
                        @Override
                        public void mouseClicked(MouseEvent e)
                        {
                            synchronized (pic) { pic.notify();}
                        }
                    });
                    f.setVisible(true);
                    try{
                        synchronized (pic) {
                            pic.wait();
                        }
                    }catch (java.lang.InterruptedException ie){
                        System.out.println("voila");
                    }

                    panelRemove(pic);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(message.startsWith("image_half")){
                try{
                    if(panel.getComponents().length >0){
                        panel.remove(0);
                        panel.revalidate();
                        panel.repaint();
                    }
                    Image img = ImageIO.read(new File("./pictures/"+message+".jpg")).getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT/2, Image.SCALE_DEFAULT);
                    JLabel pic = new JLabel( new ImageIcon( img));
                    panel.add(pic,0);

                }catch (IOException ioe){}

            }
        }
        else {
            JButton b =new JButton(message);
            f.getContentPane().add(b);
            b.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    synchronized (b) { b.notify(); }
                }
            });

            panel.add(b);
            f.setVisible(true);
            try{
                synchronized (b) {
                    b.wait();
                }
            }catch (java.lang.InterruptedException ie){
                System.out.println("voila");
            }
            panelRemove(b);
        }
        f.setVisible(true);
    }

    public int getNumber(String[] message){
        String waitThing = "wait";
        List<JButton> listButton = new ArrayList();
        JLabel label = new JLabel(message [0]);
        panel.add(label);
        for(int i = 1; i<message.length; i++){
            int k = i;
            listButton.add(new JButton(message[i]));
            listButton.get(i-1).addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    result = k;
                    synchronized (waitThing) { waitThing.notify(); }
                }
            });
            panel.add(listButton.get(i-1));
        }

        f.setVisible(true);
        try{
            synchronized (waitThing) {
                waitThing.wait();
            }
        }catch (java.lang.InterruptedException ie){
            System.out.println("voila");
        }
        panelRemove(label);
        for(int i=0; i<listButton.size();i++){
            panelRemove(listButton.get(i));
        }
        f.setVisible(true);
        return result;
    }

    public String getName(String message){

        testField1 = new JTextField ("                   ");

        panel.add(testField1);

        JButton bouton1 = new JButton("Bouton1");
        bouton1.addActionListener( new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               synchronized (bouton1) {
                   if(!testField1.getText().trim().isEmpty()){
                       bouton1.notify();
                   }
               }
           }
        });

        panel.add(bouton1);
        f.setVisible(true);
        try{
            synchronized (bouton1) {
                bouton1.wait();
            }
        }catch (java.lang.InterruptedException ie){
            System.out.println("voila");
        }
        String textToReturn = new String(testField1.getText().trim());
        panel.remove(bouton1);
        panelRemove(testField1);
        f.setVisible(true);
        return textToReturn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("texte saisie =");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.print("Bouton " + ((JRadioButton) e.getItem()).getActionCommand());
        if (e.getStateChange() == ItemEvent.DESELECTED)
            System.out.println(" deselectionne");
        if (e.getStateChange() == ItemEvent.SELECTED)
            System.out.println(" selectionne");
    }

    private void panelRemove(JComponent rm) {
        panel.remove(rm);
        panel.revalidate();
        panel.repaint();
    }

}