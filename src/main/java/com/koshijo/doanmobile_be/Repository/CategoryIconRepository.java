package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.CategoryIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryIconRepository extends JpaRepository<CategoryIcon,Long> {
    @Query(value="select u from CategoryIcon u where u.Id=:id AND u.categoryPath like %:name")
    CategoryIcon getCategoryIconByIdAndCategoryName(Long id, String name);
}
