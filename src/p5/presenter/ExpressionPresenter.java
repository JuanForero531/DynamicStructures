
package p5.presenter;

import javax.swing.SwingUtilities;
import p5.view.ExpressionEvaluator;
import p5.model.ExpressionModel;

public class ExpressionPresenter {
    private ExpressionModel model;
    private ExpressionEvaluator view;

    public ExpressionPresenter(ExpressionEvaluator view) {
        this.view = view;
        model = new ExpressionModel();
    }

    public void onEvaluateButtonClicked(String expression) {
        double result = model.evaluateExpression(expression);
        view.setResultLabel(String.valueOf(result));
    }
    
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ExpressionEvaluator evaluator = new ExpressionEvaluator();
                evaluator.setVisible(true);
            }
        });
    }
}