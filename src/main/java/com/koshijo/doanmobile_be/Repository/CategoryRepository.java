package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.Category;
import com.koshijo.doanmobile_be.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> getCategoriesByType(Type type);
}
