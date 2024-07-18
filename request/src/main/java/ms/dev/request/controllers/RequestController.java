package ms.dev.request.controllers;

import ms.dev.request.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class RequestController {
    @Autowired
    RequestService requestService;

}
