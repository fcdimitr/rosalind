package rosalind

import grails.gorm.services.Service

@Service(Exposure)
interface ExposureService {

    Exposure get(Serializable id)

    List<Exposure> list(Map args)

    Long count()

    void delete(Serializable id)

    Exposure save(Exposure exposure)

}
