package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MethodServiceSpec extends Specification {

    MethodService methodService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Method(...).save(flush: true, failOnError: true)
        //new Method(...).save(flush: true, failOnError: true)
        //Method method = new Method(...).save(flush: true, failOnError: true)
        //new Method(...).save(flush: true, failOnError: true)
        //new Method(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //method.id
    }

    void "test get"() {
        setupData()

        expect:
        methodService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Method> methodList = methodService.list(max: 2, offset: 2)

        then:
        methodList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        methodService.count() == 5
    }

    void "test delete"() {
        Long methodId = setupData()

        expect:
        methodService.count() == 5

        when:
        methodService.delete(methodId)
        sessionFactory.currentSession.flush()

        then:
        methodService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Method method = new Method()
        methodService.save(method)

        then:
        method.id != null
    }
}
