package nl.miwnn.se12.jolien.FinishedProjectRegister.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Properties of a finished project that enters our register
 */

@Entity
public class FinishedProject {

    @Id @GeneratedValue
    private Long projectId;

    private String projectName;

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
