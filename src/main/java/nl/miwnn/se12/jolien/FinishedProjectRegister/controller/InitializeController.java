package nl.miwnn.se12.jolien.FinishedProjectRegister.controller;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import net.datafaker.providers.base.Internet;
import net.datafaker.providers.base.Name;
import net.datafaker.providers.entertainment.BojackHorseman;
import net.datafaker.providers.entertainment.Pokemon;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.FinishedProject;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Maker;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.FinishedProjectRepository;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.MakerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Locale;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Initializes database with test data
 */
@Controller
@RequiredArgsConstructor
public class InitializeController {
    private static final int NUMBER_OF_MAKERS = 12;
    private static final int NUMBER_OF_FINISHED_PROJECTS = 8;

    private final MakerRepository makerRepository;
private final FinishedProjectRepository finishedProjectRepository;

    @GetMapping("/initialize")
    private String initializeDB() {
        if (!makerRepository.findAll().isEmpty()) {
            return "redirect:/";
        }

        Faker faker = new Faker(new Locale("nl"));

        ArrayList<Maker> makers = new ArrayList<>();
        for (int maker = 0; maker < NUMBER_OF_MAKERS; maker++) {
            Name name = faker.name();
            Internet internet = faker.internet();

            makers.add(new Maker(name.firstName(), name.lastName(), internet.emailAddress()));
        }

        makerRepository.saveAll(makers);

        ArrayList<FinishedProject> finishedProjects = new ArrayList<>();
        for (int finishedProject = 0; finishedProject < NUMBER_OF_FINISHED_PROJECTS; finishedProject++) {
            Pokemon pokemon = faker.pokemon();
            Maker maker = makers.get((int)(Math.random() * NUMBER_OF_MAKERS));

            finishedProjects.add(new FinishedProject(pokemon.name(), "12345", maker));
        }

        finishedProjectRepository.saveAll(finishedProjects);

        //science.tool zit in datafaker

        return "redirect:/";
    }
}
