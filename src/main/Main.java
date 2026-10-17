package main;

import javax.swing.SwingUtilities;
import ui.DashboardFrame;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new DashboardFrame();
        });

    }

}


