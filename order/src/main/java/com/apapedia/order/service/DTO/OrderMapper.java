package com.apapedia.order.service.DTO;

import com.apapedia.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target="orderId", ignore = true)
    Order orderDTOToOrder(OrderDTO orderDTO);


    Order updateOrderDTOToOrder(UpdateOrderDTO updateOrderDTO);
    UpdateOrderDTO orderToUpdateOrderDTO(Order order);


}
