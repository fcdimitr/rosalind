package rosalind

import grails.gorm.services.Service

@Service(SampleBiomarker)
interface SampleBiomarkerService {

    SampleBiomarker get(Serializable id)

    List<SampleBiomarker> list(Map args)

    Long count()

    void delete(Serializable id)

    SampleBiomarker save(SampleBiomarker sampleBiomarker)

}
