package com.koshijo.doanmobile_be.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private Long id;
    private Long userId;
    private String date;
    private String note;
    private double amount;
    private Long categoryId ;
    private String categoryName;
    private String categoryIcon;
    private String colorIcon;
}
