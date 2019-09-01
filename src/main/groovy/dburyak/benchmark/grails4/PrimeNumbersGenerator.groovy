package dburyak.benchmark.grails4

import java.time.Duration
import java.time.Instant

class PrimeNumbersGenerator {
    public static final Duration HUGE_DURATION = Duration.ofDays(1_000_000_000)
    public static final BigInteger MAX_KNOWN_PRIME = (2 as BigInteger)**(82_589_933 as BigInteger) - 1

    BigInteger maxPrime(BigInteger maxNum, Duration maxCalculationTime) {
        assert maxNum > 2
        def start = Instant.now()
        def end = start + maxCalculationTime
        def now = start
        def prime = 3 as BigInteger
        def currentNum = prime as BigInteger
        while (now < end && currentNum <= maxNum) {
            currentNum += 2
            if (isPrime(currentNum)) {
                prime = currentNum
            }
            now = Instant.now()
        }
        prime
    }

    BigInteger maxPrime(Duration maxCalculationTime) {
        maxPrime(MAX_KNOWN_PRIME, maxCalculationTime)
    }

    BigInteger maxPrime(BigInteger maxNum) {
        maxPrime(maxNum, HUGE_DURATION)
    }

    boolean isPrime(BigInteger num) {
        def max = num.sqrt() + 1
        !(2..max).find { num % it == 0 }
    }
}
