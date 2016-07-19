package components.utility;

import components.interfaces.InputValidationPassFailHandler;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.*;
import java.util.Date;

/**
 * Created by Angel on 7/17/2016.
 */
public class FormUtility {

    private static final GridBagConstraints LAST_CONSTRAINTS;
    private static final GridBagConstraints MIDDLE_CONSTRAINTS;
    private static final GridBagConstraints LABEL_CONSTRAINTS;

    static {
        LAST_CONSTRAINTS = new GridBagConstraints();
        LAST_CONSTRAINTS.fill = GridBagConstraints.HORIZONTAL;
        LAST_CONSTRAINTS.anchor = GridBagConstraints.NORTHWEST;
        LAST_CONSTRAINTS.weightx = 1.0;
        LAST_CONSTRAINTS.gridwidth = GridBagConstraints.REMAINDER;
        LAST_CONSTRAINTS.insets = new Insets(3, 3, 3, 3);

        MIDDLE_CONSTRAINTS = (GridBagConstraints) LAST_CONSTRAINTS.clone();
        MIDDLE_CONSTRAINTS.gridwidth = GridBagConstraints.RELATIVE;

        LABEL_CONSTRAINTS = (GridBagConstraints) LAST_CONSTRAINTS.clone();
        LABEL_CONSTRAINTS.weightx = 0.0;
        LABEL_CONSTRAINTS.gridwidth = 1;
    }

    public static void addLabel(String text, Container parent) {
        JLabel label = new JLabel(text + ":");
        addSetWidthField(label, parent);
    }

    public static void addRequiredLabel(String text, Container parent) {
        JLabel label = new JLabel("*"+text + ":");
        addSetWidthField(label, parent);
    }

    public static void addSetWidthField(Component c, Container parent) {
        addWithConstraints(c, parent, LABEL_CONSTRAINTS);
    }

    public static void addSetWidthField(Component c, Container parent, int width) {
        setFieldPreferredWidth(c, width);
        addWithConstraints(c, parent, LABEL_CONSTRAINTS);
    }


    public static void addLastField(Component c, Container parent) {
        addWithConstraints(c, parent, LAST_CONSTRAINTS);
    }

    public static void addMiddleField(Component c, Container parent) {
        addWithConstraints(c, parent, MIDDLE_CONSTRAINTS);
    }

    public static void addWidthScaledField(Component c, Container parent, int widthScale) {
        GridBagConstraints constraints = (GridBagConstraints) LAST_CONSTRAINTS.clone();
        constraints.gridwidth = widthScale;
        addWithConstraints(c, parent, constraints);
    }

    public static void addBlankLastField(Container parent) {
        addLastField(new JPanel(), parent);
    }

    private static void addWithConstraints(Component c, Container parent, GridBagConstraints constraints) {
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, constraints);
        parent.add(c);
    }

    private static void setFieldPreferredWidth(Component c, int width) {
        Dimension dimension = c.getPreferredSize();
        dimension.width = width;
        c.setPreferredSize(dimension);
    }

    public static JFormattedTextField getRequiredDateField(final InputValidationPassFailHandler handler, final String fieldName) {
        final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        final JFormattedTextField field = new JFormattedTextField(new DateFormatter(dateFormat));
        field.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                if (field.getText() == null || field.getText().trim().equals("")) {
                    handler.fail(fieldName + " cannot be blank", field);
                    validationFailed(field);
                } else {
                    try {
                        dateFormat.parse(field.getText());
                    } catch (ParseException e) {
                        handler.fail("Input must match mm/dd/yy", field);
                        validationFailed(field);
                        return false;
                    }
                    validationSuccess(field);
                    handler.pass(field);
                }
                return true;
            }
        });
        return field;
    }

    public static JFormattedTextField getCurrencyField(final InputValidationPassFailHandler handler) {
        final DecimalFormat format = new DecimalFormat(("#,###,###,##0.00"));
        NumberFormatter formatter = new NumberFormatter(format);
        final JFormattedTextField field = new JFormattedTextField(formatter);
        field.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                if (field.getText() != null && !field.getText().trim().equals("")) {
                    try {
                        format.parse(field.getText());
                    } catch (ParseException e) {
                        handler.fail("Input must be a number", field);
                        validationFailed(field);
                        return false;
                    }
                }
                handler.pass(field);
                validationSuccess(field);
                return true;
            }
        });
        return field;
    }

    public static void validationFailed(JComponent field) {
        field.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    public static void validationSuccess(JComponent field) {
        field.setBorder(UIManager.getBorder("TextField.border"));
    }

    public static void addRequiredValidator(final JTextComponent field, final InputValidationPassFailHandler handler, final String fieldName) {
        field.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                if (field.getText() == null || field.getText().trim().equals("")) {
                    handler.fail(fieldName + " cannot be blank", field);
                    validationFailed(field);
                } else {
                    validationSuccess(field);
                    handler.pass(field);
                }
                return true;
            }
        });
    }

    public static JFormattedTextField getRequiredTimeField(final InputValidationPassFailHandler handler, final String fieldName) {
        final DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        final JFormattedTextField field = new JFormattedTextField(new DateFormatter(dateFormat));
        field.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                if (field.getText() == null || field.getText().trim().equals("")) {
                    handler.fail(fieldName + " cannot be blank", field);
                    validationFailed(field);
                } else {
                    try {
                        dateFormat.parse(field.getText());
                    } catch (ParseException e) {
                        handler.fail("Input must match hh:mm am/pm", field);
                        validationFailed(field);
                        return false;
                    }
                    validationSuccess(field);
                    handler.pass(field);
                }
                return true;

            }
        });
        return field;
    }

    public static Date parseDateFromDateAndTime(JTextField date, JTextField time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy h:mm a");
        try {
            return dateFormat.parse(date.getText() + " " + time.getText());
        } catch (ParseException e) {
            // We want still want to continue if the date entered is invalid
        }
        return null;
    }
}
