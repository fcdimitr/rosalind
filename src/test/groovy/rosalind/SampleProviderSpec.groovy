package rosalind

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SampleProviderSpec extends Specification implements DomainUnitTest<SampleProvider> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
