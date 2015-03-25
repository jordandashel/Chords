
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
    private JPanel buttons;
    boolean isInterval;
    
    public MainView(int a){ //if a==0, interval
                            //if a==1, harmonic interval
                            //if a==2, chords
        id = a;
        isInterval = a==0||a==1;
        
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

        buttons = new JPanel();
        GridLayout grid = new GridLayout(0,4);
        buttons.setLayout(grid);
        f.add(buttons, BorderLayout.CENTER);

        String[] tones = {"A", "B", "C", "D", "E", "F", "G"};

        if(isInterval){ 
            for(String i : IntervalUtilities.intervals){
                addButton(i);
            }
            selectNewRootAndIntervalThenPlay();
        }else{ //triad
            for(String i : IntervalUtilities.triads){
                addButton(i);
            }
            selectNewRootAndTriadThenPlay();
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
    
    private void addButton(String buttonName) {
        JButton jbAdvance = new JButton(buttonName);
        jbAdvance.addActionListener(this);
        buttons.add(jbAdvance);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getActionCommand().equals("quit")){
            System.exit(0);
        }else if(e.getActionCommand().equals("replay")||
                e.getActionCommand().equals("play")){
            if(isInterval){ //harmonic or melodic interval
                IntervalUtilities.playInterval(root, currentInterval, id);
            }else{
                IntervalUtilities.playTriad(root, currentTriad);
            }
        }else if(e.getActionCommand().equals(currentInterval)||
                e.getActionCommand().equals(currentTriad)){
            if(isInterval){
                displaySuccessMessageForInterval();
                selectNewRootAndIntervalThenPlay();
            }else{
                displaySuccessMessageForTriad();
                selectNewRootAndTriadThenPlay();
            }

        }else{
            displayFailureMessage();
        }
    }
    
    private void displayFailureMessage(){
        JOptionPane.showMessageDialog(null, "Try Again");
    }
    
    private void displaySuccessMessageForInterval() {
        JOptionPane.showMessageDialog(null, "Correct\n"
                + currentInterval + "\n"
                + IntervalUtilities.getNoteName(root) + " -> "
                + IntervalUtilities.findUpperNoteGivenRootAndInterval(root, currentInterval));
    }
    
    private void displaySuccessMessageForTriad() {
        JOptionPane.showMessageDialog(null, "Correct\n"
                + IntervalUtilities.getNoteName(root) + " " + currentTriad);
    }
    
    private void selectNewRootAndIntervalThenPlay(){
        root = IntervalUtilities.randomRoot();
        currentInterval = IntervalUtilities.randomInterval();
        IntervalUtilities.playInterval(root, currentInterval, id);
    }
    
    private void selectNewRootAndTriadThenPlay(){
        root = IntervalUtilities.randomRoot();
        currentTriad = IntervalUtilities.randomTriad();
        IntervalUtilities.playTriad(root, currentTriad);
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
