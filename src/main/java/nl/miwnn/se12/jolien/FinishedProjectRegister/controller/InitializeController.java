package nl.miwnn.se12.jolien.FinishedProjectRegister.controller;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import net.datafaker.providers.base.Internet;
import net.datafaker.providers.base.Name;
import net.datafaker.providers.base.Science;
import net.datafaker.providers.entertainment.Pokemon;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.FinishedProject;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Maker;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Tool;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.FinishedProjectRepository;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.MakerRepository;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.ToolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Initializes database with test data
 */
@Controller
@RequiredArgsConstructor
public class InitializeController {
    private static final int NUMBER_OF_MAKERS = 12;
    private static final int NUMBER_OF_FINISHED_PROJECTS = 8;
    private static final int NUMBER_OF_TOOLS = 8;

    private final MakerRepository makerRepository;
    private final FinishedProjectRepository finishedProjectRepository;
    private final ToolRepository toolRepository;

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

        ArrayList<Tool> tools = new ArrayList<>();
        for (int tool = 0; tool < NUMBER_OF_TOOLS; tool++) {
            Science science = faker.science();

            tools.add(new Tool(science.tool()));
        }

        toolRepository.saveAll(tools);

        ArrayList<FinishedProject> finishedProjects = new ArrayList<>();
        for (int finishedProject = 0; finishedProject < NUMBER_OF_FINISHED_PROJECTS; finishedProject++) {
            Pokemon pokemon = faker.pokemon();
            String pokename = pokemon.name() + String.format(" V%d", finishedProject);
            Maker maker = makers.get((int)(Math.random() * NUMBER_OF_MAKERS));

            Set<Tool> toolsOfProject = new HashSet<>();
            toolsOfProject.add(tools.get((int) (Math.random() * NUMBER_OF_TOOLS)));
            toolsOfProject.add(tools.get((int) (Math.random() * NUMBER_OF_TOOLS)));

            finishedProjects.add(new FinishedProject(pokename, "12345", maker, toolsOfProject));
        }

        finishedProjectRepository.saveAll(finishedProjects);

        return "redirect:/";
    }
}
