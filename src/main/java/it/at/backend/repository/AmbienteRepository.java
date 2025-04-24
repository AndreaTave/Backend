package it.at.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import it.at.backend.entity.Ambiente;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AmbienteRepository extends JpaRepository<Ambiente, Integer> {

    @Query(value="SELECT * FROM ambiente;", nativeQuery=true)
    List<Ambiente> cercaTuttiNativo();

    @Query(value="SELECT NEW it.at.backend.entity.Ambiente(A.tabella, A.valore) \n"
                +"FROM it.at.backend.entity.Ambiente AS A "
                +"WHERE A.valore='3'")
    List<Ambiente> cercaTuttiValori3JPQL();

    Ambiente findByTabellaAndValore(String tabella, String valore);
}
