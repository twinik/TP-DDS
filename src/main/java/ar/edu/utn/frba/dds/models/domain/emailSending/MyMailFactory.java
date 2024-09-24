package ar.edu.utn.frba.dds.models.domain.emailSending;

/**
 * MyMailFactory class se encarga de crear mails.
 */

public class MyMailFactory {

    /**
     * Metodo createMail que se encarga de crear un mail.
     */
    public static MyEmail createMail(String emisor, String receptor, String asunto, String cuerpo) {
        return new MyEmail(emisor, receptor, asunto, cuerpo);
    }
}