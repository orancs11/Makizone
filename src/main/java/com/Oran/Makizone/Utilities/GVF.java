package com.Oran.Makizone.Utilities;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class GVF {
    @Value("${SECRET_KEY_TS}")
    public static String SECRET_KEY_TS;
    @Value("${EXPIRATION_TIME}")
    public static long EXPIRATION_TIME;

    @Value("${HASH_KEY_ITERATION}")
    public static int HITERATIONS;
    @Value("${HASH_KEY_LENGTH}")
    public static int HKEY_LENGTH;
    @Value("${HASH_ALGO}")
    public static String HALGORITHM;


    public static Map<String, Object> createInitialLayout(){
        Map<String, Object> map = new HashMap<>();
        Integer[][] grid_layout = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        map.put("grid", grid_layout);
        Map<Integer, String> items = new HashMap<>();
        map.put("items", items);
        return map;
    }

}
