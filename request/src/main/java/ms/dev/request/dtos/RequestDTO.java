package ms.dev.request.dtos;

public record RequestDTO(int quantity, Double unityValue, Double totalValue, Long userId, Long productId) {
}
