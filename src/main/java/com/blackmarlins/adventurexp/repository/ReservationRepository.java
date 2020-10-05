package com.blackmarlins.adventurexp.repository;

import com.blackmarlins.adventurexp.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
