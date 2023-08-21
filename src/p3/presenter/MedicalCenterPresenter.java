package p3.presenter;


import java.util.PriorityQueue;
import javax.swing.SwingUtilities;
import p3.model.Patient;
import p3.view.MedicalCenterGUI;
import p3.view.MedicalCenterView;


public class MedicalCenterPresenter {
    private PriorityQueue<Patient> patientQueue;
    private MedicalCenterView view;

    public MedicalCenterPresenter(MedicalCenterView view) {
        patientQueue = new PriorityQueue<>();
        this.view = view;
    }

    public void addPatient() {
        String name = view.getPatientName();
        int severity = view.getSeverity();

        Patient patient = new Patient(name, severity);
        patientQueue.add(patient);

        view.clearPatientFields();
        view.updatePatientQueue(patientQueue);
    }

    public void processNextPatient() {
        if (!patientQueue.isEmpty()) {
            Patient nextPatient = patientQueue.poll();
            view.showNextPatientDialog(nextPatient.getName());
            view.updatePatientQueue(patientQueue);
        } else {
            view.showEmptyQueueMessage();
        }
    }
    
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MedicalCenterGUI medicalCenterGUI = new MedicalCenterGUI();
            }
        });
    }
}
