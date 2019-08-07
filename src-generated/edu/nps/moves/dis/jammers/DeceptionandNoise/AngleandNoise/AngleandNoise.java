package edu.nps.moves.dis.jammers.DeceptionandNoise.AngleandNoise;

import edu.nps.moves.dis.*;
import edu.nps.moves.dis.enumerations.*;

/**
 * SISO-REF-010-v25, 2018-08-29
 *
 * Jamming technique uid: 284
 */
public class AngleandNoise extends JammingTechnique
{
    public AngleandNoise()
    {
        setKind((byte)3); // Deception and Noise
        setCategory((byte)5); // Angle and Noise
    }
}