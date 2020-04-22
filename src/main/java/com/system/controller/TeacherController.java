package com.system.controller;

import com.alibaba.fastjson.JSON;
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
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    @Resource(name = "trainingServiceImpl")
    private TrainingService trainingService;

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "feeServiceImpl")
    private FeeService feeService;

    
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList", list);

        return "teacher/showCourse";
    }

    
    @RequestMapping(value = "/gradeCourse")
    public String gradeCourse(Integer id, Model model) throws Exception {
        if (id == null) {
            return "";
        }
        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
        model.addAttribute("selectedCourseList", list);
        return "teacher/showGrade";
    }

    
    @RequestMapping(value = "/mark", method = {RequestMethod.GET})
    public String toMark(SelectedCourseCustom scc, Model model) throws Exception {

        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        model.addAttribute("selectedCourse", selectedCourseCustom);

        return "teacher/mark";
    }

    
    @RequestMapping(value = "/mark", method = {RequestMethod.POST})
    public String mark(SelectedCourseCustom scc) throws Exception {
//        System.out.println("sscObject:::::::::::::::");
//        System.out.println(scc);
//        System.out.println("sscJSON::::::::::::::::");
//        System.out.println(JSON.toJSONString(scc));
        selectedCourseService.updataOne(scc);

        return "redirect:/teacher/gradeCourse?id="+scc.getCourseid();
    }

    
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }

    
    @RequestMapping(value = "/showTrain")
    public String teaShowTrain(Model model, Integer page) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        TrainingExample example = new TrainingExample();
        TrainingExample.Criteria criteria = example.createCriteria();
        criteria.andTeaidEqualTo(Integer.parseInt(username));

        List<TrainingCustom> list = null;

        
        PagingVO pagingVO = new PagingVO();
        
        pagingVO.setTotalCount(trainingService.getTrainingCount());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = trainingService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = trainingService.findByPaging(page);
        }

        model.addAttribute("trainList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "teacher/showTrain";
    }

    
    @RequestMapping(value = "/addTrain", method = {RequestMethod.GET})
    public String toAddStudent(Model model) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));

        model.addAttribute("courseList", list);

        return "teacher/addTrain";
    }

    
    @RequestMapping(value = "/addTrain", method = {RequestMethod.POST})
    public String releaseTrain(Training tr, Model model) throws Exception {

        tr.setTeaid(1001);

        TrainingExample example = new TrainingExample();

        Training inserttr = new Training();
        inserttr.setTrainid(trainingService.countByExample(example)+1);

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        inserttr.setTeaid(Integer.parseInt(username));
        inserttr.setCourseid(tr.getCourseid());
        inserttr.setTrdescription(tr.getTrdescription());
//        System.out.println("trJSON:::::::::::::::::");
//        System.out.println(JSON.toJSONString(tr));

        trainingService.insert(inserttr);

        return "redirect:/teacher/showTrain";
    }

    
    @RequestMapping(value = "/editTrain", method = {RequestMethod.GET})
    public String toEditTrain(Integer id, Model model) throws Exception {
        if (id == null) {
            
            return "redirect:/teacher/showTrain";
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

        return "teacher/editTrain";
    }

    
    @RequestMapping(value = "/editTrain", method = {RequestMethod.POST})
    public String editTrain(Training training) throws Exception {

//        System.out.println("update:::::::::::::::::::::");
//        System.out.println(JSON.toJSONString(training));

        TrainingExample example = new TrainingExample();
        TrainingExample.Criteria criteria = example.createCriteria();
        criteria.andTrainidEqualTo(training.getTrainid());
        trainingService.updateByExample(training, example);

        
        return "redirect:/teacher/showTrain";
    }

    
    @RequestMapping(value = "/removeTrain", method = {RequestMethod.GET} )
    private String removeStudent(Integer id) throws Exception {
        if (id == null) {
            
            return "teacher/showTrain";
        }
        TrainingExample example = new TrainingExample();
        TrainingExample.Criteria criteria = example.createCriteria();
        criteria.andTrainidEqualTo(id);
        trainingService.deleteByExample(example);

        return "redirect:/teacher/showTrain";
    }

    @RequestMapping("/showFee")
    public String showFee(Model model, Integer page) throws  Exception{
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        Userlogin userlogin = userloginService.findByName(username);
        if(userlogin == null){
            return "teacher/showFee";
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

        return "teacher/showFee";
    }


    @RequestMapping(value = "/editFee", method = {RequestMethod.GET} )
    private String editFee(Integer id) throws Exception {
        if (id == null) {
            return "teacher/showFee";
        }
        feeService.accept(id);
        return "redirect:/teacher/showFee";
    }

    @RequestMapping(value = "/selectFee", method = {RequestMethod.GET})
    public String selectFee(Integer id, Model model) throws Exception{

        Fee fee = feeService.findById(id);
        model.addAttribute("feeList",fee);

        return "teacher/showFee";
    }

}
