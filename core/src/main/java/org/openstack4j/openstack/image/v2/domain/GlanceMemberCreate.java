package org.openstack4j.openstack.image.v2.domain;

import org.openstack4j.model.image.v2.MemberCreate;
import org.openstack4j.model.image.v2.builder.MemberCreateBuilder;

/**
 * Implementation of Glance V2 member create
 * @author emjburns
 */
public class GlanceMemberCreate implements MemberCreate {

    String member;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMember() {
        return member;
    }

    @Override
    public MemberCreateBuilder toBuilder() {
        return new MemberCreateConcreteBuilder(this);
    }

    public static MemberCreateBuilder builder() {
        return new MemberCreateConcreteBuilder();
    }

    public static class MemberCreateConcreteBuilder implements MemberCreateBuilder {
        private GlanceMemberCreate m;

        public MemberCreateConcreteBuilder() {
            this(new GlanceMemberCreate());
        }

        public MemberCreateConcreteBuilder(GlanceMemberCreate m) {
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberCreateBuilder member(String memberId) {
            m.member = memberId;
            return this;
        }

        @Override
        public MemberCreate build() {
            return m;
        }

        @Override
        public MemberCreateBuilder from(MemberCreate in) {
            this.m = (GlanceMemberCreate) in;
            return this;
        }
    }
}
