package com.example.bbcb.util;

import java.util.UUID;

public class IdProviderUtil {
    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
