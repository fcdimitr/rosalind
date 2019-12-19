package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FrontEndFieldServiceSpec extends Specification {

    FrontEndFieldService frontEndFieldService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FrontEndField(...).save(flush: true, failOnError: true)
        //new FrontEndField(...).save(flush: true, failOnError: true)
        //FrontEndField frontEndField = new FrontEndField(...).save(flush: true, failOnError: true)
        //new FrontEndField(...).save(flush: true, failOnError: true)
        //new FrontEndField(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //frontEndField.id
    }

    void "test get"() {
        setupData()

        expect:
        frontEndFieldService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FrontEndField> frontEndFieldList = frontEndFieldService.list(max: 2, offset: 2)

        then:
        frontEndFieldList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        frontEndFieldService.count() == 5
    }

    void "test delete"() {
        Long frontEndFieldId = setupData()

        expect:
        frontEndFieldService.count() == 5

        when:
        frontEndFieldService.delete(frontEndFieldId)
        sessionFactory.currentSession.flush()

        then:
        frontEndFieldService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FrontEndField frontEndField = new FrontEndField()
        frontEndFieldService.save(frontEndField)

        then:
        frontEndField.id != null
    }
}
