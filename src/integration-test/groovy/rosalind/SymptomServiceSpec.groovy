package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SymptomServiceSpec extends Specification {

    SymptomService symptomService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Symptom(...).save(flush: true, failOnError: true)
        //new Symptom(...).save(flush: true, failOnError: true)
        //Symptom symptom = new Symptom(...).save(flush: true, failOnError: true)
        //new Symptom(...).save(flush: true, failOnError: true)
        //new Symptom(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //symptom.id
    }

    void "test get"() {
        setupData()

        expect:
        symptomService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Symptom> symptomList = symptomService.list(max: 2, offset: 2)

        then:
        symptomList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        symptomService.count() == 5
    }

    void "test delete"() {
        Long symptomId = setupData()

        expect:
        symptomService.count() == 5

        when:
        symptomService.delete(symptomId)
        sessionFactory.currentSession.flush()

        then:
        symptomService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Symptom symptom = new Symptom()
        symptomService.save(symptom)

        then:
        symptom.id != null
    }
}
