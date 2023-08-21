package p4.presenter;


import java.util.Set;
import javax.swing.SwingUtilities;
import p4.model.CombinationModel;
import p4.view.CombinationView;

public class CombinationPresenter {
    private CombinationView view;
    private CombinationModel model;

    public CombinationPresenter(CombinationView view) {
        this.view = view;
        this.model = new CombinationModel();
    }

    public void generatePowerSet(String input) {
        Set<Set<String>> powerSet = model.generatePowerSet(input);
        view.updatePowerSetTextArea(powerSet);
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


