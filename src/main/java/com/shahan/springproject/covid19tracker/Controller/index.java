package com.shahan.springproject.covid19tracker.Controller;

import com.shahan.springproject.covid19tracker.DAO.Locationstats;
import com.shahan.springproject.covid19tracker.ServiceLayer.Coviddataservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class index {
    @Autowired
    Coviddataservice coviddataservice;

    @GetMapping("/")
    public String home(Model model){
        List<Locationstats> total= coviddataservice.getAllstats();
        /*for total count we could run loop and count total but because its UI only scope we are taking the list
        of objects Locationstats converting into a stream then mapping to integer the , the sum value
         */
        int totalsum =total.stream().mapToInt(stat-> stat.getTotal()).sum();
        model.addAttribute("locationstats", total);
        model.addAttribute("Totalreporttedcases",totalsum);

        return "index1";
    }
}
