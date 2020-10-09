package com.blackmarlins.adventurexp.service;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.reservation.Reservation;
import com.blackmarlins.adventurexp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void cancel(Long id) {
        reservationRepository.deleteById(id);

    }
}
