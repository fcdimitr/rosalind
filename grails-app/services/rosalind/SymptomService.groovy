package rosalind

import grails.gorm.services.Service

@Service(Symptom)
interface SymptomService {

    Symptom get(Serializable id)

    List<Symptom> list(Map args)

    Long count()

    void delete(Serializable id)

    Symptom save(Symptom symptom)

}
