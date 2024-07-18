package ms.dev.request.repositories;

import ms.dev.request.entities.RequestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestProductRepository extends JpaRepository<RequestProduct, Long> {
    List<RequestProduct> findByRequestId(Long requestId);
}

