package br.com.ets.foundation.domain;

import java.util.List;
import java.util.Objects;

/**
 * Representa uma identidade tipada por meio de um objeto de valor.
 *
 * Identificadores da mesma classe são iguais quando encapsulam valores
 * iguais. Identificadores de classes diferentes permanecem distintos,
 * mesmo quando encapsulam o mesmo valor.
 *
 * @param <T> tipo do valor encapsulado, que deve ser imutável e possuir
 *            implementações consistentes de equals e hashCode
 */
public abstract class Identifier<T> extends ValueObject {

    private final T value;

    /**
     * Inicializa o identificador com o seu valor obrigatório.
     *
     * Validações de formato e outras restrições específicas do domínio
     * devem ser realizadas pela subclasse.
     *
     * @param value valor que representa a identidade
     * @throws NullPointerException se o valor for nulo
     */
    protected Identifier(T value) {
        this.value = Objects.requireNonNull(
                value,
                "Não é possível criar um identificador sem valor."
        );
    }

    /**
     * Retorna o valor encapsulado pelo identificador.
     *
     * @return valor que representa a identidade
     */
    public final T getValue() {
        return value;
    }

    /**
     * Define o valor encapsulado como o único componente da identidade.
     *
     * A classe concreta também participa da comparação, conforme
     * a implementação de igualdade herdada de ValueObject.
     *
     * @return lista imutável contendo o valor do identificador
     */
    @Override
    protected final List<?> equalityComponents() {
        return List.of(value);
    }
}
