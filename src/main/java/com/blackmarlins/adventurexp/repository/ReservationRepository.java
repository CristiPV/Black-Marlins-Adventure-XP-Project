package com.blackmarlins.adventurexp.repository;

import com.blackmarlins.adventurexp.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByActivity_Name(String name);
}
