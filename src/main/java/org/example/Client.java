package org.example;

import java.util.HashMap;

public class Client {
    public HashMap<String, Reactor> readCommonClass(String filePath) {
        JSONReader jsonReader = new JSONReader();
        XMLReader xmlReader = new XMLReader();
        YAMLReader yamlReader = new YAMLReader();

        yamlReader.setNextFileHandler(xmlReader);
        xmlReader.setNextFileHandler(jsonReader);

        HashMap<String, Reactor> reactors = yamlReader.commonloadReactors(filePath);
        return reactors;
    }
}
