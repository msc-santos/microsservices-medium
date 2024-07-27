package ms.dev.request.services;

import ms.dev.request.ProductFeignCLient;
import ms.dev.request.dtos.RequestDTO;
import ms.dev.request.dtos.RequestResponseDTO;
import ms.dev.request.entities.Product;
import ms.dev.request.entities.Request;
import ms.dev.request.entities.RequestProduct;
import ms.dev.request.exceptions.EventNotFoundExecption;
import ms.dev.request.repositories.RequestProductRepository;
import ms.dev.request.repositories.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RequestService {
  @Autowired
  private RequestRepository requestRepository;

  @Autowired
  private RequestProductRepository requestProductRepository;

  @Autowired
  private ProductFeignCLient productFeignCLient;

  public Page<Request> findByUserId(Long userId, Pageable pageable) {
    Page<Request> requests = requestRepository.findByUserId(userId, pageable);

    if (requests.isEmpty()) {
      throw new EventNotFoundExecption("No requests found for user with id " + userId);
    }

    return requests;
  }

  @Transactional
  public RequestResponseDTO create(RequestDTO requestDTO) {
    Request data = new Request();
    RequestProduct requestProduct = new RequestProduct();
    Product product = getProductById(requestDTO.productId());

    Double total = requestDTO.quantity() * product.getPrice();

    data.setQuantity(requestDTO.quantity());
    data.setTotalValue(total);
    data.setUserId(requestDTO.userId());

    Request saved = requestRepository.save(data);

    requestProduct.setRequest(saved);
    requestProduct.setProductId(requestDTO.productId());
    requestProductRepository.save(requestProduct);

    return convertToDto(saved);
  }

  public RequestResponseDTO update(Long id, RequestDTO requestDTO) {
    Optional<Request> selectRequest = requestRepository.findById(id);

    if (selectRequest.isPresent()) {
      Product product = getProductById(requestDTO.productId());
      Double total = requestDTO.quantity() * product.getPrice();

      Request dataItem = selectRequest.get();
      dataItem.setQuantity(requestDTO.quantity());
      dataItem.setTotalValue(total);

      Request saved = requestRepository.save(dataItem);
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
        request.getTotalValue(),
        request.getUserId());
  }

  private Product getProductById(Long productId) {
    Product product = productFeignCLient.findById(productId).getBody();
    return product;
  }
}
