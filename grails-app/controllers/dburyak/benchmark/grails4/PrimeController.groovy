package dburyak.benchmark.grails4

import grails.converters.JSON

import java.time.Duration
import java.time.Instant

class PrimeController {
    static responseFormats = ['json']
    static defaultAction = 'get'

    def primeNumbersService

    def get(String max, Long maxMillis, Integer maxSeconds, Integer maxMinutes) {
        def start = Instant.now()

        if (!max && !maxMillis && !maxSeconds && !maxMinutes) {
            max = '3'
        }

        def durMins = Duration.ofMinutes(maxMinutes ?: 0)
        def durSeconds = Duration.ofSeconds(maxSeconds ?: 0)
        def durMaxMillis = Duration.ofMillis(maxMillis ?: 0)
        def maxDur = durMins + durSeconds + durMaxMillis
        if (maxDur.isZero()) {
            maxDur = primeNumbersService.hugeDuration
        }

        BigInteger maxNum = (max ? max as BigInteger : primeNumbersService.maxKnownPrime)

        // blocking computational call
        def prime = primeNumbersService.maxPrime(maxNum, maxDur)

        render([prime: prime, time: Duration.between(start, Instant.now()).toString()] as JSON)
    }
}
