
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.sound.midi.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jordan Dashel
 */
public class Main {
    
    private static final int force = 500; //Striking force of the midi piano
    private static int randomInt; //used for selecting interval or triad
    private static String randomInterval;
    private static String randomTriad;
    private static int root = 60;//root of the triad...60 == mid C
    private static final Random rand = new Random();    
    private static MidiChannel[] mc;
    private static Synthesizer synth;
    private static int id;  //0 if interval
                            //1 if harmonic interval
                            //2 if triads
            
    private static final String[] intervals = {"minor 2nd", "major 2nd", "minor 3rd", 
        "major 3rd", "perfect 4th", "diminished 5th (tritone)", "perfect 5th",
        "minor 6th", "major 6th", "minor 7th", "major 7th", "octave"}; 

    private static final String[] triads = {"major (135)", "minor (1b35)", 
        "augmented (13#5)", "diminished (1b3b5)", "sus 4 (145)"};
    
    public static void main(String[] args) throws MidiUnavailableException {
      
        synth = MidiSystem.getSynthesizer();
        synth.open();
        mc = synth.getChannels();
        Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
        synth.loadInstrument(instr[0]);
        
        int hardSwitch = 1; //Switches between modes; to be manually changed
                            //will eventually implement a select dialog
        
        if (hardSwitch == 0){
            ChordPlayer player = new ChordPlayer();
        }else if(hardSwitch == 1){
  
            optionPane optPane = new optionPane();

            int selection = optPane.getSelection();

            if(selection == -1)
                throw new Error("bad selection index");

            id = selection;
            MainView mainView;

            switch(selection){
                case(0) :   mainView = new MainView(0);
                            break;
                case(1) :   mainView = new MainView(1);
                            break;
                case(2) :   mainView = new MainView(2);
                            break;
            }
        }
    }
    
    
    /**
     * Calculates a random root from one octave below to one above middle c.
     */
    public static void randomRoot(){
        //randomize root
        if(rand.nextInt(2)==0){
            root = 60 + rand.nextInt(12);
        }else{
            root = 60 - rand.nextInt(12);
        }
    }
    
    /**
     * Given an integer value for a note, returns the name.
     * Extensive case list is to check for octaves. There is probably a 
     * better way.
     * @param value int value for a note
     * @return note name of the note
     */
    private static String getNoteName(int value){
        switch(value){
            case(60)    :   return "C";
            case(60+12) :   return "C";
            case(60-12) :   return "C";
            case(61)    :   return "C#";
            case(61+12) :   return "C#";
            case(61-12) :   return "C#";
            case(62)    :   return "D";
            case(62+12) :   return "D";
            case(62-12) :   return "D";
            case(63)    :   return "D#";
            case(63+12) :   return "D#";
            case(63-12) :   return "D#";
            case(64)    :   return "E";
            case(64+12) :   return "E";
            case(64-12) :   return "E";
            case(65)    :   return "F";
            case(65+12) :   return "F";
            case(65-12) :   return "F";
            case(66)    :   return "F#";
            case(66+12) :   return "F#";
            case(66-12) :   return "F#";
            case(67)    :   return "G";
            case(67+12) :   return "G";
            case(67-12) :   return "G";
            case(68)    :   return "G#";
            case(68+12) :   return "G#";
            case(68-12) :   return "G#";
            case(69)    :   return "A";
            case(69+12) :   return "A";
            case(69-12) :   return "A";
            case(70)    :   return "Bb";
            case(70+12) :   return "Bb";
            case(70-12) :   return "Bb";
            case(71)    :   return "B";
            case(71+12) :   return "B";
            case(71-12) :   return "B";
            default     :   throw new IllegalArgumentException("bad input");
                
            }
    }
    
    /**
     * Given a name for a note, returns the value.
     * 
     * @param name String value for a note
     * @return int value of the note
     */
    private static int getNoteValue(String name){
        switch(name){
            case("C")   :   return 60;
            case("C#")  :   return 61;
            case("D")   :   return 62;
            case("Eb")  :   return 63;
            case("E")   :   return 64;
            case("F")   :   return 65;
            case("F#")  :   return 66;
            case("G")   :   return 67;
            case("G#")  :   return 68;
            case("A")   :   return 57;
            case("Bb")  :   return 58;
            case("B")   :   return 59;
            default     :   throw new IllegalArgumentException("bad input");
                
        }
    }
    
    /**
     * Finds and plays a random interval.
     */
    private static void getInterval(){
        randomRoot();
        randomInt = rand.nextInt(12);
        randomInterval = intervals[randomInt];
        mc[5].noteOn(root, force);
        if(id == 0){
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        mc[5].noteOn(root + 1 + randomInt, force);
    }
    
    /**
     * Only plays an interval.
     * PRECONDITION: root and randomInt must be set.
     */
    private static void getIntervalPlay(){
        mc[5].noteOn(root, force);
        if(id == 0){
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        mc[5].noteOn(root + 1 + randomInt, force);
    }
    
    private static void getSeventh(){
        mc[5].noteOn(root, force);
        mc[5].noteOn(root + 4, force);
        mc[5].noteOn(root + 7, force);
        mc[5].noteOn(root + 11, force);
    }
    
    /**
     * Finds and plays a random triad.
     */
    private static void getRandomTriad(){
        randomRoot();
        randomInt = rand.nextInt(5);
        randomTriad = triads[randomInt];
        switch(randomTriad){
            case("major (135)")         :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 4, force);
                                            mc[5].noteOn(root + 7, force);
                                            break;
            case("minor (1b35)")        :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 3, force);
                                            mc[5].noteOn(root + 7, force);
                                            break;
            case("augmented (13#5)")    :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 4, force);
                                            mc[5].noteOn(root + 8, force);
                                            break;
            case("diminished (1b3b5)")  :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 3, force);
                                            mc[5].noteOn(root + 6, force);
                                            break;
            case("sus 4 (145)")         :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 5, force);
                                            mc[5].noteOn(root + 7, force);
                                            break;              
        }
    }
    
    /**
     * Plays given triad.
     * @param input name of the triad to be played
     */
    private static void getTriad(String input){
        switch(input){
            case("major (135)")         :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 4, force);
                                            mc[5].noteOn(root + 7, force);
                                            break;
            case("minor (1b35)")        :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 3, force);
                                            mc[5].noteOn(root + 7, force);
                                            break;
            case("augmented (13#5)")    :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 4, force);
                                            mc[5].noteOn(root + 8, force);
                                            break;
            case("diminished (1b3b5)")  :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 3, force);
                                            mc[5].noteOn(root + 6, force);
                                            break;
            case("sus 4 (145)")         :   mc[5].noteOn(root, force);
                                            mc[5].noteOn(root + 5, force);
                                            mc[5].noteOn(root + 7, force);
                                            break;              
        }  
    
    }

private static class optionPane extends JPanel{
    public int getSelection(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        JPanel panel = new JPanel();
        panel.add(new JLabel("Please make a selection:"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Intervals");
        model.addElement("Harmonic Intervals");
        model.addElement("Triads");
        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);
        int result = JOptionPane.showConfirmDialog(null, panel, "Selection", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch(result){
            case JOptionPane.OK_OPTION  :   return comboBox.getSelectedIndex();
        }
        return -1; //error
    }
}

private static class ChordPlayer extends JFrame implements ActionListener, MouseListener {

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
            int newRoot = getNoteValue(selectedRoot);
            root = newRoot;
            getSeventh();
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


private static class MainView extends JFrame implements ActionListener, MouseListener {

    JFrame f = new JFrame("Ear Training");


    public MainView(int a){ //if a==0, interval
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
            for(String i : intervals){
                JButton jbAdvance = new JButton(i);
                jbAdvance.addActionListener(this);
                buttons.add(jbAdvance);                
            }
            getInterval();
        }else{ //triad
            for(String i : triads){
                JButton jbAdvance = new JButton(i);
                jbAdvance.addActionListener(this);
                buttons.add(jbAdvance);                
            }
            getRandomTriad();                
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
            System.exit(0); //GTFO
        }else if(e.getActionCommand().equals("replay")||
                e.getActionCommand().equals("play")){
            if(id==0||id==1){ //harmonic or basic interval
                getIntervalPlay();
            }else{
                getTriad(randomInterval);
            }
        }else if(e.getActionCommand().equals(randomInterval)||
                e.getActionCommand().equals(randomTriad)){
            if(id == 0||id == 1){
                JOptionPane.showMessageDialog(null, "Correct\n" +
                        intervals[randomInt] + "\n" +
                        getNoteName(root) + " -> " + 
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
}