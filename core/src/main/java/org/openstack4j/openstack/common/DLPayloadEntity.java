package org.openstack4j.openstack.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.openstack4j.model.common.DLPayload;

import com.google.common.io.ByteStreams;

/**
 * A Payload which encapsulates downstream data
 * 
 * @author Jeremy Unruh
 */
public class DLPayloadEntity implements DLPayload {

    private final InputStream stream;

    private DLPayloadEntity(InputStream stream) {
        this.stream = stream;
    }

    public static DLPayloadEntity create(InputStream stream) {
        return new DLPayloadEntity(stream);
    }

    @Override
    public InputStream getInputStream() {
        return stream;
    }

    @Override
    public void writeToFile(File file) throws IOException {
        checkNotNull(file);

        ByteStreams.copy(stream, new FileOutputStream(file));
    }

}
