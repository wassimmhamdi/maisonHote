package com.gotunis.gestionactivite.repository;

import com.gotunis.gestionactivite.model.Activite;
import com.gotunis.gestionactivite.model.Convention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConventionRepository extends JpaRepository<Convention,Long> {
    @Override
    Optional<Convention> findById(Long aLong);

    @Override
    List<Convention> findAllById(Iterable<Long> iterable);

    List<Convention> findByApprove(Boolean approve);

    //---------------- PropActivity------------------//
    List<Convention> findAllByActivite_IdClientAndApproveAndPropMaison(Long user_id,boolean approuver,boolean propMai);

    List<Convention> findAllByActivite_IdClientAndApproveAndPropMaisonAndPropAct(Long user_id,boolean approuver,boolean propMai,boolean propAct);

    //---------------- PropMaison------------------//

    List<Convention> findAllByMaison_IdUserAndApproveAndPropAct(Long user_id,boolean approuver,boolean propAct);

    List<Convention> findAllByMaison_IdUserAndApproveAndPropActAndPropMaison(Long user_id,boolean approuver,boolean propAct,boolean propMai);






}
