package net.thumbtack.netexam.service;

import net.thumbtack.netexam.daoImpl.CommonDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class DataBaseClearService extends ServiceBase {

    public void clear() {
        dao.clear();
    }
}
