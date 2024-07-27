package ms.dev.request.controllers;

import ms.dev.request.dtos.RequestDTO;
import ms.dev.request.dtos.RequestResponseDTO;
import ms.dev.request.entities.Request;
import ms.dev.request.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {
  @Autowired
  RequestService requestService;

  @GetMapping(value = "/user/{userId}")
  public ResponseEntity<Page<Request>> getByUserId(@PathVariable Long userId,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(requestService.findByUserId(userId, pageable));
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
