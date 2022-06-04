package online.adambem.btac;

public class Calculator {

    private double[] numbers;

    private Operation operation;

    public Calculator() {
        numbers = new double[2];
    }

    public double doOperation() {
        double result = switch (operation) {
            case ADD -> numbers[0] + numbers[1];
            case SUBTRACT -> numbers[0] - numbers[1];
            case MULTIPLY -> numbers[0] * numbers[1];
            case DIVIDE -> numbers[0] / numbers[1];
        };

        numbers = new double[2];
        operation = null;
        return result;
    }

    // ##############################
    // Getters & Setters
    // ##############################

    public void setNumberOne(double number) {
        this.numbers[0] = number;
    }

    public void setNumberTwo(double number) {
        this.numbers[1] = number;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
