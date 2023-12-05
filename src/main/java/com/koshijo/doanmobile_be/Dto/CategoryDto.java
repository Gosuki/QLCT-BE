package com.koshijo.doanmobile_be.Dto;

import com.koshijo.doanmobile_be.Entity.CategoryIcon;
import com.koshijo.doanmobile_be.Entity.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long Id;
    private String categoryName;
    private Long iconId;
    private String iconPath;
    private String color;
    private Type type;
}
