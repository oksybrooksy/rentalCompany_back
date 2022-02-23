package com.jula.Service;

import com.jula.Model.Status;
import com.jula.Repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepo statusRepo;
    @Override
    public List<Status> getAllStatuses() {
        return (List<Status>) statusRepo.findAll();
    }
}
