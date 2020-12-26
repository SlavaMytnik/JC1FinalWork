package interfaces;

import java.io.File;

/**
 * Интерфейс ISender выполняет отправку сообщения
 */
public interface ISender {

    /**
     * Метод send выполняет отправку сообщения
     * @param addressFrom - адрес отправителя
     * @param password - пароль
     * @param addressTo - адрес получателя
     * @param subject - тема сообщения
     * @param text - текст сообщения
     * @param attachment - приложения к письму
     * @return возвращает true, если сообщение отправлено,
     * или false, если сообщение не отправлено
     */
    boolean send(String addressFrom, String password, String addressTo,
                 String subject, String text, File... attachment);
}
