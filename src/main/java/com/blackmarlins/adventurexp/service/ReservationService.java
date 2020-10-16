package com.blackmarlins.adventurexp.service;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.reservation.Reservation;
import com.blackmarlins.adventurexp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation findActivityById(Long reservationId) {
        Optional<Reservation> result = reservationRepository.findById(reservationId);
        Reservation reservation = null;
        if (result.isPresent()) {
            reservation = result.get();
        }
        return reservation;
    }


    public double calculatePrice (Reservation reservation) {
         return reservation.getActivity().getHourlyPrice() *
                reservation.getAmountOfPeople() * reservation.getHours();
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void cancel(Long id) {
        reservationRepository.deleteById(id);

    }

    public List<Reservation> findByActivityName(String name) {
        return reservationRepository.findActiveReservationsByActivity_Name(name);
    }

    public List<Reservation> findAllActiveReservations() {
        return reservationRepository.findAllActiveReservations();
    }
}
