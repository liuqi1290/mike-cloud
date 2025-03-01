package com.mike.server.user.controller;

import com.mike.common.core.domain.dto.Result;
import com.mike.server.user.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class UserController {

    @GetMapping("/get/{id}")
    public Result<UserDto> testGet(@PathVariable("id") Long id) {
        System.out.println("Get 方法测试：" + id);
        // 根据 id 查询
        UserDto dto = new UserDto();
        dto.setName("名称");
        dto.setAge(18);
        dto.setAge(1);
        return Result.success(dto);
    }

    @DeleteMapping("/del/{id}")
    public Result<String> testDel(@PathVariable("id") Long id) {
        System.out.println("Del 方法测试：" + id);
        return Result.success();
    }

    @PostMapping("/post")
    public Result<String> testPost(@RequestBody UserDto req) {
        System.out.println("Post 方法测试：" + req.toString());
        return Result.success();
    }

    @PutMapping("/put")
    public Result<String> testPut(@RequestBody UserDto req) {
        System.out.println("Put 方法测试：" + req.toString());
        return Result.success();
    }
}
