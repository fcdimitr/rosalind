package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DataRequestServiceSpec extends Specification {

    DataRequestService dataRequestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DataRequest(...).save(flush: true, failOnError: true)
        //new DataRequest(...).save(flush: true, failOnError: true)
        //DataRequest dataRequest = new DataRequest(...).save(flush: true, failOnError: true)
        //new DataRequest(...).save(flush: true, failOnError: true)
        //new DataRequest(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dataRequest.id
    }

    void "test get"() {
        setupData()

        expect:
        dataRequestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DataRequest> dataRequestList = dataRequestService.list(max: 2, offset: 2)

        then:
        dataRequestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dataRequestService.count() == 5
    }

    void "test delete"() {
        Long dataRequestId = setupData()

        expect:
        dataRequestService.count() == 5

        when:
        dataRequestService.delete(dataRequestId)
        sessionFactory.currentSession.flush()

        then:
        dataRequestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DataRequest dataRequest = new DataRequest()
        dataRequestService.save(dataRequest)

        then:
        dataRequest.id != null
    }
}
