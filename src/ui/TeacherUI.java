package ui;

import service.QuestionService;

import javax.swing.*;
import java.awt.*;

public class TeacherUI {

    public static void show() {

        JFrame f = new JFrame("CodePet - Teacher Panel");
        f.setSize(500, 450);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Same colorful background as StudentUI
        f.getContentPane().setBackground(Theme.BACKGROUND);

        // Title
        JLabel title = new JLabel("👨‍🏫 Teacher Panel", SwingConstants.CENTER);
        title.setBounds(120, 20, 250, 40);
        title.setFont(Theme.TITLE_FONT);

        // Add Question Button
        JButton addBtn = new JButton("Add Question");
        addBtn.setBounds(140, 90, 200, 45);
        addBtn.setFont(new Font("Arial", Font.BOLD, 18));

        // View Questions Button
        JButton viewBtn = new JButton("View Questions");
        viewBtn.setBounds(140, 160, 200, 45);
        viewBtn.setFont(new Font("Arial", Font.BOLD, 18));

        // Delete Question Button
        JButton deleteBtn = new JButton("Delete Question");
        deleteBtn.setBounds(140, 230, 200, 45);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 18));

        // Logout Button
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(140, 300, 200, 45);
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 18));

        f.add(title);
        f.add(addBtn);
        f.add(viewBtn);
        f.add(deleteBtn);
        f.add(logoutBtn);

        // ADD QUESTION
        addBtn.addActionListener(e -> {

            String q = JOptionPane.showInputDialog(f, "Enter Question:");
            String o1 = JOptionPane.showInputDialog(f, "Option 1:");
            String o2 = JOptionPane.showInputDialog(f, "Option 2:");
            String o3 = JOptionPane.showInputDialog(f, "Option 3:");
            String o4 = JOptionPane.showInputDialog(f, "Option 4:");

            int correct = Integer.parseInt(
                    JOptionPane.showInputDialog(f, "Correct Option (1-4):")
            );

            String[] levels = {"easy", "medium", "hard"};

            String diff = (String) JOptionPane.showInputDialog(
                    f,
                    "Choose Difficulty:",
                    "Difficulty",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    levels,
                    levels[0]
            );

            if (diff == null) return;

            QuestionService.add(q, o1, o2, o3, o4, correct, diff);

            JOptionPane.showMessageDialog(f, "Question Added Successfully!");
        });

        // VIEW QUESTIONS
        viewBtn.addActionListener(e -> {

            StringBuilder sb = new StringBuilder();

            for (model.Question q : QuestionService.getAll()) {
                sb.append(q.toString()).append("\n");
            }

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(450, 400));

            JOptionPane.showMessageDialog(
                    f,
                    scroll,
                    "All Questions",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // DELETE QUESTION
        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(
                        JOptionPane.showInputDialog(f, "Enter Question ID:")
                );

                QuestionService.delete(id);

                JOptionPane.showMessageDialog(f, "Question Deleted Successfully!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid ID!");
            }
        });

        // LOGOUT
        logoutBtn.addActionListener(e -> {
            f.dispose();
            LoginUI.show();
        });

        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}