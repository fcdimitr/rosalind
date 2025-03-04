package rosalind

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ImageServiceSpec extends Specification {

    ImageService imageService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Image(...).save(flush: true, failOnError: true)
        //new Image(...).save(flush: true, failOnError: true)
        //Image image = new Image(...).save(flush: true, failOnError: true)
        //new Image(...).save(flush: true, failOnError: true)
        //new Image(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //image.id
    }

    void "test get"() {
        setupData()

        expect:
        imageService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Image> imageList = imageService.list(max: 2, offset: 2)

        then:
        imageList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        imageService.count() == 5
    }

    void "test delete"() {
        Long imageId = setupData()

        expect:
        imageService.count() == 5

        when:
        imageService.delete(imageId)
        sessionFactory.currentSession.flush()

        then:
        imageService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Image image = new Image()
        imageService.save(image)

        then:
        image.id != null
    }
}
