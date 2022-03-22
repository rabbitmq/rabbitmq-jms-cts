/**
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "Exolab" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of Exoffice Technologies.  For written permission,
 *    please contact tma@netspace.net.au.
 *
 * 4. Products derived from this Software may not be called "Exolab"
 *    nor may "Exolab" appear in their names without prior written
 *    permission of Exoffice Technologies. Exolab is a registered
 *    trademark of Exoffice Technologies.
 *
 * 5. Due credit should be given to the Exolab Project
 *    (https://castor.exolab.org).
 *
 * THIS SOFTWARE IS PROVIDED BY EXOFFICE TECHNOLOGIES AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * EXOFFICE TECHNOLOGIES OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2001-2004 (C) Exoffice Technologies Inc. All Rights Reserved.
 *
 * $Id: Configuration.java,v 1.2 2004/02/02 03:50:09 tanderson Exp $
 */
package org.exolab.jmscts.provider;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.Unmarshaller;

import org.exolab.jmscts.util.ConfigExpander;


/**
 * The provider configuration
 *
 * @version     $Revision: 1.2 $ $Date: 2004/02/02 03:50:09 $
 * @author      <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @see         Provider
 * @see         ProviderLoader
 */
public class Configuration {

    /**
     * The providers
     */
    private ArrayList<ProviderLoader> _providers = new ArrayList<ProviderLoader>();

    /**
     * The logger
     */
    private static final Logger log =
        LoggerFactory.getLogger(Configuration.class);

    /**
     * The castor mapping
     */
    private static final Mapping MAPPING;

    /**
     * The path to the castor mapping file, as a resource
     */
    private static final String MAPPING_PATH =
        "/org/exolab/jmscts/provider/mapping.xml";


    /**
     * Construct a new <code>Configuration</code>
     */
    public Configuration() {
    }

    /**
     * Add a provider
     *
     * @param provider the provider
     */
    public void addProvider(ProviderLoader provider) {
        _providers.add(provider);
    }

    /**
     * Return the providers
     *
     * @return the providers
     */
    public ArrayList<ProviderLoader> getProviders() {
        return _providers;
    }

    /**
     * Read the configuration
     *
     * @param path the file path to the configuration
     * @return the read configuration
     * @throws Exception if the configuration can't be read
     */
    public static Configuration read(String path) throws Exception {
        Configuration result = null;
        Unmarshaller unmarshaller = new Unmarshaller(MAPPING);
        FileReader reader = new FileReader(path);
        try {
            ConfigExpander handler = new ConfigExpander(reader);
            result = (Configuration) unmarshaller.unmarshal(handler);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    static {
        // load the castor mapping file
        InputStream stream = Configuration.class.getResourceAsStream(
            MAPPING_PATH);
        if (stream == null) {
            throw new RuntimeException(
                "Failed to locate mapping file, path=" + MAPPING_PATH);
        }

        MAPPING = new Mapping();

        try {
            MAPPING.loadMapping(new InputSource(stream));
        } catch (IOException exception) {
            String message = "Failed to load mapping, path=" + MAPPING_PATH;
            log.error(message, exception);
            throw new RuntimeException(message);
        } catch (MappingException exception) {
            String message = "Failed to load mapping, path=" + MAPPING_PATH;
            log.error(message, exception);
            throw new RuntimeException(message);
        } finally {
            try {
                stream.close();
            } catch (IOException ignore) {
                // no-op
            }
        }
    }

}
