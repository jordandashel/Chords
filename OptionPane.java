
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class OptionPane extends JPanel{
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