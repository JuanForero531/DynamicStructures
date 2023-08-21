package p1.model;

import java.util.*;

import java.util.Date;

public class TaskModel {

    String description;
    Date dueDate;
    String assignedTo;

    public TaskModel(String description, Date dueDate, String assignedTo) {
        this.description = description;
        this.dueDate = dueDate;
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Due Date: " + dueDate + ", Assigned To: " + assignedTo;
    }
}


