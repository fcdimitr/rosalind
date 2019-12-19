package rosalind

import grails.gorm.services.Service

@Service(DownloadLog)
interface DownloadLogService {

    DownloadLog get(Serializable id)

    List<DownloadLog> list(Map args)

    Long count()

    void delete(Serializable id)

    DownloadLog save(DownloadLog downloadLog)

}
