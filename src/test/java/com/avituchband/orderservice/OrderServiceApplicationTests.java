package com.avituchband.orderservice;

import com.avituchband.orderservice.dto.OrderLineItemsDto;
import com.avituchband.orderservice.dto.OrderRequest;
import com.avituchband.orderservice.repository.OrderRepository;
import com.avituchband.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldCreateOrder() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderLineItemsListDto(
                List.of(
                        OrderLineItemsDto.builder()
                                .skuCode("Iphone 13")
                                .price(BigDecimal.valueOf(1200))
                                .quantity(1)
                                .build()
                )

        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest))
        ).andExpect(status().isCreated());

        orderService.placeOrder(orderRequest);





    }

}
