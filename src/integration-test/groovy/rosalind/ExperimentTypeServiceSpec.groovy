package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExperimentTypeServiceSpec extends Specification {

    ExperimentTypeService experimentTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ExperimentType(...).save(flush: true, failOnError: true)
        //new ExperimentType(...).save(flush: true, failOnError: true)
        //ExperimentType experimentType = new ExperimentType(...).save(flush: true, failOnError: true)
        //new ExperimentType(...).save(flush: true, failOnError: true)
        //new ExperimentType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //experimentType.id
    }

    void "test get"() {
        setupData()

        expect:
        experimentTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ExperimentType> experimentTypeList = experimentTypeService.list(max: 2, offset: 2)

        then:
        experimentTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        experimentTypeService.count() == 5
    }

    void "test delete"() {
        Long experimentTypeId = setupData()

        expect:
        experimentTypeService.count() == 5

        when:
        experimentTypeService.delete(experimentTypeId)
        sessionFactory.currentSession.flush()

        then:
        experimentTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ExperimentType experimentType = new ExperimentType()
        experimentTypeService.save(experimentType)

        then:
        experimentType.id != null
    }
}
