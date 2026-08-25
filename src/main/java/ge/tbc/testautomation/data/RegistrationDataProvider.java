package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDataProvider {
    @DataProvider(name = "registrationData")
    public static Object[][] registrationData() throws SQLException {
        DatabaseSteps databaseSteps = new DatabaseSteps();
        ResultSet resultSet = databaseSteps.selectAllRegistrationData();
        List<Object[]> rows = new ArrayList<>();
        while (resultSet.next()) {
            rows.add(new Object[]{
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("gender"),
                    resultSet.getString("model"),
                    resultSet.getString("address1"),
                    resultSet.getString("address2"),
                    resultSet.getString("city"),
                    resultSet.getString("contact1"),
                    resultSet.getString("contact2")
            });
        }
        resultSet.getStatement().getConnection().close();
        return rows.toArray(new Object[0][]);
    }
}
