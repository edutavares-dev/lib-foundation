package br.com.ets.foundation.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa um fato ocorrido no domínio, identificado por um UUID
 * e pelo instante de sua ocorrência.
 *
 * As subclasses devem expressar o fato e manter seus dados imutáveis.
 * A criação do evento não realiza sua publicação nem sua persistência.
 */
public abstract class DomainEvent {

    private final UUID eventId;
    private final Instant occurredOn;

    /**
     * Inicializa um novo evento com um identificador gerado automaticamente.
     *
     * O instante é fornecido explicitamente para representar a ocorrência
     * do fato e permitir o controle do tempo em testes.
     *
     * @param occurredOn instante em que o fato ocorreu
     * @throws NullPointerException se o instante for nulo
     */
    protected DomainEvent(Instant occurredOn) {
        this(UUID.randomUUID(), occurredOn);
    }

    /**
     * Inicializa o evento com identificador e instante explícitos.
     *
     * Permite reconstruir um evento persistido preservando sua identidade
     * e o instante original de ocorrência.
     *
     * @param eventId identificador do evento
     * @param occurredOn instante em que o fato ocorreu
     * @throws NullPointerException se o identificador ou o instante for nulo
     */
    protected DomainEvent(UUID eventId, Instant occurredOn) {
        this.eventId = Objects.requireNonNull(
                eventId,
                "Não é possível criar um evento de domínio sem identificador."
        );

        this.occurredOn = Objects.requireNonNull(
                occurredOn,
                "O instante de ocorrência do evento de domínio é obrigatório."
        );
    }

    /**
     * Retorna o identificador do evento.
     *
     * Esse valor pode ser utilizado para rastreamento e deduplicação.
     * Sua existência não garante processamento único.
     *
     * @return identificador do evento
     */
    public final UUID getEventId() {
        return eventId;
    }

    /**
     * Retorna o instante em que o fato ocorreu no domínio.
     *
     * Esse instante pode ser diferente do momento de publicação
     * ou processamento do evento.
     *
     * @return instante de ocorrência do fato
     */
    public final Instant getOccurredOn() {
        return occurredOn;
    }
}
