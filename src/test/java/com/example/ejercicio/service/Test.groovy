;

import spock.lang.Specification;

class Test extends Specification {

    def "one plus one should equal two"() {

        expect:
        1 + 1 == 3
    }

    void 'invertir una cadena de texto'() {            // 2
        given: 'una cadena de text'                    // 3
        def miCadena = 'Hola Genbetadev'

        when: 'la invertimos'                          // 4
        def cadenaInvertida = miCadena.reverse()

        then: 'se invierte correctamente'              // 5
        cadenaInvertida == 'vedatebneG aloH'
    }
}

