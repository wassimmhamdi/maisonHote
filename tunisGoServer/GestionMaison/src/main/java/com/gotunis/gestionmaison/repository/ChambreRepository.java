package com.gotunis.gestionmaison.repository;

import com.gotunis.gestionmaison.models.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    Optional<Chambre> findById(long id);

    List<Chambre> deleteById(long id );

}
