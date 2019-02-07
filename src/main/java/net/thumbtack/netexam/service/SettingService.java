package net.thumbtack.netexam.service;

import net.thumbtack.netexam.daoImpl.SettingDaoImpl;
import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.model.UserType;
import net.thumbtack.netexam.response.GetSettingResponse;
import net.thumbtack.netexam.utils.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SettingService extends ServiceBase {

    SettingService(){}

    public GetSettingResponse getSetting(HttpHeaders headers) throws DataBaseException {

        ConfigUtils configUtils = new ConfigUtils();
        String cookie = headers.getFirst(HttpHeaders.COOKIE);

        if( cookie== null ||StringUtils.isEmpty(cookie) || settingDao.getUser(Arrays.asList(cookie.split("=")).get(1)).getUserType() == UserType.STUDENT){
            return new GetSettingResponse(ConfigUtils.getInt("config.max_name_length"),ConfigUtils.getInt("config.min_password_length"));
        }else{
            return new GetSettingResponse(ConfigUtils.getInt("config.max_name_length"),ConfigUtils.getInt("config.min_password_length"),
                    ConfigUtils.getInt("config.min_answers"),ConfigUtils.getInt("config.min_questions_count_per_exam"),
                    ConfigUtils.getInt("config.min_time"));

        }
    }
}
