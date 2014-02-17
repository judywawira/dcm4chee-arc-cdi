/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at https://github.com/gunterze/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * Agfa Healthcare.
 * Portions created by the Initial Developer are Copyright (C) 2011-2014
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See @authors listed below
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

package org.dcm4chee.archive.store.impl;

import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.dcm4che.net.Status;
import org.dcm4che.net.service.DicomServiceException;
import org.dcm4chee.archive.conf.ArchiveAEExtension;
import org.dcm4chee.archive.conf.StoreParam;
import org.dcm4chee.archive.entity.FileSystem;
import org.dcm4chee.archive.entity.MPPS;
import org.dcm4chee.archive.store.StoreService;
import org.dcm4chee.archive.store.StoreSession;

/**
 * @author Gunter Zeilinger <gunterze@gmail.com>
 *
 */
public class StoreSessionImpl implements StoreSession {

    private final String name;
    private final StoreService storeService;
    private final String remoteAET;
    private final ArchiveAEExtension arcAE;
    private final StoreParam storeParam;
    private final MessageDigest messageDigest;
    private FileSystem storageFileSystem;
    private Path spoolDirectory;
    private MPPS cachedMPPS;
    private HashMap<String,Object> properties = new HashMap<String,Object>();

    public StoreSessionImpl(String name, StoreService storeService,
            String remoteAET, ArchiveAEExtension arcAE)
                    throws DicomServiceException {
        this.name = name;
        this.storeService = storeService;
        this.remoteAET = remoteAET;
        this.arcAE = arcAE;
        this.storeParam = arcAE.getStoreParam();
        try {
            String algorithm = arcAE.getDigestAlgorithm();
            this.messageDigest = algorithm != null
                    ? MessageDigest.getInstance(algorithm)
                    : null;
        } catch (NoSuchAlgorithmException e) {
            throw new DicomServiceException(Status.UnableToProcess, e);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getLocalAET() {
        return arcAE.getApplicationEntity().getAETitle();
    }

    @Override
    public StoreService getStoreService() {
        return storeService;
    }

    @Override
    public String getRemoteAET() {
        return remoteAET;
    }

    @Override
    public ArchiveAEExtension getArchiveAEExtension() {
        return arcAE;
    }

    @Override
    public StoreParam getStoreParam() {
        return storeParam;
    }

    @Override
    public FileSystem getStorageFileSystem() {
        return storageFileSystem;
    }

    @Override
    public void setStorageFileSystem(FileSystem fs) {
        this.storageFileSystem = fs;
    }

    @Override
    public Path getSpoolDirectory() {
        return spoolDirectory;
    }

    @Override
    public void setSpoolDirectory(Path spoolDirectory) {
        this.spoolDirectory = spoolDirectory;
    }

    @Override
    public MessageDigest getMessageDigest() {
        return messageDigest;
    }

    @Override
    public MPPS getCachedMPPS() {
        return cachedMPPS;
    }

    @Override
    public void setCachedMPPS(MPPS mpps) {
        this.cachedMPPS = mpps;
    }

    @Override
    public Object getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }
}

