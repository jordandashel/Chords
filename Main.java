
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
    private static int root = 60;//root of the triad...60 == mid C
    private static final Random rand = new Random();    
    private static MidiChannel[] mc;
    private static Synthesizer synth;
    
    public static void main(String[] args) throws MidiUnavailableException {
      
        synth = MidiSystem.getSynthesizer();
        synth.open();
        mc = synth.getChannels();
        Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
        synth.loadInstrument(instr[0]);
        
        int hardSwitch = 1; //Switches between modes; to be manually changed
                            //will eventually implement a select dialog
        
        if (hardSwitch == 0){
            ChordPlayer chordPlayer = new ChordPlayer();
        }else if(hardSwitch == 1){
  
            OptionPane optPane = new OptionPane();

            int selection = optPane.getSelection();

            if(selection == -1)
                throw new Error("bad selection index");

            MainView mainView;

            switch(selection){
                case(0) :   mainView = new MainView(0); // melodic intervals
                            break;
                case(1) :   mainView = new MainView(1); // harmonic intervals
                            break;
                case(2) :   mainView = new MainView(2); // triads
                            break;
            }
        }
    }
}