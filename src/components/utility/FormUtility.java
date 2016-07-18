package components.utility;

import javax.swing.*;
import java.awt.*;

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
        JLabel label = new JLabel(text);
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


}
