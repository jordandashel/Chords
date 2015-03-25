
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JordanD
 */
public class MainView extends JFrame implements ActionListener, MouseListener {

    JFrame f = new JFrame("Ear Training");
    int id; // identifier for mode
    int root;
    String currentInterval, currentTriad;
    
    public MainView(int a){ //if a==0, interval
                            //if a==1, harmonic interval
                            //if a==2, chords
        id = a;
        
        //main frame
        f.setLocation(10, 50);
        f.setLayout(new BorderLayout());
        f.setSize(new Dimension(600, 250));

        //making a hybrid layout 
        //  ___________
        // |           |
        // |   MAIN    | <-BorderLayout.CENTER
        // |  JPanel   |
        // |           |
        // |-----------|
        // | -BUTTONS- | <-BorderLayout.SOUTH
        // |___________|
        //
        JPanel playButton = new JPanel();
        JButton play = new JButton("play");
        play.addActionListener(this);
        playButton.add(play);
        JButton quit = new JButton("quit");
        quit.addActionListener(this);
        playButton.add(quit);
        f.add(playButton, BorderLayout.SOUTH);

        JPanel buttons = new JPanel();
        GridLayout grid = new GridLayout(0,4);
        buttons.setLayout(grid);
        f.add(buttons, BorderLayout.CENTER);

        String[] tones = {"A", "B", "C", "D", "E", "F", "G"};

        if(a==0||a==1){ //interval, harmonic or otherwise
            for(String i : IntervalUtilities.intervals){
                JButton jbAdvance = new JButton(i);
                jbAdvance.addActionListener(this);
                buttons.add(jbAdvance);                
            }
            root = IntervalUtilities.randomRoot();
            currentInterval = IntervalUtilities.randomInterval();
            IntervalUtilities.playInterval(root, currentInterval, id);
        }else{ //triad
            for(String i : IntervalUtilities.triads){
                JButton jbAdvance = new JButton(i);
                jbAdvance.addActionListener(this);
                buttons.add(jbAdvance);                
            }
            root = IntervalUtilities.randomRoot();
            currentTriad = IntervalUtilities.randomTriad();
            IntervalUtilities.playTriad(root, currentTriad);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }

        SwingUtilities.updateComponentTreeUI(f);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getActionCommand().equals("quit")){
            System.exit(0);
        }else if(e.getActionCommand().equals("replay")||
                e.getActionCommand().equals("play")){
            if(id==0||id==1){ //harmonic or basic interval
                IntervalUtilities.playInterval(root, currentInterval, id);
            }else{
                IntervalUtilities.playTriad(root, currentTriad);
            }
        }else if(e.getActionCommand().equals(currentInterval)||
                e.getActionCommand().equals(currentTriad)){
            if(id == 0||id == 1){
                JOptionPane.showMessageDialog(null, "Correct\n" +
                        currentInterval + "\n" +
                        IntervalUtilities.getNoteName(root) + " -> " + 
                        getNoteName(root + 1 + randomInt));
                getInterval();
            }else{
                JOptionPane.showMessageDialog(null, "Correct\n" +
                        getNoteName(root) + " " + triads[randomInt]);
                getRandomTriad();
            }

        }else{
            JOptionPane.showMessageDialog(null, "Try Again");
        }
    }
    public void mouseClicked(MouseEvent e) {
        //do nothing
    }

    public void mousePressed(MouseEvent e) {
        //do nothing
    }

    public void mouseReleased(MouseEvent e) {
        //do nothing
    }

    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    public void mouseExited(MouseEvent e) {
        //do nothing
    }

}
