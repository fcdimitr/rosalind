package rosalind

import grails.gorm.services.Service

@Service(FrontEndField)
interface FrontEndFieldService {

    FrontEndField get(Serializable id)

    List<FrontEndField> list(Map args)

    Long count()

    void delete(Serializable id)

    FrontEndField save(FrontEndField frontEndField)

}
