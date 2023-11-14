package com.avituchband.orderservice.service;

import com.avituchband.orderservice.dto.OrderLineItemsDto;
import com.avituchband.orderservice.dto.OrderRequest;
import com.avituchband.orderservice.model.Order;
import com.avituchband.orderservice.model.OrderLineItems;
import com.avituchband.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsListDto()
                .stream()
                .map(this::toOrderLineItems)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(order);

    }

    public OrderLineItems toOrderLineItems(OrderLineItemsDto orderLineItemsDto){
        return new OrderLineItems(
                orderLineItemsDto.getId(),
                orderLineItemsDto.getSkuCode(),
                orderLineItemsDto.getPrice(),
                orderLineItemsDto.getQuantity()
        );
    }


}
