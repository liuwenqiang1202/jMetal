//  Sphere.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.uma.jmetal.problem.singleObjective;

import org.uma.jmetal.core.Problem;
import org.uma.jmetal.core.Solution;
import org.uma.jmetal.core.Variable;
import org.uma.jmetal.encoding.solutiontype.BinaryRealSolutionType;
import org.uma.jmetal.encoding.solutiontype.RealSolutionType;
import org.uma.jmetal.util.JMetalException;

/**
 * Class representing a Sphere problem.
 */
public class Sphere extends Problem {
  /**
   *
   */
  private static final long serialVersionUID = 6623348682217370725L;

  /**
   * Constructor
   * Creates a default instance of the Sphere problem
   *
   * @param numberOfVariables Number of variables of the problem
   * @param solutionType      The solutiontype type must "Real" or "BinaryReal".
   * @throws org.uma.jmetal.util.JMetalException
   */
  public Sphere(String solutionType, Integer numberOfVariables) throws JMetalException {
    numberOfVariables_ = numberOfVariables;
    numberOfObjectives_ = 1;
    numberOfConstraints_ = 0;
    problemName_ = "Sphere";

    upperLimit_ = new double[numberOfVariables_];
    lowerLimit_ = new double[numberOfVariables_];
    for (int var = 0; var < numberOfVariables_; var++) {
      lowerLimit_[var] = -5.12;
      upperLimit_[var] = 5.12;
    }

    if (solutionType.compareTo("BinaryReal") == 0) {
      solutionType_ = new BinaryRealSolutionType(this);
    } else if (solutionType.compareTo("Real") == 0) {
      solutionType_ = new RealSolutionType(this);
    } else {
      throw new JMetalException("Error: solutiontype type " + solutionType + " invalid");
    }
  }

  /**
   * Evaluates a solutiontype
   *
   * @param solution The solutiontype to evaluate
   * @throws org.uma.jmetal.util.JMetalException
   */
  public void evaluate(Solution solution) throws JMetalException {
    Variable[] decisionVariables = solution.getDecisionVariables();

    double sum = 0.0;
    double value;
    for (int var = 0; var < numberOfVariables_; var++) {
      value = decisionVariables[var].getValue();
      sum += value * value;
    }
    solution.setObjective(0, sum);
  }
}
