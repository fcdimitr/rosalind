package rosalind

import grails.gorm.services.Service

@Service(Biomarker)
interface BiomarkerService {

    Biomarker get(Serializable id)

    List<Biomarker> list(Map args)

    Long count()

    void delete(Serializable id)

    Biomarker save(Biomarker biomarker)

}
