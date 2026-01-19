package com.nixs.dtp.examples.webchat.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IModel {
    User authUser(String userName, String password) throws SQLException;
    User getUserById(int id) throws SQLException;
    Map<Integer, User> getUsersMap() throws SQLException;
    List<Talk> getTalks(User user) throws SQLException;
    List<Message> getMessages(int talkId) throws SQLException;
    int addUser(User user) throws SQLException;
}
