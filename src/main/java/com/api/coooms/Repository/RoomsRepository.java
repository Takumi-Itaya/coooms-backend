package com.api.coooms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.coooms.Model.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {

	Rooms findByName(String Name);
}
