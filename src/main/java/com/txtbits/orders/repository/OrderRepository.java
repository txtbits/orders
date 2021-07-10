package com.txtbits.orders.repository;

import com.txtbits.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByOrderIdAsc();

    @Query("SELECT o.region, COUNT(o.region) FROM Order AS o GROUP BY o.region ORDER BY o.region DESC")
    List<Object[]> countTotalsByRegion();

    @Query("SELECT o.country, COUNT(o.country) FROM Order AS o GROUP BY o.country ORDER BY o.country DESC")
    List<Object[]> countTotalsByCountry();

    @Query("SELECT o.itemType, COUNT(o.itemType) FROM Order AS o GROUP BY o.itemType ORDER BY o.itemType DESC")
    List<Object[]> countTotalsByItemType();

    @Query("SELECT o.salesChannel, COUNT(o.salesChannel) FROM Order AS o GROUP BY o.salesChannel ORDER BY o.salesChannel DESC")
    List<Object[]> countTotalsBySalesChannel();

    @Query("SELECT o.orderPriority, COUNT(o.orderPriority) FROM Order AS o GROUP BY o.orderPriority ORDER BY o.orderPriority DESC")
    List<Object[]> countTotalsByOrderPriority();

}