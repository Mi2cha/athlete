package ch.zli.mm.athlete.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name="Sport")
@Table(name="sport")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "sport_id", updatable = false, nullable = false)
    private int id;

    private String name;
    private String description;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "sport",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Athlete> athletes;

    public Sport(){

    }

    public Sport(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }
}
