package net.thumbtack.netexam.dao;

import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.User;

public interface SettingDao {

    User getUser(String cookie) throws DataBaseException;
}
