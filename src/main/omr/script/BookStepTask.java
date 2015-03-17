//------------------------------------------------------------------------------------------------//
//                                                                                                //
//                                     B o o k S t e p T a s k                                    //
//                                                                                                //
//------------------------------------------------------------------------------------------------//
// <editor-fold defaultstate="collapsed" desc="hdr">
//  Copyright © Hervé Bitteur and others 2000-2014. All rights reserved.
//  This software is released under the GNU General Public License.
//  Goto http://kenai.com/projects/audiveris to report bugs or suggestions.
//------------------------------------------------------------------------------------------------//
// </editor-fold>
package omr.script;

import omr.sheet.Sheet;

import omr.step.Step;
import omr.step.StepException;
import omr.step.Stepping;
import omr.step.Steps;

import java.util.Collections;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class {@code BookStepTask} performs a step on a whole book.
 *
 * @author Hervé Bitteur
 */
@XmlAccessorType(XmlAccessType.NONE)
public class BookStepTask
        extends ScriptTask
{
    //~ Instance fields ----------------------------------------------------------------------------

    /** The step launched */
    private Step step;

    //~ Constructors -------------------------------------------------------------------------------
    /**
     * Create a task to apply a given step to the related book.
     *
     * @param step the step to apply
     */
    public BookStepTask (Step step)
    {
        this.step = step;
    }

    /** No-arg constructor needed by JAXB */
    private BookStepTask ()
    {
    }

    //~ Methods ------------------------------------------------------------------------------------
    //------//
    // core //
    //------//
    @Override
    public void core (final Sheet sheet)
            throws StepException
    {
        Stepping.processBook(Collections.singleton(step), null, sheet.getBook());
        logger.info("End of book step {}", step);
    }

    //-----------//
    // internals //
    //-----------//
    @Override
    protected String internals ()
    {
        StringBuilder sb = new StringBuilder(super.internals());
        sb.append(" book-step ").append(step);

        return sb.toString();
    }

    //--------------//
    // isRecordable //
    //--------------//
    /**
     * This is an implementation trick, because of a "chicken and egg" problem to allow
     * to run the LOAD step while the sheet does not exist yet!
     *
     * @return false!
     */
    @Override
    boolean isRecordable ()
    {
        return false;
    }

    //---------//
    // getStep //
    //---------//
    @PreDestroy // Don't remove this method, invoked by JAXB through reflection
    private String getStep ()
    {
        return step.getName();
    }

    //---------//
    // setStep //
    //---------//
    @PostConstruct // Don't remove this method, invoked by JAXB through reflection
    @XmlAttribute(name = "name")
    private void setStep (String name)
    {
        step = Steps.valueOf(name.toUpperCase(Locale.ENGLISH));
    }
}