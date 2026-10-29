package br.com.ets.foundation.domain;

import java.util.List;
import java.util.Objects;

/**
 * Representa um objeto de domínio definido pelos valores que o compõem.
 *
 * Objetos de valor da mesma classe são considerados iguais quando seus
 * componentes são iguais, sem a necessidade de um identificador.
 *
 * As subclasses devem ser imutáveis, protegendo também coleções e outros
 * objetos mutáveis recebidos na construção ou expostos por seus métodos.
 */
public abstract class ValueObject {

    /**
     * Retorna os componentes utilizados na comparação e no cálculo do hash.
     *
     * A lista deve ser não nula e conter todos os valores relevantes,
     * sempre na mesma ordem. Os seus componentes devem permanecer imutáveis.
     *
     * A comparação respeita o equals de cada componente. Normalizações
     * necessárias ao domínio devem ser realizadas pela subclasse.
     *
     * @return componentes que definem o valor do objeto
     */
    protected abstract List<?> equalityComponents();

    /**
     * Compara objetos de valor pela classe concreta e pelos componentes.
     *
     * A ordem dos componentes participa da comparação. Objetos de classes
     * diferentes são distintos, mesmo quando possuem componentes iguais.
     */
    @Override
    public final boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        ValueObject value = (ValueObject) other;
        return equalityComponents().equals(value.equalityComponents());
    }

    /**
     * Calcula o hash usando os mesmos critérios da igualdade.
     *
     * A imutabilidade dos componentes mantém o hash estável durante
     * o uso do objeto em coleções como HashSet e HashMap.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getClass(), equalityComponents());
    }
}
