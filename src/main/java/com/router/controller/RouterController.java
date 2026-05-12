package com.router.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.router.model.Router;
import com.router.service.RouterService;

@RestController
@RequestMapping("/api/routers")
public class RouterController {

    @Autowired
    private RouterService service;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<Router> createRouter(@RequestBody Router router) {
        Router saved = service.saveRouter(router);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<Router>> getAllRouters() {
        List<Router> list = service.getAllRouters();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRouterById(@PathVariable Long id) {
        try {
            Optional<Router> router = service.getRouterById(id);
            return new ResponseEntity<>(router, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRouter(@PathVariable Long id, @RequestBody Router router) {
        try {
            Router updated = service.updateRouter(id, router);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRouter(@PathVariable Long id) {
        try {
            String msg = service.deleteRouter(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}