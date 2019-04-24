package org.openstack4j.openstack.networking.domain;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.SubPort;
import org.openstack4j.openstack.common.ListEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Models for adding and removing subports
 *
 * @author Kashyap Jha
 */

/*
 * Used to create a SubPortCreate object which only encapsulates allowable fields
 */
@JsonRootName("sub_ports")
public class NeutronSubPortCreate implements ModelEntity {

    /*
     * Used to create a SubPortDelete object which only encapsulates allowable fields
     */
    public static class NeutronSubPortDelete implements ModelEntity {

        /*
         * Used to create a list of SubPortDelete objects to delete multiple resources at once
         */
        public static class NeutronSubPortsDelete implements ModelEntity {

            private static final long serialVersionUID = 1L;

            public static NeutronSubPortsDelete delete(List<String> portIds) {
                NeutronSubPortsDelete toDelete = new NeutronSubPortsDelete();

                for (String portId : portIds) {
                    toDelete.subPorts.add(NeutronSubPortDelete.delete(portId));
                }
                return toDelete;
            }

            @JsonProperty("sub_ports")
            private ListEntity<NeutronSubPortDelete> subPorts;

            public NeutronSubPortsDelete() {
                subPorts = new ListEntity<>();
            }

        }

        private static final long serialVersionUID = 1L;

        public static NeutronSubPortDelete delete(String portId) {
            NeutronSubPortDelete toDelete = new NeutronSubPortDelete();
            toDelete.portId = portId;
            return toDelete;
        }

        @JsonProperty("port_id")
        private String portId;
    }

   /*
    * Used to create a list of SubPortCreate objects to add multiple resources at once
    */
    public static class NeutronSubPortsCreate implements ModelEntity {

        private static final long serialVersionUID = 1L;

        public static NeutronSubPortsCreate fromSubPorts(List<? extends SubPort> subPorts) {
            NeutronSubPortsCreate toCreate = new NeutronSubPortsCreate();
            for (SubPort subPort : subPorts) {
                toCreate.subPorts.add(NeutronSubPortCreate.fromSubPort(subPort));
            }
            return toCreate;
        }

        @JsonProperty("sub_ports")
        private ListEntity<NeutronSubPortCreate> subPorts;

        public NeutronSubPortsCreate() {
            subPorts = new ListEntity<>();
        }
    }

    private static final long serialVersionUID = 1L;

    public static NeutronSubPortCreate fromSubPort(SubPort subPort) {
        NeutronSubPortCreate toCreate = new NeutronSubPortCreate();
        toCreate.segmentationId = subPort.getSegmentationId();
        toCreate.segmentationType = subPort.getSegmentationType();
        toCreate.portId = subPort.getPortId();
        return toCreate;
    }

    @JsonProperty("port_id")
    private String portId;

    @JsonProperty("segmentation_id")
    private int segmentationId;

    @JsonProperty("segmentation_type")
    private String segmentationType;
}
