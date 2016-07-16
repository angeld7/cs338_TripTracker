package components;

import data.TripSegmentType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Angel on 7/16/2016.
 */
public class TripSegmentTypePicker extends JPanel {
    private static final String LABEL_TEXT = "Please select the type of trip segment to add:";
    private static final int PREFERRED_BUTTON_HEIGHT = 100;
    private static final int PREFERRED_BUTTON_WIDTH = 100;
    private DataEntryScreen dataEntryScreen;

    public TripSegmentTypePicker(DataEntryScreen dataEntryScreen) {
        super();
        this.dataEntryScreen = dataEntryScreen;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel(LABEL_TEXT));
        addButtons();
    }

    private void addButtons() {
        for (final TripSegmentType tripSegmentType : TripSegmentType.values()) {
            JButton button = new JButton(tripSegmentType.name());
            button.setAlignmentY(CENTER_ALIGNMENT);
            button.setAlignmentX(CENTER_ALIGNMENT);
            button.setPreferredSize(new Dimension(PREFERRED_BUTTON_WIDTH, PREFERRED_BUTTON_HEIGHT));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dataEntryScreen.editNewTripSegment(tripSegmentType);
                }
            });
            add(button);
        }
    }
}
