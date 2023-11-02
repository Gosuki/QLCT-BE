package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.CategoryIconDto;
import com.koshijo.doanmobile_be.Entity.CategoryIcon;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;

public interface ICategoryIconService {
    CategoryIconDto uploadIcon(MultipartFile image);
}
