package com.jula.Controllers;

import com.jula.Model.Status;
import com.jula.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin

public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("/getAll")
    public List<Status> getStatuses(){
        return statusService.getAllStatuses();
    }

}
