package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartnerStatus {
    REGISTERED(0, "등록", "파트너쉽 등록 상태"),
    UNREGISTERED(1, "해지", "파트너쉽 해지 상태");

    private Integer id;
    private String title;
    private String description;
}
