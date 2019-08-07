package edu.nps.moves.dis.jammers.Deception.Repeater.RepeaterDigitalRadioFrequencyMemoryDRFM.DRFMLevelE;

import edu.nps.moves.dis.*;
import edu.nps.moves.dis.enumerations.*;

/**
 * SISO-REF-010-v25, 2018-08-29
 *
 * Jamming technique uid: 284
 */
public class DRFMLevelE extends JammingTechnique
{
    public DRFMLevelE()
    {
        setKind((byte)2); // Deception
        setCategory((byte)130); // Repeater
        setSubCategory((byte)30); // Repeater Digital Radio Frequency Memory (DRFM)
        setSpecific((byte)5); // DRFM Level E
    }
}