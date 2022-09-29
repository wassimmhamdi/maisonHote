package com.gotunis.gestionreservation.repositories;

import java.util.List;
import java.util.Optional;

import com.gotunis.gestionreservation.models.ReservationManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReservationActRepository extends JpaRepository<ReservationManagement, Long> {

    Optional<ReservationManagement> findById(Long id);

    //returner tous les reservation par user
    List <ReservationManagement> findAllByCustomerId(long idUser);

    //returner tous les reservation par user
  //  List <ReservationManagement> findAllByCustomerIdAndAndCanceled(long idUser,boolean canceled);

    @Query(value="SELECT * from `reservation_management` where :maisonId = id_maison AND (debut_res BETWEEN :debut AND :end OR end_res BETWEEN :debut AND :end )" , nativeQuery = true)
    List<ReservationManagement> fromDate(@Param("debut")Date debut,@Param("end")Date end,@Param("maisonId")long maisonID);

    @Query(value="SELECT * from `reservation_management` where :roomId = id_chambre AND (debut_res BETWEEN :debut AND :end OR end_res BETWEEN :debut AND :end )" , nativeQuery = true)
    List<ReservationManagement> resRoom(@Param("debut")Date debut,@Param("end")Date end,@Param("roomId")long roomId);

}
