package com.phihung.tao247.repository;

import com.phihung.tao247.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.id = :userId and " +
            "(o.orderStatus = 'ĐÃ ĐẶT' or " +
            "o.orderStatus = 'ĐÃ XÁC NHẬN' or " +
            "o.orderStatus = 'ĐÃ GIAO' or " +
            "o.orderStatus = 'ĐÃ NHẬN')")
    public List<Order> getUsersOrders(@Param("userId") Long userId);
}
