package org.example.hrFrontend;

import javax.swing.*;
import java.util.ArrayList;

public class Login extends EntryPage {

    public <T> Login (ArrayList<String[]> userData, T signupNextPage, T loginNextPage) {

        super(EntryPageType.LOGIN, (EntryPage parentComponent) -> {

            String username = parentComponent.usernameField.getText();
            String password = convertCharArrayToString(parentComponent.passwordField.getPassword());

            boolean loginStatus = false;

            for (String[] userDatum : userData) {
                if (userDatum[0].equals(username) && userDatum[1].equals(password)) {
                    loginStatus = true;
                    break;
                }
            }

            if (loginStatus) {
                parentComponent.dispose();
                ((JFrame) loginNextPage).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(parentComponent, "Invalid username or password.", "Invalid Login Details", JOptionPane.ERROR_MESSAGE);
            }
            return null;
        });

        setVisible(true);

    }
}
