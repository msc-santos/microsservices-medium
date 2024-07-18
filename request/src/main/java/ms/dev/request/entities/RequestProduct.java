package ms.dev.request.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "requests_products")
public class RequestProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    private Long productId;

    public RequestProduct() { }

    public RequestProduct(Long id, Request request, Long productId) {
        this.id = id;
        this.request = request;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
