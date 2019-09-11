package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.Contact;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactDao dao;

    public boolean insert(Contact contact) {

        int rowNumber = dao.insertOne(contact);

        boolean result = false;

        if (rowNumber > 0){

            result = true;
        }

        return result;
    }

    public int count() {
        return dao.count();
    }

    public List<Contact> selectMany() {

        return dao.selectMany();
    }

    public Contact selectOne(String title) {

        return dao.selectOne(title);
    }

}
