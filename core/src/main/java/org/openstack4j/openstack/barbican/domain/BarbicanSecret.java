package org.openstack4j.openstack.barbican.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.barbican.Secret;
import org.openstack4j.model.barbican.builder.SecretCreateBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.Date;
import java.util.List;

/**
 * Created by reneschollmeyer on 02.08.17.
 */
public class BarbicanSecret implements Secret {

    private String name;
    @JsonProperty("algorithm")
    private String algorithm;
    @JsonProperty("bit_length")
    private int bitLength;
    @JsonProperty("content-types")
    private List<String> contentTypes;
    @JsonProperty("created")
    private Date createTime;
    @JsonProperty("updated")
    private Date updateTime;
    @JsonProperty("creator_id")
    private String creatorId;
    @JsonProperty("expiration")
    private String expiration;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("secret_ref")
    private String secretReference;
    @JsonProperty("secret_type")
    private String secretType;
    @JsonProperty("status")
    private String status;

    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public int getBitLength() {
        return bitLength;
    }

    @Override
    public List<String> getContentTypes() {
        return contentTypes;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String getCreatorId() {
        return creatorId;
    }

    @Override
    public String getExpiration() {
        return expiration;
    }

    @Override
    public String getMode() {
        return mode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSecretReference() {
        return secretReference;
    }

    @Override
    public String getSecretType() {
        return secretType;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("algorithm", algorithm).add("bit_length", bitLength)
                .add("content_types", contentTypes).add("created", createTime)
                .add("creator_id", creatorId).add("expiration", expiration)
                .add("mode", mode).add("name", name)
                .add("secret_ref", secretReference).add("secret_type", secretType)
                .add("status", status).add("updated", updateTime).toString();
    }

    @Override
    public SecretCreateBuilder toBuilder() {
        return new SecretCreateConcreteBuilder();
    }

    public static class Secrets extends ListResult<BarbicanSecret> {

        @JsonProperty("total")
        private int count;

        @JsonProperty("secrets")
        private List<BarbicanSecret> list;

        protected List<BarbicanSecret> value() {
            return list;
        }

        public int getCount() {
            return count;
        }

    }

    public static class SecretCreateConcreteBuilder implements SecretCreateBuilder {

        private BarbicanSecret internalSecret;

        public SecretCreateConcreteBuilder() {
            this(new BarbicanSecret());
        }

        public SecretCreateConcreteBuilder(BarbicanSecret secret) {
            this.internalSecret = secret;
        }

        @Override
        public SecretCreateBuilder name(String name) {
            internalSecret.name = name;
            return this;
        }

        @Override
        public Secret build() {
            return internalSecret;
        }

        @Override
        public SecretCreateBuilder from(Secret in) {
            internalSecret = (BarbicanSecret) in;
            return this;
        }
    }

    public static SecretCreateBuilder builder() {
        return new SecretCreateConcreteBuilder();
    }
}
