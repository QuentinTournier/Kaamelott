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
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class UI extends JFrame implements ActionListener, ItemListener {
    private JPanel panel;

    public UI() {
        this.setTitle("Test radio boutons");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridLayout(0, 1));
        Border border = BorderFactory.createTitledBorder("Sélection");
        panel.setBorder(border);

        getName();

        Container contentPane = this.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        this.setSize(300, 150);
        this.setVisible(true);
    }

    public void display(){
        //write code to display a text until button pressed
    }

    public int getNumber(){
        //write code to display a message and a choice
        return 0;
    }

    public String getName(){
        ButtonGroup group = new ButtonGroup();

        JRadioButton radio1 = new JRadioButton("Choix 1");
        radio1.setMnemonic(KeyEvent.VK_1);
        radio1.setActionCommand("Choix_1");
        radio1.setSelected(true);

        JRadioButton radio2 = new JRadioButton("Choix 2");
        radio2.setMnemonic(KeyEvent.VK_2);
        radio2.setActionCommand("Choix_2");

        JRadioButton radio3 = new JRadioButton("Choix 3");
        radio3.setMnemonic(KeyEvent.VK_3);
        radio3.setActionCommand("Choix_3");

        group.add(radio1);
        panel.add(radio1);
        group.add(radio2);
        panel.add(radio2);
        group.add(radio3);
        panel.add(radio3);

        radio1.addActionListener(this);
        radio2.addActionListener(this);
        radio3.addActionListener(this);
        radio1.addItemListener(this);
        radio2.addItemListener(this);
        radio3.addItemListener(this);
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clic sur le bouton : " + e.getActionCommand());
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