package rosalind

import grails.gorm.services.Service

@Service(DataRequest)
interface DataRequestService {

    DataRequest get(Serializable id)

    List<DataRequest> list(Map args)

    Long count()

    void delete(Serializable id)

    DataRequest save(DataRequest dataRequest)

}
