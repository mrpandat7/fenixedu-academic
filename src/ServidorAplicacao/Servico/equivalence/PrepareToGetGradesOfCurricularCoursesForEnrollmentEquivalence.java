package ServidorAplicacao.Servico.equivalence;

import java.util.ArrayList;
import java.util.List;

import pt.utl.ist.berserk.logic.serviceManager.IService;
import DataBeans.InfoCurricularCourse;
import DataBeans.InfoEnrolmentWithInfoCurricularCourse;
import DataBeans.InfoStudentCurricularPlanWithInfoStudentAndDegree;
import DataBeans.equivalence.InfoCurricularCourseGrade;
import DataBeans.equivalence.InfoEnrollmentGrade;
import DataBeans.equivalence.InfoEquivalenceContext;
import Dominio.CurricularCourse;
import Dominio.Enrolment;
import Dominio.ICurricularCourse;
import Dominio.IEnrollment;
import Dominio.IStudentCurricularPlan;
import ServidorAplicacao.Servico.exceptions.FenixServiceException;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.IPersistentCurricularCourse;
import ServidorPersistente.IPersistentEnrollment;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;
import Util.TipoCurso;

/**
 * @author David Santos in May 12, 2004
 */

public class PrepareToGetGradesOfCurricularCoursesForEnrollmentEquivalence extends EnrollmentEquivalenceServiceUtils implements IService
{
	public PrepareToGetGradesOfCurricularCoursesForEnrollmentEquivalence()
	{
	}

	public InfoEquivalenceContext run(Integer studentNumber, TipoCurso degreeType, List idsOfChosenEnrollmentsToGiveEquivalence,
		List idsOfChosenCurricularCoursesToGetEquivalence)
		throws FenixServiceException
	{
		List args = new ArrayList();
		args.add(0, studentNumber);
		args.add(1, degreeType);
		args.add(2, idsOfChosenEnrollmentsToGiveEquivalence);
		args.add(3, idsOfChosenCurricularCoursesToGetEquivalence);
		
		List result1 = (List) convertDataInput(args);
		List result2 = (List) execute(result1);
		return (InfoEquivalenceContext) convertDataOutput(result2);
	}

	/**
	 * @see ServidorAplicacao.Servico.Service#convertDataInput(java.lang.Object)
	 * This method converts this service DataBeans input objects to their respective Domain objects.
	 * These Domain objects are to be used by the service's logic.
	 */
	protected Object convertDataInput(Object object)
	{
		return object;
	}

	/**
	 * @param List
	 * @return InfoEquivalenceContext
	 * @see ServidorAplicacao.Servico.Service#convertDataInput(java.lang.Object)
	 * This method converts this service output Domain objects to their respective DataBeans.
	 * These DataBeans are the result of executing this service logic and are to be passed on to the uper layer of the architecture.
	 */
	protected Object convertDataOutput(Object object)
	{
		List elements = (List) object;
		
		List enrollmentsToGiveEquivalences = (List) elements.get(0);
		List curricularCoursesToGetEquivalences = (List) elements.get(1);
		IStudentCurricularPlan studentCurricularPlan = (IStudentCurricularPlan) elements.get(2);
		
		List infoEnrollmentGradesToGiveEquivalences = new ArrayList();
		List infoCurricularCourseGradesToGetEquivalences = new ArrayList();

		for (int i = 0; i < enrollmentsToGiveEquivalences.size(); i++)
		{
			IEnrollment enrollment = (IEnrollment) enrollmentsToGiveEquivalences.get(i);
			InfoEnrollmentGrade infoEnrollmentGrade = new InfoEnrollmentGrade();
			//CLONER
			//infoEnrollmentGrade.setInfoEnrollment(Cloner.copyIEnrolment2InfoEnrolment(enrollment));
			infoEnrollmentGrade.setInfoEnrollment(InfoEnrolmentWithInfoCurricularCourse.newInfoFromDomain(enrollment));
			
			infoEnrollmentGrade.setGrade(getEnrollmentGrade(enrollment));
			infoEnrollmentGradesToGiveEquivalences.add(infoEnrollmentGrade);
		}

		for (int i = 0; i < curricularCoursesToGetEquivalences.size(); i++)
		{
			ICurricularCourse curricularCourse = (ICurricularCourse) curricularCoursesToGetEquivalences.get(i);
			InfoCurricularCourseGrade infoCurricularCourseGrade = new InfoCurricularCourseGrade();
			//CLONER
			//infoCurricularCourseGrade.setInfoCurricularCourse(Cloner.copyCurricularCourse2InfoCurricularCourse(curricularCourse));
			infoCurricularCourseGrade.setInfoCurricularCourse(InfoCurricularCourse.newInfoFromDomain(curricularCourse));
			
			infoCurricularCourseGrade.setGrade("");
			infoCurricularCourseGradesToGetEquivalences.add(infoCurricularCourseGrade);
		}

		InfoEquivalenceContext infoEquivalenceContext = new InfoEquivalenceContext();

		infoEquivalenceContext.setChosenInfoEnrollmentGradesToGiveEquivalence(infoEnrollmentGradesToGiveEquivalences);
		infoEquivalenceContext.setChosenInfoCurricularCourseGradesToGetEquivalence(infoCurricularCourseGradesToGetEquivalences);
		//CLONER
		//infoEquivalenceContext.setInfoStudentCurricularPlan(Cloner
		//	.copyIStudentCurricularPlan2InfoStudentCurricularPlan(studentCurricularPlan));
		infoEquivalenceContext
                .setInfoStudentCurricularPlan(InfoStudentCurricularPlanWithInfoStudentAndDegree
                        .newInfoFromDomain(studentCurricularPlan));
				
		return infoEquivalenceContext;
	}

	/**
	 * @param List
	 * @return List
	 * @throws FenixServiceException
	 * @see ServidorAplicacao.Servico.Service#convertDataInput(java.lang.Object)
	 * This method implements the buisiness logic of this service.
	 */
	protected Object execute(Object object) throws FenixServiceException
	{
		List input = (List) object;
		
		Integer studentNumber = (Integer) input.get(0);
		TipoCurso degreeType = (TipoCurso) input.get(1);
		List idsOfChosenEnrollmentsToGiveEquivalence = (List) input.get(2);
		List idsOfChosenCurricularCoursesToGetEquivalence = (List) input.get(3);
		
		List output = new ArrayList();

		try
		{
			ISuportePersistente persistenceDAO = SuportePersistenteOJB.getInstance();
			IPersistentEnrollment enrollmentDAO = persistenceDAO.getIPersistentEnrolment();
			IPersistentCurricularCourse curricularCourseDAO = persistenceDAO.getIPersistentCurricularCourse();

			List enrollmentsToGiveEquivalence = new ArrayList();
			for (int i = 0; i < idsOfChosenEnrollmentsToGiveEquivalence.size(); i++)
			{
				Integer enrollmentID = (Integer) idsOfChosenEnrollmentsToGiveEquivalence.get(i);
				IEnrollment enrollment = (IEnrollment) enrollmentDAO.readByOID(Enrolment.class, enrollmentID);
				enrollmentsToGiveEquivalence.add(enrollment);
			}

			List curricularCoursesToGetEquivalence = new ArrayList();
			for (int i = 0; i < idsOfChosenCurricularCoursesToGetEquivalence.size(); i++)
			{
				Integer curricularCourseID = (Integer) idsOfChosenCurricularCoursesToGetEquivalence.get(i);
				ICurricularCourse curricularCourse = (ICurricularCourse) curricularCourseDAO.readByOID(CurricularCourse.class,
					curricularCourseID);
				curricularCoursesToGetEquivalence.add(curricularCourse);
			}

			output.add(0, enrollmentsToGiveEquivalence);
			output.add(1, curricularCoursesToGetEquivalence);
			output.add(2, getActiveStudentCurricularPlan(studentNumber, degreeType));

		} catch (ExcepcaoPersistencia e)
		{
			throw new FenixServiceException(e);
		}

		return output;
	}

}