package components;

import components.interfaces.FormPresenter;
import data.TripSegmentType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */

/**
 * This screen is used to pick the type of segment to add
 *
 */
public class TripSegmentTypePicker extends JPanel {
    private static final String LABEL_TEXT = "Please select the type of trip segment to add:";
    private FormPresenter dataEntryScreen;

    public TripSegmentTypePicker(FormPresenter dataEntryScreen) {
        super();
        this.dataEntryScreen = dataEntryScreen;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(LABEL_TEXT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setAlignmentY(CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(label);
        add(Box.createRigidArea(new Dimension(0,15)));
        addButtons();
        add(Box.createVerticalGlue());

    }

    /**
     * This method loops through all of the trip segment types and adds a button for each.
     */
    private void addButtons() {
        for (final TripSegmentType tripSegmentType : TripSegmentType.values()) {
            JButton button = new JButton(tripSegmentType.name());
            button.setVerticalAlignment(SwingConstants.CENTER);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setAlignmentX(CENTER_ALIGNMENT);
            button.setAlignmentY(CENTER_ALIGNMENT);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //This sends the signal to start editing a new segment of this type
                    dataEntryScreen.editNewTripSegment(tripSegmentType);
                }
            });
            add(button);
        }
    }
}
