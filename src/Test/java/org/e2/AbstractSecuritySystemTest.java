package org.e2;

import org.e2.assessment.ISecuritySystem;
import org.e2.assessment.SecuritySystemImpl;
import org.e2.assessment.exception.SystemException;
import org.junit.Before;

public abstract class AbstractSecuritySystemTest {
    protected ISecuritySystem securitySystem = new SecuritySystemImpl();

    protected static final String JOHN_USERNAME = "john";
    protected static final String TOM_USERNAME = "tom";
    protected static final String MARY_USERNAME = "mary";
    protected static final String ANGELINA_USERNAME = "angelina";
    protected static final String ELON_USERNAME = "elon";

    protected static final String DOWNLOAD_MOVIE_PRIVILEGE = "download_movie";
    protected static final String CREATE_USERS_PRIVILEGE = "create_users";
    protected static final String DELETE_USERS_PRIVILEGE = "delete_users";
    protected static final String UPLOAD_FILES_PRIVILEGE = "upload_files";

    protected static final String CEO_ROLENAME = "CEO";
    protected static final String CTO_ROLENAME = "CTO";
    protected static final String CFO_ROLENAME = "CFO";
    protected static final String ACCOUNTING_ROLENAME = "ACCOUNTING";
    protected static final String SALES_ROLENAME = "SALES";
    protected static final String DEV_ROLENAME = "DEV";

    @Before
    public abstract void before() throws SystemException;
}
