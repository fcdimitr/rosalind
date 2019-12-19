package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DownloadLogServiceSpec extends Specification {

    DownloadLogService downloadLogService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DownloadLog(...).save(flush: true, failOnError: true)
        //new DownloadLog(...).save(flush: true, failOnError: true)
        //DownloadLog downloadLog = new DownloadLog(...).save(flush: true, failOnError: true)
        //new DownloadLog(...).save(flush: true, failOnError: true)
        //new DownloadLog(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //downloadLog.id
    }

    void "test get"() {
        setupData()

        expect:
        downloadLogService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DownloadLog> downloadLogList = downloadLogService.list(max: 2, offset: 2)

        then:
        downloadLogList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        downloadLogService.count() == 5
    }

    void "test delete"() {
        Long downloadLogId = setupData()

        expect:
        downloadLogService.count() == 5

        when:
        downloadLogService.delete(downloadLogId)
        sessionFactory.currentSession.flush()

        then:
        downloadLogService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DownloadLog downloadLog = new DownloadLog()
        downloadLogService.save(downloadLog)

        then:
        downloadLog.id != null
    }
}
