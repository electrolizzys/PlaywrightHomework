package ge.tbc.testautomation.data;

import com.github.javafaker.Faker;

public class UserData {
    private static final Faker FAKER = new Faker();

    public final String firstName;
    public final String lastName;
    public final String dateOfBirth;
    public final String country;
    public final String postalCode;
    public final String houseNumber;
    public final String street;
    public final String city;
    public final String state;
    public final String phone;
    public final String email;
    public final String password;

    public UserData(String email) {
        this.firstName = Constants.FIRST_NAME;
        this.lastName = Constants.LAST_NAME;
        this.dateOfBirth = Constants.DATE_OF_BIRTH;
        this.country = Constants.COUNTRY;
        this.postalCode = Constants.POSTAL_CODE;
        this.houseNumber = Constants.HOUSE_NUMBER;
        this.street = Constants.STREET;
        this.city = Constants.CITY;
        this.state = Constants.STATE;
        this.phone = Constants.PHONE;
        this.email = email;
        this.password = Constants.PASSWORD;
    }

    public static UserData randomUser() {
        String email = FAKER.internet().emailAddress().replace("'", "");
        return new UserData(email);
    }
}
