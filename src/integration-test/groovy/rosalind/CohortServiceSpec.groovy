package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CohortServiceSpec extends Specification {

    CohortService cohortService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Cohort(...).save(flush: true, failOnError: true)
        //new Cohort(...).save(flush: true, failOnError: true)
        //Cohort cohort = new Cohort(...).save(flush: true, failOnError: true)
        //new Cohort(...).save(flush: true, failOnError: true)
        //new Cohort(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cohort.id
    }

    void "test get"() {
        setupData()

        expect:
        cohortService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Cohort> cohortList = cohortService.list(max: 2, offset: 2)

        then:
        cohortList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        cohortService.count() == 5
    }

    void "test delete"() {
        Long cohortId = setupData()

        expect:
        cohortService.count() == 5

        when:
        cohortService.delete(cohortId)
        sessionFactory.currentSession.flush()

        then:
        cohortService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Cohort cohort = new Cohort()
        cohortService.save(cohort)

        then:
        cohort.id != null
    }
}
