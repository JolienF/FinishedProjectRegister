package nl.miwnn.se12.jolien.FinishedProjectRegister.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(nullable = false) private String firstName;
    private String infixName;
    @Column(nullable = false) private String lastName;

    @OneToMany(mappedBy = "finishedProject", cascade = CascadeType.ALL)
    private Set<FinishedProject> finishedProjects;

    public Maker(String firstName, String infixName, String lastName) {
        this.firstName = firstName;
        this.infixName = infixName;
        this.lastName = lastName;
    }

    public Maker(String firstName, String lastName) {
        this(firstName, null, lastName);
    }

    //TODO waarom hoef je hier niets te initializen?
    public Maker() {
    }

    public String getDisplayName() {
        String displayName = firstName + " ";

        if (infixName != null && (!infixName.equals(""))) {
            displayName += infixName + " ";
        }

        displayName += lastName;

        return displayName;
    }
}
