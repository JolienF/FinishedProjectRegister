package nl.miwnn.se12.jolien.FinishedProjectRegister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Properties of a finished project that enters our register
 */

@Entity
@Getter
@Setter
public class FinishedProject {

    @Id @GeneratedValue
    private Long projectId;
    // TODO deze uniek maken per maker
    @Column(unique = true)
    private String projectName;
    private String finishDate;

    @ManyToOne
    private Maker maker;
}
