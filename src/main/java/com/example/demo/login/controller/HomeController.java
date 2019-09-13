package com.example.demo.login.controller;

import com.example.demo.login.domain.model.Contact;
import com.example.demo.login.domain.model.ContactForm;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.ContactService;
import com.example.demo.login.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    private Map<String,String> radioMarriage;

    private Map<String,String> initRadioMarrige() {

        Map<String,String> radio = new LinkedHashMap<>();

        radio.put("男性","true");
        radio.put("女性","false");

        return radio;
    }

    @GetMapping("/home")
    public String getHome(Model model) {

        model.addAttribute("contents","login/home::home_contents");

        return "login/homeLayout";
    }

    @GetMapping("/userList")
    public String getUserList(Model model) {

        model.addAttribute("contents","login/userList::userList_contents");

        List<User> userList = userService.selectMany();

        model.addAttribute("userList",userList);

        int count = userService.count();
        model.addAttribute("userListCount",count);

        return "login/homeLayout";
    }

    @GetMapping("/contactList")
    public String getContactList(Model model) {

        model.addAttribute("contents","login/contactList::contactList_contents");
        List<Contact> contactList = contactService.selectMany();

        model.addAttribute("contactList",contactList);

        int count = contactService.count();
        model.addAttribute("contactListCount",count);

        return "login/homeLayout";
    }

    @GetMapping("/userList/csv")
    public String getUserListCsv(Model model) {

        return getUserList(model);
    }

    @GetMapping("/userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id")String userId) {

        System.out.println("userId="+userId);

        model.addAttribute("contents","login/userDetail::userDetail_contents");

        radioMarriage = initRadioMarrige();

        model.addAttribute("radioMarriage",radioMarriage);

        if (userId != null && userId.length()>0){

            User user = userService.selectOne(userId);

            form.setUserId(user.getUserId());
            form.setUserName(user.getUserName());
            form.setBirthday(user.getBirthday());
            form.setAge(user.getAge());
            form.setMarriage(user.isMarriage());

            model.addAttribute("signupForm",form);


        }
        return "login/homeLayout";
    }

    @GetMapping("/contactDetail/{id:.+}")
    public String getContactDetail(@ModelAttribute ContactForm form, Model model, @PathVariable("id")String title) {

        System.out.println("title="+title);

        model.addAttribute("contents","login/contactDetail::contactDetail_contents");


        if (title != null && title.length()>0){

            Contact contact = contactService.selectOne(title);

            form.setTitle(contact.getTitle());
            form.setText(contact.getText());
            form.setContributor(contact.getContributor());
            form.setCreateDate(contact.getCreateDate());

            model.addAttribute("contactForm",form);


        }
        return "login/homeLayout";
    }

    @PostMapping(value = "/userDetail",params = "update")
    public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {

        System.out.println("更新ボタンの処理");

        User user = new User();

        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());

        try {

            boolean result = userService.updateOne(user);

            if (result == true) {
                model.addAttribute("result", "更新成功");
            } else {
                model.addAttribute("result", "更新失敗");
            }

        } catch (DataAccessException e) {

            model.addAttribute("result", "更新失敗(トランザクションテスト)");

        }

        //ユーザー一覧画面を表示
        return getUserList(model);


    }

    @PostMapping(value = "/userDetail",params = "delete")
    public String postUserDetailDelete(@ModelAttribute SignupForm form,Model model) {

        System.out.println("削除ボタンの処理");

        boolean result = userService.deleteOne(form.getUserId());

        if (result == true) {
            model.addAttribute("result", "削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
        }


    //ユーザー一覧画面を表示
        return getUserList(model);
    }


    @PostMapping(value = "/contactDetail",params = "delete")
    public String postContactDetailDelete(@ModelAttribute ContactForm form,Model model) {

        System.out.println("削除ボタンの処理");

        boolean result = contactService.deleteOne(form.getTitle());

        if (result == true) {
            model.addAttribute("result", "削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
        }


        //ユーザー一覧画面を表示
        return getContactList(model);
    }

    @PostMapping("/logout")
    public String postLogout() {

        return "redirect:/login";
    }


}
