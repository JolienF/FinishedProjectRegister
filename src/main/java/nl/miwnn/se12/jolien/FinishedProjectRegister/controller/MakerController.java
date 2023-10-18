package nl.miwnn.se12.jolien.FinishedProjectRegister.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Maker;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.MakerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jolien Franke <j.franke@st.hanze.nl>
 * Handles the makers (haha like that's possible)
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/maker")
public class MakerController {
    private final MakerRepository makerRepository;

    @GetMapping("/all")
//    TODO moet dit protected worden? en de post ook?
    private String showMakerOverview(Model model) {
        model.addAttribute("allMakers", makerRepository.findAll());
        model.addAttribute("newMaker", new Maker());
        return "makerOverview";
    }

    @PostMapping("/new")
    private String saveOrUpdateMaker(@ModelAttribute("newMaker") Maker maker, BindingResult result) {
        if (!(result.hasErrors())) {
            makerRepository.save(maker);
        }

        return "redirect:/maker/all";
    }
}
