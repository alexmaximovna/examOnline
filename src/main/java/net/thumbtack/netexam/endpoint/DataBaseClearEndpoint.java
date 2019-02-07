package net.thumbtack.netexam.endpoint;

import net.thumbtack.netexam.response.BaseResponseObject;
import net.thumbtack.netexam.service.DataBaseClearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataBaseClearEndpoint {
    private DataBaseClearService dataBaseClearService;


    @Autowired
    public DataBaseClearEndpoint(DataBaseClearService dataBaseClearService) {
        this.dataBaseClearService = dataBaseClearService;

    }


    @PostMapping(value = "/api/debug/clear", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponseObject clear() {
        dataBaseClearService.clear();
        return new BaseResponseObject();

    }
}
