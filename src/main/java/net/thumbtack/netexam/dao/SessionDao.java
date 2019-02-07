package net.thumbtack.netexam.dao;

import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.Session;
import net.thumbtack.netexam.model.User;

import java.util.UUID;

public interface SessionDao {
    Session insert(Session session) throws DataBaseException;

    int getUserIdByCookie(UUID cookies) throws DataBaseException;

    void deleteSession(String cookie) throws DataBaseException;

    void deleteSession(UUID cookie) throws DataBaseException;

    User getUserByCookie(String cookie) throws DataBaseException;
}
