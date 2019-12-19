package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PublicationServiceSpec extends Specification {

    PublicationService publicationService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Publication(...).save(flush: true, failOnError: true)
        //new Publication(...).save(flush: true, failOnError: true)
        //Publication publication = new Publication(...).save(flush: true, failOnError: true)
        //new Publication(...).save(flush: true, failOnError: true)
        //new Publication(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //publication.id
    }

    void "test get"() {
        setupData()

        expect:
        publicationService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Publication> publicationList = publicationService.list(max: 2, offset: 2)

        then:
        publicationList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        publicationService.count() == 5
    }

    void "test delete"() {
        Long publicationId = setupData()

        expect:
        publicationService.count() == 5

        when:
        publicationService.delete(publicationId)
        sessionFactory.currentSession.flush()

        then:
        publicationService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Publication publication = new Publication()
        publicationService.save(publication)

        then:
        publication.id != null
    }
}
