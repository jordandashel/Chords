
import javax.sound.midi.*;

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
    
    public static void main(String[] args) throws MidiUnavailableException {
        
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