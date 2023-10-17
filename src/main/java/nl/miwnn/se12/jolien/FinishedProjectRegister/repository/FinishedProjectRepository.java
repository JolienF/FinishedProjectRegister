package nl.miwnn.se12.jolien.FinishedProjectRegister.repository;

import nl.miwnn.se12.jolien.FinishedProjectRegister.model.FinishedProject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Praten met de database waar de afgemaakte projecten staan
 */
public interface FinishedProjectRepository extends JpaRepository<FinishedProject, Long> {
}
