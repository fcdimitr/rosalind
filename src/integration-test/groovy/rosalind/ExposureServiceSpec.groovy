package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExposureServiceSpec extends Specification {

    ExposureService exposureService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Exposure(...).save(flush: true, failOnError: true)
        //new Exposure(...).save(flush: true, failOnError: true)
        //Exposure exposure = new Exposure(...).save(flush: true, failOnError: true)
        //new Exposure(...).save(flush: true, failOnError: true)
        //new Exposure(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //exposure.id
    }

    void "test get"() {
        setupData()

        expect:
        exposureService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Exposure> exposureList = exposureService.list(max: 2, offset: 2)

        then:
        exposureList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        exposureService.count() == 5
    }

    void "test delete"() {
        Long exposureId = setupData()

        expect:
        exposureService.count() == 5

        when:
        exposureService.delete(exposureId)
        sessionFactory.currentSession.flush()

        then:
        exposureService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Exposure exposure = new Exposure()
        exposureService.save(exposure)

        then:
        exposure.id != null
    }
}
