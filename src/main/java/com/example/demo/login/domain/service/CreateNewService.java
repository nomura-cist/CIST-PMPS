package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.Contact;
import com.example.demo.login.domain.repository.CreateNewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateNewService {

    @Autowired
    CreateNewDao createNewDao;

    public boolean insert(Contact contact) {

        int rowNumber = createNewDao.insertOne(contact);

        boolean result = false;

        if (rowNumber > 0){

            result = true;
        }

        return result;
    }

    public List<Contact> selectMany() {

        return createNewDao.selectMany();
    }
}
