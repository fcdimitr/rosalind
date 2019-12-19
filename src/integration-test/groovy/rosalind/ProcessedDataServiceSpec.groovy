package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProcessedDataServiceSpec extends Specification {

    ProcessedDataService processedDataService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProcessedData(...).save(flush: true, failOnError: true)
        //new ProcessedData(...).save(flush: true, failOnError: true)
        //ProcessedData processedData = new ProcessedData(...).save(flush: true, failOnError: true)
        //new ProcessedData(...).save(flush: true, failOnError: true)
        //new ProcessedData(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //processedData.id
    }

    void "test get"() {
        setupData()

        expect:
        processedDataService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProcessedData> processedDataList = processedDataService.list(max: 2, offset: 2)

        then:
        processedDataList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        processedDataService.count() == 5
    }

    void "test delete"() {
        Long processedDataId = setupData()

        expect:
        processedDataService.count() == 5

        when:
        processedDataService.delete(processedDataId)
        sessionFactory.currentSession.flush()

        then:
        processedDataService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProcessedData processedData = new ProcessedData()
        processedDataService.save(processedData)

        then:
        processedData.id != null
    }
}
