package com.applicantportal.ApplicantPortal.controller.jobs;

import com.applicantportal.ApplicantPortal.dto.Jobsdto;
import com.applicantportal.ApplicantPortal.repository.jobs.JobsPaginationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ShowAllJobs {
//    @Autowired
//    private JobsPaginationRepositoryImpl jobsPaginationRepositoryImpl;
//
//    //Pagination:
//    @GetMapping("/alljobs")
//    public String showAllPages(Model model){
//        return showOnePage(1,"job_id","asc",5, model);
//    }
//    @GetMapping("/alljobs/page={pageNo}")
//    public String showOnePage(@PathVariable("pageNo") int pageNo,
//                              @RequestParam("sortField") String sortField,
//                              @RequestParam("sortDir") String sortDir,
//                              @RequestParam("dropdownSize") int size,
//                              Model model) {
//        int pageSize = size;
//        Page<Jobsdto> page = jobsPaginationRepositoryImpl.findPage(pageNo, pageSize, sortField, sortDir);
//        int totalPages = page.getTotalPages();
//        long totalItems = page.getTotalElements();
//        List<Jobsdto> showAllJobsList = page.getContent();
//                                    Jobsdto jobsdto = new Jobsdto();
//                                    Field[] fields =jobsdto.getClass().getDeclaredFields();
//                                    List<String> variableNames = new ArrayList<>();
//                                    for(Field field : fields){
//                                        variableNames.add(field.getName());
//                                    }
//        model.addAttribute("jobsDtoDropdown", variableNames);
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalItems", totalItems);
//        model.addAttribute("showAllJobs", showAllJobsList);
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//        model.addAttribute("size", size);
//        return "showjobs";
//    }
//

}
