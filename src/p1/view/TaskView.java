package p1.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;
import p1.model.TaskModel;
import p1.presenter.TaskPresenter;

public class TaskView extends JFrame {
    private JTextField descriptionField;
    private JTextField dueDateField;
    private JTextField assignedToField;
    private JTextArea taskListArea;
    private JLabel taskCountLabel;
    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    private JButton addButton;
    
    private TaskPresenter presenter;

    public TaskView() {
        setupUI();
    }

    public void setPresenter(TaskPresenter presenter) {
        this.presenter = presenter;
    }

    private void setupUI() {
        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel dueDateLabel = new JLabel("Due Date (dd/MM/yy):");
        dueDateField = new JTextField();
        JLabel assignedToLabel = new JLabel("Assigned To:");
        assignedToField = new JTextField();
        addButton = new JButton("Add Task");
        
        addButton.addActionListener(e -> {
            String description = descriptionField.getText();
            String dueDateText = dueDateField.getText();
            String assignedTo = assignedToField.getText();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            dateFormat.setLenient(false);
            Date dueDate = null;
            try {
                dueDate = dateFormat.parse(dueDateText);
            } catch (ParseException ex) {
                showErrorMessage("Invalid date format");
                return;
            }
            
            presenter.addTask(description, dueDate, assignedTo);
        });

        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionField);
        inputPanel.add(dueDateLabel);
        inputPanel.add(dueDateField);
        inputPanel.add(assignedToLabel);
        inputPanel.add(assignedToField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        taskListArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(taskListArea);

        taskCountLabel = new JLabel();

        JPanel userListPanel = new JPanel(new BorderLayout());
        JLabel userListLabel = new JLabel("Users:");
        userList = new JList<>();
        userListModel = new DefaultListModel<>();
        userList.setModel(userListModel);
        JScrollPane userListScrollPane = new JScrollPane(userList);
        userListPanel.add(userListLabel, BorderLayout.NORTH);
        userListPanel.add(userListScrollPane, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(taskCountLabel, BorderLayout.SOUTH);
        add(userListPanel, BorderLayout.WEST);

        pack();
        setVisible(true);
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public String getDueDate() {
        return dueDateField.getText();
    }

    public String getAssignedTo() {
        return assignedToField.getText();
    }

  public void updateTaskList(LinkedList<TaskModel> tasks) {
    taskListArea.setText(""); // Limpiar el contenido anterior
    
    for (TaskModel task : tasks) {
        taskListArea.append(task.toString() + "\n");
    }
}

    public void updateUserList(Set<String> users) {
        userListModel.clear();
        for (String user : users) {
            userListModel.addElement(user);
        }
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void setAddButtonListener(ActionListener listener) {
    addButton.addActionListener(listener);
}


    
}

