package org.batfish.representation.cisco;

import java.io.Serializable;

public abstract class RoutePolicySetStatement extends RoutePolicyStatement {

   private static final long serialVersionUID = 1L;

   public RoutePolicyStatementType getType() { return RoutePolicyStatementType.SET; }

   public abstract RoutePolicySetType getSetType();

}
