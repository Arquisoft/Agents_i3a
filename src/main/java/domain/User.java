package domain;

import java.io.IOException;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import util.CSVReader;
import util.JasyptEncryptor;

/**
 * Created by Damian on 06/02/2017.
 */

@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;

    private String name;
    private String email;
    private String password;
    private Date dateOfBirth;
    private String address;
    private String nationality;
    private String userId;
    private String location;
    private int kindCode;
    private String kind;
    

    User() {

    }

    public User(String name, String email, String password) {
	this.name = name;
	this.email = email;
	this.password = encryptPass(password);
    }

    public User(String name, String location, String email, String password,
	    Date dateOfBirth, String address, String nationality, String userId,
	    int kindCode) throws IOException {
	this(name, email, password);
	this.dateOfBirth = dateOfBirth;
	this.address = address;
	this.nationality = nationality;
	this.location = location;
	this.userId = userId;
	setKindCode(kindCode);
    }

    @Override
    public String toString() {
	return "User{" + "name='" + name + '\'' + ", lastName='" + location
		+ '\'' + ", email='" + email + '\'' + ", password='" + password
		+ '\'' + ", dateOfBirth='" + dateOfBirth + '\'' + ", address='"
		+ address + '\'' + ", nationality='" + nationality + '\''
		+ ", id='" + userId + '\'' + ", kind='" + kind + '\'' + ", kindcode=" + kindCode +'}';
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	User user = (User) o;

	return userId.equals(user.userId);

    }

    @Override
    public int hashCode() {
	return userId.hashCode();
    }

    public String getName() {
	return name;
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

    public void setName(String name) {
	this.name = name;
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

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getKind(){
        return kind;
    }

    public int getKindCode(){
	return kindCode;
    }

    public void setKindCode(int kindCode) throws IOException {
        this.kindCode = kindCode;
        kind = CSVReader.getValueForKind( kindCode );
    }


    private String encryptPass(String password) {
	JasyptEncryptor encryptor = new JasyptEncryptor();
	return encryptor.encryptPassword(password);
    }



}
