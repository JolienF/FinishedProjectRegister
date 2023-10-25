package nl.miwnn.se12.jolien.FinishedProjectRegister.repository;

import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Maker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MakerRepository extends JpaRepository<Maker, Long> {
    // displayName MOET unique zijn
//    Optional<Maker> findMakerByDisplayName(String displayName);
    Optional<Maker> findMakerByFirstNameAndInfixNameAndLastName(String firstName, String infixName, String lastname);
}
