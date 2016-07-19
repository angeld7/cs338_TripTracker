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

/**
 * Created by Angel on 7/16/2016.
 */
public class DataEntryScreen extends JPanel implements FormPresenter {
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

    private boolean deleting = false;
    private boolean unsavedChanges = false;

    public DataEntryScreen() {
        super(new BorderLayout());
        formPanelFactory = new FormPanelFactory(this);
        setupNewTripSegmentScreen();

        JPanel leftPanel = new JPanel();
        setupLeftPanel(leftPanel);

        setupCenterPanel();

        JPanel bottomPanel = new JPanel();
        setupBottomPanel(bottomPanel);

        add(centerPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupLeftPanel(JPanel leftPanel) {
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(200, 100));
        listModel = new DefaultListModel<>();
        tripSegmentJList = new JList<>(listModel);
        tripSegmentJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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

    private void setupNewTripSegmentScreen() {
        tripSegmentTypePicker = new TripSegmentTypePicker(this);
        tripSegmentTypePicker.setAlignmentX(CENTER_ALIGNMENT);
        tripSegmentTypePicker.setAlignmentY(CENTER_ALIGNMENT);
        currentlyDisplayedPanel = tripSegmentTypePicker;

    }

    private void displayNewTripSegmentScreen() {
        if (isItOkToProceed()) {
            resetState();
            saveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            clearTitle();
            changeCenterPanel(tripSegmentTypePicker);
        }
    }

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

    public void setTitle(TripSegmentType tripSegmentType) {
        segmentTitle.setText(tripSegmentType + " Trip Segment");
    }

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

    @Override
    public void dataUpdated(TripSegment tripSegment) {
        unsavedChanges = true;
        segmentLabel.setText(tripSegment.toString());
    }

    @Override
    public void inputValidationFailed(String message) {
        errorLabel.setText(message);
    }

    @Override
    public void clearValidationErrors() {
        errorLabel.setText("");
    }

    private void changeCenterPanel(JPanel panel) {
        centerPanel.remove(currentlyDisplayedPanel);
        currentlyDisplayedPanel = panel;
        centerPanel.add(currentlyDisplayedPanel, BorderLayout.CENTER);
        refresh();
    }

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

    private void clearTitle() {
        segmentLabel.setText("");
        segmentTitle.setText("");
    }

    public void resetState() {
        unsavedChanges = false;
        errorLabel.setText("");
    }

    public void refresh() {
        revalidate();
        repaint();
    }

}
