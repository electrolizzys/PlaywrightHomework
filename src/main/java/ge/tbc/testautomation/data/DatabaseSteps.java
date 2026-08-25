package ge.tbc.testautomation.data;

import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSteps {
    @Step("Select all rows from RegistrationData")
    public ResultSet selectAllRegistrationData() {
        try {
            Connection connection = MSSQLConnection.getConnection();
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            return statement.executeQuery("SELECT * FROM RegistrationData");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
