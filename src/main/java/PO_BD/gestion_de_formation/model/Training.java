package PO_BD.gestion_de_formation.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("trainings")
public class Training {
    @Id
    private String id;

    private String title;
    private int year;
    private int duration;
    private String field;
    private Double budget;

    public Training(String id, String title, int year, int duration, String field, Double budget) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.field = field;
        this.budget = budget;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }
	
}
