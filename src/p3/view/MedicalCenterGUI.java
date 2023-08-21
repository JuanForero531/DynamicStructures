package p3.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import p3.model.Patient;
import p3.view.MedicalCenterView;
import p3.presenter.MedicalCenterPresenter;

public class MedicalCenterGUI extends JFrame implements MedicalCenterView {
    private JTextField nameField;
    private JTextField severityField;
    private JTextArea patientQueueTextArea;
    private MedicalCenterPresenter presenter;

    public MedicalCenterGUI() {
        presenter = new MedicalCenterPresenter(this);
        setupUI();
    }

    private void setupUI() {
        setTitle("Medical Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("Patient Name:");
        nameField = new JTextField(10);
        JLabel severityLabel = new JLabel("Severity:");
        severityField = new JTextField(5);
        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.addPatient();
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(severityLabel);
        inputPanel.add(severityField);
        inputPanel.add(addButton);

        patientQueueTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(patientQueueTextArea);

        JPanel processPanel = new JPanel(new FlowLayout());
        JButton processButton = new JButton("Process Next Patient");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.processNextPatient();
            }
        });
        processPanel.add(processButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(processPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    @Override
    public String getPatientName() {
        return nameField.getText();
    }

    @Override
    public int getSeverity() {
         int severity=0;
        try {
             severity = Integer.parseInt(severityField.getText());
        }  catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MedicalCenterGUI.this,
                        "Invalid severity value. Please enter a valid number.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                
            
        }return severity;
    }

    @Override
    public void clearPatientFields() {
        nameField.setText("");
        severityField.setText("");
    }

    @Override
    public void updatePatientQueue(PriorityQueue<Patient> patientQueue) {
        patientQueueTextArea.setText("");
        for (Patient patient : patientQueue) {
            patientQueueTextArea.append(patient.toString() + "\n");
        }
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showNextPatientDialog(String patientName) {
        JOptionPane.showMessageDialog(this, "Processing Patient: " + patientName, "Next Patient", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showEmptyQueueMessage() {
        JOptionPane.showMessageDialog(this, "No patients in the queue", "Empty Queue", JOptionPane.INFORMATION_MESSAGE);
    }
}