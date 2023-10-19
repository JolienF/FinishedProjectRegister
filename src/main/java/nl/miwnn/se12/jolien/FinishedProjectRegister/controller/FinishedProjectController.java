package nl.miwnn.se12.jolien.FinishedProjectRegister.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.FinishedProject;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Maker;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.FinishedProjectRepository;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.MakerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("/")
    private String showFinishedProjectOverview(Model model) {
        model.addAttribute("allFinishedProjects", finishedProjectRepository.findAll());

        return "finishedProjectOverview";
    }

    @GetMapping("/finishedProject/new")
    private String showFinishedProjectFrom(Model model) {
        model.addAttribute("finishedProject", new FinishedProject());

        return "finishedProjectFrom";
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
