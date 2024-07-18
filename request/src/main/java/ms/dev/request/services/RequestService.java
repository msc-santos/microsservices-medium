package ms.dev.request.services;

import ms.dev.request.dtos.RequestDTO;
import ms.dev.request.dtos.RequestResponseDTO;
import ms.dev.request.entities.Request;
import ms.dev.request.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public Page<Request> findAll(int page, int size) {
        return requestRepository.findAll(PageRequest.of(page, size));
    }

    public List<Request> findByUserId(Long userId) {
        return requestRepository.findByUserId(userId);
    }

    public RequestResponseDTO  create (RequestDTO requestDTO) {
        Request data = new Request();

        Double total =  requestDTO.quantity() * requestDTO.unityValue();

        data.setQuantity(requestDTO.quantity());
        data.setUnityValue(requestDTO.unityValue());
        data.setTotalValue(total);
        data.setUserId(requestDTO.userId());

        Request saved = requestRepository.save(data);
        return convertToDto(saved);
    }

    public RequestResponseDTO update (Long id, RequestDTO requestDTO) {
        Optional<Request> selectRequest = requestRepository.findById(id);

        if (selectRequest.isPresent()) {
            Double total =  requestDTO.quantity() * requestDTO.unityValue();

            Request dataItem = selectRequest.get();
            dataItem.setQuantity(requestDTO.quantity());
            dataItem.setUnityValue(requestDTO.unityValue());
            dataItem.setTotalValue(total);

            Request saved =  requestRepository.save(dataItem);
            return convertToDto(saved);
        }

        throw new RuntimeException("Request not found with id " + id);
    }

    public void delete(Long id) {
        if (requestRepository.existsById(id)) {
            requestRepository.deleteById(id);
        } else {
            throw new RuntimeException("Request not found with id " + id);
        }
    }

    private RequestResponseDTO convertToDto(Request request) {
        return new RequestResponseDTO(
                request.getId(),
                request.getQuantity(),
                request.getUnityValue(),
                request.getTotalValue(),
                request.getUserId()
        );
    }
    
}
