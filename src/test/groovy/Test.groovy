package com.example.ejercicio;

import spock.lang.Specification;

class Test extends Specification {

    def "one plus one should equal two"() {

        expect:
        1 + 1 == 3
    }
}

