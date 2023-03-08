import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author YITANR
 */
public class CalculadoraPersonas extends Application {

    private final TextField nombre1 = new TextField();
    private final ComboBox<String> provincia1 = new ComboBox<>();
    private final TextField edad1 = new TextField();

    private final TextField nombre2 = new TextField();
    private final ComboBox<String> provincia2 = new ComboBox<>();
    private final TextField edad2 = new TextField();

    private final TextField resultado = new TextField();

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        // Configuración de ComboBox para provincias
        provincia1.getItems().addAll("San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón");
        provincia1.setValue("San José");

        provincia2.getItems().addAll("San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón");
        provincia2.setValue("San José");

        // Configuración de TextField para edad
        edad1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                edad1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        edad2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                edad2.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Configuración de botones
        Button sumar = new Button("+");
        Button restar = new Button("-");
        Button multiplicar = new Button("*");
        Button dividir = new Button("/");

        sumar.setOnAction(event -> {
            int num1 = Integer.parseInt(edad1.getText());
            int num2 = Integer.parseInt(edad2.getText());
            resultado.setText(String.valueOf(num1 + num2));
        });

        restar.setOnAction(event -> {
            int num1 = Integer.parseInt(edad1.getText());
            int num2 = Integer.parseInt(edad2.getText());
            resultado.setText(String.valueOf(num1 - num2));
        });

        multiplicar.setOnAction(event -> {
            int num1 = Integer.parseInt(edad1.getText());
            int num2 = Integer.parseInt(edad2.getText());
            resultado.setText(String.valueOf(num1 * num2));
        });

        dividir.setOnAction(event -> {
            int num1 = Integer.parseInt(edad1.getText());
            int num2 = Integer.parseInt(edad2.getText());
            if (num2 != 0) {
                resultado.setText(String.valueOf(num1 / num2));
            } else {
                resultado.setText("Error: división por cero");
            }
        });

        // Configuración del GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Agregar elementos al GridPane
        grid.add(new Label("Nombre"), 0, 0);
        grid.add(new Label("Provincia"), 1, 0);
        grid.add(new Label("Edad"), 2, 0);

        grid.add(nombre1, 0, 1);
        grid.add(provincia1, 1, 1);
        grid.add(edad1, 2, 1);
                grid.add(nombre2, 0, 2);
        grid.add(provincia2, 1, 2);
        grid.add(edad2, 2, 2);

        grid.add(new Label("Resultado:"), 0, 4);
        grid.add(resultado, 1, 4, 2, 1);

        grid.add(sumar, 0, 3);
        grid.add(restar, 1, 3);
        grid.add(multiplicar, 2, 3);
        grid.add(dividir, 3, 3);

        // Configuración de la escena
        Scene scene = new Scene(grid, 500, 250);

        // Configuración de la ventana principal
        primaryStage.setTitle("Calculadora de Personas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

       
