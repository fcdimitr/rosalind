package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SampleDataServiceSpec extends Specification {

    SampleDataService sampleDataService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SampleData(...).save(flush: true, failOnError: true)
        //new SampleData(...).save(flush: true, failOnError: true)
        //SampleData sampleData = new SampleData(...).save(flush: true, failOnError: true)
        //new SampleData(...).save(flush: true, failOnError: true)
        //new SampleData(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sampleData.id
    }

    void "test get"() {
        setupData()

        expect:
        sampleDataService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SampleData> sampleDataList = sampleDataService.list(max: 2, offset: 2)

        then:
        sampleDataList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sampleDataService.count() == 5
    }

    void "test delete"() {
        Long sampleDataId = setupData()

        expect:
        sampleDataService.count() == 5

        when:
        sampleDataService.delete(sampleDataId)
        sessionFactory.currentSession.flush()

        then:
        sampleDataService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SampleData sampleData = new SampleData()
        sampleDataService.save(sampleData)

        then:
        sampleData.id != null
    }
}
