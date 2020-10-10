package crud.controller;

import crud.entity.User;
import crud.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @RequestMapping("/")
    public ModelAndView home() {
        List<User> listUser = userService.listAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listUser", listUser);
        return mav;
    }

    @RequestMapping("/new")
    public String newUserForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public ModelAndView editUserForm(@RequestParam Integer id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.get(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/delete")
    public String deleteCustomerForm(@RequestParam Integer id) {
        userService.delete(id);
        return "redirect:/";
    }
}
