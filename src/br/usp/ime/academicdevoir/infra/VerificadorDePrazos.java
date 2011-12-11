package br.usp.ime.academicdevoir.infra;

import java.util.Date;

public class VerificadorDePrazos {
    public static Boolean estaNoPrazo(Date prazo) {
        Date atual = new Date();
        return prazo.after(atual);
    }
}
