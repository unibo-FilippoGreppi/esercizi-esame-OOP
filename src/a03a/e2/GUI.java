package src.a03a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private static final String PLAYER_ICO = "*";
    private static final String BOT_ICO = "o";
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final Logic logics;
    
    public GUI(int size) {
        this.logics = new LogicImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                logics.movePlayer(position);
                if (logics.toQuit()) {
                    System.out.println("umano vince");
                    logics.reset();
                } else {
                    logics.moveBot();
                    if (logics.toQuit()) {
                        System.out.println("computer vince");
                        logics.reset();
                    }
                }
                GUIUpdate();
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<Integer,Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        GUIUpdate();
        this.setVisible(true);
    }

    private void GUIUpdate() {
        final Pair<Integer, Integer> botPosition = this.logics.getBotPosition();
        final Pair<Integer, Integer> playerPosition = this.logics.getPlayerPosition();
        
        for (final var button : this.cells.entrySet()) {
            var position = button.getValue();
            String text = " ";
            if (position.equals(botPosition)) {
                text = BOT_ICO;
            } else if (position.equals(playerPosition)) {
                text = PLAYER_ICO;
            } 
            button.getKey().setText(text);
        }
    }
}
