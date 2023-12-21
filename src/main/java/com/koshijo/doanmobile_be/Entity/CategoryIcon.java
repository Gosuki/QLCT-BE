package com.koshijo.doanmobile_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CategoryIcons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryIcon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "category_path")
    private String categoryPath;


    @OneToOne(mappedBy = "icon")
    private Category category;
}
