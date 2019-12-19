package rosalind

import grails.gorm.services.Service

@Service(SampleData)
interface SampleDataService {

    SampleData get(Serializable id)

    List<SampleData> list(Map args)

    Long count()

    void delete(Serializable id)

    SampleData save(SampleData sampleData)

}
