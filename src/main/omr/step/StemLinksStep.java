//------------------------------------------------------------------------------------------------//
//                                                                                                //
//                                    S t e m L i n k s S t e p                                   //
//                                                                                                //
//------------------------------------------------------------------------------------------------//
// <editor-fold defaultstate="collapsed" desc="hdr">
//  Copyright © Herve Bitteur and others 2000-2014. All rights reserved.
//  This software is released under the GNU General Public License.
//  Goto http://kenai.com/projects/audiveris to report bugs or suggestions.
//------------------------------------------------------------------------------------------------//
// </editor-fold>
package omr.step;

import omr.sheet.SystemInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class {@code StemLinksStep} implements <b>STEM_LINKS</b> step, which establishes all
 * possible relations between stems and note heads or beams.
 *
 * @author Hervé Bitteur
 */
public class StemLinksStep
        extends AbstractSystemStep
{
    //~ Static fields/initializers -----------------------------------------------------------------

    private static final Logger logger = LoggerFactory.getLogger(StemLinksStep.class);

    //~ Constructors -------------------------------------------------------------------------------
    //---------------//
    // StemLinksStep //
    //---------------//
    /**
     * Creates a new StemLinksStep object.
     */
    public StemLinksStep ()
    {
        super(
                Steps.STEM_LINKS,
                Level.SHEET_LEVEL,
                Mandatory.MANDATORY,
                DATA_TAB,
                "Link stems to heads & beams");
    }

    //~ Methods ------------------------------------------------------------------------------------
    //----------//
    // doSystem //
    //----------//
    @Override
    public void doSystem (SystemInfo system)
            throws StepException
    {
        system.stemsBuilder.linkStems(); // -> Stems links to heads & beams
        system.sigSolver.contextualize();
    }
}