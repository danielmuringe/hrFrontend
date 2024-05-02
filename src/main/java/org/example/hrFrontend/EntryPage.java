package org.example.hrFrontend;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.function.Function;

public class EntryPage extends JFrame {

    public final JTextField usernameField;
    public final JPasswordField passwordField;
    public Function<EntryPage, Void> checkerFunction;


    public EntryPage (EntryPageType entryPageType, Function<EntryPage, Void> checkerFunction) {
        this.checkerFunction = checkerFunction;

        EntryPage parentComponent = this;

        // Set title string
        String title = entryPageType == EntryPageType.LOGIN ? "Login" : "Sign Up";

        // Create main panel
        JPanel mainPanel = new JPanel() {{
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(10, 50, 50, 50));
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setMaximumSize(new Dimension(500, 500));
            setPreferredSize(new Dimension(500, 500));
        }};

        // Create title label
        createTitleLabel(
                "System " + title,
                mainPanel
        );

        // Create input fields
        this.usernameField = (JTextField) EntryPage.createInput(
                "Username",
                FieldType.TEXT_FIELD,
                mainPanel
        );
        this.passwordField = (JPasswordField) EntryPage.createInput(
                "Password",
                FieldType.PASSWORD_FIELD,
                mainPanel
        );

        // Add horizontal divider
        mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // Create button panel
        createButtonPanel(
                new HashMap<String, Function<ActionEvent, Void>>() {
                    {
                        put("Reset", (eventAction) -> {
                            usernameField.setText("");
                            passwordField.setText("");
                            return null;
                        }); // Reset button
                        put(title, (event) -> checkerFunction.apply(parentComponent)); // Login/Sign Up button
                        put("Close", (eventAction) -> {
                            int exitConfirmation = JOptionPane.showConfirmDialog(parentComponent, "Are you sure you want to exit the application?", "Confirm exit", JOptionPane.YES_NO_OPTION);
                            if (exitConfirmation == JOptionPane.YES_OPTION) {
                                System.exit(0);
                            }
                            return null;
                        }); // Close button
                    }
                },
                mainPanel
        );

        // Create body panel
        JPanel bodyPanel = new JPanel(new BorderLayout()) {{
            setPreferredSize(new Dimension(720, 500));
            setMaximumSize(new Dimension(720, 500));
            setBorder(new LineBorder(Color.WHITE, 100, false));
            setBackground(Color.WHITE);
            setLayout(new BorderLayout());
            add(mainPanel, BorderLayout.CENTER);
        }};
        getContentPane().add(bodyPanel, BorderLayout.CENTER);

        // Set window properties
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the window

        // Set the size of the frame
        bodyPanel.setMaximumSize(new Dimension(720, 500));
        setSize(720, 500);
    }

    public static String convertCharArrayToString (char[] charArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : charArray) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void createButtonPanel (HashMap<String, Function<ActionEvent, Void>> buttonsInfo, Component parentComponent) {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(Box.createHorizontalGlue());

        final int buttonsNumber = buttonsInfo.size();
        int counter = 0;
        for (String text : buttonsInfo.keySet()) {
            JButton button = new JButton(text);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener((eventAction) -> buttonsInfo.get(text).apply(eventAction));
            buttonPanel.add(button);
            if (counter < buttonsNumber - 1) {
                buttonPanel.add(Box.createHorizontalStrut(20));
            }
            counter++;
        }
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ((JPanel) parentComponent).add(buttonPanel);
    }

    public static Component createInput (String label, FieldType fieldType, Component parentComponent) {
        Component field = null;
        if (fieldType == FieldType.TEXT_FIELD) {
            field = new JTextField(15);
        } else if (fieldType == FieldType.PASSWORD_FIELD) {
            field = new JPasswordField(15);
        }

        assert field != null;
        field.setPreferredSize(new Dimension(400, 30));
        field.setMaximumSize(new Dimension(400, 30));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(new JLabel(label));
        inputPanel.add(Box.createHorizontalStrut(10));
        inputPanel.add(field);
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ((JPanel) parentComponent).add(inputPanel, BorderLayout.CENTER);
        return field;
    }

    public static void createTitleLabel (String title, Component parentComponent) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to parent component
        ((JPanel) parentComponent).add(titleLabel);
        ((JPanel) parentComponent).add(new JSeparator(SwingConstants.HORIZONTAL)); // Add title divider
    }


    public enum FieldType {
        TEXT_FIELD,
        PASSWORD_FIELD
    }

    public enum EntryPageType {
        LOGIN,
        SIGNUP
    }
}
