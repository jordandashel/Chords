
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JordanD
 */
public class IntervalUtilities {
    public static final String[] intervals = {"minor 2nd", "major 2nd", "minor 3rd", 
        "major 3rd", "perfect 4th", "diminished 5th (tritone)", "perfect 5th",
        "minor 6th", "major 6th", "minor 7th", "major 7th", "octave"}; 

    public static final String[] triads = {"major (135)", "minor (1b35)", 
        "augmented (13#5)", "diminished (1b3b5)", "sus 4 (145)"};
    private static final int FORCE = 500; //Striking force of the midi piano
    
    static Random rand = new Random();
    
    /**
     * Calculates a random root from one octave below to one above middle c.
     */
    public static int randomRoot(){
        //randomize root
        if(rand.nextInt(2)==0){
            return 60 + rand.nextInt(12);
        }else{
            return 60 - rand.nextInt(12);
        }
    }
    
    public static String randomInterval(){
        return intervals[rand.nextInt(intervals.length)];
    }
    
    public static String randomTriad(){
        return triads[rand.nextInt(triads.length)];
    }
    
    /**
     * Given an integer value for a note, returns the name.
     * Extensive case list is to check for octaves. There is probably a 
     * better way.
     * @param value int value for a note
     * @return note name of the note
     */
    public static String getNoteName(int value){
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
    public static int getNoteValue(String name){
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
    
    public void findAndPlayRandomInterval(){
        int root = randomRoot();
        int randomInt = rand.nextInt(12);
        String randomInterval = intervals[randomInt];
        mc[5].noteOn(root, FORCE);
        if(id == 0){
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        mc[5].noteOn(root + 1 + randomInt, FORCE);
    }
    
    /**
     * Only plays an interval.
     */
    public static void playInterval(int root, String interval, int id){
        mc[5].noteOn(root, FORCE);
        if(id == 0){ waitGivenTime(1000);}
        int selectedInterval = Arrays.asList(intervals).indexOf(interval);
        mc[5].noteOn(root + 1 + selectedInterval, FORCE);
    }

    private static void waitGivenTime(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static int findUpperNoteGivenRootAndInterval(int root, String interval){
        return root + 1 + Arrays.asList(intervals).indexOf(interval);
    }
        
    public static void playSeventh(int root){
        mc[5].noteOn(root, FORCE);
        mc[5].noteOn(root + 4, FORCE);
        mc[5].noteOn(root + 7, FORCE);
        mc[5].noteOn(root + 11, FORCE);
    }
    
    /**
     * Finds and plays a random triad.
     */
    private void getRandomTriad(){
        int root = randomRoot();
        int randomInt = rand.nextInt(5);
        String randomTriad = triads[randomInt];
        switch(randomTriad){
            case("major (135)")         :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 4, FORCE);
                                            mc[5].noteOn(root + 7, FORCE);
                                            break;
            case("minor (1b35)")        :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 3, FORCE);
                                            mc[5].noteOn(root + 7, FORCE);
                                            break;
            case("augmented (13#5)")    :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 4, FORCE);
                                            mc[5].noteOn(root + 8, FORCE);
                                            break;
            case("diminished (1b3b5)")  :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 3, FORCE);
                                            mc[5].noteOn(root + 6, FORCE);
                                            break;
            case("sus 4 (145)")         :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 5, FORCE);
                                            mc[5].noteOn(root + 7, FORCE);
                                            break;              
        }
    }
    
    /**
     * Plays given triad.
     * @param input name of the triad to be played
     */
    public static void playTriad(int root, String input){
        switch(input){
            case("major (135)")         :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 4, FORCE);
                                            mc[5].noteOn(root + 7, FORCE);
                                            break;
            case("minor (1b35)")        :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 3, FORCE);
                                            mc[5].noteOn(root + 7, FORCE);
                                            break;
            case("augmented (13#5)")    :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 4, FORCE);
                                            mc[5].noteOn(root + 8, FORCE);
                                            break;
            case("diminished (1b3b5)")  :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 3, FORCE);
                                            mc[5].noteOn(root + 6, FORCE);
                                            break;
            case("sus 4 (145)")         :   mc[5].noteOn(root, FORCE);
                                            mc[5].noteOn(root + 5, FORCE);
                                            mc[5].noteOn(root + 7, FORCE);
                                            break;              
        }  
    }
    
}
