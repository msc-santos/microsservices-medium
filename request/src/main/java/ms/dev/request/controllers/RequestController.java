package ms.dev.request.controllers;

import ms.dev.request.dtos.RequestDTO;
import ms.dev.request.dtos.RequestResponseDTO;
import ms.dev.request.entities.Request;
import ms.dev.request.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {
    @Autowired
    RequestService requestService;

    @GetMapping
    public ResponseEntity<Page<Request>> index(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {

        Page<Request> requests = requestService.findAll(page, size);
        return ResponseEntity.ok(requests);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Request>> getByUserId(@PathVariable Long userId) {

        List<Request> requestsByUserId = requestService.findByUserId(userId);
        return ResponseEntity.ok(requestsByUserId);
    }

    @PostMapping
    public RequestResponseDTO create(@RequestBody RequestDTO requestDTO) {
        return requestService.create(requestDTO);
    }

    @PutMapping("/{id}")
    public RequestResponseDTO update(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        return requestService.update(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        requestService.delete(id);
    }

}
