package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.CategoryIconDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICategoryIconService {
    CategoryIconDto uploadIcon(MultipartFile image);
    List<CategoryIconDto> getAllCategoriesIcon();
    byte[] downloadCategoryIconImage(Long id, String fileName) throws IOException;
}
