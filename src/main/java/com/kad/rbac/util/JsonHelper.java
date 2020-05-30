package com.kad.rbac.util;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;

public class JsonHelper {


    public static String serialize(Object employee){
        // JSON对象序列化
        String employeeJson = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StringWriter stringWriter = new StringWriter();
            JsonGenerator jsonGenerator = new JsonFactory().createJsonGenerator(stringWriter);
            objectMapper.writeValue(jsonGenerator, employee);
            jsonGenerator.close();
            employeeJson = stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeJson;
    }

    /**
     * 将JSON格式反序列化为employee对象
     * @param employeeJson
     * @return
     */
    public <T> T deserialize(String employeeJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(employeeJson, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
