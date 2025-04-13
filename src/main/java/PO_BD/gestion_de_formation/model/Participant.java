package PO_BD.gestion_de_formation.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("participants")
public class Participant {
	@Id
    private String id;
    private String firstName;
    private String lastName;
    private String structure;
    private String profile;
    private String email;
    private String tel;

    public Participant(String firstName, String lastName, String structure, String profile, String email, String tel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.structure = structure;
        this.profile = profile;
        this.email = email;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getStructure() {
        return structure;
    }
    public void setStructure(String structure) {
        this.structure = structure;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    


}
