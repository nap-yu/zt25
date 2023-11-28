package org.zt25.persistence.orm.generator;

import cn.hutool.core.util.IdUtil;
import org.zt25.persistence.enums.PkType;

public enum DefaultPrimaryKeyGenerator {

    AUTO_INCR{
        @Override
        protected Long createPk() {
            return null;
        }
        @Override
        public String toString() {
            return PkType.AUTO_INCR.getCode();
        }
    },
    UUID{
        @Override
        protected String createPk() {
            return IdUtil.randomUUID();
        }
        @Override
        public String toString() {
            return PkType.UUID.getCode();
        }
    },
    SIMPLE_UUID{
        @Override
        protected String createPk() {
            return IdUtil.simpleUUID();
        }
        @Override
        public String toString() {
            return PkType.SIMPLE_UUID.getCode();
        }
    },
    SNOW_FLAKE_STRING{
        @Override
        protected String createPk() {
            return IdUtil.getSnowflakeNextIdStr();
        }
        @Override
        public String toString() {
            return PkType.SNOW_FLAKE_STRING.getCode();
        }
    },
    SNOW_FLAKE{
        @Override
        protected Long createPk() {
            return IdUtil.getSnowflakeNextId();
        }
        @Override
        public String toString() {
            return PkType.SNOW_FLAKE.getCode();
        }
    },
    OBJECT_ID{
        @Override
        protected String createPk() {
            return IdUtil.objectId();
        }
        @Override
        public String toString() {
            return PkType.OBJECT_ID.getCode();
        }
    },
    NANO {
        @Override
        protected String createPk() {
            return IdUtil.nanoId();
        }

        @Override
        public String toString() {
            return PkType.NANO.getCode();
        }
    },
    DEFAULT{
        @Override
        protected String createPk() {
            return IdUtil.getSnowflakeNextIdStr();
        }

        @Override
        public String toString() {
            return PkType.DEFAULT.getCode();
        }
    }
    ;

    public Object get() {
        return createPk();
    }

    protected abstract Object createPk();
}
