package online.adambem.btac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {

    private final String lightStyle = getClass().getResource("light.css").toExternalForm();
    private final String darkStyle = getClass().getResource("style.css").toExternalForm();

    Calculator calculator;

    @FXML
    private TextField numberField;

    @FXML
    private GridPane mainGrid;

    // Stores information if any number was typed
    // after last press of any operation button
    boolean numberTyped;

    // Stores information if numberField needs
    // clearing before typing another number
    boolean needClear;

    boolean darkModeEnabled;

    public Controller() {
        calculator = new Calculator();
        needClear = false;
        numberTyped = false;
        darkModeEnabled = true;
    }

    // '+', '-', '*', '/' buttons
    @FXML
    protected void setOperation(ActionEvent event) {
        Button source = (Button) event.getSource();

        // If some number was typed before clicking operation button...
        if (numberTyped) {
            double number = Double.parseDouble(numberField.getText());

            // If this is first operation, begin it
            if (calculator.getOperation() == null) {
                calculator.setNumberOne(number);
            }
            // If there is previous operation to finish, finish it
            // and assign result as first number to the new one
            else {
                calculator.setNumberTwo(number);
                double previousResult = calculator.doOperation();
                calculator.setNumberOne(previousResult);
            }
        }

        // Assign operation depending on clicked button
        switch (source.getText()) {
            case "+" -> calculator.setOperation(Operation.ADD);
            case "-" -> calculator.setOperation(Operation.SUBTRACT);
            case "*" -> calculator.setOperation(Operation.MULTIPLY);
            case "/" -> calculator.setOperation(Operation.DIVIDE);
        }

        needClear = true;
        numberTyped = false;

    }

    // '=' button
    // Executes chosen operation and displays result in numberField
    @FXML
    protected void doOperation() {
        try {
            double number = Double.parseDouble(numberField.getText());
            calculator.setNumberTwo(number);

            double result = calculator.doOperation();
            numberField.setText( Double.toString(result) );

            needClear = true;
            numberTyped = true;
        }
        catch (NumberFormatException e) {
            numberField.setText("Number only!");
        }

    }

    // Number buttons
    // Writes number to numberField
    @FXML
    protected void writeNumber(ActionEvent event) {
        clearIfNeeded();
        Button source = (Button) event.getSource();
        String currentText = numberField.getText();
        numberField.setText(currentText + source.getText());
        numberTyped = true;

    }

    // Clears numberField on the first number button press after pressing operation button
    public void clearIfNeeded() {
        if (needClear) {
            numberField.setText("");
            needClear = false;
        }
    }

    @FXML
    protected void changeStyle() {
        // Check if dark mode is enabled
        boolean darkModeEnabled = mainGrid.getStylesheets().contains(darkStyle);

        // If yes, remove it and apply light stylesheet
        if (darkModeEnabled) {
            mainGrid.getStylesheets().remove(darkStyle);
            mainGrid.getStylesheets().add(lightStyle);
        }
        // If not, do the opposite thing
        else {
            mainGrid.getStylesheets().remove(lightStyle);
            mainGrid.getStylesheets().add(darkStyle);
        }

    }

}
