package nl.miwnn.se12.jolien.FinishedProjectRegister.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.FinishedProject;
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

    @GetMapping({"/", "/finishedProject/overview"})
    private String showFinishedProjectOverview(Model model) {
        model.addAttribute("allFinishedProjects", finishedProjectRepository.findAll());

        return "finishedProjectOverview";
    }

    @GetMapping("/finishedProject/new")
    private String showFinishedProjectFrom(Model model) {
        model.addAttribute("finishedProject", new FinishedProject());
        model.addAttribute("allMakers", makerRepository.findAll());

        return "finishedProjectForm";
    }

    //TODO niet vinden op unieke project, maar via maker en dan unieke projectnaam
    @GetMapping("/finishedProject/edit/{projectName}")
    private String showEditFinishedProjectForm(@PathVariable("projectName") String projectName, Model model) {
        Optional<FinishedProject> optionalFinishedProject =
                finishedProjectRepository.findFinishedProjectByProjectName(projectName);

        if (optionalFinishedProject.isEmpty()) {
            return "redirect:/finishedProject/overview";
        }

        model.addAttribute("finishedProject", optionalFinishedProject.get());
        model.addAttribute("allMakers", makerRepository.findAll());

        return "finishedProjectForm";
    }

    @GetMapping("/finishedProject/delete/{projectName}")
    private String deleteFinishedProject(@PathVariable("projectName") String projectName) {
        Optional<FinishedProject> optionalFinishedProject =
                finishedProjectRepository.findFinishedProjectByProjectName(projectName);

        if (optionalFinishedProject.isEmpty()) {
            return "redirect:/finishedProject/overview";
        }

        finishedProjectRepository.delete(optionalFinishedProject.get());

        return "redirect:/finishedProject/overview";
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
