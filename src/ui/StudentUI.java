package ui;

import model.Pet;
import model.Question;
import service.QuestionService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class StudentUI {

    public static void show() {

        JFrame f = new JFrame("CodePet");
        f.setSize(400,450);
        f.setLayout(null);

        // ✅ Always on top (desktop pet feel)
        f.setAlwaysOnTop(true);

        // ✅ Background color
        f.getContentPane().setBackground(Theme.BACKGROUND);// light orange

        // Title
        JLabel title = new JLabel("🐾 CodePet", SwingConstants.CENTER);
        title.setBounds(100,10,200,30);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // ✅ Resize Image
        ImageIcon original = new ImageIcon("happy.gif");
        Image scaled = original.getImage().getScaledInstance(180,180, Image.SCALE_DEFAULT);
        JLabel petLabel = new JLabel(new ImageIcon(scaled));
        petLabel.setBounds(110,50,180,180);

        // Health Bar
        JProgressBar healthBar = new JProgressBar(0,100);
        healthBar.setBounds(100,240,200,20);
        healthBar.setValue(100);
        healthBar.setForeground(Color.GREEN);

        // Button
        JButton solveBtn = new JButton("Solve Question");
        solveBtn.setBounds(120,280,160,35);

        f.add(title);
        f.add(petLabel);
        f.add(healthBar);
        f.add(solveBtn);

        // Pet Object
        Pet pet = new Pet();

        // Health decay
        pet.startDecay(() -> {
            SwingUtilities.invokeLater(() -> {
                healthBar.setValue(pet.health);

                if(pet.health < 40) {
                    ImageIcon sad = new ImageIcon("sadlion.gif");
                    Image sadScaled = sad.getImage().getScaledInstance(180,180, Image.SCALE_DEFAULT);
                    petLabel.setIcon(new ImageIcon(sadScaled));
                    healthBar.setForeground(Color.RED);
                } else {
                    ImageIcon happy = new ImageIcon("happylion.gif");
                    Image happyScaled = happy.getImage().getScaledInstance(180,180, Image.SCALE_DEFAULT);
                    petLabel.setIcon(new ImageIcon(happyScaled));
                    healthBar.setForeground(Color.GREEN);
                }
            });
        });

        // Button Action
        solveBtn.addActionListener(e -> {

            String[] levels = {"easy", "medium", "hard"};

            String selected = (String) JOptionPane.showInputDialog(
                    f,
                    "Choose difficulty:",
                    "Select Level",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    levels,
                    levels[0]
            );

            if(selected == null) return;

            List<Question> list = QuestionService.getByDifficulty(selected);

            if(list.isEmpty()) {
                JOptionPane.showMessageDialog(f, "No questions available!");
                return;
            }

            Question q = list.get(new Random().nextInt(list.size()));

            int ans = JOptionPane.showOptionDialog(
                    f,
                    q.question,
                    "Question",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    q.options,
                    q.options[0]
            );

            if(ans == -1) return;

            if(ans + 1 == q.correct) {
                pet.increase(q.difficulty);
                JOptionPane.showMessageDialog(f,
                        "Correct! +" + q.difficulty.toUpperCase() + " boost 🎉");
            } else {
                JOptionPane.showMessageDialog(f, "Wrong 😢");
            }
        });

        // ❌ DO NOT close automatically
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        f.setVisible(true);
    }
}