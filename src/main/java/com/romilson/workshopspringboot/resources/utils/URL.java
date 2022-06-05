package com.romilson.workshopspringboot.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String s){
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> decodeIntegerList(String listString){
        if(Objects.isNull(listString) || listString.isEmpty()) return new ArrayList<Integer>();
        return Arrays.stream(listString.split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
    }

}
