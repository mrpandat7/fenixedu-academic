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
package org.fenixedu.academic.domain.organizationalStructure;

import org.apache.commons.lang.StringUtils;
import org.fenixedu.TINValidator;
import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.bennu.core.domain.Bennu;

import eu.europa.ec.taxud.tin.algorithm.TINValid;

public class PartySocialSecurityNumber extends PartySocialSecurityNumber_Base {

    private PartySocialSecurityNumber() {
        super();
        setRootDomainObject(Bennu.getInstance());
    }

    public PartySocialSecurityNumber(final Party party, final String socialSecurityNumber) {
        this();
        checkParameters(party, socialSecurityNumber);
        super.setParty(party);
        super.setSocialSecurityNumber(socialSecurityNumber);
    }

    private void checkParameters(final Party party, final String socialSecurityNumber) {
        if (party == null) {
            throw new DomainException("error.PartySocialSecurityNumber.invalid.party");
        }
        if (socialSecurityNumber == null || socialSecurityNumber.length() == 0) {
            throw new DomainException("error.PartySocialSecurityNumber.invalid.socialSecurityNumber");
        }
    }

    public boolean hasSocialSecurityNumber(String socialSecurityNumber) {
        return getSocialSecurityNumber().equals(socialSecurityNumber);
    }

    public void delete() {
        setParty(null);
        setRootDomainObject(null);
        super.deleteDomainObject();
    }

    public static Party readPartyBySocialSecurityNumber(final String socialSecurityNumber) {
        if (socialSecurityNumber == null || socialSecurityNumber.length() == 0) {
            return null;
        }
        for (final PartySocialSecurityNumber securityNumber : Bennu.getInstance().getPartySocialSecurityNumbersSet()) {
            if (securityNumber.hasSocialSecurityNumber(socialSecurityNumber)) {
                return securityNumber.getParty();
            }
        }
        return null;
    }

    public static boolean isValid(String socialSecurityNumber) {
        socialSecurityNumber = StringUtils.trimToNull(socialSecurityNumber);
        if (socialSecurityNumber != null && !StringUtils.isBlank(socialSecurityNumber)) {
            final String tinCountryCode = socialSecurityNumber.length() > 2
                    && Character.isAlphabetic(socialSecurityNumber.charAt(0))
                    && Character.isAlphabetic(socialSecurityNumber.charAt(1)) ?
                    socialSecurityNumber.substring(0, 2) : null;
            if ("PT999999990".equals(socialSecurityNumber) || tinCountryCode == null
                    || !TINValidator.isValid(tinCountryCode, socialSecurityNumber.substring(2))) {
                return false;
            }
        }
        return true;
    }

}
