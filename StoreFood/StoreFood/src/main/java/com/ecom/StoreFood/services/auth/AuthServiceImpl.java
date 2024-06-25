package com.ecom.StoreFood.services.auth;

import com.ecom.StoreFood.dto.SignupRequest;
import com.ecom.StoreFood.dto.UserDto;
import com.ecom.StoreFood.entity.Order;
import com.ecom.StoreFood.entity.User;
import com.ecom.StoreFood.enums.OrderStatus;
import com.ecom.StoreFood.enums.UserRole;
import com.ecom.StoreFood.repository.OrderRepository;
import com.ecom.StoreFood.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OrderRepository orderRepository;
    public UserDto createUser(SignupRequest signupRequest){
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setLimiteCredito(0.0);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);



        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(createdUser);
        order.setOrderStatus(OrderStatus.Pending);
        orderRepository.save(order);



        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;


    }

    public Boolean hasUserWithEmail (String email){
        return userRepository.findFirstByEmail(email).isPresent();
    }


    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if( null == adminAccount){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }



    // New method to update credit limit by admin
    public UserDto updateCreditLimit(Long userId, Double newCreditLimit) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setLimiteCredito(newCreditLimit);
        User updatedUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(updatedUser.getId());
        userDto.setEmail(updatedUser.getEmail());
        userDto.setName(updatedUser.getName());
        userDto.setLimiteCredito(updatedUser.getLimiteCredito());


        return userDto;
    }
}
