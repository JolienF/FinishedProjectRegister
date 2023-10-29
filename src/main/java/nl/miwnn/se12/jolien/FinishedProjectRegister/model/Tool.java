package nl.miwnn.se12.jolien.FinishedProjectRegister.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Properties of tools used to make the project
 */
@Entity
@Getter @Setter
@AllArgsConstructor
public class Tool {
    @Id @GeneratedValue
    private Long toolId;

    private String toolName;

    public Tool(String toolName) {
        this.toolName = toolName;
    }

    public Tool() {
    }
}
