package nl.miwnn.se12.jolien.FinishedProjectRegister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Properties of a participant of the Maakplek
 * A finished project can have only one maker
 */

@Entity
@Getter @Setter
public class Maker {

    @Id @GeneratedValue
    private Long makerId;

    @Column(nullable = false, unique = true)
    private String makerEmail;
    @Column(nullable = false)
    private String firstName;
    private String infixName;
    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "maker", cascade = CascadeType.ALL)
    private Set<FinishedProject> finishedProjects = new HashSet<>();

    public Maker(String firstName, String infixName, String lastName, String makerEmail) {
        this.firstName = firstName;
        this.infixName = infixName;
        this.lastName = lastName;
        this.makerEmail = makerEmail;
    }

    public Maker(String firstName, String lastName, String makerEmail) {
        this(firstName, null, lastName, makerEmail);
    }

    public Maker() {
    }

    public String getDisplayName() {
        String displayName = firstName + " ";

        if (infixName != null && (!infixName.isEmpty())) {
            displayName += infixName + " ";
        }

        displayName += lastName;

        return displayName;
    }

    public String getAllFinishedProjectsDisplayString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (FinishedProject finishedProject : finishedProjects) {
            stringBuilder.append(finishedProject.getProjectName()).append(", ");
        }

        return stringBuilder.toString();
    }
}
