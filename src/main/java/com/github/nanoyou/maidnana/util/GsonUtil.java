package com.github.nanoyou.maidnana.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
}
