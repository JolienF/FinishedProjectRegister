package nl.miwnn.se12.jolien.FinishedProjectRegister.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.jolien.FinishedProjectRegister.model.Maker;
import nl.miwnn.se12.jolien.FinishedProjectRegister.repository.MakerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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
        } else {
            System.err.println(result.getAllErrors());
        }

        return "redirect:/maker/all";
    }

    //TODO methode schrijven om dubbele code eruit te halen
    @GetMapping("/detail/{firstName}/{lastName}")
    private String showMakerDetails(@PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName,
                                    Model model) {
        Optional<Maker> optionalMaker = makerRepository.findMakerByFirstNameAndInfixNameAndLastName(
                firstName, "", lastName);
        if (optionalMaker.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("makerToBeShown", optionalMaker.get());

        return "makerDetail";
    }

    @GetMapping("/detail/{firstName}/{infixName}/{lastName}")
    private String showMakerDetails(@PathVariable("firstName") String firstName,
                                    @PathVariable("infixName") String infixName,
                                    @PathVariable("lastName") String lastName,
                                    Model model) {
        Optional<Maker> optionalMaker = makerRepository.findMakerByFirstNameAndInfixNameAndLastName(
                firstName, infixName, lastName);
        if (optionalMaker.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("makerToBeShown", optionalMaker.get());

        return "makerDetail";
    }
}
