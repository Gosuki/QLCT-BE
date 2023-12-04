package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.CategoryIconDto;
import com.koshijo.doanmobile_be.Service.ICategoryIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/categoryIcons")
public class CategoryIconController {
    @Autowired
    private ICategoryIconService iCategoryIconService;

    @PostMapping("/upload")
    public ResponseEntity<BaseResponse>uploadIcon(@RequestParam(name = "image") MultipartFile image){
        try{
            CategoryIconDto icon = iCategoryIconService.uploadIcon(image);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),icon,"updated"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), e,e.getMessage()));
        }
    }
    @GetMapping("/{id}/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName,@PathVariable Long id) throws IOException {
        byte[] imageData = iCategoryIconService.downloadCategoryIconImage(id, fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(MediaType.APPLICATION_XML_VALUE))
                .body(imageData);

    }
    @GetMapping()
    public ResponseEntity<BaseResponse> getAllCategoriesIcon(){
        try{
            List<CategoryIconDto> icons = iCategoryIconService.getAllCategoriesIcon();
            return ResponseEntity.ok(
                    new BaseResponse(
                            HttpStatus.OK.value(),
                            icons,
                            "Get All Categories Icon"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), e,e.getMessage()));
        }
    }
}
