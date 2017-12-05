import javafx.beans.property.SimpleStringProperty;

public class Person {

    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public Person(SimpleStringProperty username, SimpleStringProperty password) {
        this.username = username;
        this.password = password;
    }
}