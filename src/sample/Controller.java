package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Student>studentTableView;

    @FXML
    private TableColumn<Student, Integer>IdTableColumn;
    @FXML
    private TableColumn<Student, String>NameTableClumn;
    @FXML
    private TableColumn<Student, String>SurnameTableColumn;
    @FXML
    private TableColumn<Student, String>PhoneTableColumn;
    @FXML
    private TableColumn<Student, String>EmailTableColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection connection = MyObUtils.createConnection();
        if(connection != null){
            List<Student> students = getStudents(connection);

            IdTableColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
            NameTableClumn.setCellValueFactory( new PropertyValueFactory<Student, String>("name"));
            SurnameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
            PhoneTableColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("phone"));
            EmailTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

            studentTableView.setItems(FXCollections.observableList(students));

            /*Alert alert  = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Prisijungimas " + students.size());
            alert.show();*/

        }
    }
    private List<Student> getStudents(Connection connection){
        List< Student> students = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * from  students");
            while( resultSet.next()){
                Student student = new Student(resultSet.getInt("Id"),resultSet.getString("Name"),resultSet.getString("Surname"), resultSet.getString("Phone"), resultSet.getString("Email"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
