package ms.dev.request.repositories;

import ms.dev.request.entities.Request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
  Page<Request> findByUserId(Long userId, Pageable pageable);
}
