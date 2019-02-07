package net.thumbtack.netexam.endpoint;

import net.thumbtack.netexam.exception.DataBaseException;
import net.thumbtack.netexam.response.GetSettingResponse;
import net.thumbtack.netexam.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter.HEADER_NAME;

@RestController
public class SettingEndpoint {
    private SettingService settingService;

    @Autowired
    public SettingEndpoint(SettingService settingService) {
        this.settingService = settingService;

    }

    @GetMapping(value = "/api/settings", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetSettingResponse getSetting(@RequestHeader(HEADER_NAME) HttpHeaders headers) throws DataBaseException {

        return settingService.getSetting(headers);
    }


}
