package domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import util.JasyptEncryptor;

import java.util.Date;

/**
 * Created by Damian on 06/02/2017.
 */

@Document(collection ="users")
public class User {

    @Id
    private ObjectId id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
    private String address;
    private String nationality;
    private String userId;


    User(){

    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=encryptPass(password); 
    }

    public User(String firstName, String lastName, String email,
                String password, Date dateOfBirth, String address, String nationality, String userId) {
        this(firstName, lastName, email, password);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.nationality = nationality;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", nationality='" + nationality + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId.equals(user.userId);

    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime());
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUserId() {
        return userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = encryptPass(password);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    private String encryptPass(String password){
    	JasyptEncryptor encryptor = new JasyptEncryptor();
        return encryptor.encryptPassword(password);
    }
}
