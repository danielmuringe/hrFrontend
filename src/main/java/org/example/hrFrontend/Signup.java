package org.example.hrFrontend;

import javax.swing.*;
import java.util.ArrayList;

public class Signup extends EntryPage {

    public <T> Signup (ArrayList<String[]> userData, T signupNextPage, T loginNextPage) {

        super(EntryPageType.SIGNUP, (parentComponent) -> {

            String username = parentComponent.usernameField.getText();

            boolean signupStatus = true;

            for (String[] userDatum : userData) {
                if (userDatum[0].equals(username)) {
                    signupStatus = false;
                    break;
                }
            }

            if (signupStatus) {
                parentComponent.dispose();
                ((JFrame) signupNextPage).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(parentComponent, "Username already exists.");
            }
            return null;

        });

        setVisible(true);

    }
}
