package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Dto.CategoryIconDto;
import com.koshijo.doanmobile_be.Entity.CategoryIcon;
import com.koshijo.doanmobile_be.Repository.CategoryIconRepository;
import com.koshijo.doanmobile_be.Service.ICategoryIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryIconServiceImpl implements ICategoryIconService{
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir")+"/src/main/resources");
    @Autowired
    private CategoryIconRepository categoryIconRepository;
    @Override
    public CategoryIconDto uploadIcon(MultipartFile image) {
        String fileName= StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        Path staticPath= Paths.get("static");
        Path imagePath=Paths.get("category-icons");
        Path uploadPath = Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath)));
        if (!Files.exists(Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath))))) {
            try {
                Files.createDirectories(Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath))));
                System.out.println("Đã tạo thư mục tĩnh: " + staticPath);
            } catch (IOException e) {
                throw new RuntimeException("Không thể tạo thư mục tĩnh: " + staticPath, e);
            }
        }
        if (!Files.exists(Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))))) {
            try {
                Files.createDirectories(Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))));
                System.out.println("Đã tạo thư mục con: " + imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Không thể tạo thư mục con: " + imagePath, e);
            }
        }
        Path filePath = uploadPath.resolve(fileName);
        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CategoryIcon icon = new CategoryIcon();
//        Path url = Paths.get("main", "resources").resolve(staticPath).resolve(imagePath);
        icon.setCategoryPath(image.getOriginalFilename());
        categoryIconRepository.save(icon);
        return convertToDto(icon);
    }

    @Override
    public List<CategoryIconDto> getAllCategoriesIcon() {
        List<CategoryIcon> categoryIconList = categoryIconRepository.findAll();
        return categoryIconList.stream().map(categoryIcon ->{
            return new CategoryIconDto(categoryIcon.getId(), categoryIcon.getCategoryPath());
        }).collect(Collectors.toList());
    }

    @Override
    public byte[] downloadCategoryIconImage(String fileName) throws IOException {
        CategoryIcon categoryIcon= categoryIconRepository.getCategoryIconByIdAndCategoryName(fileName);
        String filePath = System.getProperty("user.dir")+"/src/main/resources/static/category-icons/" + categoryIcon.getCategoryPath();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    public CategoryIconDto convertToDto(CategoryIcon icon) {
        CategoryIconDto dto = new CategoryIconDto();
        dto.setId(icon.getId());
        dto.setCategoryPath(icon.getCategoryPath());
        return dto;
    }
}
