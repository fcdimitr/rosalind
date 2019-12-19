package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BiomarkerServiceSpec extends Specification {

    BiomarkerService biomarkerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Biomarker(...).save(flush: true, failOnError: true)
        //new Biomarker(...).save(flush: true, failOnError: true)
        //Biomarker biomarker = new Biomarker(...).save(flush: true, failOnError: true)
        //new Biomarker(...).save(flush: true, failOnError: true)
        //new Biomarker(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //biomarker.id
    }

    void "test get"() {
        setupData()

        expect:
        biomarkerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Biomarker> biomarkerList = biomarkerService.list(max: 2, offset: 2)

        then:
        biomarkerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        biomarkerService.count() == 5
    }

    void "test delete"() {
        Long biomarkerId = setupData()

        expect:
        biomarkerService.count() == 5

        when:
        biomarkerService.delete(biomarkerId)
        sessionFactory.currentSession.flush()

        then:
        biomarkerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Biomarker biomarker = new Biomarker()
        biomarkerService.save(biomarker)

        then:
        biomarker.id != null
    }
}
