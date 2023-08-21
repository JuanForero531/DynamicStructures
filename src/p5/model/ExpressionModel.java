
package p5.model;

import java.util.Stack;

public class ExpressionModel {
    private Stack<Character> operators;
    private Stack<Double> values;

    public ExpressionModel() {
        operators = new Stack<>();
        values = new Stack<>();
    }

    public double evaluateExpression(String expression) {
        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if (Character.isDigit(c)) {
                    StringBuilder numBuilder = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        numBuilder.append(expression.charAt(i));
                        i++;
                    }
                    i--; // Move back one step to correctly process the next character
                    values.push(Double.parseDouble(numBuilder.toString()));
                } else if (c == '(') {
                    operators.push(c);
                } else if (c == ')') {
                    while (operators.peek() != '(') {
                        evaluateTop();
                    }
                    operators.pop(); // Pop '('
                } else if (isOperator(c)) {
                    while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                        evaluateTop();
                    }
                    operators.push(c);
                }
            }
            while (!operators.isEmpty()) {
                evaluateTop();
            }
            return values.pop();
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }

    private void evaluateTop() {
        char operator = operators.pop();
        double value2 = values.pop();
        double value1 = values.pop();
        switch (operator) {
            case '+':
                values.push(value1 + value2);
                break;
            case '-':
                values.push(value1 - value2);
                break;
            case '*':
                values.push(value1 * value2);
                break;
            case '/':
                values.push(value1 / value2);
                break;
        }
    }
}