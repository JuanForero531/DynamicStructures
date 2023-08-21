package p4.view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import p4.presenter.CombinationPresenter;

public class CombinationView extends JFrame {
    private JTextArea inputTextArea;
    private JTextArea powerSetTextArea;
    private CombinationPresenter presenter;

    public CombinationView() {
        presenter = new CombinationPresenter(this);
        setupUI();
    }

    private void setupUI() {
        setTitle("Power Set Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel("Enter a set of elements (comma-separated):");
        inputTextArea = new JTextArea(3, 30);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        JButton generateButton = new JButton("Generate Power Set");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                presenter.generatePowerSet(input);
            }
        });

        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(inputScrollPane, BorderLayout.CENTER);
        inputPanel.add(generateButton, BorderLayout.SOUTH);

        powerSetTextArea = new JTextArea(10, 30);
        JScrollPane powerSetScrollPane = new JScrollPane(powerSetTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(powerSetScrollPane, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public void updatePowerSetTextArea(Set<Set<String>> powerSet) {
        powerSetTextArea.setText("");
        for (Set<String> subset : powerSet) {
            powerSetTextArea.append(subset.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CombinationView combinationView = new CombinationView();
            }
        });
    }
}

