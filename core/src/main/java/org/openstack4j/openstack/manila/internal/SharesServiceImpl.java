package org.openstack4j.openstack.manila.internal;

import org.openstack4j.api.manila.SharesService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.manila.Share;
import org.openstack4j.model.manila.ShareCreate;
import org.openstack4j.model.manila.ShareUpdateOptions;
import org.openstack4j.model.manila.builder.ShareCreateBuilder;
import org.openstack4j.openstack.common.Metadata;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.manila.domain.ManilaShare;
import org.openstack4j.openstack.manila.domain.ManilaShareCreate;
import org.openstack4j.openstack.manila.domain.ManilaShareUpdate;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SharesServiceImpl extends BaseShareServices implements SharesService {
    /**
     * {@inheritDoc}
     */
    @Override
    public Share create(ShareCreate shareCreate) {
        checkNotNull(shareCreate);
        return post(ManilaShare.class, uri("/shares"))
                .entity(shareCreate)
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Share> list() {
        return list(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Share> listDetails() {
        return list(true);
    }

    private List<? extends Share> list(boolean detail) {
        return get(ManilaShare.Shares.class, uri("/shares" +  (detail ? "/detail" : "")))
                .execute()
                .getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Share get(String shareId) {
        checkNotNull(shareId);
        return get(ManilaShare.class, uri("/shares/%s", shareId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Share update(String shareId, ShareUpdateOptions shareUpdateOptions) {
        checkNotNull(shareId);
        checkNotNull(shareUpdateOptions);

        return put(ManilaShare.class, uri("/shares/%s", shareId))
                .entity(ManilaShareUpdate.fromOptions(shareUpdateOptions))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String shareId) {
        checkNotNull(shareId);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/shares/%s", shareId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Metadata getMetadata(String shareId) {
        checkNotNull(shareId);
        return get(Metadata.class, uri("/shares/%s/metadata", shareId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Metadata updateMetadata(String shareId, Metadata metadata) {
        checkNotNull(shareId);
        checkNotNull(metadata);

        return put(Metadata.class, uri("/shares/%s/metadata", shareId))
                .entity(metadata)
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Metadata setMetadata(String shareId, Metadata metadata) {
        checkNotNull(shareId);
        checkNotNull(metadata);

        return post(Metadata.class, uri("/shares/%s/metadata/", shareId))
                .entity(metadata)
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse unsetMetadata(String shareId, String metadataKey) {
        checkNotNull(shareId);
        checkNotNull(metadataKey);

        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/shares/%s/metadata/%s", shareId, metadataKey)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareCreateBuilder shareCreateBuilder() {
        return ManilaShareCreate.builder();
    }
}
