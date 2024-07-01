package ru.nikita.apicourse.spring.boot.restapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import ru.nikita.apicourse.spring.boot.restapi.response.ResponseMaker;
import ru.nikita.apicourse.spring.boot.serializer.entity.create.RestEntityCreator;
public abstract class BaseRestController {

    @Autowired
    protected RestEntityCreator entityCreator;
    @Autowired
    protected ResponseMaker responseMaker;

}
