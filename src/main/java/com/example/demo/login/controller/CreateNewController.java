package com.example.demo.login.controller;

import com.example.demo.login.domain.model.Contact;
import com.example.demo.login.domain.model.CreateNewForm;
import com.example.demo.login.domain.service.CreateNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class CreateNewController {

    @Autowired
    private CreateNewService createNewService;

    @GetMapping("/createNew")
    public String getCreateNew(@ModelAttribute CreateNewForm form, Model model) {

        model.addAttribute("contents","login/createNew::createNew_contents");



        return "login/homeLayout";
    }

    @PostMapping("/createNew")
    public String postCreateNew(@ModelAttribute CreateNewForm form, BindingResult bindingResult, Model model) {

        //model.addAttribute("contents","login/createNew::createNew_contents");
        model.addAttribute("contents","login/contactList::contactList_contents");

        if (bindingResult.hasErrors()){

            return getCreateNew(form,model);
        }
        System.out.println(form);

        Contact contact = new Contact();

        contact.setTitle(form.getTitle());
        contact.setText(form.getText());
        contact.setContributor(form.getContributor());
        contact.setCreateDate(form.getCreateDate());
        contact.setRole("ROLE_ADMIN");

        boolean result = createNewService.insert(contact);

        if (result == true){
            System.out.println("insert成功");
        }else {
            System.out.println("insert失敗");
        }

        model.addAttribute("createNewForm",form);



        return getContactList(model);
    }


    public String getContactList(Model model) {

        model.addAttribute("contents","login/contactList::contactList_contents");
        List<Contact> contactList = createNewService.selectMany();

        model.addAttribute("contactList",contactList);

//        int count = createNewService.count();
//        model.addAttribute("contactListCount",count);

        return "login/homeLayout";
    }




}

