package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.exception.CustomException;
import com.system.entity.*;
import com.system.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    @Resource(name = "trainingServiceImpl")
    private TrainingService trainingService;

    @Resource(name = "feeServiceImpl")
    private FeeService feeService;

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model, Integer page) throws Exception {

        List<CourseCustom> list = null;
        
        PagingVO pagingVO = new PagingVO();
        
        pagingVO.setTotalCount(courseService.getCountCourse());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = courseService.findByPaging(page);
        }

        model.addAttribute("courseList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "student/showCourse";
    }

    
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        //        System.out.println("coursesssss::::::::::::::::::::::::::::::");
        //        System.out.println(JSON.toJSONString(selectedCourseCustom));

        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
        } else {
            throw new CustomException("repetitive selection");
        }

        return "redirect:/student/selectedCourse";
    }

    @RequestMapping(value = "/showTrain")
    public String teaShowTrain(Model model) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<Training> trainList = trainingService.findAllById(Integer.parseInt(username));
        model.addAttribute("trainList", trainList);

        return "student/showTrain";
    }

    @RequestMapping(value = "/viewTrain", method = {RequestMethod.GET})
    public String toViewTrain(Integer id, Model model) throws Exception {
        if (id == null) {

            return "redirect:/student/showTrain";
        }
//        System.out.println("id::::::::::::::::::::::::::::::");
//        System.out.println(id);

        TrainingExample example = new TrainingExample();
        TrainingExample.Criteria criteria = example.createCriteria();
        criteria.andTrainidEqualTo(id);
        List<Training> list = trainingService.selectByExample(example);

        Training training = list.get(0);
//        System.out.println("training2222::::::::::::::::::::::::::::::");
//        System.out.println(JSON.toJSONString(training));

        model.addAttribute("training", training);

        return "student/viewTrain";
    }


//    @RequestMapping(value = "/viewTrain", method = {RequestMethod.POST})
//    public String viewTrain(Training training) throws Exception {
//
//        System.out.println("update:::::::::::::::::::::");
//        System.out.println(JSON.toJSONString(training));
//
//        TrainingExample example = new TrainingExample();
//        TrainingExample.Criteria criteria = example.createCriteria();
//        criteria.andTrainidEqualTo(training.getTrainid());
//        trainingService.updateByExample(training, example);
//
//
//        return "redirect:/student/showTrain";
//    }

    
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        selectedCourseService.remove(selectedCourseCustom);

        return "redirect:/student/selectedCourse";
    }

    
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model, Integer page) throws Exception {

        PagingVO pagingVO = new PagingVO();
        
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);
        model.addAttribute("pagingVO", pagingVO);
        System.out.println(list);

        return "student/selectCourse";
    }

    
    @RequestMapping(value = "/overCourse")
    public String overCourse(Model model) throws Exception {

        
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/overCourse";
    }

    
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }


    @RequestMapping("/showFee")
    public String showFee(Model model, Integer page) throws  Exception{
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        Userlogin userlogin = userloginService.findByName(username);
        if(userlogin == null){
            return "student/showFee";
        }

        List<Fee> list = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalCount(feeService.getFeeCountByName(username));
        if(page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = feeService.findByName(username);
        }else{
            pagingVO.setToPageNo(page);
            list = feeService.findByName(username);
        }

        model.addAttribute("feeList",list);
        model.addAttribute("pagingVO",pagingVO);

        return "student/showFee";
    }


    @RequestMapping(value = "/editFee", method = {RequestMethod.GET} )
    private String editFee(Integer id) throws Exception {
        if (id == null) {
            return "student/showFee";
        }
        feeService.accept(id);
        return "redirect:/student/showFee";
    }

    @RequestMapping(value = "/selectFee", method = {RequestMethod.GET})
    public String selectFee(Integer id, Model model) throws Exception{

        Fee fee = feeService.findById(id);
        model.addAttribute("feeList",fee);

        return "student/showFee";
    }

}
