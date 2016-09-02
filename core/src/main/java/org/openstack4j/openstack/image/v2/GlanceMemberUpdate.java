package org.openstack4j.openstack.image.v2;

import org.openstack4j.model.image.v2.Member;
import org.openstack4j.model.image.v2.MemberUpdate;
import org.openstack4j.model.image.v2.builder.MemberUpdateBuilder;

public class GlanceMemberUpdate implements MemberUpdate {

    Member.MemberStatus status;

    /**
     * {@inheritDoc}
     */
    @Override
    public Member.MemberStatus getStatus() {
        return status;
    }

    @Override
    public MemberUpdateBuilder toBuilder() {
        return new MemberUpdateConcreteBuilder(this);
    }

    public static MemberUpdateBuilder builder() {
        return new MemberUpdateConcreteBuilder();
    }


    public static class MemberUpdateConcreteBuilder implements MemberUpdateBuilder {
        private GlanceMemberUpdate m;

        public MemberUpdateConcreteBuilder() {
            this(new GlanceMemberUpdate());
        }

        public MemberUpdateConcreteBuilder(GlanceMemberUpdate m) {
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemberUpdateBuilder status(Member.MemberStatus status) {
            m.status = status;
            return this;
        }

        @Override
        public MemberUpdate build() {
            return m;
        }

        @Override
        public MemberUpdateBuilder from(MemberUpdate in) {
            m = (GlanceMemberUpdate) in;
            return this;
        }
    }
}
