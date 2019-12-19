package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SampleProviderServiceSpec extends Specification {

    SampleProviderService sampleProviderService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SampleProvider(...).save(flush: true, failOnError: true)
        //new SampleProvider(...).save(flush: true, failOnError: true)
        //SampleProvider sampleProvider = new SampleProvider(...).save(flush: true, failOnError: true)
        //new SampleProvider(...).save(flush: true, failOnError: true)
        //new SampleProvider(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sampleProvider.id
    }

    void "test get"() {
        setupData()

        expect:
        sampleProviderService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SampleProvider> sampleProviderList = sampleProviderService.list(max: 2, offset: 2)

        then:
        sampleProviderList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sampleProviderService.count() == 5
    }

    void "test delete"() {
        Long sampleProviderId = setupData()

        expect:
        sampleProviderService.count() == 5

        when:
        sampleProviderService.delete(sampleProviderId)
        sessionFactory.currentSession.flush()

        then:
        sampleProviderService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SampleProvider sampleProvider = new SampleProvider()
        sampleProviderService.save(sampleProvider)

        then:
        sampleProvider.id != null
    }
}
