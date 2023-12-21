package com.keyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@Controller
public class BinarySearchTreeController {
    @Autowired
    private TreeNodeRepository nodeRepository;

    @Autowired
    private TreeStructureRepository treeRepository;

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
        try {

            List<Integer> numberList = parseNumbers(numbers);

            if (numberList.isEmpty()) {

                model.addAttribute("errorMessage", "Please enter valid numbers");
                return "enter-numbers";
            }

            TreeStructure tree = constructBinarySearchTree(numberList);
            treeRepository.save(tree);

            model.addAttribute("tree", tree);

            return "enter-numbers";
        } catch (Exception e) {

            model.addAttribute("errorMessage", "error");
            return "enter-numbers";
        }
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<TreeStructure> trees = treeRepository.findAll();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }

    private List<Integer> parseNumbers(String numbers) {
        return Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    private TreeStructure constructBinarySearchTree(List<Integer> numbers) {
        TreeStructure tree = new TreeStructure();
        for (Integer number : numbers) {
            TreeNode node = new TreeNode();
            node.setValue(number);
            node.setTree(tree);
            nodeRepository.save(node);
        }
        return tree;
    }
}
