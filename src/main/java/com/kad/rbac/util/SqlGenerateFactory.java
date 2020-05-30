package com.kad.rbac.util;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
/**
 * sql语句生成工厂
 */
public class SqlGenerateFactory {


    private static final char UNDERLINE = '_';

    public static <T> String insertOrUpdate(List<T> list) {

        if (list == null || list.size() <= 0) {
            throw new NullPointerException("list can not be empty!");
        }

         T oj = list.get(0);
        String tableName = getTableName(oj);
        if (tableName.isEmpty() || tableName == null) {
            throw new NullPointerException("请先在实体中注入表名!");
        }
        String sql;
        List<String> fieldNames = getFiledName(list.get(0));
        List<String> fieldNamesToSQL = fieldNames.stream().map(a -> camelToUnderline(a)).collect(Collectors.toList());
        sql = String.format("insert into %s (%s) values", tableName, String.join(",", fieldNamesToSQL));
        List<String> values = new ArrayList<>();
        for (var d : list) {
            List<String> str = new ArrayList<>();
            for (var field : fieldNames) {
                var fieldValue = getFieldValueByName(field, d);
                if (fieldValue == null) {
                    str.add("null");
                } else {
                    str.add("'" + fieldValue.toString() + "'");
                }
            }
            values.add("(" + String.join(",", str) + ")");
        }
        sql += String.join(",", values);
        sql += " ON DUPLICATE KEY UPDATE " + String.join(",", fieldNamesToSQL.stream().map(t -> t + " = VALUES(" + t + ")").collect(Collectors.toList()));
        log.info(sql);
        return sql;
    }


    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获取属性名数组
     */
    public static List<String> getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        fields= getSuperClassFields(fields,o.getClass());
        List<String> fieldNames = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            fieldNames.add(fields[i].getName());
        }
        return fieldNames;
    }

    /**
     * 根据属性名获取属性值
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            if(value.getClass().equals(Date.class))
            {
              return   DateHelper.DateFormat((Date) value);
            }
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getValue(String fieldName,Object obj) throws IntrospectionException {

        PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
        Method getMethod = pd.getReadMethod();//获得get方法
        Object fieldValue = ReflectionUtils.invokeMethod(getMethod, obj);
      return fieldValue.toString();
    }



    public static Field[] getSuperClassFields(Field[] tableFields, Class<?> clazz) {
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz.equals(Object.class)) {
            return tableFields;
        }
        Field[] tableSuperFields = superClazz.getDeclaredFields();

        Field[] c = new Field[tableFields.length + tableSuperFields.length];
        System.arraycopy(tableFields, 0, c, 0, tableFields.length);
        System.arraycopy(tableSuperFields, 0, c, tableFields.length, tableSuperFields.length);
        getSuperClassFields(c, superClazz);
        return c;
    }

    /**
     * 根据实体获取表名
     * @param o
     * @return
     */
    private static String getTableName(Object o) {
        try {
         return o.getClass().getAnnotation(SqlAnnotationTableName.class).value();

        } catch (Exception e) {
            return null;
        }
    }
}

