import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
       launch(args);
    }

    private ObservableList<Person> persons = FXCollections.observableArrayList();
    private TableView<Person> tableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        Label label = new Label("Welcome");
        vBox.getChildren().add(label);
        Scene scene = new Scene(vBox);

        tableView.setEditable(true);
        TableColumn usernameCol = getUsernameColumn();
        TableColumn passwordCol = getPasswordColumn();
        persons.add(new Person(new SimpleStringProperty("anna"), new SimpleStringProperty("1234")));
        persons.add(new Person(new SimpleStringProperty("Ben"), new SimpleStringProperty("5432")));
        tableView.setItems(persons);
        tableView.getColumns().addAll(usernameCol, passwordCol);
        vBox.getChildren().add(tableView);
        addCreateSection(vBox);
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(700);
        primaryStage.show();
    }

    private void addCreateSection(VBox vBox) {
        // add button and fields
        TextField addUsernameField = new TextField();
        TextField addPasswordField = new TextField();
        Button addButton = new Button("Add User");
        addButton.setOnAction(event -> {
            Person person = new Person(new SimpleStringProperty(addUsernameField.getText()), new SimpleStringProperty(addPasswordField.getText()));
            persons.add(person);
        });
        vBox.getChildren().addAll(addUsernameField, addPasswordField, addButton);
    }

    private TableColumn getUsernameColumn() {
        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("username"));
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        EventHandler<TableColumn.CellEditEvent<Person, String>> usernameHandler = t -> {t.getTableView().getItems().get(
                t.getTablePosition().getRow()).setUsername(t.getNewValue());
        };

        usernameCol.setOnEditCommit(usernameHandler);
        return usernameCol;
    }

    private TableColumn getPasswordColumn() {
        TableColumn passwordCol = new TableColumn("Password");
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> t) {
                         t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setPassword(t.getNewValue());
                    }
                }
        );

        passwordCol.setCellValueFactory(new PropertyValueFactory<Person, String>("password"));
        return passwordCol;
    }


}
