package it.at.backend.utils;

import it.at.backend.dto.Errore;
import lombok.extern.slf4j.Slf4j;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

@Slf4j
public class Libreria {

    public static Errore gestisciEccezioni(Exception e, String dati) {
        Errore errore=new Errore();

        log.info("ERRORE: "+e.getMessage());
        e.printStackTrace();

        errore.setCodice( e.getClass().getCanonicalName() );
        errore.setDescrizione( e.getMessage() );
        errore.setCausa( getRootCause(e).toString() );
        errore.setDatiInErrore(dati);

        return errore;
    }
}
