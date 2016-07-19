package components;

import components.form.FormPanel;
import components.form.FormPanelFactory;
import components.interfaces.FormPresenter;
import data.TripSegment;
import data.TripSegmentType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Angel Delgado
 * ald363@drexel.edu
 * CS338:GUI, Assignment 2, Trip Tracker
 */


/**
 * This class defines most of the applications behavior.  I use the term presenter to refer to its job.
 */
public class DataEntryScreen extends JPanel implements FormPresenter {
    /**
     * The form panel factory is used to retrieve the correct form for the trip segment we are working with
     */
    private FormPanelFactory formPanelFactory;

    private JPanel centerPanel;
    private JPanel currentlyDisplayedPanel;
    private JLabel errorLabel;
    private TripSegmentTypePicker tripSegmentTypePicker;
    private JList<TripSegment> tripSegmentJList;
    private DefaultListModel<TripSegment> listModel;
    private FormPanel currentTripSegmentForm;
    private JButton saveButton;
    private JButton deleteButton;
    private JLabel segmentLabel;
    private JLabel segmentTitle;

    /**
     * True if we are in the process of deleting the trip segment
     */
    private boolean deleting = false;

    /**
     * True if there are any unsaved changes
     */
    private boolean unsavedChanges = false;

    public DataEntryScreen() {
        super(new BorderLayout());
        formPanelFactory = new FormPanelFactory(this);

        setupNewTripSegmentScreen();

        //The left panel contains the list of saved segments
        JPanel leftPanel = new JPanel();
        setupLeftPanel(leftPanel);

        //The center panel contains the title and the form being displayed
        setupCenterPanel();

        //The bottom panel contains the new button, error label, save, and delete buttons
        JPanel bottomPanel = new JPanel();
        setupBottomPanel(bottomPanel);

        add(centerPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets up the trip segment JList that displays all saved trip segments
     * @param leftPanel
     */
    private void setupLeftPanel(JPanel leftPanel) {
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(200, 100));
        listModel = new DefaultListModel<>();
        tripSegmentJList = new JList<>(listModel);
        tripSegmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tripSegmentJList.setLayoutOrientation(JList.VERTICAL);
        tripSegmentJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!deleting) {
                    setTripSegment(tripSegmentJList.getSelectedValue());
                }
            }
        });

        JLabel tripSegmentsLabel = new JLabel("Trip Segments");
        tripSegmentsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        leftPanel.add(tripSegmentsLabel, BorderLayout.NORTH);
        leftPanel.add(tripSegmentJList, BorderLayout.CENTER);
    }


    /**
     * The center panel contains a title bar and a place for the forms to be displayed
     */
    private void setupCenterPanel() {
        segmentTitle = new JLabel();
        segmentLabel = new JLabel();
        centerPanel = new JPanel(new BorderLayout());

        segmentTitle.setAlignmentX(CENTER_ALIGNMENT);
        segmentLabel.setAlignmentX(CENTER_ALIGNMENT);
        segmentTitle.setFont(new Font("Sans-Serif", Font.BOLD, 18));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        titlePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        titlePanel.add(segmentTitle);
        titlePanel.add(segmentLabel);

        centerPanel.add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Creates the new segment selection screen
     */
    private void setupNewTripSegmentScreen() {
        tripSegmentTypePicker = new TripSegmentTypePicker(this);
        tripSegmentTypePicker.setAlignmentX(CENTER_ALIGNMENT);
        tripSegmentTypePicker.setAlignmentY(CENTER_ALIGNMENT);
        currentlyDisplayedPanel = tripSegmentTypePicker;

    }

    /**
     * Displays the new segment selection screen
     */
    private void displayNewTripSegmentScreen() {
        if (isItOkToProceed()) {
            resetState();
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            clearTitle();
            changeCenterPanel(tripSegmentTypePicker);
        }
    }

    /**
     * Displays the new button, error label, save, and delete buttons.
     * @param bottomPanel
     */
    private void setupBottomPanel(JPanel bottomPanel) {
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        JButton newTripSegmentButton = new JButton("Add New Trip Segment");
        newTripSegmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNewTripSegmentScreen();
            }
        });

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

        saveButton = new JButton(("Save"));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCurrentSegment();
            }
        });
        saveButton.setEnabled(false);

        deleteButton = new JButton("Delete");
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.RED);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Delete confirmation
                if (JOptionPane.showConfirmDialog(DataEntryScreen.this, "Do you want to delete the current trip segment?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                    deleteCurrentTripSegment();
                }
            }
        });
        bottomPanel.add(newTripSegmentButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(errorLabel);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(saveButton);
        bottomPanel.add(deleteButton);
    }

    /**
     * This will open a blank form for the trip segment type specified
     * @param tripSegmentType
     */
    @Override
    public void editNewTripSegment(TripSegmentType tripSegmentType) {
        if (isItOkToProceed()) {
            resetState();
            if (currentlyDisplayedPanel != null) {
                remove(currentlyDisplayedPanel);
            }
            currentTripSegmentForm = formPanelFactory.getForm(tripSegmentType);
            currentTripSegmentForm.clear();
            deleteButton.setEnabled(false);
            saveButton.setEnabled(true);
            setTitle(tripSegmentType);
            changeCenterPanel(currentTripSegmentForm);
        }
    }

    /**
     * Sets the title for the center of the screen
     * @param tripSegmentType
     */
    public void setTitle(TripSegmentType tripSegmentType) {
        segmentTitle.setText(tripSegmentType + " Trip Segment");
    }

    /**
     * Loads up the screen for the specified trip segment and loads the data onto it.
     * @param segment
     */
    public void setTripSegment(TripSegment segment) {
        if (isItOkToProceed()) {
            resetState();
            currentTripSegmentForm = formPanelFactory.getForm(segment.getTripSegmentType());
            currentTripSegmentForm.setTripSegment(segment);
            segmentLabel.setText(segment.toString());
            deleteButton.setEnabled(true);
            saveButton.setEnabled(true);
            setTitle(segment.getTripSegmentType());
            changeCenterPanel(currentTripSegmentForm);
        }
    }

    /**
     * Called whenever the data changes in one of the forms.
     * @param tripSegment
     */
    @Override
    public void dataUpdated(TripSegment tripSegment) {
        unsavedChanges = true;
        segmentLabel.setText(tripSegment.toString());
    }

    /**
     * Called when a field fails validation
     * @param message
     */
    @Override
    public void inputValidationFailed(String message) {
        errorLabel.setText(message);
    }

    /**
     * Clears the error label text
     */
    @Override
    public void clearValidationErrors() {
        errorLabel.setText("");
    }

    /**
     * Changes the center panel to the specified panel
     * @param panel
     */
    private void changeCenterPanel(JPanel panel) {
        centerPanel.remove(currentlyDisplayedPanel);
        currentlyDisplayedPanel = panel;
        centerPanel.add(currentlyDisplayedPanel, BorderLayout.CENTER);
        refresh();
    }

    /**
     * Saves the current trip segment
     * @return
     */
    public boolean saveCurrentSegment() {
        if(!currentTripSegmentForm.hasErrors()) {
            TripSegment tripSegment = currentTripSegmentForm.flushChanges();
            if (!listModel.contains(tripSegment)) {
                listModel.addElement(tripSegment);
            }
            resetState();
            setTripSegment(tripSegment);
            refresh();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Called to check if there are any unsaved changes.  If there are the user will be prompt to save them.
     * @return
     */
    public boolean isItOkToProceed() {
        boolean isItOK = true;
        if (currentTripSegmentForm != null && unsavedChanges) {
            int choice = JOptionPane.showConfirmDialog(this, "Unsaved changes detected.  Would you like to save your changes?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == 2) {
                isItOK = false;
            } else if (choice == 0) {
                isItOK = saveCurrentSegment();
            }
        }
        return isItOK;
    }

    /**
     * Deletes the trip segment
     */
    public void deleteCurrentTripSegment() {
        deleting = true;
        tripSegmentJList.clearSelection();
        listModel.removeElement(currentTripSegmentForm.getTripSegment());
        deleteButton.setEnabled(false);
        saveButton.setEnabled(false);
        centerPanel.remove(currentTripSegmentForm);
        resetState();
        refresh();
        deleting = false;
    }

    /**
     * Clears the title on the screen
     */
    private void clearTitle() {
        segmentLabel.setText("");
        segmentTitle.setText("");
    }

    /**
     * Clears the error label and sets the internal state to no changes have been made.
     */
    public void resetState() {
        unsavedChanges = false;
        errorLabel.setText("");
    }

    /**
     * Repaints the screen.
     */
    public void refresh() {
        revalidate();
        repaint();
    }

}
