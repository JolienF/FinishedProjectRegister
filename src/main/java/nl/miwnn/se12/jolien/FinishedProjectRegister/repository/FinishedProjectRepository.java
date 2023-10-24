package nl.miwnn.se12.jolien.FinishedProjectRegister.repository;

import nl.miwnn.se12.jolien.FinishedProjectRegister.model.FinishedProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinishedProjectRepository extends JpaRepository<FinishedProject, Long> {
    Optional<FinishedProject> findFinishedProjectByProjectName(String projectName);
}
