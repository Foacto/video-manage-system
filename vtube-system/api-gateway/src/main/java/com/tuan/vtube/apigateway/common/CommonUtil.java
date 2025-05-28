package com.tuan.vtube.apigateway.common;

import java.util.List;

public class CommonUtil {
    public static Boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static Boolean isNullOrEmpty(List str) {
        return str == null || str.isEmpty();
    }
}
