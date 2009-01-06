import com.mchange.v2.c3p0.C3P0Registry
import fr.cg95.cvq.dao.hibernate.HibernateUtil
import fr.cg95.cvq.service.authority.ILocalAuthorityRegistry
import java.lang.management.ManagementFactory
import org.hibernate.SessionFactory
import org.hibernate.jmx.StatisticsService

class MonitoringService {
    
    ILocalAuthorityRegistry localAuthorityRegistry
    StatisticsService statisticsService
    def offset = 30
    
    def getHibernateService = { authorityName ->
        SessionFactory sf = this.getSessionFactory(authorityName)
        HibernateUtil.setSessionFactory(sf)
        
        StatisticsService service = new StatisticsService()
        service.setSessionFactory(sf)
        service.setStatisticsEnabled(true)
        
        return service
    }
    
    def getC3P0Information = { authorityName ->
        def result = [:]
        
        def ds = C3P0Registry.pooledDataSourceByName(authorityName)
        def server = ManagementFactory.platformMBeanServer
        def mbean = new GroovyMBean(server, 
                "com.mchange.v2.c3p0:type=PooledDataSource[${ds.identityToken}]")
        
        if(mbean) {
            result.minPoolSize = mbean.minPoolSize
            result.maxPoolSize = mbean.maxPoolSize
            result.initialPoolSize = mbean.initialPoolSize
            result.numUserPools = mbean.numUserPools
            result.numAllConnections = mbean.numConnectionsAllUsers
            result.numIdleConnections = mbean.numIdleConnectionsAllUsers
            result.numUnclosedConnections = mbean.numUnclosedOrphanedConnectionsAllUsers
        }
        return result
    }
    
    def getDatabaseInformation = { authorityName ->
        def result = [:]
        StatisticsService service = this.getHibernateService(authorityName)
        if(!service) return result
        
        result.closeStatementCount = service.closeStatementCount
        result.collectionFetchCount = service.collectionFetchCount
        result.collectionLoadCount = service.collectionLoadCount
        result.collectionRecreateCount = service.collectionRecreateCount
        result.collectionRemoveCount = service.collectionRemoveCount
        result.collectionRoleNames = service.collectionRoleNames
        result.collectionUpdateCount = service.collectionUpdateCount
        result.connectCount = service.connectCount
        result.entityDeleteCount = service.entityDeleteCount
        result.entityFetchCount = service.entityFetchCount
        result.entityInsertCount = service.entityInsertCount
        result.entityLoadCount = service.entityLoadCount
        result.entityUpdateCount = service.entityUpdateCount
        result.entityNames = service.entityNames
        result.flushCount = service.flushCount
        result.optimisticFailureCount = service.optimisticFailureCount
        result.prepareStatementCount = service.prepareStatementCount
        result.queryCacheHitCount = service.queryCacheHitCount
        result.queryCacheMissCount = service.queryCacheMissCount
        result.queryCachePutCount = service.queryCachePutCount
        result.queryExecutionCount = service.queryExecutionCount
        result.queryExecutionMaxTime = service.queryExecutionMaxTime
        result.queryExecutionMaxTimeQueryString = service.queryExecutionMaxTimeQueryString
        result.secondLevelCacheHitCount = service.secondLevelCacheHitCount
        result.secondLevelCacheMissCount = service.secondLevelCacheMissCount
        result.secondLevelCachePutCount = service.secondLevelCachePutCount
        result.secondLevelCacheRegionNames = service.secondLevelCacheRegionNames
        result.sessionCloseCount = service.sessionCloseCount
        result.sessionOpenCount = service.sessionOpenCount
        result.startTime = service.startTime
        result.successfulTransactionCount = service.successfulTransactionCount
        result.transactionCount = service.transactionCount
        result.logSummary = service.logSummary()
        
        if(service.queries.size()>offset) result.queries = service.queries[0..offset]
        else result.queries = service.queries
        
        return result
    }
    
    def getSystemInformation = {
        def result = [:]
        def os = ManagementFactory.operatingSystemMXBean
        def rt = ManagementFactory.runtimeMXBean
        def cl = ManagementFactory.classLoadingMXBean
        def comp = ManagementFactory.compilationMXBean
        def mem = ManagementFactory.memoryMXBean
        def heapUsage = mem.heapMemoryUsage
        def nonHeapUsage = mem.nonHeapMemoryUsage
        def td = ManagementFactory.threadMXBean
        
        result.threads = []
        result.memoryPools = []
        result.garbageCollectors = []
        
        result.os = [
            'architecture': os.arch,
            'name' : os.name,
            'version' : os.version,
            'processors' : os.availableProcessors
        ]
        
        result.runtime = [
            'name' : rt.name,
            'specName' : rt.specName,
            'vendor': rt.specVendor,
            'specVersion' : rt.specVersion,
            'managementSpecVersion' : rt.managementSpecVersion
        ]
        
        result.classLoadingSystem = [
            'isVerbose': cl.isVerbose(),
            'loadedClassCount': cl.loadedClassCount,
            'totalLoadedClassCount': cl.totalLoadedClassCount,
            'unloadedClassCount': cl.unloadedClassCount
        ]
        
        result.compilation = ['totalCompilationTime':comp.totalCompilationTime]
        td.allThreadIds.each{ tid -> result.threads.add(['name' : td.getThreadInfo(tid).threadName])}
        
        result.nonHeapStorage = [
            'committed' : nonHeapUsage.committed,
            'init': nonHeapUsage.init,
            'max': nonHeapUsage.max,
            'used': nonHeapUsage.used
        ]
        
        result.heapStorage = [
            'committed' : heapUsage.committed,
            'init': heapUsage.init,
            'max': heapUsage.max,
            'used': heapUsage.used
        ]
        
        ManagementFactory.memoryPoolMXBeans.each{ mp ->
            def pool = [:]
            pool.managers = []
            
            pool.name = mp.name
            pool.type = mp.type
            pool.isUsageThresholdSupported = mp.isUsageThresholdSupported()
            mp.memoryManagerNames.each{ mmname -> pool.managers.add(['name' : mmname])}
            
            result.memoryPools.add(pool)
        }
        
        ManagementFactory.garbageCollectorMXBeans.each { gc ->
            def collector = [:]
            collector.pools = []
            collector.name = gc.name
            collector.collectionCount = gc.collectionCount
            collector.collectionTime = gc.collectionTime
            
            gc.memoryPoolNames.each { mpoolName -> collector.pools.add(['name':mpoolName])}
            result.garbageCollectors.add(collector)
        }
        
        return result
    }
    
    def getSessionFactory = { name ->
        return this.localAuthorityRegistry.getLocalAuthorityBeanByName(name).getSessionFactory()
    }
    
    def getLocalAuthorities = { 
        return localAuthorityRegistry.getAllLocalAuthoritiesNames() as List;
    }
}