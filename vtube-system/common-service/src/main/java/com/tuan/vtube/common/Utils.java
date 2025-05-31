package com.tuan.vtube.common;

import com.tuan.vtube.model.UserData;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
        UserData data = new UserData();
        String[] userData = request.getHeaders(Constants.USER_DATA).nextElement().split(Constants.DELIMITER);
        data.setId(userData[0]);
        data.setName(userData[1]);
        String listString = request.getHeaders(Constants.USER_DATA_ACCESS).nextElement();
        List<String> vtubeAccess = List.of(listString.substring(1, listString.length() - 1).split(Constants.DELIMITER));
        vtubeAccess.forEach(authority -> {
            authority = authority.trim().replaceAll("\"", "");
            String[] parts = authority.split(Constants.UNDERSCORE);
            if (parts.length > 0) {
                String authorityValue = String.join(Constants.UNDERSCORE, Arrays.copyOfRange(parts, 1, parts.length));
                switch (parts[0]) {
                    case Constants.ROLE:
                        data.getRoles().add(authorityValue);
                        break;
                    case Constants.ACTION:
                        data.getActions().add(authorityValue);
                        break;
                    case Constants.SCOPE:
                        data.getScopes().add(authorityValue);
                        break;
                }
            }
        });
        return data;
    }
}
