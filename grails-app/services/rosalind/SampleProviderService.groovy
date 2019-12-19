package rosalind

import grails.gorm.services.Service

@Service(SampleProvider)
interface SampleProviderService {

    SampleProvider get(Serializable id)

    List<SampleProvider> list(Map args)

    Long count()

    void delete(Serializable id)

    SampleProvider save(SampleProvider sampleProvider)

}
