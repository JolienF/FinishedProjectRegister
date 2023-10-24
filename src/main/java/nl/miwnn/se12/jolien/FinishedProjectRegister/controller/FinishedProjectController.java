package nl.miwnn.se12.jolien.FinishedProjectRegister.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.FinishedProject;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Maker;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.FinishedProjectRepository;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.MakerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Als er om een pagina wordt gevraagd, voert deze een actie uit en stuurt pagina terug
 */

@Controller
@RequiredArgsConstructor
public class FinishedProjectController {

    private final FinishedProjectRepository finishedProjectRepository;
    private final MakerRepository makerRepository;

    //    Get vraagt de pagina op
    @GetMapping({"/", "/finishedProjects/overview"})
    private String showFinishedProjectOverview(Model model) {
        model.addAttribute("allFinishedProjects", finishedProjectRepository.findAll());

        return "finishedProjectOverview";
    }

    @GetMapping("/finishedProject/new")
    private String showFinishedProjectFrom(Model model) {
        model.addAttribute("finishedProject", new FinishedProject());
        model.addAttribute("allMakers", makerRepository.findAll());

        return "finishedProjectFrom";
    }

    //TODO niet vinden op unieke project, maar via maker en dan unieke projectnaam
    @GetMapping("/finishedProject/edit/{projectName}")
    private String showEditBookForm(@PathVariable("projectName") String projectName, Model model) {
        Optional<FinishedProject> optionalFinishedProject =
                finishedProjectRepository.findFinishedProjectByProjectName(projectName);

        if (optionalFinishedProject.isEmpty()) {
            return "redirect:/finishedProject/overview";
        }

        model.addAttribute("finishedProject", optionalFinishedProject.get());
        model.addAttribute("allMakers", makerRepository.findAll());

        return "finishedProjectForm";
    }

    @PostMapping("/finishedProject/new")
    private String saveOrUpdateFinishedProject(
            @ModelAttribute("finishedProject") FinishedProject finishedProjectToBeSaved, BindingResult result) {
        if ((!result.hasErrors())) {
            finishedProjectRepository.save(finishedProjectToBeSaved);
        } else {
            System.err.println(result.getAllErrors());
        }

        return "redirect:/";
    }
}
