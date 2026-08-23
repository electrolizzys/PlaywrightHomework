package ge.tbc.testautomation.data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class UserData {
    private static final Faker FAKER = new Faker();
    private static final String VALID_PASSWORD = "Test#2222!";
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

    private UserData(String firstName, String lastName, String dateOfBirth, String country, String postalCode,
                     String houseNumber, String street, String city, String state, String phone,
                     String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public static UserData randomUser() {
        return new UserData(
                letters(FAKER.name().firstName(), "Anna"),
                letters(FAKER.name().lastName(), "Beridze"),
                new SimpleDateFormat("yyyy-MM-dd").format(FAKER.date().birthday(18, 60)),
                "Georgia",
                "0108",
                String.valueOf(FAKER.number().numberBetween(1, 200)),
                "Chavchavadze Avenue",
                "Tbilisi",
                "Tbilisi",
                FAKER.numerify("5########"),
                FAKER.internet().emailAddress().replace("'", ""),
                VALID_PASSWORD
        );
    }

    private static String letters(String value, String fallback) {
        String cleaned = value.replaceAll("[^A-Za-z]", "");
        return cleaned.length() < 2 ? fallback : cleaned;
    }
}
