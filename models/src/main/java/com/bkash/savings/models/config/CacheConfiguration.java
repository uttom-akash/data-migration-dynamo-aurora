package com.bkash.savings.models.config;

import com.bkash.savings.models.properties.PlatformProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.ehcache.jsr107.Eh107Configuration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;
import java.util.List;


@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    // List of entity cache names
    private static final List<String> ENTITY_CACHE_NAMES = List.of(
            "entity.notification",
            "entity.organization"
    );

    /**
     * Constructs a new CacheConfiguration with the specified platform properties.
     *
     * @param properties the platform properties containing cache configuration details
     */
    public CacheConfiguration(PlatformProperties properties) {

        // Enable statistics with proper configuration
        CacheConfigurationBuilder<Object, Object> configBuilder = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(
                        Object.class,
                        Object.class,
                        ResourcePoolsBuilder.heap(properties.getCache().getMaxEntries())
                                .offheap(properties.getCache().getSizeInMB(), MemoryUnit.MB)
                )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                        Duration.ofSeconds(properties.getCache().getTimeToLiveSeconds())));

        // Add cache event listener for statistics
        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration =
                CacheEventListenerConfigurationBuilder
                        .newEventListenerConfiguration(new CacheEventLogger(),
                                EventType.CREATED, EventType.EXPIRED, EventType.REMOVED, EventType.UPDATED)
                        .unordered()
                        .asynchronous();

        this.jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
                configBuilder.withService(cacheEventListenerConfiguration).build()
        );
    }

    /**
     * Bean definition for CacheManager. Initializes the cache manager and creates caches for the specified entity cache
     * names.
     *
     * @return the configured CacheManager
     */
    @Bean
    public CacheManager ehCacheManager() {
        CachingProvider cachingProvider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
        CacheManager manager = cachingProvider.getCacheManager();
        ENTITY_CACHE_NAMES.forEach(cacheName -> createCache(manager, cacheName));
        return manager;
    }

    /**
     * Customizes Hibernate properties to use the specified CacheManager.
     *
     * @param cacheManager the CacheManager to be used by Hibernate
     * @return the HibernatePropertiesCustomizer
     */
    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(CacheManager cacheManager) {
        return hibernateProperties -> {
            hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
            hibernateProperties.put(org.hibernate.cfg.Environment.USE_QUERY_CACHE, true);
            hibernateProperties.put(org.hibernate.cfg.Environment.GENERATE_STATISTICS, false);
        };
    }


    /**
     * Customizes the JCacheManager to create entity caches.
     *
     * @return the JCacheManagerCustomizer
     */
    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            // Create entity caches
            ENTITY_CACHE_NAMES.forEach(cacheName -> createCache(cm, cacheName));
        };
    }

    // Cache Event Logger for statistics
    public static class CacheEventLogger implements CacheEventListener<Object, Object> {
        private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CacheEventLogger.class);

        @Override
        public void onEvent(CacheEvent<?, ?> event) {
            log.info("Cache event {} for key '{}' with value '{}'",
                    event.getType(), event.getKey(), event.getNewValue());
        }
    }

    /**
     * Creates a cache with the specified name if it does not already exist. If the cache exists, it clears the cache.
     *
     * @param cm        the CacheManager to create the cache in
     * @param cacheName the name of the cache to create
     */
    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }
}
