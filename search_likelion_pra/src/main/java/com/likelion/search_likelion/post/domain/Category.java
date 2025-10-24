package com.likelion.search_likelion.post.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.likelion.search_likelion.exception.CustomException;
import com.likelion.search_likelion.exception.status.ErrorStatus;

public enum Category {

    CAFE("카페"),
    RESTAURANT("음식점"),
    SUPERMARKET("마트");

    private final String label;

    Category(String label) {
        this.label = label;
    }


    @JsonCreator
    public static Category from(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        String trimmed = input.trim();

        for (Category c : values()) {
            if (c.name().equalsIgnoreCase(trimmed) || c.label.equalsIgnoreCase(trimmed)) {
                return c;
            }
        }

            throw new CustomException(ErrorStatus.INVALID_PARAMETER,
                    ErrorStatus.INVALID_PARAMETER.getCode());
        }


        @JsonValue
        public String toJson () {
            return this.label;
        }
    }


