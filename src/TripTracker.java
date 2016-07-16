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
                new TripTracker().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        final JFrame frame = new JFrame("Trip Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        frame.add(new DataEntryScreen());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
