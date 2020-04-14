package ru.itis.jlab.services.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PrepareMailModelServiceImpl implements PrepareMailModelService {

    @Autowired
    private Environment environment;

    @Override
    public Map prepareMailModel(String code, String name) {
        Map model = new HashMap();
        model.put("name", name);
        model.put("location", "Kazan");
        model.put("link", "http://localhost:8080/confirm/" + code);
        return model;
    }
}
