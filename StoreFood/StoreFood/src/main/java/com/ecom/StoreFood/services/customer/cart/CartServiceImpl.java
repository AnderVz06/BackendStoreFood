package com.ecom.StoreFood.services.customer.cart;

import com.ecom.StoreFood.dto.AddProductInCartDto;
import com.ecom.StoreFood.dto.CartItemsDto;
import com.ecom.StoreFood.dto.OrderDto;
import com.ecom.StoreFood.entity.CartItems;
import com.ecom.StoreFood.entity.Order;
import com.ecom.StoreFood.entity.Product;
import com.ecom.StoreFood.entity.User;
import com.ecom.StoreFood.enums.OrderStatus;
import com.ecom.StoreFood.repository.CartItemsRepository;
import com.ecom.StoreFood.repository.OrderRepository;
import com.ecom.StoreFood.repository.ProductRepository;
import com.ecom.StoreFood.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private ProductRepository productRepository;



    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        try {
            // Verificar que los valores no sean nulos
            if (addProductInCartDto.getUserId() == null || addProductInCartDto.getProductId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID or Product ID is null");
            }

            // Buscar el pedido activo del usuario
            Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);

            // Verificar si se encontró un pedido activo
            if (activeOrder == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Active order not found for the user");
            }

            // Buscar si el producto ya está en el carrito para este pedido y usuario
            Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(
                    addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());

            // Verificar si el producto ya está en el carrito
            if (optionalCartItems.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already exists in the cart");
            } else {
                // Buscar el producto y el usuario
                Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
                Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

                // Verificar si se encontraron el producto y el usuario
                if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                    // Crear un nuevo elemento de carrito
                    CartItems cart = new CartItems();

                    cart.setProduct(optionalProduct.get());
                    cart.setPrice(optionalProduct.get().getPrice());
                    cart.setQuantity(1L);
                    cart.setUser(optionalUser.get());
                    cart.setOrder(activeOrder);

                    // Guardar el elemento de carrito y actualizar el pedido
                    CartItems updatedCart = cartItemsRepository.save(cart);

                    activeOrder.setTotalAmount(activeOrder.getTotalAmount() + updatedCart.getPrice());
                    activeOrder.setAmount(activeOrder.getAmount() + updatedCart.getPrice());
                    activeOrder.getCartItems().add(updatedCart);
                    orderRepository.save(activeOrder);

                    return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
                }
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }


    public OrderDto getCartByUserId(Long userId){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList =activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);


        return orderDto;

    }


}
