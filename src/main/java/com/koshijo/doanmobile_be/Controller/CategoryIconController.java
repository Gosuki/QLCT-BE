package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.CategoryIconDto;
import com.koshijo.doanmobile_be.Entity.CategoryIcon;
import com.koshijo.doanmobile_be.Repository.CategoryIconRepository;
import com.koshijo.doanmobile_be.Service.ICategoryIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/api/categoryIcons/v1")
public class CategoryIconController {
    @Autowired
    private ICategoryIconService iCategoryIconService;

    @PostMapping("/upload")
    public ResponseEntity<BaseResponse>uploadIcon(@RequestParam(name = "image") MultipartFile image){
        try{
            CategoryIconDto icon =iCategoryIconService.uploadIcon(image);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),icon,"updated"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), e,e.getMessage()));
        }

    }
}
