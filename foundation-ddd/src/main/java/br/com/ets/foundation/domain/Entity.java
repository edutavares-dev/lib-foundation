package br.com.ets.foundation.domain;

import java.util.Objects;

/**
 * Representa um objeto de domínio definido por sua identidade.
 *
 * Entidades da mesma classe e com o mesmo identificador são consideradas
 * iguais, mesmo quando os seus demais atributos possuem valores diferentes.
 *
 * @param <ID> tipo do identificador, que deve ser imutável e possuir
 *             implementações consistentes de equals e hashCode
 */
public abstract class Entity<ID> {

    private final ID id;

    /**
     * Inicializa a entidade com um identificador obrigatório.
     *
     * A identidade é definida na construção e permanece durante
     * todo o ciclo de vida da entidade.
     *
     * @param id identificador da entidade
     * @throws NullPointerException se o identificador for nulo
     */
    protected Entity(ID id) {
        this.id = Objects.requireNonNull(
                id,
                "Não é possível criar uma entidade sem identificador."
        );
    }

    /**
     * Retorna o identificador da entidade.
     */
    public final ID getId() {
        return id;
    }

    /**
     * Compara entidades pela classe concreta e pelo identificador.
     *
     * Os demais atributos não participam da comparação. Entidades
     * de classes diferentes são distintas, mesmo com identificadores iguais.
     */
    @Override
    public final boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Entity<?> entity = (Entity<?>) other;
        return id.equals(entity.id);
    }

    /**
     * Calcula o hash usando os mesmos critérios da igualdade.
     *
     * Alterações nos demais atributos não afetam a localização
     * da entidade em coleções como HashSet e HashMap.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getClass(), id);
    }
}
