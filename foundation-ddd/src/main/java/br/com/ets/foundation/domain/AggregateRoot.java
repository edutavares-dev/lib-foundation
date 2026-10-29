package br.com.ets.foundation.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa a entidade raiz de um agregado e registra os seus eventos de domínio.
 *
 * A raiz controla as alterações nos objetos do agregado e deve preservar
 * as suas regras de consistência.
 *
 * Os eventos são armazenados em memória. A camada de aplicação coordena
 * a sua publicação ou persistência e a posterior limpeza da coleção.
 *
 * @param <ID> tipo do identificador da raiz, que deve ser imutável
 */
public abstract class AggregateRoot<ID> extends Entity<ID>  {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * Inicializa a raiz do agregado com a sua identidade.
     *
     * @param id identificador da raiz do agregado
     * @throws NullPointerException se o identificador for nulo
     */
    protected AggregateRoot(ID id) {
        super(id);
    }

    /**
     * Registra um evento que representa um fato ocorrido no domínio.
     *
     * O registro preserva a ordem de inclusão e não realiza a publicação
     * nem a persistência do evento.
     *
     * @param event evento de domínio a ser registrado
     * @throws NullPointerException se o evento for nulo
     */
    protected final void addDomainEvent(DomainEvent event) {
        domainEvents.add(Objects.requireNonNull(
                event,
                "Não é possível registrar um evento de domínio nulo."
        ));
    }

    /**
     * Retorna uma cópia imutável da coleção de eventos registrados.
     *
     * A consulta não remove os eventos. Novos registros ou limpezas
     * não alteram a coleção retornada anteriormente.
     *
     * @return eventos registrados, na ordem de inclusão
     */
    public final List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

    /**
     * Informa se existem eventos registrados aguardando tratamento.
     *
     * @return true se a coleção de eventos não estiver vazia
     */
    public final boolean hasDomainEvents() {
        return !domainEvents.isEmpty();
    }

    /**
     * Remove todos os eventos registrados na raiz do agregado.
     *
     * A camada de aplicação deve coordenar essa limpeza com a estratégia
     * de entrega e a transação. A remoção não confirma que os eventos
     * foram publicados, persistidos ou processados.
     */
    public final void clearDomainEvents() {
        domainEvents.clear();
    }
}
