package com.example.demo.login.domain.repository;

import com.example.demo.login.domain.model.Contact;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ContactDao {

    public int count() throws DataAccessException;

    public int insertOne(Contact contact) throws DataAccessException;

    public Contact selectOne(String title) throws DataAccessException;

    public List<Contact> selectMany() throws DataAccessException;

    public int updateOne(Contact contact) throws DataAccessException;

    public int deleteOne(String title) throws DataAccessException;


}

