package com.bkash.savings.models.config;

import java.util.function.Supplier;

public final class DatabaseContextHolder {
    private static final ThreadLocal<DataSourceType> CONTEXT = ThreadLocal.withInitial(() -> DataSourceType.MASTER);

    private DatabaseContextHolder() {
    } // Prevent instantiation

    public static DataSourceType getReplicaType() {
        return CONTEXT.get();
    }

    public static void setReplicaType(DataSourceType replicaType) {
        CONTEXT.set(replicaType);
    }

    public static void clear() {
        CONTEXT.remove();
    }

    // Helper method for structured operations
    public static <T> T executeWithReadReplica(Supplier<T> operation) {
        setReplicaType(DataSourceType.SLAVE);
        try {
            return operation.get();
        } finally {
            clear();
        }
    }

    // Helper method for void operations
    public static void executeWithReadReplica(Runnable operation) {
        setReplicaType(DataSourceType.SLAVE);
        try {
            operation.run();
        } finally {
            clear();
        }
    }
}