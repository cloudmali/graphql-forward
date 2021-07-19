package com.graphqljava.tutorial.bookdetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyDataFethcer implements DataFetcher {
    private final String json;
    private final String key;
    private Map<String, Object> result = new HashMap<>();

    MyDataFethcer(String key) {
        json = "{\n" +
                "    \"location\": {\n" +
                "      \"created\": \"2017-11-10T12:42:04.162Z\",\n" +
                "      \"dimension\": \"Dimension C-137\",\n" +
                "      \"residents\": [\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"38\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"45\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Dead\",\n" +
                "          \"id\": \"71\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"unknown\",\n" +
                "          \"id\": \"82\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"unknown\",\n" +
                "          \"id\": \"83\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Dead\",\n" +
                "          \"id\": \"92\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"112\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"unknown\",\n" +
                "          \"id\": \"114\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Dead\",\n" +
                "          \"id\": \"116\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Dead\",\n" +
                "          \"id\": \"117\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Dead\",\n" +
                "          \"id\": \"120\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Dead\",\n" +
                "          \"id\": \"127\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"155\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"169\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"175\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"179\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"186\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"201\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"216\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"239\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"271\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Dead\",\n" +
                "          \"id\": \"302\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"303\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"338\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"343\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Alive\",\n" +
                "          \"id\": \"356\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"status\": \"Dead\",\n" +
                "          \"id\": \"394\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"character\": {\n" +
                "      \"name\": \"Rick Sanchez\"\n" +
                "    }\n" +
                "  \n" +
                "}";
        System.out.println(json);

        try {
            Map<String, Map<String, Object>> x =
                    new ObjectMapper().readValue(json, HashMap.class);
            result = x.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.key = key;
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        return result;

    }
}