package dburyak.benchmark.grails4

import org.springframework.beans.factory.annotation.Autowired

import java.time.Duration

class PrimeNumbersService {

    @Autowired
    @Delegate
    PrimeNumbersGenerator primeNumbersGenerator

    BigInteger getMaxKnownPrime() {
        primeNumbersGenerator.MAX_KNOWN_PRIME
    }

    Duration getHugeDuration() {
        primeNumbersGenerator.HUGE_DURATION
    }
}
