package rosalind

import grails.gorm.services.Service

@Service(PublicationType)
interface PublicationTypeService {

    PublicationType get(Serializable id)

    List<PublicationType> list(Map args)

    Long count()

    void delete(Serializable id)

    PublicationType save(PublicationType publicationType)

}
