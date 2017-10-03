package KaamelottGraphical;

import javax.swing.*;
import java.awt.event.*;



import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class UI extends JFrame implements ActionListener, ItemListener {
    private JPanel panel;
    JTextField  testField1 = null;
    private  JFrame f;
    private int result = 0;


    public UI() {
        this.setTitle("Test radio boutons");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridLayout(0, 1));
        Border border = BorderFactory.createTitledBorder("Sélection");
        panel.setBorder(border);
        Container contentPane = this.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        f = new JFrame("Kaamelott");
        f.setSize(600, 400);

        getNumber("Chose an action \n 1. Use Potion"+"\n"
                +" 2. Equip Character"+"\n"
                +" 3. View Stats"+"\n"
                +" 4. Continue adventure"+"\n");
        display("hihihihihi");
        getName();
    }

    public void display(String message){
        JButton b =new JButton(message);
        f.getContentPane().add(b);
        b.addActionListener( new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               synchronized (b) { b.notify(); }
           }
        });

        panel.add(b);
        f.getContentPane().add(panel);
        f.setVisible(true);
        try{
            synchronized (b) {
                b.wait();
            }
        }catch (java.lang.InterruptedException ie){
            System.out.println("voila");
        }
        System.out.println("-------------------------");
        panel.remove(b);
        f.setVisible(true);
    }

    public int getNumber(String message){
        String[] parts = message.split("[0-9]");
        String waitThing = "wait";
        List<JButton> listButton = new ArrayList();
        for(int i = 1; i<parts.length; i++){
            int k = i;
            listButton.add(new JButton(parts[i].replaceAll("\n|\\.", "")));
            listButton.get(i-1).addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    result = k;
                    synchronized (waitThing) { waitThing.notify(); }
                }
            });
            panel.add(listButton.get(i-1));
        }

        f.getContentPane().add(panel);
        f.setVisible(true);
        try{
            synchronized (waitThing) {
                waitThing.wait();
            }
        }catch (java.lang.InterruptedException ie){
            System.out.println("voila");
        }
        for(int i=0; i<listButton.size();i++){
            panel.remove(listButton.get(i));
        }
        f.setVisible(true);
        System.out.println(result);
        return result;
    }

    public String getName(){

        testField1 = new JTextField ("                   ");

        panel.add(testField1);
        f.getContentPane().add(panel);

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
        f.getContentPane().add(panel);
        f.setVisible(true);
        try{
            synchronized (bouton1) {
                bouton1.wait();
            }
        }catch (java.lang.InterruptedException ie){
            System.out.println("voila");
        }
        String textToReturn = new String(testField1.getText());
        panel.remove(bouton1);
        panel.remove(testField1);
        f.setVisible(true);
        return textToReturn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("texte saisie = " + testField1.getText());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.print("Bouton " + ((JRadioButton) e.getItem()).getActionCommand());
        if (e.getStateChange() == ItemEvent.DESELECTED)
            System.out.println(" deselectionne");
        if (e.getStateChange() == ItemEvent.SELECTED)
            System.out.println(" selectionne");
    }
}