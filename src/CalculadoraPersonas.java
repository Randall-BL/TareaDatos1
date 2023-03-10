import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 *
 * @author YITANR
 */
public class PersonCalculator extends Application {
    
    private Person[] people = new Person[4];
    private ComboBox<Person> person1Combo;
    private ComboBox<Person> person2Combo;
    private ComboBox<String> operationCombo;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de Personas");

        // Creando el GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Creando el label y text field
        Label nameLabel = new Label("Nombre:");
        grid.add(nameLabel, 0, 0);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 0);

        // Creando el label de provincia y el combobox
        Label provinceLabel = new Label("Provincia:");
        grid.add(provinceLabel, 0, 1);
        ComboBox<String> provinceComboBox = new ComboBox<>();
        provinceComboBox.getItems().addAll(
                "San José",
                "Alajuela",
                "Cartago",
                "Heredia",
                "Guanacaste",
                "Puntarenas",
                "Limón"
        );
        grid.add(provinceComboBox, 1, 1);

        // Creando el label de Edad y el TextField
        Label ageLabel = new Label("Edad:");
        grid.add(ageLabel, 0, 2);
        TextField ageTextField = new TextField();
        grid.add(ageTextField, 1, 2);

        // Creando el boton de añadir persona
        Button addButton = new Button("Añadir persona");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameTextField.getText();
                String province = provinceComboBox.getValue();
                int age = Integer.parseInt(ageTextField.getText());
                Person person = new Person(name, province, age);
                for (int i = 0; i < people.length; i++) {
                    if (people[i] == null) {
                        people[i] = person;
                        break;
                    }
                }
                updateComboBoxes();
                nameTextField.clear();
                provinceComboBox.getSelectionModel().clearSelection();
                ageTextField.clear();
            }
        });
        grid.add(addButton, 0, 3);

        //Creando el label de persona1 y el comboBox
        Label person1Label = new Label("Persona 1:");
        grid.add(person1Label, 0, 4);
        person1Combo = new ComboBox<>();
        grid.add(person1Combo, 1, 4);

        // Creando el label de persona2 y el comboBox
        Label person2Label = new Label("Persona 2:");
        grid.add(person2Label, 0, 5);
        person2Combo = new ComboBox<>();
        grid.add(person2Combo, 1, 5);

        // Creando el label de operacion y el combobox
        Label operationLabel = new Label("Operacion:");
        grid.add(operationLabel, 0, 6);
        operationCombo = new ComboBox<>();
            operationCombo.getItems().addAll(
            "+",
            "-",
            "*",
            "/"
    );
    grid.add(operationCombo, 1, 6);

    // Creando el boton para calcular la operacion
    Button calculateButton = new Button("Calcular");
    calculateButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Person person1 = person1Combo.getValue();
            Person person2 = person2Combo.getValue();
            String operation = operationCombo.getValue();
            if (person1 == null || person2 == null || operation == null) {
                resultLabel.setText("Please select two people and an operation.");
            } else {
                int result = calculate(person1, person2, operation);
                resultLabel.setText(String.valueOf(result));
            }
        }
    });
    grid.add(calculateButton, 0, 7);

    // Creando el label de resultado
    Label resultTitleLabel = new Label("Resultado:");
    grid.add(resultTitleLabel, 0, 8);
    resultLabel = new Label();
    grid.add(resultLabel, 1, 8);

    // Colocando la escena
    Scene scene = new Scene(grid, 400, 300);
    primaryStage.setScene(scene);
    primaryStage.show();
}

private void updateComboBoxes() {
    person1Combo.getItems().clear();
    person2Combo.getItems().clear();
    for (Person person : people) {
        if (person != null) {
            person1Combo.getItems().add(person);
            person2Combo.getItems().add(person);
        }
    }
}

private int calculate(Person person1, Person person2, String operation) {
    int result = 0;
    switch (operation) {
        case "+":
            result = person1.getAge() + person2.getAge();
            break;
        case "-":
            result = person1.getAge() - person2.getAge();
            break;
        case "*":
            result = person1.getAge() * person2.getAge();
            break;
        case "/":
            if (person2.getAge() == 0) {
                resultLabel.setText("Cannot divide by zero.");
            } else {
                result = person1.getAge() / person2.getAge();
            }
            break;
    }
    return result;
}

public static void main(String[] args) {
    launch(args);
}

private class Person {
    private String name;
    private String province;
    private int age;

    public Person(String name, String province, int age) {
        this.name = name;
        this.province = province;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + province + ", " + age + ")";
    }
}}


       
