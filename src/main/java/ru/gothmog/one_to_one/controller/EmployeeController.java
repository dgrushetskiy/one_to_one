package ru.gothmog.one_to_one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.gothmog.one_to_one.domain.Employee;
import ru.gothmog.one_to_one.service.EmployeeService;

@Controller
@RequestMapping({"/employee", ""})
public class EmployeeController {

    private static final String PATH = "/employee";
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping({"/list", ""})
    public String list(Model model) {
        model.addAttribute("employees", employeeService.list());
        return PATH + "/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("employee", null);
        return PATH + "/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Employee employee, Model model) {
        employee = employeeService.save(employee);
        model.addAttribute("employee", employee);
        return "redirect:show/" + employee.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("employee", employeeService.get(id));
        return PATH + "/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("employee", employeeService.get(id));
        return PATH + "/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Employee employee) {
        employee = employeeService.update(employee);
        return "redirect:" + PATH + "/show/" + employee.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        employeeService.delete(id);
        return "redirect:" + PATH + "/list";
    }
}
