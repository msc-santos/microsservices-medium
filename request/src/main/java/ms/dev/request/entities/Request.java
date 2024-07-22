package ms.dev.request.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private Double unityValue;
    private Double totalValue;
    private Long userId;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<RequestProduct> requestProducts = new HashSet<>();

    public Request() { }

    public Request(Long id, int quantity, Double unityValue, Double totalValue, Long userId) {
        this.id = id;
        this.quantity = quantity;
        this.unityValue = unityValue;
        this.totalValue = totalValue;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getUnityValue() {
        return unityValue;
    }

    public void setUnityValue(Double unityValue) {
        this.unityValue = unityValue;
    }

    public Set<RequestProduct> getRequestProducts() {
        return requestProducts;
    }

    public void setRequestProducts(Set<RequestProduct> requestProducts) {
        this.requestProducts = requestProducts;
    }
}
