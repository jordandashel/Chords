
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
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
public class ChordPlayer extends JFrame implements ActionListener, MouseListener {

    JFrame f = new JFrame("Chord Player");


    public ChordPlayer(){ //if a==0, interval
                            //if a==1, harmonic interval
                            //if a==2, chords

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
//        JPanel playButton = new JPanel();
//        JButton play = new JButton("play");
//        play.addActionListener(this);
//        playButton.add(play);
//        JButton quit = new JButton("quit");
//        quit.addActionListener(this);
//        playButton.add(quit);
//        f.add(playButton, BorderLayout.SOUTH);

        JPanel buttons = new JPanel();
        GridLayout grid = new GridLayout(0,4);
        buttons.setLayout(grid);
        f.add(buttons, BorderLayout.CENTER);

        String[] tones = {"A", "B", "Bb", "C", "C#", "D", "D#", "E",
            "Eb", "F", "F#", "G", "G#"};
        
        for(String i : tones){
            JButton jbAdvance = new JButton(i);
            jbAdvance.addActionListener(this);
            buttons.add(jbAdvance);  
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

    
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedRoot = e.getActionCommand();
            int newRoot = IntervalUtilities.getNoteValue(selectedRoot);
            IntervalUtilities.playSeventh(newRoot);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
}