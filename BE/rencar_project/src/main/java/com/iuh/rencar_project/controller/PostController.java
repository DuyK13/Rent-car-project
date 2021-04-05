//package com.iuh.rencar_project.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.iuh.rencarproject.dto.request.PostRequestDTO;
//import com.iuh.rencarproject.dto.response.AppResponse;
//import com.iuh.rencarproject.entity.Post;
//import com.iuh.rencarproject.service.template.IPostService;
//import com.iuh.rencarproject.ultis.mapper.IModelMapper;
//
//@RestController
//public class PostController {
//
//    @Autowired
//    IPostService postService;
//    IModelMapper<Post, PostRequestDTO> postMapper;
//
//    @PostMapping("/admin/post")
//    public ResponseEntity<AppResponse> addPost(@RequestBody PostRequestDTO requestPostDTO) {
//        return postService.responseSavePost(requestPostDTO);
//    }
//
//    @DeleteMapping("admin/post/{id}")
//    public ResponseEntity<AppResponse> deletePost(@PathVariable("id") Long id) {
//        return postService.responseDeletePost(id);
//    }
//
//    @GetMapping(value = "/post")
//    public ResponseEntity<AppResponse> getPost(@RequestParam(name = "id") Long id) {
//        return postService.responsefindPostById(id);
//    }
//
//    @PutMapping(value = "admin/post/{id}")
//    public ResponseEntity<AppResponse> updatePost(@PathVariable("id") Long id, @RequestBody PostRequestDTO dTOPost) {
//        return postService.responseUpdatePostById(id, dTOPost);
//    }
//
//    @GetMapping(value = "category/{categoryName}")
//    public ResponseEntity<AppResponse> findPostsByCategory(@PathVariable("categoryName") String categoryName) {
//        return postService.responseFindAllPostByCategory(categoryName);
//    }
//
//    @GetMapping(value = "category/{categoryName}/lastestposts")
//    public ResponseEntity<AppResponse> findLastestPostsByCategory(@PathVariable("categoryName") String categoryName) {
//        return postService.responseFindLastestPostByCategory(categoryName);
//    }
//}
