package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.Contact;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDaoJdbcImpl implements ContactDao {

    @Autowired
    JdbcTemplate jdbc;

    // Userテーブルの件数を取得.
    @Override
    public int count() throws DataAccessException {

        int count = jdbc.queryForObject("SELECT COUNT( * )FROM m_contact",Integer.class);
        return count;
    }

    // Userテーブルにデータを1件insert.
    @Override
    public int insertOne(Contact contact) throws DataAccessException {
        int rowNumber2 = jdbc.update("insert into m_contact(title, text, contribute, create_date, role) values (?,?,?,?,?)"
                ,contact.getTitle()
                ,contact.getText()
                ,contact.getContributor()
                ,contact.getCreateDate()
                ,contact.getRole());

                return rowNumber2;
    }

    // Userテーブルのデータを１件取得
    @Override
    public Contact selectOne(String title) throws DataAccessException {
        Map<String,Object> map = jdbc.queryForMap("select * from m_contact where title = ?",title);

        Contact contact = new Contact();

        contact.setTitle((String)map.get("title"));
        contact.setText((String)map.get("text"));
        contact.setContributor((String)map.get("contribute"));
        contact.setCreateDate((Date) map.get("create_date"));
        contact.setRole((String)map.get("role"));

        return contact;
    }

    // Userテーブルの全データを取得.
    @Override
    public List<Contact> selectMany() throws DataAccessException {

        List<Map<String,Object>> getList = jdbc.queryForList("SELECT * FROM m_contact");
        List<Contact> contactList = new ArrayList<>();

        for (Map<String,Object> map: getList){

            Contact contact = new Contact();

            contact.setTitle((String)map.get("title"));
            contact.setText((String)map.get("text"));
            contact.setContributor((String)map.get("contribute"));
            contact.setCreateDate((Date) map.get("create_date"));
            contact.setRole((String)map.get("role"));
            System.out.println(contact);
            contactList.add(contact);

        }

        return contactList;
    }

    // Userテーブルを１件更新.
    @Override
    public int updateOne(Contact contact) throws DataAccessException {

        int rowNumber = jdbc.update("UPDATE m_contact set text=?,contribute=?,create_date=? where title=?",contact.getText(),contact.getContributor(),contact.getCreateDate(),contact.getTitle());
        return rowNumber;
    }

    // Userテーブルを１件削除.
    @Override
    public int deleteOne(String title) throws DataAccessException {
        int rowNumber = jdbc.update("delete from m_contact where title=?",title);

        return rowNumber;
    }

    // SQL取得結果をサーバーにCSVで保存する

}





