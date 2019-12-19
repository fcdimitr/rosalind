package rosalind

import grails.gorm.services.Service

@Service(Medication)
interface MedicationService {

    Medication get(Serializable id)

    List<Medication> list(Map args)

    Long count()

    void delete(Serializable id)

    Medication save(Medication medication)

}
