package org.migration.migrators.base;

import jakarta.persistence.*;
import org.aurora.postgres.base.BaseEntity;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component
public class PostgresWriter {
    @PersistenceContext
    private EntityManager entityManager;


    public <T> void merge(T entity, String tableName, String conflictColumns){
        merge(entity, tableName, conflictColumns, false);
    }

    public <T> void merge(T entity, String tableName, String conflictColumns, Boolean doNothing) {
        var params = buildParams(entity);

        var columns = new StringBuilder();
        var placeholders = new StringBuilder();
        var updateClause = new StringBuilder();

        for (var column : params.keySet()) {
            columns.append(column).append(", ");
            placeholders.append(":").append(column).append(", ");
            updateClause.append(column).append(" = EXCLUDED.").append(column).append(", ");
        }

        columns.setLength(columns.length() - 2);
        placeholders.setLength(placeholders.length() - 2);
        updateClause.setLength(updateClause.length() - 2);

        String sql =
                "INSERT INTO "+ tableName +" (" + columns + ") " +
                        "VALUES (" + placeholders + ") " +
                        "ON CONFLICT (" + conflictColumns + ") " +
                        (doNothing ? "DO NOTHING" :
                        "DO UPDATE SET " + updateClause);

        Query query = entityManager.createNativeQuery(sql);

        params.forEach(query::setParameter);

        query.executeUpdate();
    }

    private <T> Map<String, Object> buildParams(T entity) {
        Map<String, Object> params = new HashMap<>();

        Class<?> entityClass = entity.getClass();

        Field[] fields = entityClass.getDeclaredFields();

        if (BaseEntity.class.isAssignableFrom(entityClass)) {
            var baseEntity = (BaseEntity) entity;
            if (baseEntity.getId() != null) params.put("id", baseEntity.getId());
            if (baseEntity.getCreatedAt() != null) params.put("created_date", baseEntity.getCreatedAt());
            if (baseEntity.getLastModifiedAt() != null)
                params.put("last_modified_date", baseEntity.getLastModifiedAt());
            if (baseEntity.getVersion() != null) params.put("version", baseEntity.getVersion());
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation.name();

                if (!field.isAnnotationPresent(Transient.class)) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        if (value != null) {
                            if (value.getClass().isEnum()) {
                                value = value.toString(); // Convert enum to its name (string)
                            }
                            if(columnName.equals(":") || columnName.equals("")){
                                System.out.println();
                            }
                            params.put(columnName, value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return params;
    }

    protected <EntityType extends  BaseEntity> void merge(EntityType currentEntity, EntityType existingEntity){
        if(existingEntity !=null) {
            currentEntity.setVersion(existingEntity.getVersion() == null ? 0 : existingEntity.getVersion());
            currentEntity.setId(existingEntity.getId());
            entityManager.merge(currentEntity);
        }
        else {
            currentEntity.setVersion(0);
            entityManager.persist(currentEntity);
        }
    }
}



