/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.commons.math.analysis;

import org.apache.commons.math.MathException;

/**
 * Utility class comprised of root finding techniques.
 *
 * @version $Revision: 1.4 $ $Date: 2003/09/07 03:12:56 $
 */
public class RootFinding {
    /**
     * Default constructor. Prohibit construction.
     */
    private RootFinding() {
        super();
    }

    /**
     * For a function, f, this method returns two values, a and b that bracket
     * a root of f.  That is to say, there exists a value c between a and b
     * such that f(c) = 0.
     *
     * @param function the function
     * @param initial midpoint of the returned range.
     * @param lowerBound for numerical safety, a never is less than this value.
     * @param upperBound for numerical safety, b never is greater than this
     *                   value.
     * @return a two element array holding {a, b}.
     * @throws MathException if a root can not be bracketted.
     */
    public static double[] bracket(UnivariateRealFunction function,
                                   double initial,
                                   double lowerBound,
                                   double upperBound) throws MathException {
        return bracket( function, initial, lowerBound, upperBound,
            Integer.MAX_VALUE ) ;
    }

    /**
     * For a function, f, this method returns two values, a and b that bracket
     * a root of f.  That is to say, there exists a value c between a and b
     * such that f(c) = 0.
     *
     * @param function the function
     * @param initial midpoint of the returned range.
     * @param lowerBound for numerical safety, a never is less than this value.
     * @param upperBound for numerical safety, b never is greater than this
     *                   value.
     * @param maximumIterations to guard against infinite looping, maximum
     *                          number of iterations to perform
     * @return a two element array holding {a, b}.
     * @throws MathException if a root can not be bracketted.
     */
    public static double[] bracket(UnivariateRealFunction function,
                                   double initial,
                                   double lowerBound,
                                   double upperBound,
                                   int maximumIterations) throws MathException {
        double a = initial;
        double b = initial;
        double fa;
        double fb;
        int numIterations = 0 ;

        do {
            a = Math.max(a - 1.0, lowerBound);
            b = Math.min(b + 1.0, upperBound);
            fa = function.value(a);
            fb = function.value(b);
            numIterations += 1 ;
        } while ( (fa * fb > 0.0) && ( numIterations < maximumIterations ) );

        return new double[]{a, b};
    }
}
