package com.osm8.booksjustbooks.repo;

import com.osm8.booksjustbooks.models.OrderModel;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderModel, Long> {
}
