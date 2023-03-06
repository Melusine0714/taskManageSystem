package com.Ayano.springboot1.web.controller;

import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import com.Ayano.springboot1.service.TaskService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @RequestMapping("/toTaskAdd")
    public String toTaskAdd(HttpServletRequest request) {
        //跳转页前获取该主管下所有员工，以下拉菜单显示
        User user = (User) request.getSession().getAttribute("user");
        Integer supervisorId = user.getUserId();
        List<User> employees = taskService.selectEmployeeAll(supervisorId);
        request.getSession().setAttribute("employees", employees);
        return "/taskAdd";
    }

    @RequestMapping("/toTaskUpdate")
    public String toTaskUpdate(HttpServletRequest request) {
        //跳转页前获取该主管下所有员工，以下拉菜单显示
        User user = (User) request.getSession().getAttribute("user");
        Integer supervisorId = user.getUserId();
        List<User> employees = taskService.selectEmployeeAll(supervisorId);
        request.getSession().setAttribute("employees", employees);
        return "/taskUpdate";
    }

    @RequestMapping("/SelectTask")
    public String selectTask(Integer pageSize, Integer pageNum, HttpServletRequest request) {
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 5;
        //获取主管ID
        User user = (User) request.getSession().getAttribute("user");
        Integer supervisorId = user.getUserId();

        PageInfo<Task> pageInfo = taskService.selectTask(supervisorId, pageNum, pageSize);
        if (pageInfo == null || pageInfo.getSize() == 0) request.setAttribute("msg", "对不起没有查询到任何的结果");

        int totalPage = pageInfo.getPages();
        Integer[] totalPages = new Integer[totalPage];
        for (int i = 0; i < totalPage; i++) {
            totalPages[i] = i + 1;
        }

        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        request.getSession().setAttribute("totalPages", totalPages);
        request.getSession().setAttribute("pageInfo", pageInfo);
        return "/taskSelect";
    }

    @RequestMapping("/SelectEmployee")
    public String selectEmployee(Integer pageSize, Integer pageNum, HttpServletRequest request) {
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 5;

        User user = (User) request.getSession().getAttribute("user");
        Integer supervisorId = user.getUserId();

        PageInfo<User> pageInfo = taskService.selectEmployee(supervisorId, pageNum, pageSize);
        if (pageInfo == null || pageInfo.getSize() == 0) request.setAttribute("msg", "对不起没有查询到任何的结果");

        int totalPage = pageInfo.getPages();
        Integer[] totalPages = new Integer[totalPage];
        for (int i = 0; i < totalPage; i++) {
            totalPages[i] = i + 1;
        }

        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        request.getSession().setAttribute("totalPages", totalPages);
        request.getSession().setAttribute("pageInfo", pageInfo);
        return "/employeeSelect";
    }

    //获取单个员工信息
    @RequestMapping("/SelectEmployeeDetail")
    public String toEmployeeDetail(User user, HttpServletRequest request) {
        System.out.println(user.getUserId());
        User employee = taskService.selectEmployeeDetail(user.getUserId());
        System.out.println(employee + "-------------------from taskController");
        request.getSession().setAttribute("employee", employee);
        return "/employeeDetail";
    }

    @RequestMapping("/AddTask")
    public String addTask(Task task, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从登陆时存储的作用域中获取主管ID
        User supervisor = (User) request.getSession().getAttribute("user");
        task.setSupervisorId(supervisor.getUserId());
        taskService.addTask(task);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<script>alert('添加成功');</script>");
        return "/welcome";
    }

    @RequestMapping("TrackTask")
    public String trackTask(Task task, HttpServletRequest request) {
        List<Task> taskList = taskService.selectTaskOne(task.getTaskId());
        List<Task> plans = taskService.selectPlans(task.getTaskId());
        //储存当前任务的ID，并带到下一个页面以进行更新和删除操作
        if (plans == null || plans.size() == 0) request.setAttribute("msg", "对不起没有查询到任何的结果");
        request.getSession().setAttribute("taskId", task);
        request.getSession().setAttribute("taskList", taskList);
        request.getSession().setAttribute("plans", plans);
        return "/taskTrack";
    }

    @RequestMapping("/UpdateTaskStatus")
    public String updateTaskStatus(Task task, HttpServletRequest request) {
        Task taskId = (Task)request.getSession().getAttribute("taskId");
        task.setTaskId(taskId.getTaskId());
        taskService.updateTaskStatus(task);
        return "/welcome";
    }

    @RequestMapping("/UpdateTask")
    public String updateTask(Task task, HttpServletRequest request) {
        Task taskId = (Task)request.getSession().getAttribute("taskId");
        task.setTaskId(taskId.getTaskId());
        taskService.updateTask(task);
        return "/welcome";
    }

    @RequestMapping("/DeleteTask")
    public String deleteTask(Task task) {
        taskService.deleteTask(task);
        return "/welcome";
    }
}