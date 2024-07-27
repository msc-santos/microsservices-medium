package ms.dev.request.repositories;

import ms.dev.request.entities.RequestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestProductRepository extends JpaRepository<RequestProduct, Long> {
}
