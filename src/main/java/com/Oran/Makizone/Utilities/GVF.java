package com.Oran.Makizone.Utilities;

import java.util.HashMap;
import java.util.Map;

public class GVF {
    public static final String SECRET_KEY_TS = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    public static final int HITERATIONS = 65536;
    public static final int HKEY_LENGTH = 256;
    public static final String HALGORITHM = "PBKDF2WithHmacSHA256";


    public static Map<String, Object> createInitialLayout(){
        Map<String, Object> map = new HashMap<>();
        Integer[][] grid_layout = new Integer[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        map.put("grid", grid_layout);
        Map<Integer, String> items = new HashMap<>();
        map.put("items", items);
        return map;
    }

}
