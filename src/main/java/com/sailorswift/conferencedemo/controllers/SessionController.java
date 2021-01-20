package com.sailorswift.conferencedemo.controllers;

import com.sailorswift.conferencedemo.models.Session;
import com.sailorswift.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    // http verb
    @GetMapping
    public List<Session> list() {
        // findAll goes out and query all of the sessions in the database,
        // and return them back as a list of Session Objects,
        // Spring MVC will then pass that over to Jackson (a serialization library)
        // which will turn those sessions into JSON and return back to the caller
        return sessionRepository.findAll();
    }

    // create more endpoints
    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    // curd create update read delete
    // create a new session into the database
    // This is a API call
    // Spring MVC takes Json payload and get them into a session object
    // and then simply pass it to our sessionRepository
    @PostMapping
    public Session create(@RequestBody final Session session) {
        // save the object and push the object into the database.
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // also need to check for children records before delete
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
