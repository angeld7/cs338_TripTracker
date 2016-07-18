package components;

import components.form.FormPanel;
import components.form.FormPanelFactory;
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
public class DataEntryScreen extends JPanel {
    private JPanel currentlyDisplayedPanel;
    private JSplitPane splitPane;
    private TripSegmentTypePicker tripSegmentTypePicker;
    private JList<TripSegment> tripSegmentJList;
    private DefaultListModel<TripSegment> listModel;
    private FormPanel currentTripSegmentForm;
    private JButton saveButton;
    private JButton deleteButton;

    private boolean deleting = false;

    public DataEntryScreen() {
        super(new BorderLayout());

        JPanel leftPanel = new JPanel();
        setupLeftPanel(leftPanel);

        setupNewTripSegmentScreen();

        JPanel bottomPanel = new JPanel();
        setupBottomPanel(bottomPanel);

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
                if(!deleting) {
                    setTripSegment(tripSegmentJList.getSelectedValue());
                }
            }
        });

        leftPanel.add(new JLabel("Trip Segments"), BorderLayout.NORTH);
        leftPanel.add(tripSegmentJList, BorderLayout.CENTER);
    }

    private void setupNewTripSegmentScreen() {
        tripSegmentTypePicker = new TripSegmentTypePicker(this);
        tripSegmentTypePicker.setAlignmentX(CENTER_ALIGNMENT);
        tripSegmentTypePicker.setAlignmentY(CENTER_ALIGNMENT);
        currentlyDisplayedPanel = tripSegmentTypePicker;

    }

    private void displayNewTripSegmentScreen() {
        saveButton.setEnabled(false);
        deleteButton.setEnabled(false);
        changeCenterPanel(tripSegmentTypePicker);
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
        bottomPanel.add(saveButton);
        bottomPanel.add(deleteButton);
    }

    public void editNewTripSegment(TripSegmentType tripSegmentType) {
        if (currentlyDisplayedPanel != null) {
            remove(currentlyDisplayedPanel);
        }
        currentTripSegmentForm = FormPanelFactory.getForm(tripSegmentType);
        currentTripSegmentForm.clear();
        deleteButton.setEnabled(false);
        saveButton.setEnabled(true);
        changeCenterPanel(currentTripSegmentForm);
    }

    private void changeCenterPanel(JPanel panel) {
        if (currentTripSegmentForm != null && currentTripSegmentForm.hasUnsavedChanges()) {
            int choice = JOptionPane.showConfirmDialog(this, "Unsaved changes detected.  Would you like to save your changes?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == 0) {
                saveCurrentSegment();
            } else if (choice == 2) {
                return;
            }
        }
        remove(currentlyDisplayedPanel);
        currentlyDisplayedPanel = panel;
        add(currentlyDisplayedPanel, BorderLayout.CENTER);
        refresh();
    }

    public void setTripSegment(TripSegment segment) {
        currentTripSegmentForm = FormPanelFactory.getForm(segment.getTripSegmentType());
        currentTripSegmentForm.setTripSegment(segment);
        deleteButton.setEnabled(true);
        saveButton.setEnabled(true);
        changeCenterPanel(currentTripSegmentForm);
    }

    public void saveCurrentSegment() {
        TripSegment tripSegment = currentTripSegmentForm.flushChanges();
        if (!listModel.contains(tripSegment)) {
            listModel.addElement(tripSegment);
        }
        setTripSegment(tripSegment);
        refresh();
    }

    public void deleteCurrentTripSegment() {
        deleting = true;
        tripSegmentJList.clearSelection();
        listModel.removeElement(currentTripSegmentForm.getTripSegment());
        deleteButton.setEnabled(false);
        saveButton.setEnabled(false);
        remove(currentTripSegmentForm);
        refresh();
        deleting = false;
    }

    public void refresh() {
        revalidate();
        repaint();
    }

}
