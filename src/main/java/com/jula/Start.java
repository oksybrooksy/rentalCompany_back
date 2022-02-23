package com.jula;

import com.jula.Repository.GameRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class Start {
    private GameRepo gameRepo;

//    public Start(GameRepo gameRepo) {
//        this.gameRepo = gameRepo;
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {

    }



}
