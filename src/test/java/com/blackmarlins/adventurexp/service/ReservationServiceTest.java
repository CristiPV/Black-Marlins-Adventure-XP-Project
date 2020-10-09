package com.blackmarlins.adventurexp.service;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.Customer;
import com.blackmarlins.adventurexp.model.reservation.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;

    private static Stream<Arguments> calculatePriceTestArguments()
    {
        return Stream.of(
                Arguments.of(new Reservation(new Activity("test1", "desc1", 2.5, 5), new Customer("c1", "c1", "t1"), LocalDateTime.now(), 2, 10), 50.0, true),
                // This should fail - Actual value 245.0
                Arguments.of(new Reservation(new Activity("test2", "desc2", 3.5, 5), new Customer("c2", "c2", "t2"), LocalDateTime.now(), 10, 7), 230.0, false),
                Arguments.of(new Reservation(new Activity("test3", "desc3", 4.5, 5), new Customer("c3", "c3", "t3"), LocalDateTime.now(), 48, 4), 864.0, true),
                Arguments.of(new Reservation(new Activity("test4", "desc4", 6.0, 5), new Customer("c4", "c4", "t4"), LocalDateTime.now(), 0, 9), 0.0, true)
        );
    }

    @ParameterizedTest
    @MethodSource("calculatePriceTestArguments")
    @DisplayName("Can calculate reservation price")
    void calculatePriceTest(Reservation reservation, double result , boolean expectedMatch)
    {
        assertThat(reservationService.calculatePrice(reservation) == result).isEqualTo(expectedMatch);
    }
}