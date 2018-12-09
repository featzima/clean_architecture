package com.example.quarter.data;

import com.google.gson.annotations.SerializedName;

public class NoteEntity {

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;
}
