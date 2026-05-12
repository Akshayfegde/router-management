package com.router.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.router.model.Router;
import com.router.repository.RouterRepository;

@Service
public class RouterService {

	@Autowired
    private RouterRepository repository;
	
	 // 🔹 CREATE (POST)
    public Router saveRouter(Router router) {
        return repository.save(router);
    }

    // 🔹 READ ALL (GET)
    public List<Router> getAllRouters() {
        return repository.findAll();
    }

    // 🔹 READ BY ID (GET)
    public Optional<Router> getRouterById(Long id) {
        return repository.findById(id);
    }

    // 🔹 UPDATE (PUT)
    public Router updateRouter(Long id, Router router) {
        Optional<Router> existing = repository.findById(id);

        if (existing.isPresent()) {
            Router updatedRouter = existing.get();
            updatedRouter.setRouterId(router.getRouterId());
            updatedRouter.setCpuUsage(router.getCpuUsage());
            updatedRouter.setStatus(router.getStatus());
            updatedRouter.setTimestamp(router.getTimestamp());
            return repository.save(updatedRouter);
        } else {
            return null;
        }
    }

    // 🔹 DELETE (DELETE)
    public String deleteRouter(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Router deleted successfully";
        } else {
            throw new RuntimeException("Router not found with id: " + id);
        }
    }
}
