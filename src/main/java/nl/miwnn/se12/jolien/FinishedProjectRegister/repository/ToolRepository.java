package nl.miwnn.se12.jolien.FinishedProjectRegister.repository;


import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    Optional<Tool> findToolByToolName(String toolName);
}
