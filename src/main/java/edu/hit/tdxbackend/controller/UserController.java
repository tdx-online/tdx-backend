package edu.hit.tdxbackend.controller;

import edu.hit.tdxbackend.entity.ResultInfo;
import edu.hit.tdxbackend.entity.User;
import edu.hit.tdxbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@CrossOrigin
@RequestMapping("/user")
@RestController
@SessionAttributes("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param user  用户
     * @param model 模型
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResultInfo login(@RequestBody User user, Model model) {
        User existUser = userService.login(user.getUsername(), user.getPassword());
        ResultInfo info = new ResultInfo();
        if (null != existUser) {
            model.addAttribute("user", existUser);
//            System.out.println("写入existUser" + existUser);
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        return info;
    }

    /**
     * 用户注册
     *
     * @param user 用户
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResultInfo register(@RequestBody User user) {
        ResultInfo info = new ResultInfo();
        boolean flag = userService.register(user);
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("用户名已存在");
        }
        return info;
    }

    /**
     * 获取在线用户
     *
     * @param user 用户
     * @return 在线用户
     */
    @GetMapping("/getUser")
    public ResultInfo getUser(@SessionAttribute(name = "user", required = false) User user) {
        ResultInfo info = new ResultInfo();
        if (user != null) {
            info.setFlag(true);
            info.setData(user);
        } else {
            info.setFlag(false);
            info.setErrorMsg("获取在线用户信息失败");
        }
        return info;
    }

    /**
     * 用户登出
     *
     * @param sessionStatus 会话状态
     * @return 登出结果
     */
    @GetMapping("/logout")
    public ResultInfo logout(SessionStatus sessionStatus) {
        ResultInfo info = new ResultInfo();
        try {
            sessionStatus.setComplete();
            info.setFlag(true);
        } catch (Exception e) {
            info.setFlag(false);
        }
        return info;
    }

    /**
     * 获取所有用户
     *
     * @return 所有用户
     */
    @GetMapping("/getAllUser")
    public ResultInfo getAllUser() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setData(userService.getUserList());
        resultInfo.setFlag(true);
        return resultInfo;
    }

}
