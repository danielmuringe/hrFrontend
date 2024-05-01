package org.example.hrFrontend;

import javax.swing.*;
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
            setSize(720, 500);
            setTitle("Empty Page");
            add(new JLabel("This page is empty because the value 'loginNextPage' has not been supplied. \n Check README.md for more information'"));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setLocationRelativeTo(null); // Center the window
        }};


        JFrame emptySignupNextPage = new JFrame() {{
            setSize(720, 500);
            setTitle("Empty Page");
            add(new JLabel("This page is empty because the value 'signupNextPage' has not been supplied. \n Check README.md for more information'"));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setLocationRelativeTo(null); // Center the window
        }};

        Signup signup = new Signup(
                userData,
                emptySignupNextPage,
                emptyLoginNextPage
        );
        signup.dispose();

        Login login = new Login(
                userData,
                emptySignupNextPage,
                emptyLoginNextPage
        );
        login.setVisible(true);
    }
}
