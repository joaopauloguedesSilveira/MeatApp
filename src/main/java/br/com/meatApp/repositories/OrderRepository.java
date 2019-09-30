package br.com.meatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meatApp.domain.Orders;

@Repository//para enteder que é da camada de repository
public interface OrderRepository  extends JpaRepository<Orders, Integer>{

}
