package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SampleBiomarkerServiceSpec extends Specification {

    SampleBiomarkerService sampleBiomarkerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SampleBiomarker(...).save(flush: true, failOnError: true)
        //new SampleBiomarker(...).save(flush: true, failOnError: true)
        //SampleBiomarker sampleBiomarker = new SampleBiomarker(...).save(flush: true, failOnError: true)
        //new SampleBiomarker(...).save(flush: true, failOnError: true)
        //new SampleBiomarker(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sampleBiomarker.id
    }

    void "test get"() {
        setupData()

        expect:
        sampleBiomarkerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SampleBiomarker> sampleBiomarkerList = sampleBiomarkerService.list(max: 2, offset: 2)

        then:
        sampleBiomarkerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sampleBiomarkerService.count() == 5
    }

    void "test delete"() {
        Long sampleBiomarkerId = setupData()

        expect:
        sampleBiomarkerService.count() == 5

        when:
        sampleBiomarkerService.delete(sampleBiomarkerId)
        sessionFactory.currentSession.flush()

        then:
        sampleBiomarkerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SampleBiomarker sampleBiomarker = new SampleBiomarker()
        sampleBiomarkerService.save(sampleBiomarker)

        then:
        sampleBiomarker.id != null
    }
}
