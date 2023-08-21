package p1.model;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class TaskManager {

    LinkedList<TaskModel> tasks;
    HashSet<String> users;

    public TaskManager() {
        tasks = new LinkedList<>();
        users = new HashSet<>(); // Se usa HashSet con el fin de manejar la repeticion de usuarios
    }

    public void addTask(TaskModel task) {
        tasks.add(task);
        users.add(task.assignedTo);
    }

    public LinkedList<TaskModel> getTasksByUser(String user) {
        LinkedList<TaskModel> userTasks = new LinkedList<>();
        for (TaskModel task : tasks) {
            if (task.assignedTo.equals(user)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

    public int getTaskCountByUser(String user) {
        int count = 0;
        for (TaskModel task : tasks) {
            if (task.assignedTo.equals(user)) {
                count++;
            }
        }
        return count;
    }

    public Set<String> getAllUsers() {
        return users;
    }
}
