package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EducationLevelServiceSpec extends Specification {

    EducationLevelService educationLevelService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new EducationLevel(...).save(flush: true, failOnError: true)
        //new EducationLevel(...).save(flush: true, failOnError: true)
        //EducationLevel educationLevel = new EducationLevel(...).save(flush: true, failOnError: true)
        //new EducationLevel(...).save(flush: true, failOnError: true)
        //new EducationLevel(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //educationLevel.id
    }

    void "test get"() {
        setupData()

        expect:
        educationLevelService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<EducationLevel> educationLevelList = educationLevelService.list(max: 2, offset: 2)

        then:
        educationLevelList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        educationLevelService.count() == 5
    }

    void "test delete"() {
        Long educationLevelId = setupData()

        expect:
        educationLevelService.count() == 5

        when:
        educationLevelService.delete(educationLevelId)
        sessionFactory.currentSession.flush()

        then:
        educationLevelService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        EducationLevel educationLevel = new EducationLevel()
        educationLevelService.save(educationLevel)

        then:
        educationLevel.id != null
    }
}
