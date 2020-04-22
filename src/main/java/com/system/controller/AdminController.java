package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.system.exception.CustomException;
import com.system.entity.*;
import com.system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "collegeServiceImpl")
    private CollegeService collegeService;

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "feeServiceImpl")
    private FeeService feeService;

    // used to go to the student list page and add data onto the table
    @RequestMapping("/showStudent")
    public String showStudent(Model model, Integer page) throws Exception {

//        System.out.println("111111111111111111111111");
//        System.out.println(page);

        List<StudentCustom> list = null;
        PagingVO pagingVO = new PagingVO();
        pagingVO.setTotalCount(studentService.getCountStudent());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = studentService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = studentService.findByPaging(page);
        }

//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
//        System.out.println(JSON.toJSONString(list));

        model.addAttribute("studentList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showStudent";
    }

    // to go the add student page
    @RequestMapping(value = "/addStudent", method = {RequestMethod.GET})
    public String toAddStudent(Model model) throws Exception {

        List<College> list = collegeService.finAll();
//        System.out.println(JSON.toJSONString(list));

        model.addAttribute("collegeList", list);

        return "admin/addStudent";
    }

    // receive the new student information
    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
    public String addStudent(StudentCustom studentCustom, Model model) throws Exception {

        Boolean result = studentService.save(studentCustom);

        if (!result) {
            model.addAttribute("message", "repetitive stuID");
            return "error";
        }
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(studentCustom.getUserid().toString());
        userlogin.setPassword("123456");
        userlogin.setRole(2);
        userloginService.save(userlogin);

        return "redirect:/admin/showStudent";
    }

    // edit the specific student. go to the edit page
    @RequestMapping(value = "/editStudent", method = {RequestMethod.GET})
    public String toEditStudent(Integer id, Model model) throws Exception {

        StudentCustom studentCustom = studentService.findById(id);
        if (studentCustom == null) {
            throw new CustomException("No Such Student");
        }
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
        model.addAttribute("student", studentCustom);


        return "admin/editStudent";
    }

    // receive the new information
    @RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
    public String editStudent(StudentCustom studentCustom) throws Exception {

        studentService.updataById(studentCustom.getUserid(), studentCustom);
        return "redirect:/admin/showStudent";
    }

    //remove a student
    @RequestMapping(value = "/removeStudent", method = {RequestMethod.GET} )
    private String removeStudent(Integer id) throws Exception {
        if (id == null) {
            return "admin/showStudent";
        }
        studentService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showStudent";
    }

    // search a student
    @RequestMapping(value = "searchStudent", method = {RequestMethod.POST})
    private String searchStudent(String findByName, Model model) throws Exception {

        //        System.out.println("*************************");
        //        System.out.println(findByName);

        List<StudentCustom> list = studentService.findByName(findByName);

        model.addAttribute("studentList", list);
        return "admin/showStudent";
    }

    // go to the teacher list page
    @RequestMapping("/showTeacher")
    public String showTeacher(Model model, Integer page) throws Exception {

        List<TeacherCustom> list = null;
        PagingVO pagingVO = new PagingVO();
        pagingVO.setTotalCount(teacherService.getCountTeacher());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = teacherService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = teacherService.findByPaging(page);
        }

        model.addAttribute("teacherList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showTeacher";

    }

    // add a new teacher page
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
    public String toAddTeacher(Model model) throws Exception {

        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);

        return "admin/addTeacher";
    }

    //receive information of the new teacher
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
    public String addTeacher(TeacherCustom teacherCustom, Model model) throws Exception {

        Boolean result = teacherService.save(teacherCustom);

        if (!result) {
            model.addAttribute("message", "repetitive teaID");
            return "error";
        }
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(teacherCustom.getUserid().toString());
        userlogin.setPassword("123456");
        userlogin.setRole(1);
        //        System.out.println("*************************");
        //        System.out.println(userlogin);
        userloginService.save(userlogin);

        return "redirect:/admin/showTeacher";
    }

    // go to edit page
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.GET})
    public String toEditTeacher(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showTeacher";
        }
        TeacherCustom teacherCustom = teacherService.findById(id);
        if (teacherCustom == null) {
            throw new CustomException("No such student");
        }
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
        model.addAttribute("teacher", teacherCustom);


        return "admin/editTeacher";
    }

    // receive modification information
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
    public String editTeacher(TeacherCustom teacherCustom) throws Exception {

        teacherService.updateById(teacherCustom.getUserid(), teacherCustom);

        return "redirect:/admin/showTeacher";
    }

    // remove  a teacher
    @RequestMapping("/removeTeacher")
    public String removeTeacher(Integer id) throws Exception {
        //        System.out.println(id);
        if (id == null) {
            return "admin/showTeacher";
        }
        teacherService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showTeacher";
    }

    // search a teacher
    @RequestMapping(value = "searchTeacher", method = {RequestMethod.POST})
    private String searchTeacher(String findByName, Model model) throws Exception {

        List<TeacherCustom> list = teacherService.findByName(findByName);

        model.addAttribute("teacherList", list);
        return "admin/showTeacher";
    }

    // go to course list page
    @RequestMapping("/showCourse")
    public String showCourse(Model model, Integer page) throws Exception {

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
        //        System.out.println("LLLLLLLLLLLLLLLLLL");
        //        System.out.println(findByName);

        return "admin/showCourse";

    }

    // add a new course page
    @RequestMapping(value = "/addCourse", method = {RequestMethod.GET})
    public String toAddCourse(Model model) throws Exception {

        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.finAll();

        System.out.println("111111111111111111111111");
        System.out.println(JSON.toJSONString(list));

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("teacherList", list);

        //        System.out.println("LLLLLLLLLLLLLLLLLL");
        //        System.out.println(model);

        return "admin/addCourse";
    }

    // receive new course information
    @RequestMapping(value = "/addCourse", method = {RequestMethod.POST})
    public String addCourse(CourseCustom courseCustom, Model model) throws Exception {

        //        System.out.println("course::::::::::::::::::::::");
        //        System.out.println(courseCustom);

        Boolean result = courseService.save(courseCustom);

        if (!result) {
            model.addAttribute("message", "redundant course");
            return "error";
        }

        return "redirect:/admin/showCourse";
    }

    // edit the course page
    @RequestMapping(value = "/editCourse", method = {RequestMethod.GET})
    public String toEditCourse(Integer id, Model model) throws Exception {
        CourseCustom courseCustom = courseService.findById(id);
        if (courseCustom == null) {
            throw new CustomException("No such courses");
        }
        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.finAll();

//        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
//        System.out.println(JSON.toJSONString(list));
//        System.out.println("eeeeeeeeeeeeeeeeee");
//        System.out.println(JSON.toJSONString(collegeList));
//        System.out.println("wwwwwwwwwwwwwwwwwwwww");
//        System.out.println(JSON.toJSONString(courseCustom));

        model.addAttribute("teacherList", list);
        model.addAttribute("collegeList", collegeList);
        model.addAttribute("course", courseCustom);


        return "admin/editCourse";
    }

    // receive modified information
    @RequestMapping(value = "/editCourse", method = {RequestMethod.POST})
    public String editCourse(CourseCustom courseCustom) throws Exception {
//        System.out.println("ttttttttttttttttttttttttttttttttttttt");
//        System.out.println(JSON.toJSONString(courseCustom));

        courseService.upadteById(courseCustom.getCourseid(), courseCustom);
        return "redirect:/admin/showCourse";
    }

    //remove a course
    @RequestMapping("/removeCourse")
    public String removeCourse(Integer id) throws Exception {
        if (id == null) {
            return "admin/showCourse";
        }
        courseService.removeById(id);

        return "redirect:/admin/showCourse";
    }

    //search a course
    @RequestMapping(value = "searchCourse", method = {RequestMethod.POST})
    private String searchCourse(String findByName, Model model) throws Exception {

        List<CourseCustom> list = courseService.findByName(findByName);

        model.addAttribute("courseList", list);
        return "admin/showCourse";
    }

    //reset password page
    @RequestMapping("/userPasswordRest")
    public String toUserPasswordRest() throws Exception {
        return "admin/userPasswordRest";
    }

    // receive modifed information
    @RequestMapping(value = "/userPasswordRest", method = {RequestMethod.POST})
    public String userPasswordRest(Userlogin userlogin) throws Exception {

        Userlogin username = userloginService.findByName(userlogin.getUsername());

//        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//        System.out.println(username);
//        System.out.println("ttttttttttttttttttttttttttttttttttttt");
//        System.out.println(JSON.toJSONString(userlogin));

        if (username != null) {
            if (username.getRole() == 0) {
                throw new CustomException("Can't reset a administrator");
            }
            username.setPassword(userlogin.getPassword());
            userloginService.updateByName(userlogin.getUsername(), username);
        } else {
            throw new CustomException("No such user");
        }

        return "admin/userPasswordRest";
    }
    // reset own password page
    @RequestMapping("/passwordRest")
    public String toPasswordRest() throws Exception {
        return "admin/passwordRest";
    }

    //go to fee page
    @RequestMapping("/showFee")
    public String showFee(Model model, Integer page) throws  Exception{
        List<Fee> list = null;
//        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//        System.out.println(JSON.toJSONString(page));
        PagingVO pagingVO = new PagingVO();
        pagingVO.setTotalCount(feeService.getFeeCount());
        if(page == null || page == 0){
            pagingVO.setToPageNo(1);
            list = feeService.findByPaging(1);
        }else{
            pagingVO.setToPageNo(page);
            list = feeService.findByPaging(page);
        }

        model.addAttribute("feeList",list);
        model.addAttribute("pagingVO",pagingVO);
//        System.out.println("::::::::::::::::::::::::::::::");
//        System.out.println(JSON.toJSONString(model));

        return "admin/showFee";
    }

    // go to add new fee page
    @RequestMapping(value = "/addFee", method = {RequestMethod.GET})
    public String toAddFee(Model model) throws Exception{
        List<Userlogin> list = userloginService.findAll();
        model.addAttribute("userloginList",list);
        return "admin/addFee";
    }

    // receive new information
    @RequestMapping(value = "/addFee", method = {RequestMethod.POST})
    public String addFee (Fee fee, Model model) throws Exception{
        if (fee.getFstatus() == null){
            fee.setFstatus(0);
        }

        Boolean result = feeService.save(fee);
        if(!result){
            model.addAttribute("message","repetitive record");
            return "error";
        }

        return "redirect:/admin/showFee";
    }

    //go to edit fee page
    @RequestMapping(value = "/editFee", method = {RequestMethod.GET})
    public String toEditFee(Integer id, Model model) throws Exception{
        Fee fee = feeService.findById(id);
        if (fee == null) {
            throw new CustomException("no such record");
        }

        List<Userlogin> list = userloginService.findAll();
        model.addAttribute("fee",fee);
        model.addAttribute("userloginList",list);
//        System.out.println("////////////////////////////");
//        System.out.println(JSON.toJSONString(fee));

        return "admin/editFee";
    }

    // receive modified fee information
    @RequestMapping(value = "/editFee", method = {RequestMethod.POST})
    public String editFee(Fee fee, Model model) throws Exception{

//        System.out.println("FEE::::::::::::::::::::::::::::::");
//        System.out.println(JSON.toJSONString(fee));
        if (fee.getFstatus() == null){
            fee.setFstatus(0);
        }

        feeService.updatedById(fee.getFid(), fee);
        return "redirect:/admin/showFee";
    }

    // remove a fee record
    @RequestMapping(value = "/removeFee", method = {RequestMethod.GET} )
    private String removeFee(Integer id) throws Exception {

//        System.out.println("id::::::::::::::::::::::::::::::");
//        System.out.println(id);
        if (id == null) {
            return "admin/showFee";
        }
        feeService.removeById(id);
        return "redirect:/admin/showFee";
    }

    //search a fee record
    @RequestMapping(value = "/searchFee", method = {RequestMethod.GET})
    public String searchFee(Integer id, Model model) throws Exception{

//        System.out.println("id::::::::::::::::::::::::::::::");
//        System.out.println(id);

        Fee fee = feeService.findById(id);
        model.addAttribute("feeList",fee);

        return "admin/showFee";
    }

}
