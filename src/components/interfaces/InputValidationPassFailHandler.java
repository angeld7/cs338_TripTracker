package components.interfaces;

import javax.swing.*;

/**
 * Created by Angel on 7/19/2016.
 */
public interface InputValidationPassFailHandler {
    void fail(String message, JComponent component);
    void pass(JComponent component);
}
