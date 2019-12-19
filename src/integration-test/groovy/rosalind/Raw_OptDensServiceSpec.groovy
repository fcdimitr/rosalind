package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class Raw_OptDensServiceSpec extends Specification {

    Raw_OptDensService raw_OptDensService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Raw_OptDens(...).save(flush: true, failOnError: true)
        //new Raw_OptDens(...).save(flush: true, failOnError: true)
        //Raw_OptDens raw_OptDens = new Raw_OptDens(...).save(flush: true, failOnError: true)
        //new Raw_OptDens(...).save(flush: true, failOnError: true)
        //new Raw_OptDens(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //raw_OptDens.id
    }

    void "test get"() {
        setupData()

        expect:
        raw_OptDensService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Raw_OptDens> raw_OptDensList = raw_OptDensService.list(max: 2, offset: 2)

        then:
        raw_OptDensList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        raw_OptDensService.count() == 5
    }

    void "test delete"() {
        Long raw_OptDensId = setupData()

        expect:
        raw_OptDensService.count() == 5

        when:
        raw_OptDensService.delete(raw_OptDensId)
        sessionFactory.currentSession.flush()

        then:
        raw_OptDensService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Raw_OptDens raw_OptDens = new Raw_OptDens()
        raw_OptDensService.save(raw_OptDens)

        then:
        raw_OptDens.id != null
    }
}
