package com.example.demo.login.domain.repository;

import com.example.demo.login.domain.model.Contact;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CreateNewDao {
    public int insertOne(Contact contact) throws DataAccessException;

    public List<Contact> selectMany() throws DataAccessException;

    public int deleteOne(String title) throws DataAccessException;
}
