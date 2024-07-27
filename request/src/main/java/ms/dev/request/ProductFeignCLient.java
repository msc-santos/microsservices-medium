package ms.dev.request;

import ms.dev.request.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "product", path = "/products", url = "localhost:8000")
public interface ProductFeignCLient {
  @GetMapping(value = "/{id}")
  ResponseEntity<Product> findById(@PathVariable Long id);
}
