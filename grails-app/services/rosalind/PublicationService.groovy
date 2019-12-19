package rosalind

import grails.gorm.services.Service

@Service(Publication)
interface PublicationService {

    Publication get(Serializable id)

    List<Publication> list(Map args)

    Long count()

    void delete(Serializable id)

    Publication save(Publication publication)

}
