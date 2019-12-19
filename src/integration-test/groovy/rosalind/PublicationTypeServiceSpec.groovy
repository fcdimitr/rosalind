package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PublicationTypeServiceSpec extends Specification {

    PublicationTypeService publicationTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PublicationType(...).save(flush: true, failOnError: true)
        //new PublicationType(...).save(flush: true, failOnError: true)
        //PublicationType publicationType = new PublicationType(...).save(flush: true, failOnError: true)
        //new PublicationType(...).save(flush: true, failOnError: true)
        //new PublicationType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //publicationType.id
    }

    void "test get"() {
        setupData()

        expect:
        publicationTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PublicationType> publicationTypeList = publicationTypeService.list(max: 2, offset: 2)

        then:
        publicationTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        publicationTypeService.count() == 5
    }

    void "test delete"() {
        Long publicationTypeId = setupData()

        expect:
        publicationTypeService.count() == 5

        when:
        publicationTypeService.delete(publicationTypeId)
        sessionFactory.currentSession.flush()

        then:
        publicationTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PublicationType publicationType = new PublicationType()
        publicationTypeService.save(publicationType)

        then:
        publicationType.id != null
    }
}
