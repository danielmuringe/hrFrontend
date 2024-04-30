package org.example.hrFrontend;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

public class Login extends JFrame {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final ArrayList<String[]> userData;
    private final Object loginNextPage;
    private final Object signupNextPage;
    public String username;
    public String password;
    private Boolean loginStatus = false;

    public <T> Login (ArrayList<String[]> userData, T loginNextPage, T signupNextPage) {

        // userData -> list of String arrays, each containing a username and password
        // loginNextPage -> the next page to navigate to after clicking the login button
        // signupNextPage -> the next page to navigate to after successful signup

        // ** See the App.java file for an example of how to use this class **


        Login parentComponent = this;
        this.userData = userData;
        this.loginNextPage = loginNextPage;
        this.signupNextPage = signupNextPage;


        // Create Label UI components
        JPanel bodyPanel = new JPanel(new BorderLayout());
        bodyPanel.setBorder(new LineBorder(Color.WHITE, 100, false));
        bodyPanel.setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 50, 50));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameField = new JTextField(15);
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));


        JButton resetButton = new JButton("Reset");
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton closeButton = new JButton("Close");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton signupButton = new JButton("Sign Up");
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                username = usernameField.getText();
                password = new String(passwordField.getPassword()); // Avoid storing password directly

                for (String[] userDatum : userData) {
                    if (userDatum[0].equals(username) && userDatum[1].equals(password)) {
                        loginStatus = true;
                        break;
                    }
                }

                if (loginStatus) {
                    dispose();
                    ((JFrame) loginNextPage).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(parentComponent, "Invalid username or password.");
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                System.exit(0);
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose();
                Signup signup = new Signup(userData, signupNextPage, loginNextPage);
            }
        });


        JPanel usernameInputPanel = new JPanel();
        usernameInputPanel.setLayout(new BoxLayout(usernameInputPanel, BoxLayout.X_AXIS));
        usernameInputPanel.add(usernameLabel);
        usernameInputPanel.add(Box.createHorizontalStrut(10));
        usernameInputPanel.add(usernameField);
        usernameInputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel passwordInputPanel = new JPanel();
        passwordInputPanel.setLayout(new BoxLayout(passwordInputPanel, BoxLayout.X_AXIS));
        passwordInputPanel.add(passwordLabel);
        passwordInputPanel.add(Box.createHorizontalStrut(10));
        passwordInputPanel.add(passwordField);
        passwordInputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(usernameInputPanel);
        mainPanel.add(passwordInputPanel);

        JPanel actionButtonsPanel = new JPanel();

        actionButtonsPanel.setLayout(new BoxLayout(actionButtonsPanel, BoxLayout.X_AXIS));
        actionButtonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));


        actionButtonsPanel.add(Box.createHorizontalGlue());
        actionButtonsPanel.add(loginButton);
        actionButtonsPanel.add(Box.createHorizontalStrut(10));
        actionButtonsPanel.add(resetButton);
        actionButtonsPanel.add(Box.createHorizontalStrut(0));

        actionButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(actionButtonsPanel);

        JPanel bottomButtonsPanel = new JPanel();
        bottomButtonsPanel.setLayout(new BoxLayout(bottomButtonsPanel, BoxLayout.X_AXIS));
        bottomButtonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        bottomButtonsPanel.add(Box.createHorizontalGlue());
        bottomButtonsPanel.add(signupButton);
        bottomButtonsPanel.add(Box.createHorizontalStrut(10));
        bottomButtonsPanel.add(closeButton);
        bottomButtonsPanel.setBackground(Color.WHITE);

        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(mainPanel, BorderLayout.CENTER);
        bodyPanel.add(bottomButtonsPanel, BorderLayout.SOUTH);
        getContentPane().add(bodyPanel, BorderLayout.CENTER);

        // Set window properties
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the window

        // Set the size of the frame
        bodyPanel.setMaximumSize(new Dimension(720, 500));
        setSize(720, 500);
        setVisible(true);

    }

    private JButton createButton (String text, Function eventAction) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> eventAction.apply(e));
        return button;
    }

    private JPanel createButtonPanel (ArrayList<JButton> buttons) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(Box.createHorizontalGlue());

        int buttonsNumber = buttons.size();
        for (int i = 0; i < buttonsNumber; i++) {
            JButton button = buttons.get(i);
            if ((i != 0) && (i != buttons.size() - 1)) buttonPanel.add(Box.createHorizontalStrut(10));
            buttonPanel.add(button);
            i++;
        }
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return buttonPanel;
    }

}