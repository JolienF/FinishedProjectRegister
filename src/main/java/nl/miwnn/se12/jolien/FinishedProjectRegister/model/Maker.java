package nl.miwnn.se12.jolien.FinishedProjectRegister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public String getDisplayName() {
        String displayName = firstName + " ";

        if (infixName != null && (!infixName.equals(""))) {
            displayName += infixName + " ";
        }

        displayName += lastName;

        return displayName;
    }

}
