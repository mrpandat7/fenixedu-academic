package net.sourceforge.fenixedu.domain.accessControl;

import net.sourceforge.fenixedu.domain.Role;
import net.sourceforge.fenixedu.domain.accessControl.groups.language.Argument;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.renderers.utils.RenderUtils;

/**
 * The group of very logged person. This group has as elements all the persons
 * that may login in the system but only allows a person or a UserView when the
 * person is logged in.
 * 
 * @author cfgi
 */
public class InternalPersonGroup extends RoleGroup {
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;

    public InternalPersonGroup() {
        super(Role.getRoleByRoleType(RoleType.PERSON));
    }

    @Override
    protected Argument[] getExpressionArguments() {
        return new Argument[0];
    }
    
    @Override
    public String getName() {
	return RenderUtils.getResourceString("SITE_RESOURCES", "label.net.sourceforge.fenixedu.domain.accessControl.InternalPersonGroup");
    }
}
