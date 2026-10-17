package ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {

        setLayout(new BorderLayout());

        JLabel welcome = new JLabel(
                "<html><center>"
                        + "<h1>Welcome to Pet Care Management System</h1>"
                        + "<br>"
                        + "Select a module from the left menu."
                        + "</center></html>",
                SwingConstants.CENTER);

        add(welcome, BorderLayout.CENTER);
    }
}
