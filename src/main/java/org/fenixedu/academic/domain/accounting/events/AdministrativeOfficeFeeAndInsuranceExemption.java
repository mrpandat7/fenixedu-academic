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

import org.fenixedu.academic.util.Money;

/**
 * Use {@link org.fenixedu.academic.domain.accounting.events.EventExemption}
 */
@Deprecated
public class AdministrativeOfficeFeeAndInsuranceExemption extends AdministrativeOfficeFeeAndInsuranceExemption_Base {

    private AdministrativeOfficeFeeAndInsuranceExemption() {
        super();
    }

    @Override
    public boolean isAdministrativeOfficeFeeAndInsuranceExemption() {
        return true;
    }

    @Override
    public boolean isForAdministrativeOfficeFee() {
        return true;
    }

    @Override
    public boolean isForInsurance() {
        return true;
    }

    @Override
    public Money getExemptionAmount(Money money) {
        final AdministrativeOfficeFeeAndInsuranceEvent event = (AdministrativeOfficeFeeAndInsuranceEvent) getEvent();
        return event.getInsuranceAmount().add(event.getAdministrativeOfficeFeeAmount());
    }
}
