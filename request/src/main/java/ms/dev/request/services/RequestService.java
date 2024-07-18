package ms.dev.request.services;

import ms.dev.request.dtos.RequestDTO;
import ms.dev.request.entities.Request;
import ms.dev.request.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public Page<Request> getAll(int page, int size) {
        return requestRepository.findAll(PageRequest.of(page, size));
    }

    public Request findById(Long id) {
        return requestRepository.findById(id).get();
    }

    public void create (RequestDTO item) {
        var data = new Request();

        data.setQuantity(item.quantity());
        data.setTotalValue(item.totalValue());
        data.setUserId(item.userId());

        requestRepository.save(data);
    }

    public Request update (RequestDTO item) {
        Optional<Request> selectRequest = requestRepository.findById(item.id());

        if (selectRequest.isPresent()) {
            Request dataItem = selectRequest.get();
            dataItem.setQuantity(item.quantity());
            dataItem.setTotalValue(item.totalValue());

            return requestRepository.save(dataItem);
        }

        return null;
    }

    public void delete (RequestDTO item) {
        Optional<Request> selectRequest = requestRepository.findById(item.id());

        if (selectRequest.isPresent()) {
            requestRepository.deleteById(item.id());
        }

    }
    
}
