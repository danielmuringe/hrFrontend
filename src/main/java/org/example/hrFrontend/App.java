package org.example.hrFrontend;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App {

    public static void main (String[] args) {
        ArrayList<String[]> userData = new ArrayList<String[]>(
        ) {{
            add(new String[]{"hussain", "test"});
            add(new String[]{"ali", "test"});
            add(new String[]{"hassan", "test"});
        }};

        JFrame emptyLoginNextPage = new JFrame() {{
            setTitle("Empty Page");
            JPanel panel = new JPanel() {{
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                add(new JLabel("This page is empty because the value 'loginNextPage' has not been supplied."));
                add(new JLabel("Check README.md for more information'"));
            }};
            add(panel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setLocationRelativeTo(null); // Center the window
            setSize(720, 500);
            setPreferredSize(new Dimension(720, 500));
        }};


        JFrame emptySignupNextPage = new JFrame() {{
            setTitle("Empty Page");
            JPanel panel = new JPanel() {{
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                add(new JLabel("This page is empty because the value 'signupNextPage' has not been supplied."));
                add(new JLabel("Check README.md for more information'"));
            }};
            add(panel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setLocationRelativeTo(null); // Center the window
            setSize(720, 500);
            setPreferredSize(new Dimension(720, 500));
        }};

        Signup signup = new Signup(
                userData,
                emptySignupNextPage,
                emptyLoginNextPage
        );
        signup.setVisible(true);

        Login login = new Login(
                userData,
                emptySignupNextPage,
                emptyLoginNextPage
        );
        login.setVisible(true);
    }
}
