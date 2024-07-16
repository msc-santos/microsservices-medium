package ms.dev.request.services;

import ms.dev.request.entities.Request;
import ms.dev.request.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @GetMapping
    public Page<Request> getAll(int page, int size) {
        return requestRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping(value = "/{id}")
    public Request findById(@PathVariable Long id) {
        return requestRepository.findById(id).get();
    }
}
