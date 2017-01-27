package org.openstack4j.test;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.client.IOSClientBuilder;
import org.openstack4j.model.artifact.ArtifactUpdate;
import org.openstack4j.model.artifact.ToscaTemplatesArtifact;
import org.openstack4j.model.artifact.ToscaTemplatesArtifacts;
import org.openstack4j.model.artifact.builder.ArtifactUpdateBuilder;
import org.openstack4j.model.artifact.builder.ToscaTemplatesArtifactBuilder;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.artifact.domain.ArtifactUpdateModel;
import org.openstack4j.openstack.artifact.domain.ToscaTemplates;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadavi on 20-01-2017.
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
        IOSClientBuilder.V3 osfactory = OSFactory.builderV3();
        osfactory.endpoint("http://135.248.18.166:5000/v3");
        osfactory.credentials("admin", "password", Identifier.byName("Default"));
        osfactory.scopeToProject(Identifier.byName("admin"),Identifier.byName("Default"));
        OSClient osClient = osfactory.authenticate();

        //list artifacts
        ToscaTemplatesArtifacts toscaTemplatesArtifacts = osClient.artifact().toscaTemplatesArtifact().list();

        String id = toscaTemplatesArtifacts.getToscaTemplates().get(0).getId();

        //get specific artifact
        ToscaTemplatesArtifact toscaTemplates = osClient.artifact().toscaTemplatesArtifact().get(id);

        //create artifact
        ToscaTemplatesArtifactBuilder builder = ToscaTemplates.builder();
        builder.name("ttt");
        ToscaTemplatesArtifact toscaTemplatesArtifact = osClient.artifact().toscaTemplatesArtifact().create(builder.build());

        //update artifact
        ArtifactUpdateBuilder updateBuilder = ArtifactUpdateModel.builder();
        updateBuilder.op("replace");
        updateBuilder.path("/name");
        updateBuilder.value("rrr");

        List<ArtifactUpdate> updates = new ArrayList<>();

        updates.add(updateBuilder.build());
        toscaTemplatesArtifact = osClient.artifact().toscaTemplatesArtifact().update(toscaTemplatesArtifact.getId(), updates);

        updateBuilder.op("replace");
        updateBuilder.path("/template_format");
        updateBuilder.value("yaml");

        updates.add(updateBuilder.build());
        toscaTemplatesArtifact = osClient.artifact().toscaTemplatesArtifact().update(toscaTemplatesArtifact.getId(), updates);

        //upload artifact
        File file = new File("D:\\userdata\\vadavi\\Desktop\\NSD\\test.zip");
        toscaTemplatesArtifact = osClient.artifact().toscaTemplatesArtifact().upload(toscaTemplatesArtifact.getId(), file);

        //download artifact
        InputStream in = osClient.artifact().toscaTemplatesArtifact().download(toscaTemplatesArtifact.getId());
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("D:\\userdata\\vadavi\\Desktop\\NSD\\test1.zip");

            final byte data[] = new byte[1024];
            int count;

            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }

        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }

        //activate artifact
        osClient.artifact().toscaTemplatesArtifact().activate(toscaTemplatesArtifact.getId());

        //deactivate artifact
        osClient.artifact().toscaTemplatesArtifact().deactivate(toscaTemplatesArtifact.getId());

        //reactivate artifact
        osClient.artifact().toscaTemplatesArtifact().reactivate(toscaTemplatesArtifact.getId());

        //publish artifact
        osClient.artifact().toscaTemplatesArtifact().publish(toscaTemplatesArtifact.getId());

        //delete artifact
        osClient.artifact().toscaTemplatesArtifact().delete(toscaTemplatesArtifact.getId());

        //System.out.println(new ObjectMapper().writeValueAsString(toscaTemplatesArtifact));
    }
}

