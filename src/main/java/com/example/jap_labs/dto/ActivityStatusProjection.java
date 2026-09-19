package com.example.jap_labs.dto;

import com.example.jap_labs.enums.Activity;

public interface ActivityStatusProjection {
    Long getId();
    String getEmail();
    Activity getActivity(); ;
}
