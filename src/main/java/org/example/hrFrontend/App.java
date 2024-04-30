package org.example.hrFrontend;

import javax.swing.*;
import java.util.ArrayList;

public class App {

    public static void main (String[] args) {
        ArrayList<String[]> userData = new ArrayList<String[]>(
        ) {{
            add(new String[]{"username1", "password1"});
            add(new String[]{"username2", "password2"});
        }};

        Login login = new Login(
                userData, new JFrame(), new JFrame()
        );

    }
}
