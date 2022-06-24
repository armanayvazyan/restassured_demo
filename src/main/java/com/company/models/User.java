package com.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty("id")
    public Integer id;

    @NonNull
    @JsonProperty("name")
    public String name;

    @NonNull
    @JsonProperty("email")
    public String email;

    @NonNull
    @JsonProperty("gender")
    public String gender;

    @NonNull
    @JsonProperty("status")
    public String status;
}
