package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.config.AppConstants;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.payload.ProductDTO;
import com.ecommerce.sb_ecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ecommerce.sb_ecom.config.AppConstants.*;

//CONTROLLER SHOULD NOT CONTAIN ANY TRY CATCH OR ANY LOGIC...

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }




//    Case-1: simple we are passing any string
    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message") String message){
        return  new ResponseEntity<>("Echoed Message: " + message,HttpStatus.OK);
    }

//    Case-2: We are giving default value
//    @GetMapping("/echo")
//    public ResponseEntity<String> echoMessage(@RequestParam(name = "message" , defaultValue = "Hello world") String message){
//        return  new ResponseEntity<>("Echoed Message: " + message,HttpStatus.OK);
//    }


//  case -3 If we are not giving any default value and call it Without any name it will give error So to avoid that error
    // We add required = false , By default it is true...
//    @GetMapping("/echo")
//    public ResponseEntity<String> echoMessage(@RequestParam(name = "message" , required = false) String message){
//        return  new ResponseEntity<>("Echoed Message: " + message,HttpStatus.OK);
//    }

    //@GetMapping("/public/categories")
    @RequestMapping(value = "/public/categories",method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber",defaultValue = PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(name="pageSize",defaultValue = PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(name="sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
            @RequestParam(name="sortOrder",defaultValue = AppConstants.SORT_DIR,required = false)String sortOrder)  {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDto = categoryService.createCategory(categoryDTO);
        return  new ResponseEntity<>(savedCategoryDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
            CategoryDTO deletedCategoryDto = categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(deletedCategoryDto);
            //look actually here status is the body .
            //return new ResponseEntity<>(status , HttpStatus.OK);
            //return ResponseEntity.ok(status);

    }
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId){

            CategoryDTO savedCategoryDto =  categoryService.updateCategory(categoryDTO, categoryId);
            return new ResponseEntity<>(savedCategoryDto,HttpStatus.OK);
    }









}
