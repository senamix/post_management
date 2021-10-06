package com.example.su.controllers;

import com.example.su.constant.SystemConstant;
import com.example.su.converters.PostConverter;
import com.example.su.dtos.PostDto;
import com.example.su.security.CustomUserDetails;
import com.example.su.security.SecurityUtil;
import com.example.su.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    IPostService postService;

    @Autowired
    PostConverter postConverter;

    /*
    Using @ModelAttribute:
    - Method:
    + It will be run before controller to create "a model" for all controllers
     [ model.addAttribute("customUser" ,SecurityUtil.getCustomUser()); for all controller ]

    - Argument:
    + Get data from "form of view" if spring form is used.
     */
    @ModelAttribute("customUser")
    CustomUserDetails getCustomUser(){
        return SecurityUtil.getCustomUser();
    }

    @GetMapping(value = "/form")
    public String createForm(Model model){
        model.addAttribute("model",new PostDto());
        return "PostAddOrEdit";
    }

    @GetMapping("/list")
    public ModelAndView listPost(@RequestParam(value = "postName", required = false) String postName) {
        List<PostDto> posts;
        if(postName != null){
            posts = postService.findByPostNameContains(postName);
        }else {
            posts = postService.findAll();
        }
        ModelAndView mav = new ModelAndView("PostList");
        mav.addObject("posts", posts);
        return mav;
    }

    @GetMapping("/paging")
    public ModelAndView pagePost(@RequestParam(value = "page", required = true) int page) {
        PostDto postDto = new PostDto();
        postDto.setPage(page);
        postDto.setLimit(SystemConstant.PAGE_SIZE);
        ModelAndView mav = new ModelAndView("PostPage");
        Pageable pageable = PageRequest.of(page - 1, SystemConstant.PAGE_SIZE);
        List<PostDto> posts = postService.findAll(pageable);
        postDto.setTotalItem((int) postService.totalPost());
        postDto.setTotalPage((int) Math.ceil((double) postDto.getTotalItem() / postDto.getLimit()));
        mav.addObject("posts",posts);
        mav.addObject("currentPage",page);
        mav.addObject("postDto", postDto);
        return mav;
    }

    @GetMapping(value = "/edit/{postId}")
    public ModelAndView edit(@PathVariable("postId")long postId){
        PostDto dto = postService.findById(postId);
        if(dto.getUserId().longValue() != SecurityUtil.getCustomUser().getUserId().longValue()){
            return new ModelAndView("forward:/notify?denied");
        }
        ModelAndView mav = new ModelAndView("PostAddOrEdit");
        mav.addObject("model", dto);
        return mav;
    }

    @PostMapping( "/save")
    public ModelAndView addOrUpdate(@Validated @ModelAttribute("model") PostDto model, BindingResult result){
        /*
        - Using @Validated to check invalid input to view
        + return back to input form by BindingResult
        + at view side, we need to add <form:error path> of spring form
         */
        if(result.hasErrors()){
            return new ModelAndView("PostAddOrEdit");
        }

        PostDto dto = postService.save(model);
        if(dto.getUserId().longValue() != SecurityUtil.getCustomUser().getUserId().longValue()){
            return new ModelAndView("forward:/notify?denied");
        }

        if(dto == null){
            return new ModelAndView("redirect:/posts/paging?page=1&invalid_post");
        }

        if(model.getPostId() == null) {
            return new ModelAndView("redirect:/posts/paging?page=1&add_success");
        }
        else {
            return new ModelAndView("redirect:/posts/paging?page=1&update_success");
        }
    }

    @GetMapping("/delete/{postId}")
    public ModelAndView delete(@PathVariable("postId")long postId){
        PostDto dto = postService.findById(postId);
        if(dto.getUserId().longValue() != SecurityUtil.getCustomUser().getUserId().longValue()){
            return new ModelAndView("forward:/notify?denied");
        }
        postService.delete(Long.valueOf(postId));
        return new ModelAndView("redirect:/posts/paging?page=1&delete_success");
    }

}
