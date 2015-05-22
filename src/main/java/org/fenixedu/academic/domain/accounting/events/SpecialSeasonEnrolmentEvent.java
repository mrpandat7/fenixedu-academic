/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Academic.
 *
 * FenixEdu Academic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Academic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Academic.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fenixedu.academic.domain.accounting.events;

import java.util.Collection;
import java.util.Set;

import org.fenixedu.academic.domain.EnrolmentEvaluation;
import org.fenixedu.academic.domain.Person;
import org.fenixedu.academic.domain.accounting.EventType;
import org.fenixedu.academic.domain.administrativeOffice.AdministrativeOffice;

public class SpecialSeasonEnrolmentEvent extends SpecialSeasonEnrolmentEvent_Base {

    protected SpecialSeasonEnrolmentEvent() {
        super();
    }

    public SpecialSeasonEnrolmentEvent(final AdministrativeOffice administrativeOffice, final Person person,
            final Collection<EnrolmentEvaluation> enrolmentEvaluations) {
        this();
        init(administrativeOffice, EventType.SPECIAL_SEASON_ENROLMENT, person, enrolmentEvaluations);
    }

    @Override
    protected void addAll(Collection<EnrolmentEvaluation> enrolmentEvaluations) {
        getSpecialSeasonEnrolmentEvaluationsSet().addAll(enrolmentEvaluations);
    }

    @Override
    protected Set<EnrolmentEvaluation> getAllEnrolmentEvaluationsSet() {
        return getSpecialSeasonEnrolmentEvaluationsSet();
    }

    @Override
    public boolean isExemptionAppliable() {
        return false;
    }

}
