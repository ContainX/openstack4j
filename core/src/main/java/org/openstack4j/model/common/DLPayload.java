package org.openstack4j.model.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * A Payload which encapsulates downstream data
 * 
 * @author Jeremy Unruh
 */
public interface DLPayload {

    /**
     * The raw inputstream
     * 
     * @return the inputstream
     */
    InputStream getInputStream();
    
    /**
     * Writes the current stream to the specified {@code file}
     * 
     * @param file the file to write to
     * @throws IOException
     */
    void writeToFile(File file) throws IOException;
}
