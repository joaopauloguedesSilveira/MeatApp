package br.com.meatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.meatApp.domain.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {


}
