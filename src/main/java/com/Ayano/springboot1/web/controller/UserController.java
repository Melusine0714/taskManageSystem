package com.Ayano.springboot1.web.controller;

import com.Ayano.springboot1.pojo.S_E;
import com.Ayano.springboot1.pojo.User;
import com.Ayano.springboot1.service.PlanService;
import com.Ayano.springboot1.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping("/toUserAdd")
    public String toUserAdd() {
        return "/userAdd";
    }

    @RequestMapping("toUserUpdate")
    public String toUserUpdate(User user, HttpServletRequest request) {
        User newUser = userService.selectUserOne(user);
        request.getSession().setAttribute("newUser", newUser);
        return "/userUpdate";
    }


    @RequestMapping("/Login")
    public String login(String account, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.selectUser(account, password);
        if (user == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('您输入的账户或密码有误!');</script>");
            return "/login";
        }
        request.getSession().setAttribute("user", user);
        return "/main";
    }

    @RequestMapping("/SelectUserAll")
    public String selectUserAll(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        //1.判断
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 5;
        //2. 查询
        PageInfo<User> pageInfo = userService.selectUserAll(pageNum, pageSize);
        List<User> supervisors = userService.selectSupervisorAll();
        //3. 获取总页数
        int totalPage = pageInfo.getPages();
        Integer[] totalPages = new Integer[totalPage];
        for (int i = 0; i < totalPage; i++) {
            totalPages[i] = i + 1;
        }
        //4. 设置pageSize和pageNum给pageInfo
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        request.getSession().setAttribute("totalPages", totalPages);
        request.getSession().setAttribute("pageInfo", pageInfo);
        request.getSession().setAttribute("sList", supervisors);
        return "/userSelectAll";
    }

    @RequestMapping("/AddUser")
    public String addUser(User user, HttpServletResponse response) throws IOException {
        userService.addUser(user);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('添加成功');</script>");
        return "/welcome";
    }

    @RequestMapping("/UpdateUser")
    public String update(User user, S_E s_e,HttpServletRequest request, HttpServletResponse response) throws IOException {
        User newUser = (User)request.getSession().getAttribute("newUser");
        user.setUserId(newUser.getUserId());
        s_e.setEmployeeId(user.getUserId());
        int res = userService.updateUser(user, s_e);
        if (res == 1) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('修改成功');</script>");
        } else if (res == 0) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('修改失败！请检查输入！');</script>");
        }
        return "/userSelectAll";
    }

    @RequestMapping("/DeleteUser")
    public String deleteUser(User user) {
        userService.deleteUser(user.getUserId());
        return "/welcome";
    }
}
