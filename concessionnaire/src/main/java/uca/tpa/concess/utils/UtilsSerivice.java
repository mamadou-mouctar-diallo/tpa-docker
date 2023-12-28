package uca.tpa.concess.utils;

import org.bson.Document;

import java.lang.reflect.Field;

abstract public class UtilsSerivice {
    public static Document validate(Document document) {
        if (document == null) {
            return null;
        }
        System.out.println("Hello from validate");
        Class<?> documentClass = document.getClass();
        int field = documentClass.getModifiers();
        System.out.println(field);
        return document;
    }
}
