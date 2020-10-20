package com.blackmarlins.adventurexp.repository;

import com.blackmarlins.adventurexp.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // native query to get all future reservations matching the given name parameter
    @Query(value = "select * from reservation inner join activity a on reservation.activity_id = a.id " +
                    "where a.name = ?1 and date > current_timestamp order by date", nativeQuery = true)
    List<Reservation> findActiveReservationsByActivity_Name(String name);

    // native query to get all future reservations
    @Query(value = "select * from reservation where date >= current_timestamp order by date", nativeQuery = true)
    List<Reservation> findAllActiveReservations();
}
