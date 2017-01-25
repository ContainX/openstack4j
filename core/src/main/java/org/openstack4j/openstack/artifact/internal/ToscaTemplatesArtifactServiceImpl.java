package org.openstack4j.openstack.artifact.internal;

import com.google.common.base.Preconditions;
import org.openstack4j.api.artifact.ToscaTemplatesArtifactService;
import org.openstack4j.model.artifact.ArtifactType;
import org.openstack4j.model.artifact.ArtifactUpdate;
import org.openstack4j.model.artifact.ToscaTemplatesArtifact;
import org.openstack4j.model.artifact.ToscaTemplatesArtifacts;
import org.openstack4j.model.artifact.builder.ArtifactUpdateBuilder;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.payloads.FilePayload;
import org.openstack4j.openstack.artifact.domain.ArtifactUpdateModel;
import org.openstack4j.openstack.artifact.domain.ToscaTemplates;
import org.openstack4j.openstack.artifact.domain.ToscaTemplatesList;
import org.openstack4j.openstack.common.ListEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadavi on 19-01-2017.
 */
public class ToscaTemplatesArtifactServiceImpl extends BaseArtifactServiceImpl implements ToscaTemplatesArtifactService {

    ToscaTemplatesArtifactServiceImpl() {
        super(ArtifactType.TOSCA_TEMPLATES);
    }

    @Override
    public ToscaTemplatesArtifacts list() {

        return super.list(ToscaTemplatesList.class);


        //return get(ToscaTemplatesList.class,uri("/artifacts/"+ ArtifactType.TOSCA_TEMPLATES.value())).execute();

    }

    @Override
    public ToscaTemplatesArtifact get(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return get(ToscaTemplates.class,uri("/artifacts/"+ ArtifactType.TOSCA_TEMPLATES.value()+"/%s",artifactId)).execute();
    }

    @Override
    public ToscaTemplatesArtifact create(ToscaTemplatesArtifact toscaTemplatesArtifact) {
        Preconditions.checkNotNull(toscaTemplatesArtifact);
        return post(ToscaTemplates.class,uri("/artifacts/"+ ArtifactType.TOSCA_TEMPLATES.value())).entity(toscaTemplatesArtifact).execute();
    }

    @Override
    public ToscaTemplatesArtifact upload(String artifactId, File file) {
        Preconditions.checkNotNull(artifactId);

        Payload<?> payload = new FilePayload(file);

        Invocation<ToscaTemplates> invocation = put(ToscaTemplates.class,uri("/artifacts/"+ ArtifactType.TOSCA_TEMPLATES.value()+"/%s/template",artifactId));
        invocation.entity(payload);
        return invocation.execute();
    }

    @Override
    public ToscaTemplatesArtifact download(String artifactId, File file) {
        return null;
    }

    @Override
    public ActionResponse delete(String artifactId) {
        Preconditions.checkNotNull(artifactId);
        return deleteWithResponse(uri("/artifacts/"+ ArtifactType.TOSCA_TEMPLATES.value()+"/%s",artifactId)).execute();
    }

    @Override
    public ToscaTemplatesArtifact update(String artifactId, List<ArtifactUpdate> artifactUpdates) {
        Preconditions.checkNotNull(artifactId);
        Preconditions.checkNotNull(artifactUpdates);

        Invocation<ToscaTemplates> invocation = patch(ToscaTemplates.class,uri("/artifacts/"+ ArtifactType.TOSCA_TEMPLATES.value()+"/%s",artifactId));
        invocation.entity(new ListEntity<ArtifactUpdate>(artifactUpdates));
        invocation.contentType("application/json-patch+json");
        return invocation.execute();
    }

    @Override
    public ToscaTemplatesArtifact activate(String artifactId) {
        return update(artifactId, "replace", "/status", "active");
    }

    @Override
    public ToscaTemplatesArtifact deactivate(String artifactId) {
        return update(artifactId, "replace", "/status", "deactivated");
    }

    @Override
    public ToscaTemplatesArtifact reactivate(String artifactId) {
        return update(artifactId, "replace", "/status", "active");
    }

    @Override
    public ToscaTemplatesArtifact publish(String artifactId) {
        return update(artifactId, "replace", "/visibility", "public");
    }

    private ToscaTemplatesArtifact update(String artifactId, String op, String path, String value)
    {
        Preconditions.checkNotNull(artifactId);

        ArtifactUpdateBuilder updateBuilder = ArtifactUpdateModel.builder();
        updateBuilder.op(op);
        updateBuilder.path(path);
        updateBuilder.value(value);

        List<ArtifactUpdate> artifactUpdates = new ArrayList<>();
        artifactUpdates.add(updateBuilder.build());

        Invocation<ToscaTemplates> invocation = patch(ToscaTemplates.class,uri("/artifacts/"+ ArtifactType.TOSCA_TEMPLATES.value()+"/%s",artifactId));
        invocation.entity(new ListEntity<ArtifactUpdate>(artifactUpdates));
        invocation.contentType("application/json-patch+json");
        return invocation.execute();
    }
}
