package p3.view;


import java.util.PriorityQueue;
import p3.model.Patient;

public interface MedicalCenterView {
    String getPatientName();

    int getSeverity();

    void clearPatientFields();

    void updatePatientQueue(PriorityQueue<Patient> patientQueue);

    void showErrorMessage(String message);

    void showNextPatientDialog(String patientName);

    void showEmptyQueueMessage();
}
