package com.system.controller;

import com.system.exception.CustomException;
import com.system.po.*;
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

    @RequestMapping("/showStudent")
    public String showStudent(Model model, Integer page) throws Exception {

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

        model.addAttribute("studentList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showStudent";
    }

    @RequestMapping(value = "/addStudent", method = {RequestMethod.GET})
    public String toAddStudent(Model model) throws Exception {

        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);

        return "admin/addStudent";
    }

    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
    public String addStudent(StudentCustom studentCustom, Model model) throws Exception {

        Boolean result = studentService.save(studentCustom);

        if (!result) {
            model.addAttribute("message", "repetitive stuID");
            return "error";
        }
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(studentCustom.getUserid().toString());
        userlogin.setPassword("123");
        userlogin.setRole(2);
        userloginService.save(userlogin);

        return "redirect:/admin/showStudent";
    }

    @RequestMapping(value = "/editStudent", method = {RequestMethod.GET})
    public String toEditStudent(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showStudent";
        }
        StudentCustom studentCustom = studentService.findById(id);
        if (studentCustom == null) {
            throw new CustomException("No Such Student");
        }
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
        model.addAttribute("student", studentCustom);


        return "admin/editStudent";
    }

    @RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
    public String editStudent(StudentCustom studentCustom) throws Exception {

        studentService.updataById(studentCustom.getUserid(), studentCustom);
        return "redirect:/admin/showStudent";
    }

    @RequestMapping(value = "/removeStudent", method = {RequestMethod.GET} )
    private String removeStudent(Integer id) throws Exception {
        if (id == null) {
            return "admin/showStudent";
        }
        studentService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showStudent";
    }

    @RequestMapping(value = "searchStudent", method = {RequestMethod.POST})
    private String searchStudent(String findByName, Model model) throws Exception {

        List<StudentCustom> list = studentService.findByName(findByName);

        model.addAttribute("studentList", list);
        return "admin/showStudent";
    }

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

    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
    public String toAddTeacher(Model model) throws Exception {

        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);

        return "admin/addTeacher";
    }

    @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
    public String addTeacher(TeacherCustom teacherCustom, Model model) throws Exception {

        Boolean result = teacherService.save(teacherCustom);

        if (!result) {
            model.addAttribute("message", "repetitive teaID");
            return "error";
        }
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(teacherCustom.getUserid().toString());
        userlogin.setPassword("123");
        userlogin.setRole(1);
        userloginService.save(userlogin);

        return "redirect:/admin/showTeacher";
    }

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

    @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
    public String editTeacher(TeacherCustom teacherCustom) throws Exception {

        teacherService.updateById(teacherCustom.getUserid(), teacherCustom);

        return "redirect:/admin/showTeacher";
    }

    @RequestMapping("/removeTeacher")
    public String removeTeacher(Integer id) throws Exception {
        if (id == null) {
            return "admin/showTeacher";
        }
        teacherService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showTeacher";
    }

    @RequestMapping(value = "searchTeacher", method = {RequestMethod.POST})
    private String searchTeacher(String findByName, Model model) throws Exception {

        List<TeacherCustom> list = teacherService.findByName(findByName);

        model.addAttribute("teacherList", list);
        return "admin/showTeacher";
    }

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

        return "admin/showCourse";

    }

    @RequestMapping(value = "/addCourse", method = {RequestMethod.GET})
    public String toAddCourse(Model model) throws Exception {

        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.finAll();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("teacherList", list);

        return "admin/addCourse";
    }

    @RequestMapping(value = "/addCourse", method = {RequestMethod.POST})
    public String addCourse(CourseCustom courseCustom, Model model) throws Exception {

        Boolean result = courseService.save(courseCustom);

        if (!result) {
            model.addAttribute("message", "课程号重复");
            return "error";
        }

        return "redirect:/admin/showCourse";
    }

    @RequestMapping(value = "/editCourse", method = {RequestMethod.GET})
    public String toEditCourse(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showCourse";
        }
        CourseCustom courseCustom = courseService.findById(id);
        if (courseCustom == null) {
            throw new CustomException("未找到该课程");
        }
        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.finAll();

        model.addAttribute("teacherList", list);
        model.addAttribute("collegeList", collegeList);
        model.addAttribute("course", courseCustom);


        return "admin/editCourse";
    }

    @RequestMapping(value = "/editCourse", method = {RequestMethod.POST})
    public String editCourse(CourseCustom courseCustom) throws Exception {

        courseService.upadteById(courseCustom.getCourseid(), courseCustom);
        return "redirect:/admin/showCourse";
    }

    @RequestMapping("/removeCourse")
    public String removeCourse(Integer id) throws Exception {
        if (id == null) {
            return "admin/showCourse";
        }
        courseService.removeById(id);

        return "redirect:/admin/showCourse";
    }

    @RequestMapping(value = "searchCourse", method = {RequestMethod.POST})
    private String searchCourse(String findByName, Model model) throws Exception {

        List<CourseCustom> list = courseService.findByName(findByName);

        model.addAttribute("courseList", list);
        return "admin/showCourse";
    }

    @RequestMapping("/userPasswordRest")
    public String toUserPasswordRest() throws Exception {
        return "admin/userPasswordRest";
    }

    @RequestMapping(value = "/userPasswordRest", method = {RequestMethod.POST})
    public String userPasswordRest(Userlogin userlogin) throws Exception {

        Userlogin u = userloginService.findByName(userlogin.getUsername());

        if (u != null) {
            if (u.getRole() == 0) {
                throw new CustomException("Can't reset a administrator");
            }
            u.setPassword(userlogin.getPassword());
            userloginService.updateByName(userlogin.getUsername(), u);
        } else {
            throw new CustomException("No such user");
        }

        return "admin/userPasswordRest";
    }

    @RequestMapping("/passwordRest")
    public String toPasswordRest() throws Exception {
        return "admin/passwordRest";
    }


    @RequestMapping("/showFee")
    public String showFee(Model model, Integer page) throws  Exception{
        List<Fee> list = null;
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

        return "admin/showFee";
    }

    @RequestMapping(value = "/addFee", method = {RequestMethod.GET})
    public String toAddFee(Model model) throws Exception{
        List<Userlogin> list = userloginService.findAll();
        model.addAttribute("userloginList",list);
        return "admin/addFee";
    }

    @RequestMapping(value = "/addFee", method = {RequestMethod.POST})
    public String addFee (Fee fee, Model model) throws Exception{
        if (fee.getFstatus() == null){
            fee.setFstatus(0);
        }

        Boolean result = feeService.save(fee);
        if(!result){
            model.addAttribute("message","已存在收费记录");
            return "error";
        }

        return "redirect:/admin/showFee";
    }

    @RequestMapping(value = "/editFee", method = {RequestMethod.GET})
    public String toEditFee(Integer id, Model model) throws Exception{
        if (id == null) {
            return "redirect:/admin/showFee";
        }
        Fee fee = feeService.findById(id);
        if (fee == null) {
            throw new CustomException("未找到收费记录");
        }

        List<Userlogin> list = userloginService.findAll();
        model.addAttribute("fee",fee);
        model.addAttribute("userloginList",list);

        return "admin/editFee";
    }

    @RequestMapping(value = "/editFee", method = {RequestMethod.POST})
    public String editFee(Fee fee, Model model) throws Exception{
        if (fee.getFstatus() == null){
            fee.setFstatus(0);
        }

        feeService.updatedById(fee.getFid(), fee);
        return "redirect:/admin/showFee";
    }

    @RequestMapping(value = "/removeFee", method = {RequestMethod.GET} )
    private String removeFee(Integer id) throws Exception {
        if (id == null) {
            return "admin/showFee";
        }
        feeService.removeById(id);
        return "redirect:/admin/showFee";
    }

    @RequestMapping(value = "/searchFee", method = {RequestMethod.GET})
    public String searchFee(Integer id, Model model) throws Exception{

        Fee fee = feeService.findById(id);
        model.addAttribute("feeList",fee);

        return "admin/showFee";
    }

}
