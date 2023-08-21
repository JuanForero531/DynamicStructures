package p1.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.*;

import p1.model.TaskManager;


import p1.model.*;
import p1.view.TaskView;

public class TaskPresenter {
    private TaskManager model;
    private TaskView view;

    public TaskPresenter(TaskManager model, TaskView view) {
        this.model = model;
        this.view = view;

        updateUsersList();
        
        
        
    }

    private void updateUsersList() {
        Set<String> users = model.getAllUsers();
        view.updateUserList(users);
    }

    public void addTask(String description, Date dueDate, String assignedTo) {
        TaskModel task = new TaskModel(description, dueDate, assignedTo);
        updateUsersList(); 
        model.addTask(task);
       
        showTasksForUser(assignedTo);
    }

    public void showTasksForUser(String user) {
        LinkedList<TaskModel> tasks = model.getTasksByUser(user);
        view.updateTaskList(tasks);
        
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskManager model = new TaskManager();
            TaskView view = new TaskView();
            TaskPresenter presenter = new TaskPresenter(model, view);
            
            view.setPresenter(presenter);
            view.setVisible(true);
        });
    }
}


