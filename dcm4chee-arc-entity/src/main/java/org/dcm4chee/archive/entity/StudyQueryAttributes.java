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

package org.dcm4chee.archive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dcm4che3.util.StringUtils;
import org.dcm4chee.storage.conf.Availability;

/**
 * @author Gunter Zeilinger <gunterze@gmail.com>
 *
 */
@Entity
@Table(name = "study_query_attrs")
public class StudyQueryAttributes {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "pk")
    private long pk;

    @Column(name = "view_id")
    private String viewID;

    @Column(name = "num_series")
    private int numberOfSeries;

    @Column(name = "num_instances")
    private int numberOfInstances;

    @Column(name = "mods_in_study")
    private String modalitiesInStudy;

    @Column(name = "cuids_in_study", length = 1024)
    private String sopClassesInStudy;

    @Column(name = "retrieve_aets")
    private String retrieveAETs;

    @Column(name = "availability")
    private Availability availability;

    @ManyToOne(optional = false)
    @JoinColumn(name = "study_fk")
    private Study study;

    public long getPk() {
        return pk;
    }

    public String getViewID() {
        return viewID;
    }

    public void setViewID(String viewID) {
        this.viewID = viewID;
    }

    public int getNumberOfSeries() {
        return numberOfSeries;
    }

    public void setNumberOfSeries(int numberOfSeries) {
        this.numberOfSeries = numberOfSeries;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getRawModalitiesInStudy() {
        return modalitiesInStudy;
    }

    public String[] getModalitiesInStudy() {
        return StringUtils.split(modalitiesInStudy, '\\');
    }

    public void setModalitiesInStudy(String... modalitiesInStudy) {
        this.modalitiesInStudy = StringUtils.concat(modalitiesInStudy, '\\');
    }

    public String getRawSOPClassesInStudy() {
        return retrieveAETs;
    }

    public String[] getSOPClassesInStudy() {
        return StringUtils.split(sopClassesInStudy, '\\');
    }

    public void setSOPClassesInStudy(String... sopClassesInStudy) {
        this.sopClassesInStudy = StringUtils.concat(sopClassesInStudy, '\\');
    }

    public String getRawRetrieveAETs() {
        return retrieveAETs;
    }

    public String[] getRetrieveAETs() {
        return StringUtils.split(retrieveAETs, '\\');
    }

    public void setRetrieveAETs(String... retrieveAETs) {
        this.retrieveAETs = StringUtils.concat(retrieveAETs, '\\');
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }
}
