package nl.miwnn.se12.jolien.FinishedProjectRegister.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Properties of a finished project that enters our register
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
public class FinishedProject {

    public FinishedProject(String projectName, String finishDate, Maker maker) {
        this.projectName = projectName;
        this.finishDate = finishDate;
        this.maker = maker;
    }

    public FinishedProject() {
    }

    @Id
    @GeneratedValue
    private Long projectId;
    // TODO deze uniek maken per maker
    @Column(nullable = false, unique = true)
    private String projectName;
    private String finishDate;

    @ManyToOne
    private Maker maker;

    @ManyToMany
    private Set<Tool> tools;

    public String getAllToolsDisplayString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Tool tool : tools) {
            stringBuilder.append(tool.getToolName()).append(", ");
        }

        return stringBuilder.toString();
    }
}
