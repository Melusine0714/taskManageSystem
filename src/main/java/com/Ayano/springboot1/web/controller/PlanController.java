package com.Ayano.springboot1.web.controller;

import com.Ayano.springboot1.pojo.Plan;
import com.Ayano.springboot1.pojo.Task;
import com.Ayano.springboot1.pojo.User;
import com.Ayano.springboot1.service.PlanService;
import com.Ayano.springboot1.service.TaskService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Plan")
public class PlanController {
    @Resource
    private PlanService planService;
    @Resource
    private TaskService taskService;

    @RequestMapping("/toPlanSelect")
    public String toPlanSelect(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Task> taskList = planService.selectTaskList(user);
        request.getSession().setAttribute("taskList", taskList);
        return "/planSelect";
    }

    @RequestMapping("/toPlanAdd")
    public String toPlanAdd(Task task, HttpServletRequest request) {
        request.getSession().setAttribute("task", task);
        return "/planAdd";
    }

    @RequestMapping("/toPlanUpdate")
    public String toPlanUpdate(Plan plan, HttpServletRequest request) {
        request.getSession().setAttribute("plan", plan);
        return "planUpdate";
    }

    @RequestMapping("/SelectTask")
    public String selectTask(User user, Integer pageSize, Integer pageNum, HttpServletRequest request) {
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 5;

        User employee = (User) request.getSession().getAttribute("user");
        user.setUserId(employee.getUserId());
        PageInfo<Task> pageInfo = planService.selectTask(user, pageNum, pageSize);
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
        return "taskESelect";
    }

    @RequestMapping("/SelectPlans")
    public String selectPlans(Plan plan, Integer pageSize, Integer pageNum, HttpServletRequest request) {
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 5;

        PageInfo<Plan> pageInfo = planService.selectPlans(plan, pageNum, pageSize);
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
        return "/planSelectResult";
    }

    @RequestMapping("/SelectTaskPlans")
    public String selectTaskPlans(User user, Integer pageSize, Integer pageNum, HttpServletRequest request) {
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 5;

        User employee = (User) request.getSession().getAttribute("user");
        user.setUserId(employee.getUserId());
        PageInfo<Task> pageInfo = planService.selectTask(user, pageNum, pageSize);
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
        return "/taskEMine";
    }

    @RequestMapping("/SelectTaskDetail")
    public String selectTaskDetail(Task task, HttpServletRequest request) {
        List<Task> tList = planService.selectTaskOne(task.getTaskId());
        List<Plan> task_plans = planService.selectTask_Plans(task.getTaskId());
        request.getSession().setAttribute("tList", tList);
        request.getSession().setAttribute("task_plans", task_plans);
        if (task_plans == null || task_plans.size() == 0) request.setAttribute("msg", "对不起没有查询到任何的结果");
        return "/taskEDetail";
    }

    @RequestMapping("/AddPlan")
    public String AddPlan(Plan plan, HttpServletRequest request) {
        User employee = (User) request.getSession().getAttribute("user");
        Task task = (Task) request.getSession().getAttribute("task");
        plan.setPlanEmployee(employee.getUserId());
        plan.setTaskId(task.getTaskId());
        planService.addPlan(plan);

        //添加计划后将任务改为执行中状态
        task.setTaskStatus(1);
        taskService.updateTaskStatus(task);
        return "/welcome";
    }

    @RequestMapping("/UpdatePlan")
    public String updatePlan(Plan plan, HttpServletRequest request) {
        Plan plan1 = (Plan) request.getSession().getAttribute("plan");
        plan.setPlanId(plan1.getPlanId());
        if (plan.getPlanFeedback().equals("")) {
            plan.setPlanIsFeedback(0);
        }else{
            plan.setPlanIsFeedback(1);
        }
        planService.updatePlan(plan);
        return "/welcome";
    }

    @RequestMapping("/DeletePlan")
    public String deletePlan(Plan plan, HttpServletRequest request) {
        planService.deletePlan(plan);
        return "/welcome";
    }
}
