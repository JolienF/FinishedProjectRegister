package nl.miwnn.se12.jolien.FinishedProjectRegister.controller;

import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.FinishedProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Als er om een pagina wordt gevraagd, voert deze een actie uit en stuurt pagina terug
 */

@Controller
public class FinishedProjectController {

    private final FinishedProjectRepository finishedProjectRepository;

    public FinishedProjectController(FinishedProjectRepository finishedProjectRepository) {
        this.finishedProjectRepository = finishedProjectRepository;
    }

    //    Get vraagt de pagina op
    @GetMapping("/")
    private String showFinishedProjectOverview(Model model) {
        model.addAttribute("allFinishedProjects", finishedProjectRepository.findAll());

        return "finishedProjectOverview";
    }
}
