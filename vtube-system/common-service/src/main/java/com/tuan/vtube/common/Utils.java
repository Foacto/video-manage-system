package com.tuan.vtube.common;

import com.google.gson.Gson;
import com.tuan.vtube.model.UserData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Utils {
    /**
     * @param fileName - name of video file with file extension.
     * @return only file name, without file extension.
     * Take video name to save JPEG-preview file with similar file name.
     */
    public static String getFileNameWithoutExt(String fileName) {
        int extIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, extIndex);
    }

    public static Boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static Boolean isNullOrEmpty(List str) {
        return str == null || str.isEmpty();
    }

    public static UserData getUserData(HttpServletRequest request) {
        Gson gson = new Gson();
        UserData data = gson.fromJson(AESEncryption.decrypt(request.getHeaders(Constants.USER_DATA).nextElement()), UserData.class);
        return data;
    }
}
