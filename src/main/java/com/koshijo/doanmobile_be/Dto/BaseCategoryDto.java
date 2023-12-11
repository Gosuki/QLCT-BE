package com.koshijo.doanmobile_be.Dto;

import com.koshijo.doanmobile_be.Entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseCategoryDto {
    private Long Id;
    private Long userId;
    private String categoryName;
    private Long categoryIconId;
    private String categoryIconPath;
    private String color;
    private Type type;
}
