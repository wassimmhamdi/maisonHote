package com.gotunis.gestionreservation.repositories;

import com.gotunis.gestionreservation.models.ReservationActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.Optional;

public interface ReservationActivityUser extends JpaRepository<ReservationActivity, Long> {
    @Override
    Optional<ReservationActivity> findById(Long aLong);

    //verify la reservation s'il existe
    Optional<ReservationActivity> findByIdMaisonAndIdActivityAndDateAndDoneAndCancelAndValidate(long idMaison,long idActivity,Date dateRes,boolean done,boolean cancel,boolean validate);
}
