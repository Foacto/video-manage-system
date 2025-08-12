package com.tuan.vtube.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserData {
    private String id;
    private String name;
    private String email;
    private List<String> roles = new ArrayList<>();
    private List<String> actions = new ArrayList<>();
    private List<String> scopes = new ArrayList<>();
}
