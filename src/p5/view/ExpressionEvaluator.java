/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p5.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import p5.presenter.ExpressionPresenter;

public class ExpressionEvaluator extends JFrame {

    private JTextField inputField;
    private JLabel resultLabel;
    private ExpressionPresenter presenter;

    public ExpressionEvaluator() {
        setTitle("Expression Evaluator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        inputField = new JTextField(20);
        JButton evaluateButton = new JButton("Evaluate");
        resultLabel = new JLabel("Result: ");
        

        presenter = new ExpressionPresenter(this);

        evaluateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expression = inputField.getText();
                presenter.onEvaluateButtonClicked(expression);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(inputField);
        panel.add(evaluateButton);
        panel.add(resultLabel);

        add(panel);
    }

    public void setResultLabel(String result) {
        resultLabel.setText("Result: " + result);
    }
}
