package ui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    public DashboardFrame() {

        setTitle("Pet Care Management System");

        setSize(1200,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new DashboardPanel(),"dashboard");
        contentPanel.add(new PetPanel(),"pet");
        contentPanel.add(new OwnerPanel(),"owner");
        contentPanel.add(new HealthPanel(),"health");
        contentPanel.add(new FeedingPanel(), "feeding");
        contentPanel.add(
                new VaccinationPanel(),
                "vaccination"
        );

        contentPanel.add(
                new AppointmentPanel(),
                "appointment"
        );

        contentPanel.add(
                new ActivityPanel(),
                "activity"
        );
        contentPanel.add(
                new ExpensePanel(),
                "expense"
        );

        contentPanel.add(
                new ReminderPanel(),
                "reminder"
        );
        //-------------------------
        // Header
        //-------------------------

        JLabel title = new JLabel(
                "PET CARE MANAGEMENT SYSTEM",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 28));

        title.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(title, BorderLayout.NORTH);

        //-------------------------
        // Menu Panel
        //-------------------------

        JPanel menu = new JPanel();

        menu.setLayout(new GridLayout(12,1,5,5));

        JButton dashboardBtn = new JButton("Dashboard");
        dashboardBtn.addActionListener(e ->
                cardLayout.show(contentPanel,"dashboard"));

        JButton petBtn = new JButton("Pet Management");
        petBtn.addActionListener(e ->
                cardLayout.show(contentPanel,"pet"));

        JButton ownerBtn = new JButton("Owner Management");
        ownerBtn.addActionListener(e ->

                cardLayout.show(contentPanel,"owner"));


        JButton healthBtn = new JButton("Health Records");
        healthBtn.addActionListener(e->

                cardLayout.show(contentPanel,"health"));

        JButton feedingBtn = new JButton("Feeding Schedule");
        feedingBtn.addActionListener(e -> cardLayout.show(contentPanel, "feeding"));


        JButton vaccineBtn = new JButton("Vaccination");

        vaccineBtn.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "vaccination"
                )
        );

        JButton appointmentBtn = new JButton("Appointment");
        appointmentBtn.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "appointment"
                )
        );

        JButton activityBtn = new JButton("Activity Tracking");
        activityBtn.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "activity"
                )
        );
        JButton expenseBtn = new JButton("Expense");
        expenseBtn.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "expense"
                )
        );
        JButton reminderBtn = new JButton("Reminder");
        reminderBtn.addActionListener(e ->
                cardLayout.show(
                        contentPanel,
                        "reminder"
                )
        );
        JButton backupBtn = new JButton("Backup");
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Exit Application?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if(choice == JOptionPane.YES_OPTION){

                System.exit(0);

            }

        });

        menu.add(dashboardBtn);
        menu.add(petBtn);
        menu.add(ownerBtn);
        menu.add(feedingBtn);
        menu.add(healthBtn);
        menu.add(vaccineBtn);
        menu.add(appointmentBtn);
        menu.add(activityBtn);

        menu.add(expenseBtn);
        menu.add(reminderBtn);
        menu.add(backupBtn);
        menu.add(exitBtn);

        add(menu,BorderLayout.WEST);

        add(contentPanel,BorderLayout.CENTER);

        //-------------------------
        // Footer
        //-------------------------

        JLabel footer = new JLabel(
                "Developed using Java Swing",
                SwingConstants.CENTER);

        footer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        add(footer, BorderLayout.SOUTH);

        //-------------------------

        setVisible(true);

    }

}