import components.DataEntryScreen;

import javax.swing.*;
import java.awt.*;

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */
public class TripTracker {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Create an instance of our application and start it.
                new TripTracker().createAndShowGUI();
            }
        });
    }

    /**
     * This creates the basic frame of teh application and attaches the main screen to it
     */
    private void createAndShowGUI() {
        final JFrame frame = new JFrame("Trip Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        //Attach the main screen
        frame.add(new DataEntryScreen());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
